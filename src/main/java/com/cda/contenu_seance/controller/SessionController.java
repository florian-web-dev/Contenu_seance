package com.cda.contenu_seance.controller;


import com.cda.contenu_seance.DTO.SessionDTO;
import com.cda.contenu_seance.model.*;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.IntervenantService;
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

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(value = "/Coordinateur")
public class SessionController {
    @Autowired
    FicheSuivieService ficheSuivieService;
    @Autowired
    IntervenantService intervenantService;



    private void sessionViewModel(Model model) {
        List<Session> sessionList = ficheSuivieService.getSessions();
        List<Centre> centreList = ficheSuivieService.getCentres();
        List<Formation>formationList = ficheSuivieService.getFormations();
        List<Coordinateur> coordinateurList = intervenantService.getCoordinateurs();
        List<Formateur> formateurList = intervenantService.getFormateurs();
        List<Seance> seanceList = ficheSuivieService.getSeances();


        model.addAttribute("sessionList",sessionList);
        model.addAttribute("centreList",centreList);
        model.addAttribute("formations",formationList);
        model.addAttribute("coordinateurList",coordinateurList);
        model.addAttribute("formateurList",formateurList);
        model.addAttribute("seanceList",seanceList);
    }


    @GetMapping(value = {"/Sessions", "/Modifier-Session/{id}","/Sessions/formation/{idFormation}"})
    public String getSessions(@PathVariable(required = false) Long id,@PathVariable(required = false) Long idFormation,Model model, SessionDTO sessionDTO){

        sessionViewModel(model);

        if(null != idFormation){
            List<Session> sessionList = (List<Session>) model.getAttribute("sessionList");

            Formation formation = ficheSuivieService.getFormation(idFormation);

            sessionList = formation.getSessions();
            model.addAttribute("sessionList",sessionList);
        }

        model.addAttribute("id",id);
        model.addAttribute("sessionDTO",sessionDTO);
        return "coordinateurs/pages/formulaires/session/session";
    }

    @PostMapping("/traitement-session")
    public String postAddSession(@Validated SessionDTO sessionDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()) {
            if (null ==sessionDTO.getId()){
                sessionViewModel(model);
                return "coordinateurs/pages/formulaires/session/session";
            }else {
                Long id = sessionDTO.getId();
                model.addAttribute("id",id);
                sessionViewModel(model);
                return "coordinateurs/pages/formulaires/session/session";
            }

        }
//        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy ");
        String dateDebut = sessionDTO.getDateDebut().format(format);
        String dateFin = sessionDTO.getDateFin().format(format);

        redirectAttributes.addFlashAttribute("message","La session a la date " + dateDebut +" | " + dateFin + "a bien été modifié/supprimé." );

        ficheSuivieService.saveSession(sessionDTO);
        return "redirect:/Coordinateur/Sessions";
    }

    @GetMapping("/traitement-supp-session/{id}")
    public String getSupSession(@PathVariable(name = "id") long id){
        ficheSuivieService.supSessionById(id);
        return "redirect:/Coordinateur/Sessions";
    }


}
