package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.DTO.IntervenantDTO;
import com.cda.contenu_seance.model.*;
import com.cda.contenu_seance.repositories.SeanceRepository;
import com.cda.contenu_seance.service.Excel.ExelService;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.IntervenantService;
import com.cda.contenu_seance.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

@Validated
@Controller
@RequestMapping(value = "/Coordinateur")
public class CoordinateurController {

    @Autowired
    IntervenantService intervenantService;

    @Autowired
    ExelService exelService;

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    ReferentielService referentielService;

    @Autowired
    SeanceRepository seanceRepository;


    @GetMapping("/notice")
    public String getReferentiel(Model model) {
        List<Coordinateur> coordinateurList = intervenantService.getCoordinateurs();


        model.addAttribute("coordinateurList", coordinateurList);
        return "coordinateurs/pages/notice";
    }

//  --------------------------------  Fiche de suivie ---------------------------------------------

    private void showCurrentDate(Model model) {
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatDateTime = datetime1.format(format);
        model.addAttribute("formatDateTime", formatDateTime);
    }

//    @GetMapping("/Fiche_suivie")
//    public String getAllFicheSuivi(Model model  ) {
//        showCurrentDate(model);
//        List<Seance> seances = ficheSuivieService.getSeances();
//        List<Session> sessionList = ficheSuivieService.getSessions();
//
//        model.addAttribute("seances", seances);
//        model.addAttribute("sessionList", sessionList);
//
//        return "/formateurs/pages/ficheSuivie";
//    }

    @GetMapping("/Fiche_suivie")
    public String getAllFicheSuivi(Model model) {

//        List<Seance> seances = ficheSuivieService.getSeances();
//        List<Session> sessionList = ficheSuivieService.getSessions();
//
//        model.addAttribute("seances", seances);
//        model.addAttribute("sessionList", sessionList);

        return findPaginated(1,model);
    }

    @GetMapping("/Fiche_suivie/page/{pageNum}")
    public String findPaginated(@PathVariable int pageNum, Model model) {
        showCurrentDate(model);
        Page<Seance> page = ficheSuivieService.listAll(pageNum,5);

        List<Seance> seancesPage = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sessionList", ficheSuivieService.getSessions());
        model.addAttribute("seances", seancesPage);
        return "/formateurs/pages/ficheSuivie";
    }

    @GetMapping("Fiche_suivie/Non_renseigner")
    public String getSeanceEmpty(Model model) {

//        List<Seance> seances = seanceRepository.seanceWhereAttributInferiorOneOrdeByDateDuJour();
//
//        model.addAttribute("sessionList", ficheSuivieService.getSessions());
//        model.addAttribute("seances", seances);

//        return "/formateurs/pages/ficheSuivie";
        return fingpaginedSeanceEmpty(1,model);
    }

    @GetMapping("Fiche_suivie/Non_renseigner/page/{pageNum}")
    public String fingpaginedSeanceEmpty(@PathVariable int pageNum,Model model) {

        Page<Seance> seancePage = ficheSuivieService.listWSeanceAttEmptyOrdeByDateDuJour(pageNum,5);

        List<Seance> seances = seancePage.getContent();

        String vide = "vide";

        model.addAttribute("vide",vide);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", seancePage.getTotalPages());
        model.addAttribute("totalItems", seancePage.getTotalElements());

        model.addAttribute("sessionList", ficheSuivieService.getSessions());
        model.addAttribute("seances", seances);
        return "/formateurs/pages/ficheSuivie";
    }

//    ------------------------------------------------------------------------------------------------------
//    ------------------------------------------------------------------------------------------------------


    @GetMapping("/showAll")
    public String showAll(Model model) {

        List<Formation> formations = ficheSuivieService.getFormations();
        model.addAttribute("formations", formations);

        return "coordinateurs/pages/showAll";
    }

    @GetMapping("/showExel")
    public String showAllfForExel(Model model) {

        List<Activite> activiteList = referentielService.getActivites();
        model.addAttribute("activiteList", activiteList);


        return "coordinateurs/pages/showExel";
    }


//------------------------------------ Formateur --------------------------------------------

    //TODO modifer afin que le formateur puisse modifier SES info

    @GetMapping(value = {"/Formateurs", "/ModifierFormateur/{id}"})
    public String getAddCompentence(@PathVariable(required = false) Long id, IntervenantDTO intervenantDTO, Model model, BindingResult bindingResult) {
        List<Formateur> formateurList = intervenantService.getFormateurs();
        List<Session> sessionList = ficheSuivieService.getSessions();
        List<Seance> seanceList = ficheSuivieService.getSeances();

        model.addAttribute("formateurList", formateurList);
        model.addAttribute("id", id);
        model.addAttribute("intervenantDTO", intervenantDTO);
        model.addAttribute("sessionList", sessionList);
        model.addAttribute("seanceList", seanceList);

        return "coordinateurs/pages/formulaires/formateur/formateurs";
    }

    @PostMapping("/traitement-formateur")
    public String postSaveFormateur(@Validated IntervenantDTO intervenantDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        if (bindingResult.hasErrors()) {
            String message = null;

            for (ObjectError uneError : bindingResult.getAllErrors()) {
                message = uneError.getDefaultMessage();
            }

            if (null == intervenantDTO.getId()) {
                model.addAttribute("formateurList", intervenantService.getFormateurs());
//                modelMap.addAttribute("intervenantDTO",intervenantDTO);

                model.addAttribute("messageError", message);
                return "coordinateurs/pages/formulaires/formateur/formateurs";
                //return "redirect:/Coordinateur/Formateurs";
            } else {
                Long id = intervenantDTO.getId();
                model.addAttribute("id", id);
                model.addAttribute("messageError", message);
                return "coordinateurs/pages/formulaires/formateur/formateurs";
            }

        }
        String nomFormateur = intervenantDTO.getNom();
        redirectAttrs.addFlashAttribute("message", "Le formateur " + nomFormateur + " a bien été ajouté/modifié.");
        intervenantService.saveFormateur(intervenantDTO);
        return "redirect:/Coordinateur/Formateurs";
    }

    @GetMapping("/Formateur-sup/{id}")
    public String supFormateur(@PathVariable Long id, RedirectAttributes redirectAttributes){

        Formateur formateur = intervenantService.getFormateur(id);

        redirectAttributes.addFlashAttribute("message", "Le formateur " + formateur.concatNomPrenom() + " a bien été supprimé.");
        intervenantService.supFormateur(id);

        return "redirect:/Coordinateur/Formateurs";
    }

}
