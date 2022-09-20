package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.DTO.CentreDTO;
import com.cda.contenu_seance.model.Centre;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.IntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/Coordinateur")
public class CentreController {

    @Autowired
    FicheSuivieService ficheSuivieService;
    @Autowired
    IntervenantService intervenantService;


    @GetMapping(value = {"/Centres","/ModifierCentre/{id}"})
    public String centreResult(@PathVariable(required = false) Long id, Model model, CentreDTO centreDTO) {
        List<Centre> centres = ficheSuivieService.getCentres();

        model.addAttribute("coordinateurList",intervenantService.getCoordinateurs());
        model.addAttribute("sessionList",ficheSuivieService.getSessions());

        model.addAttribute("lesCentres", centres);
        model.addAttribute("centreDTO", centreDTO);
        model.addAttribute("id",id);

        return "coordinateurs/pages/formulaires/Centre/centre";
    }
    @GetMapping("/Centre/{id}")
    public String supCentre(@PathVariable(name = "id") long id) {
        ficheSuivieService.supCentrebyid(id);
        return "redirect:/Coordinateur/Centres";
    }

    @PostMapping("/traitement")
    public ModelAndView centreTraitement(@Validated CentreDTO centreDTO, BindingResult bindingResult, ModelMap modelMap, RedirectAttributes redirectAttrs) {

        if (bindingResult.hasErrors()) {

            if (null == centreDTO.getId()) {
                modelMap.addAttribute("lesCentres", ficheSuivieService.getCentres());
                return new ModelAndView("coordinateurs/pages/formulaires/Centre/centre", modelMap);
            } else {
                Long id = centreDTO.getId();
                modelMap.addAttribute("id", id);
                return new ModelAndView("coordinateurs/pages/formulaires/Centre/centre", modelMap);
            }
        }
        String nomCentre = centreDTO.getNom();
        redirectAttrs.addFlashAttribute("message","Le centre : "+ nomCentre + " a bien été créé/modifie. ");

        ficheSuivieService.saveCentre(centreDTO);
        return new ModelAndView("redirect:Centres");
    }








}
