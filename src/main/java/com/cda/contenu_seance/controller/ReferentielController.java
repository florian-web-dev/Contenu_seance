package com.cda.contenu_seance.controller;

import com.cda.contenu_seance.DTO.ActiviteDTO;
import com.cda.contenu_seance.DTO.CompetenceDTO;
import com.cda.contenu_seance.DTO.ReacDTO;
import com.cda.contenu_seance.DTO.SavoirFaireDTO;
import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.Competence;
import com.cda.contenu_seance.model.Formation;
import com.cda.contenu_seance.model.Reac;
import com.cda.contenu_seance.service.Excel.ExcelReadService;
import com.cda.contenu_seance.service.FicheSuivieService;
import com.cda.contenu_seance.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/Coordinateur")
public class ReferentielController {
    @Autowired
    ReferentielService referentielService;

    @Autowired
    FicheSuivieService ficheSuivieService;

    @Autowired
    ExcelReadService excelReadService;


    @GetMapping("/Référentiel")
    public String getReferentiel(Model model) {
        List<Formation> formations = ficheSuivieService.getFormations();
        List<Activite> activites = referentielService.getActivites();
        List<Competence> competences = referentielService.getCompetences();
        List<Reac> reacs = referentielService.getReacs();

        model.addAttribute("formations", formations);
        model.addAttribute("reacs", reacs);
        model.addAttribute("activites", activites);
        model.addAttribute("competences", competences);
        return "coordinateurs/pages/formulaires/referentiel/referentiel";
    }
// ------------------------------------------------- Reac ------------------------------------------------

    private void reacViewModel(Model model) {
        List<Reac> reacs = referentielService.getReacs();
        List<Formation> formations = ficheSuivieService.getFormations();

        model.addAttribute("reacs", reacs);
        model.addAttribute("formations", formations);

    }

    @GetMapping(value = {"/Reac", "/Modifier-Reac/{id}"})
    public String getReac(@PathVariable(required = false) Long id, Model model, ReacDTO reacDTO) {
        reacViewModel(model);
        model.addAttribute("reacDTO", reacDTO);
        model.addAttribute("id", id);

        return "coordinateurs/pages/formulaires/referentiel/reac";
    }


    @PostMapping("/traitement-Reac")
    public String postAddReac(@RequestParam("file") MultipartFile file, @Validated ReacDTO reacDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws IOException {

        if (bindingResult.hasErrors()) {

            if (null == reacDTO.getId()) {
                reacViewModel(model);
                return "coordinateurs/pages/formulaires/referentiel/reac";
            } else {
                Long id = reacDTO.getId();
                reacViewModel(model);
                model.addAttribute("id", id);
                return "coordinateurs/pages/formulaires/referentiel/reac";
            }
        }
        String nomReac = reacDTO.getNom();
        redirectAttributes.addFlashAttribute("message", "Le Reac : " + nomReac + " a bien été créé/modifie. ");
       Reac reac = referentielService.saveReacDto(reacDTO);


        excelReadService.generateDtoByExel(file.getInputStream(),reac);
        return "redirect:/Coordinateur/Reac";
    }


    @GetMapping("/Reac/{id}")
    public String suppReac(@PathVariable(name = "id") long id) {
        referentielService.supReacByID(id);
        return "redirect:/Coordinateur/Reac";
    }

    // --------------------------------- Activites ----------------------------------------------------

    private void activiteViewModel(Model model) {
        List<Activite> activites = referentielService.getActivites();
        List<Reac> reacs = referentielService.getReacs();

        model.addAttribute("activites", activites);
        model.addAttribute("reacs", reacs);
    }

    @GetMapping(value = {"/Activites", "/Modifier-Activite/{id}"})
    public String getActivites(@PathVariable(required = false) Long id, ActiviteDTO activiteDTO, Model model) {
        if (null!=id){
            Activite activite = referentielService.getActivite(id);
            activiteDTO = referentielService.convertEntityActivitetoDto(activite);
        }

        activiteViewModel(model);
        model.addAttribute("id", id);
        model.addAttribute("activiteDTO", activiteDTO);
        return "coordinateurs/pages/formulaires/referentiel/activites";
    }

    @PostMapping("/traitement-activites")
    public String postaddActivites(@Validated ActiviteDTO activiteDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("activites", referentielService.getActivites());
            model.addAttribute("reacs", referentielService.getReacs());
            if (null == activiteDTO.getId()) {
                activiteViewModel(model);
                return "coordinateurs/pages/formulaires/referentiel/activites";
            } else {
                Long id = activiteDTO.getId();
                model.addAttribute("id", id);
                activiteViewModel(model);
                return "coordinateurs/pages/formulaires/referentiel/activites";
            }

        }
        String nomActivite = activiteDTO.getActivitesTypes();
        redirectAttributes.addFlashAttribute("message", "L'activité " + nomActivite + " a bien été créé/modifie.");

        referentielService.saveActivite(activiteDTO);
        return "redirect:/Coordinateur/Activites";
    }


