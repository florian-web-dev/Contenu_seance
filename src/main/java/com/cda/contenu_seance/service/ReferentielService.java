package com.cda.contenu_seance.service;

import com.cda.contenu_seance.DTO.ActiviteDTO;
import com.cda.contenu_seance.DTO.CompetenceDTO;
import com.cda.contenu_seance.DTO.ReacDTO;
import com.cda.contenu_seance.DTO.SavoirFaireDTO;
import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.Competence;
import com.cda.contenu_seance.model.Reac;
import com.cda.contenu_seance.model.SavoirFaire;
import com.cda.contenu_seance.repositories.ActiviteRepository;
import com.cda.contenu_seance.repositories.CompetenceRepository;
import com.cda.contenu_seance.repositories.ReacRepository;
import com.cda.contenu_seance.repositories.SavoirFaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferentielService {
    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    CompetenceRepository competenceRepository;
    @Autowired
    ReacRepository reacRepository;
    @Autowired
    SavoirFaireRepository savoirFaireRepository;


    //--------------------------- Activite ---------------------------------
    public List<Activite> getActivites(){
        return activiteRepository.findAll();//orElse new Activite
    }

    public Activite getActivite (Long id){
        return activiteRepository.findById(id).orElse(null);
    }

    /**
     * Update and creat with DTO param
     * if id activity doesn't exist so creat an activity object
     * @param activiteDTO is a DTO object
     */
    public void updateActivite(ActiviteDTO activiteDTO){
        Activite activite = activiteRepository.findById(activiteDTO.getId()).orElse(new Activite());
        activite.setActivitesTypes(activiteDTO.getActivitesTypes().trim());
        activite.setNumOdre(activiteDTO.getNumOdre());
        activite.setReac(activiteDTO.getReac());
        activiteRepository.save(activite);
    }
    public void saveActivite(ActiviteDTO activiteDTO){
        Activite activiteBD;

        if (null == activiteDTO.getId()){
            activiteBD = new Activite();
        }else {
            activiteBD = activiteRepository.findById(activiteDTO.getId()).orElse(new Activite());
        }
        activiteBD.setActivitesTypes(activiteDTO.getActivitesTypes().trim());
        activiteBD.setNumOdre(activiteDTO.getNumOdre());
        activiteBD.setReac(activiteDTO.getReac());
        activiteBD.setCompetences(activiteDTO.getCompetences());

        activiteRepository.save(activiteBD);
    }

    public void supActivite(Long id){
        activiteRepository.deleteById(id);
    }

    public void updateActiveEnty(Activite activite){
        activiteRepository.findById(activite.getId());
        activite.setActivitesTypes(activite.getActivitesTypes().trim());
        activite.setNumOdre(activite.getNumOdre());
        activiteRepository.save(activite);
    }
    public Activite creatActiviteEntityExcel(Activite activite){
        return activiteRepository.save(activite);
    }

    public Activite createActiveEnty(Activite activite){
        Activite newActivite;
        if (null==activite.getId()){
            newActivite = new Activite();
        }else {
            newActivite = activiteRepository.findById(activite.getId()).orElse(new Activite());
        }
        newActivite.setActivitesTypes(activite.getActivitesTypes().trim());
        newActivite.setNumOdre(activite.getNumOdre());
        return activiteRepository.save(newActivite);
    }
    public ActiviteDTO convertEntityActivitetoDto(Activite activite){
        ActiviteDTO activiteDTO = new ActiviteDTO();
        activiteDTO.setActivitesTypes(activite.getActivitesTypes());
        activiteDTO.setNumOdre(activite.getNumOdre());
        return activiteDTO;
    }
//------------------------------------- Competence ------------------------------------------

    public List <Competence> getCompetences (){
        return competenceRepository.findAll();
    }

    public Competence getCompetence(Long id){
        return competenceRepository.findById(id).orElse(null);
    }


    public void saveCompetence(CompetenceDTO competenceDTO){
        Competence competenceBD;

        if (null==competenceDTO.getId()){
            competenceBD = new Competence();
        }else {
            competenceBD = competenceRepository.findById(competenceDTO.getId()).orElse(new Competence());
        }
        competenceBD.setNumOdre(competenceDTO.getNumOdre());
        competenceBD.setNom(competenceDTO.getNom().trim());
        competenceBD.setActivite(competenceDTO.getActivite());
        competenceBD.setSavoirFaires(competenceDTO.getSavoirFaires());

        competenceRepository.save(competenceBD);
    }

//    public Competence createCompetenceEntity(Competence competence){
//        Competence newCompetence;
//        if(null==competence.getId()){
//            newCompetence=new Competence();
//        }
//        else {
//            newCompetence = competenceRepository.findById(competence.getId()).orElse(new Competence());
//        }
//        newCompetence.setNumOdre(competence.getNumOdre());
//        newCompetence.setNom(competence.getNom().trim());
//        newCompetence.setActivite(competence.getActivite());
//
//        return competenceRepository.save(newCompetence);
//
//    }
    public Competence createCompetenceEntity(Competence competence){

       return competenceRepository.save(competence);
    }

    public void updateCompetence(CompetenceDTO competenceDTO){
        Competence competenceBD = competenceRepository.findById(competenceDTO.getId()).orElse(new Competence());
        //competenceBD.setId(competenceDTO.getId());
        competenceBD.setNumOdre(competenceDTO.getNumOdre());
        competenceBD.setNom(competenceDTO.getNom().trim());
        competenceBD.setActivite(competenceDTO.getActivite());

        competenceRepository.save(competenceBD);
    }


    public void updateCompetenceEntity(Competence competence){
        competenceRepository.findById(competence.getId());
        competence.setNumOdre(competence.getNumOdre());
        competence.setNom(competence.getNom().trim());

        competenceRepository.save(competence);
    }
    public void supCompetence(Long id){

        competenceRepository.deleteById(id);
    }

    //------------------------------------ Reac --------------------------------------------

    public Reac getReac(Long id){
        return reacRepository.findById(id).orElse(null);
    }

    public List <Reac> getReacs(){
        return reacRepository.findAll();
    }

    public void supReacByID(Long id){
        reacRepository.deleteById(id);
    }

    public Reac saveReacDto(ReacDTO reacDTO){
        Reac reacDB;
        if (null == reacDTO.getId()){
            reacDB = new Reac();
        }else {
            reacDB = reacRepository.findById(reacDTO.getId()).orElse(new Reac());
        }
        reacDB.setNom(reacDTO.getNom().trim());
        reacDB.setLien(reacDTO.getLien().trim());
        reacDB.setDateDebut(reacDTO.getDateDebut());
        reacDB.setDurer(reacDTO.getDurer());
        reacDB.setFormation(reacDTO.getFormation());
        reacDB.setActivites(reacDTO.getActivites());

        return reacRepository.save(reacDB);
    }


    public void updateReacDto(ReacDTO reacDTO){
        Reac reacDB = reacRepository.findById(reacDTO.getId()).orElse(new Reac());
        reacDB.setLien(reacDTO.getLien().trim());

        reacDB.setDateDebut(reacDTO.getDateDebut());

        reacDB.setDurer(reacDTO.getDurer());
        reacDB.setFormation(reacDTO.getFormation());
        reacRepository.save(reacDB);
    }

    public void supReac(Reac reac){
        reacRepository.delete(reac);
    }

    public Reac saveReacEntityExcel(Reac reac){
        return reacRepository.save(reac);
    }
    public Reac saveReacEntity(Reac reac){
        Reac newReac;
        if (null == reac) {
            newReac=new Reac();
        }else {
            newReac = reacRepository.findById(reac.getId()).orElse(new Reac());

        }
        //assert reac != null; cree une exeption non recuperable crach appli NullPointerException
        newReac.setDateDebut(reac.getDateDebut());
        newReac.setDurer(reac.getDurer());
        newReac.setLien(reac.getLien());
        newReac.setFormation(reac.getFormation());
        return reacRepository.save(newReac);
    }

// ---------------------------------------- Savoir Faire -------------------

    public List <SavoirFaire> getSavoirFaires(){
        return savoirFaireRepository.findAll();
    }
    public SavoirFaire getSavoirFaire(Long id){
        return savoirFaireRepository.findById(id).orElse(new SavoirFaire());
    }

    public void saveSavoirFaireEntity(SavoirFaire savoirFaire){

        savoirFaireRepository.save(savoirFaire);
    }

    public void saveSavoirFaire (SavoirFaireDTO savoirFaireDTO){
        SavoirFaire savoirFaireBD;
        if (null == savoirFaireDTO.getId()){
            savoirFaireBD = new SavoirFaire();
        }else {
            savoirFaireBD = getSavoirFaire(savoirFaireDTO.getId());
        }
        savoirFaireBD.setNom(savoirFaireDTO.getNom());
        savoirFaireBD.setCompetence(savoirFaireDTO.getCompetence());
        savoirFaireBD.setSeances(savoirFaireDTO.getSeances());
        savoirFaireRepository.save(savoirFaireBD);
    }

    public void supSavoirFaireById(Long id){
        savoirFaireRepository.deleteById(id);
    }


}
