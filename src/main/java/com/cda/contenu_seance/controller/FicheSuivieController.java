package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.DTO.SeanceDTO;
import com.cda.contenu_seance.model.*;
import com.cda.contenu_seance.repositories.SeanceRepository;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.IntervenantService;
import com.cda.contenu_seance.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Validated
@Controller
@RequestMapping(value = "/Formateur")
public class FicheSuivieController {

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    IntervenantService intervenantService;

    @Autowired
    ReferentielService referentielService;
    @Autowired
    SeanceRepository seanceRepository;


    private void viewModelFicheSuiviForm(Model model) {
        List<Formateur> formateursList = intervenantService.getFormateurs();
        List<Session> sessionsList = ficheSuivieService.getSessions();
        List<Competence> competenceList = referentielService.getCompetences();
        List<Seance> seanceList = ficheSuivieService.getSeances();
        List<SavoirFaire> savoirFaireList = referentielService.getSavoirFaires();


        model.addAttribute("formateursList", formateursList);
        model.addAttribute("sessionsList", sessionsList);
        model.addAttribute("competenceList", competenceList);
        model.addAttribute("seanceList", seanceList);
        model.addAttribute("savoirFaireList", savoirFaireList);

    }

    @GetMapping(value = {"/Fiche_suivie/ajouter", "/ModifierSeance/{id}"})
    public String getFormFicheSuiviForm(@PathVariable(required = false) Long id, SeanceDTO seanceDTO, Model model) {

        viewModelFicheSuiviForm(model);
        model.addAttribute("id", id);
        model.addAttribute("seanceDTO", seanceDTO);
        return "/formateurs/pages/formulaires/ficheForm";
    }

    @PostMapping(value = {"/traitement-seance"})
    public String postSaveFicheSuivi(@Validated SeanceDTO seanceDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            if (null == seanceDTO.getId()) {
                viewModelFicheSuiviForm(model);

                return "formateurs/pages/formulaires/ficheForm";
            } else {
                Long id = seanceDTO.getId();

                viewModelFicheSuiviForm(model);
                model.addAttribute("id", id);
                model.addAttribute("seanceDTO", seanceDTO);
                return "formateurs/pages/formulaires/ficheForm";
            }
        }

        LocalDate dateSeance = seanceDTO.getDateDuJour();
        DateTimeFormatter unFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String mes = "La seance a la date " + dateSeance.format(unFormat) + " a bien été créé/modifie.";
//        modelMap.addAttribute("mes",mes);
        redirectAttributes.addFlashAttribute("message", mes);


        ficheSuivieService.saveSeance(seanceDTO);
        return "redirect:/Formateur/index";
    }



//    @PostMapping("/pre-traitement")
//    public String postUneSessionForSeance(@ModelAttribute(name = "uneSession") SessionDTO sessionDTO, SeanceDTO seanceDTO, Model model) {
//
//        Seance newSeance = new Seance();
////        sessionDTO.getSeances().stream().filter(fil->)
//
////        seanceDTO.setSession(newSeanceDTO.getSession());
//        List<SavoirFaire> savoirFaireList = referentielService.getSavoirFaires();
//
//        List<SavoirFaire> savoirFaires = savoirFaireList.stream()
//                .filter(fil -> seanceDTO.getSavoirFaires().equals(newSeance.getSavoirFaires()))
//                .collect(Collectors.toList());
//
//        seanceDTO.setSavoirFaires(savoirFaires);
//
////        model.addAttribute("savoirFaireOptional", savoirFaireOptional);
////        return "redirect:/Formateur/Fiche_suivie/ajouter";
//
//       // model.addAttribute("newSeanceDTO",newSeanceDTO);
//
//        return getFormFicheSuiviForm(null,seanceDTO,model);
//    }


//   @GetMapping("Fiche_suivie/Non_renseigner")
//   public String getSeanceEmpty(Model model){
//
////       List<Seance> seances = seanceRepository.findBySavoirFairesIsNull();
//       List<Seance> seances = seanceRepository.seanceWhereAttributInferiorOneOrdeByDateDuJour();
////
////       if (null != id){
////           Session session = ficheSuivieService.getSession(id);
////           seances = session.getSeances();
////
////       }
//
//       model.addAttribute("sessionList",ficheSuivieService.getSessions());
//       model.addAttribute("seances",seances);
//       return "/formateurs/pages/ficheSuivie";
//   }


}
