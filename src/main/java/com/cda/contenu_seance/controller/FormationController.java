package com.cda.contenu_seance.controller;


import com.cda.contenu_seance.DTO.FormationDTO;
import com.cda.contenu_seance.model.Formation;
import com.cda.contenu_seance.service.FicheSuivieService;
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

import java.util.List;

@Controller
@RequestMapping(value = "/Coordinateur")
public class FormationController {

    @Autowired
    FicheSuivieService ficheSuivieService;

    /**
     *
     * @param id ID formation transmit dans l'URL Se PathVariable n'est pas obligatoire
     * @param model permet de transmettre des attributs au template
     * @param formationDTO permet la représentation du DTO en formulaire
     * @return le nom du template qui est utilisé pour le rendu et auquel on communique
     * le model.
     */
    @GetMapping(value = {"/formation","traitement-modif/{id}"})
    public String getFormFormation(@PathVariable(required = false) Long id, Model model,FormationDTO formationDTO){
        List<Formation> formations = ficheSuivieService.getFormations();

        model.addAttribute("formations",formations);
        model.addAttribute("formationDTO", formationDTO);
        model.addAttribute("id",id);
        return "coordinateurs/pages/formulaires/formation/formation";
    }

    /**
     * Recoit un DTO pour validation si il y a une erreur retourne la vue avec le message correspondant
     * si il n'y a pas d'erreur enregistre les informations du DTO dans la base de donnée
     * @param formationDTO Objet recut représentant les informations du formulaire
     * @param bindingResult Objet représentant le résultat des erreurs de champs que l'utilisateur a faite
     * @param redirectAttributes Objet permettant  d'ajouter un message flash
     * @param model Objet permettant de transmettre des attributs au template
     * @return  un String, une redirection vers un autre mapping qui a la méthodes GET
     */
    @PostMapping("/traitemant-formation")
    public String postAddFormation(@Validated FormationDTO formationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){

        if(bindingResult.hasErrors()){

            if (null == formationDTO.getId()){
                model.addAttribute("formations",ficheSuivieService.getFormations());
                return "coordinateurs/pages/formulaires/formation/formation";
            } else{
                Long id = formationDTO.getId();
                model.addAttribute("id",id);
                model.addAttribute("formations",ficheSuivieService.getFormations());
                return "coordinateurs/pages/formulaires/formation/formation";
            }

        }
        String nomFormation = formationDTO.getTypeFormation();
        redirectAttributes.addFlashAttribute("message","La Formation : "+ nomFormation + " a bien été créé/modifie. ");
        ficheSuivieService.saveFormation(formationDTO);
        return "redirect:formation";
    }

    /**
     * Supprime une formation par son Id
     * @param id ID formation PathVariable transmit dans l'URL
     * @return un String, une redirection vers un autre mapping
     */
    @GetMapping("formation-sup/{id}")
    public String supFormation(@PathVariable(name = "id") long id){
        ficheSuivieService.supFormationById(id);
        return "redirect:/Coordinateur/formation";
    }




}
