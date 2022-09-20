package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.model.Session;
import com.cda.contenu_seance.repositories.SeanceRepository;
import com.cda.contenu_seance.service.FicheSuivieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Validated
@Controller
public class SeanceController {

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    SeanceRepository seanceRepository;

    private void showCurrentDate(Model model) {
        LocalDateTime datetime1 = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formatDateTime = datetime1.format(format);
        model.addAttribute("formatDateTime", formatDateTime);
    }


    @GetMapping(value = {"/Coordinateur/Fiche_suivie/session/{id}"})
    public String getFicheSuivie(@PathVariable(required = false)Long id, Model model){

        return getFicheSuiviePage(id,1, model);
    }

    @GetMapping(value = {"/Coordinateur/Fiche_suivie/session/{id}/page/{pageNum}"})
    public String getFicheSuiviePage(@PathVariable(required = false)Long id,@PathVariable int pageNum, Model model){


//        List<Seance> seances = ficheSuivieService.getSeances();
        List<Session> sessionList = ficheSuivieService.getSessions();

//        if (null != id){
//            Session session = ficheSuivieService.getSession(id);
//            seances = session.getSeances();
//        }
        Page<Seance> seancePage = ficheSuivieService.listSeancesBySessionId(pageNum,5,id);
        List<Seance> seances = seancePage.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", seancePage.getTotalPages());
        model.addAttribute("totalItems", seancePage.getTotalElements());

//        String session = "session";
//        model.addAttribute("session",session);
        model.addAttribute("idSession",id);
        showCurrentDate(model);

        model.addAttribute("seances",seances);
        model.addAttribute("sessionList",sessionList);


        return "/formateurs/pages/ficheSuivie";
    }


    @GetMapping("/sup-Seance/{id}")
    public String suppSeance(@PathVariable Long id, RedirectAttributes redirectAttributes){

        Seance seance = ficheSuivieService.getSeance(id);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateDuJour = seance.getDateDuJour().format(format);
        redirectAttributes.addFlashAttribute("message","la Seance "+dateDuJour+" a bien été supprimé" );

        ficheSuivieService.supSeance(id);
        return "redirect:/Coordinateur/Fiche_suivie";
    }



}