    @GetMapping("/Suppirmer-Activites/{id}")
    public String supActivites(@PathVariable(name = "id") long id) {
        referentielService.supActivite(id);
        return "redirect:/Coordinateur/Activites";
    }

    //---------------------------------- Competences ----------------------

    private void competenceViewModel(Model model) {
        List<Competence> competences = referentielService.getCompetences();
        List<Activite> activites = referentielService.getActivites();

        model.addAttribute("competences", competences);
        model.addAttribute("activites", activites);
    }

    @GetMapping(value = {"/Competences", "/Modifier-Competence/{id}"})
    public String getCompetences(@PathVariable(required = false) Long id, CompetenceDTO competenceDTO, Model model) {
        competenceViewModel(model);
        model.addAttribute("id", id);
        model.addAttribute("competenceDTO", competenceDTO);
        return "coordinateurs/pages/formulaires/referentiel/competences";
    }

    @PostMapping("/traitement-competence")
    public String postCompetenceAdd(@Validated CompetenceDTO competenceDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            if (null == competenceDTO.getId()) {
                competenceViewModel(model);
                return "coordinateurs/pages/formulaires/referentiel/competences";
            } else {
                Long id = competenceDTO.getId();
                competenceViewModel(model);
                model.addAttribute("id", id);
                return "coordinateurs/pages/formulaires/referentiel/competences";
            }

        }
        String nomCompetence = competenceDTO.getNom();
        redirectAttributes.addFlashAttribute("message", "la Competence " + nomCompetence + " a bien été créé/modifie.");
        referentielService.saveCompetence(competenceDTO);
        return "redirect:/Coordinateur/Competences";
    }

    @GetMapping("/Competence/{id}")
    public String supCompetence(@PathVariable(name = "id") long id) {
        referentielService.supCompetence(id);
        return "redirect:/Coordinateur/Competences";
    }

    //---------------------------------- Savoir Faire | CONTENUS PEDAGOGIQUES ----------------------

    @GetMapping(value = {"/Savoir_Faire", "/Modifier-Savoir_Faire/{id}"})
    public String getSavoirFaire(@PathVariable(required = false) Long id, SavoirFaireDTO savoirFaireDTO, Model model, RedirectAttributes attributes) {

        //attributes.getFlashAttributes();

        model.addAttribute("seanceList",ficheSuivieService.getSeances());
        model.addAttribute("savoirFaires", referentielService.getSavoirFaires());
        model.addAttribute("competences",referentielService.getCompetences());
        model.addAttribute("savoirFaireDTO", savoirFaireDTO);
        model.addAttribute("id", id);

        return "coordinateurs/pages/formulaires/referentiel/savoirFaire";
    }

    @PostMapping("/traitemant-Savoir_Faire")
    public String postSavoirFaire(@Validated SavoirFaireDTO savoirFaireDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            if (null == savoirFaireDTO.getId()) {
                model.addAttribute("savoirFaires", referentielService.getSavoirFaires());
                model.addAttribute("competences",referentielService.getCompetences());
                return "coordinateurs/pages/formulaires/referentiel/savoirFaire";
            } else {
                Long id = savoirFaireDTO.getId();
                model.addAttribute("id", id);
                model.addAttribute("savoirFaires", referentielService.getSavoirFaires());
                model.addAttribute("competences",referentielService.getCompetences());
                return "coordinateurs/pages/formulaires/referentiel/savoirFaire";
            }
        }
        String nomSavoirFaire = savoirFaireDTO.getNom();
        redirectAttributes.addFlashAttribute("message", "Le savoir faire " + nomSavoirFaire + " a bien été modifier");
        referentielService.saveSavoirFaire(savoirFaireDTO);
        return "redirect:/Coordinateur/Savoir_Faire";
    }

    @GetMapping("/Savoir_Faire-sup/{id}")
    public String supSavoirFaire(@PathVariable(name = "id") long id) {
        referentielService.supSavoirFaireById(id);
        return "redirect:/Coordinateur/Savoir_Faire";
    }
}
