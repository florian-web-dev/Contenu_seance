package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.DTO.IntervenantDTO;
import com.cda.contenu_seance.model.Formateur;
import com.cda.contenu_seance.model.Intervenant;
import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.repositories.IntervenantRepository;
import com.cda.contenu_seance.repositories.SeanceRepository;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.IntervenantService;
import com.cda.contenu_seance.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

//TODO
//@SessionAttributes({"myBean1", "myBean3"}) + HttpSession session

@Validated
@Controller
@RequestMapping(value = "/Formateur")
public class FormateurController {

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    IntervenantService intervenantService;

    @Autowired
    ReferentielService referentielService;

    @Autowired
    IntervenantRepository intervenantRepository;

    @Autowired
    SeanceRepository seanceRepository;

    private void showCurrentDate(Model model) {
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDateTime = datetime1.format(format);
        model.addAttribute("formatDateTime", formatDateTime);
    }

    @GetMapping(value = {"/{id}/Fiche_suivie", "/Seances/{email}"})
    public String getFicheSuivieFormateur(@AuthenticationPrincipal UserDetails principal,
                                          @PathVariable(required = false) Long id,
                                          @PathVariable(required = false) String email,
                                          Model model, RedirectAttributes redirectAttributes) {

        String emailUsername = principal.getUsername();

        Intervenant intervenant = intervenantRepository.findByEmail(emailUsername);

        List<Seance> seances = ficheSuivieService.getSeances();

        if (null != id) {
            if (!intervenant.getId().equals(id)) {

                redirectAttributes.addFlashAttribute("accesMessage", "action non autoriser");

                return "redirect:/login";
            }
            Formateur formateur = intervenantService.getFormateur(id);
            seances = formateur.getSeances();
        }
        if (!"".equals(email) && null != email) {
            if (!emailUsername.equals(email)) {
                redirectAttributes.addFlashAttribute("accesMessage", "action non autoriser");

                return "redirect:/login";
            }
            seances = seanceRepository.findSeancesByFormateurEmail(email);

        }

        model.addAttribute("seances", seances);
        model.addAttribute("sessionList", ficheSuivieService.getSessions());


        showCurrentDate(model);
        return "/formateurs/pages/ficheSuivie";
    }

    @GetMapping(value = {"/{id}/Fiche_suivie/{vide}", "/Fiche_suivie/{email}/{vide}"})
    public String getSeanceEmpty(@AuthenticationPrincipal UserDetails principal,
                                 @PathVariable(required = false) Long id,
                                 @PathVariable(required = false) String email,
                                 @PathVariable(required = false) String vide, Model model,
                                 RedirectAttributes redirectAttributes) {

        String emailUserName = principal.getUsername();
        Intervenant intervenant = intervenantRepository.findByEmail(emailUserName);

        if (null != id) {
            if (!intervenant.getId().equals(id)) {

                redirectAttributes.addFlashAttribute("accesMessage", "action non autoriser");

//                SecurityContextHolder.getContext().setAuthentication(null);

                return "redirect:/login";
            }

        }
        if (null != email) {
            if (!emailUserName.equals(email)) {
                redirectAttributes.addFlashAttribute("accesMessage", "action non autoriser");

                return "redirect:/login";
            }
        }

        List<Seance> seances;

        model.addAttribute("sessionList", ficheSuivieService.getSessions());

        if (vide.equals("vide")) {

            seances = ficheSuivieService.getSeanceByEmptyAttAndFormateurNotNullOrderByDateDuJour();

                id = intervenant.getId();
                Formateur formateur = intervenantService.getFormateur(id);

                Long finalId = id;
                seances = seances.stream()
                    .filter(seance -> seance.getFormateur().equals(intervenantService.getFormateur(finalId)))
                    .collect(Collectors.toList());

            model.addAttribute("seances", seances);


        }
        return "/formateurs/pages/ficheSuivie";

    }

// -----------------------------------------   Modifier formateur ------------------------------------

    @GetMapping("/{email}/modifier")
    public String getModifierFormateur(@AuthenticationPrincipal UserDetails principal, @PathVariable String email, Model model, RedirectAttributes redirectAttributes){
        String emailUserName = principal.getUsername();
        if (null != email) {
            if (!emailUserName.equals(email)) {
                redirectAttributes.addFlashAttribute("accesMessage", "action non autoriser");

                return "redirect:/login";
            }
        }

        Formateur formateur = intervenantService.getFormateurByEmail(email);

        IntervenantDTO intervenantDTO = intervenantService.convertFormateurToIntervenantDTO(formateur);

        model.addAttribute("intervenantDTO", intervenantDTO);
        return "/formateurs/pages/formulaires/formateursModif";
    }

    @PostMapping("/traitement")
    public String postModifFormateur(@Validated IntervenantDTO intervenantDTO, BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            String message = null;

            for (ObjectError uneError : bindingResult.getAllErrors()) {
                message = uneError.getDefaultMessage();
            }

            model.addAttribute("intervenantDTO",intervenantDTO);
            model.addAttribute("messageError",message);
            return "/formateurs/pages/formulaires/formateursModif";
        }

        String nomFormateur = intervenantDTO.getNom();
        redirectAttributes.addFlashAttribute("message", nomFormateur + " a bien été modifié.");
        intervenantService.saveFormateur(intervenantDTO);

        return "redirect:/Formateur/index";
    }

}
