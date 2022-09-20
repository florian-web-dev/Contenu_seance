package com.cda.contenu_seance.service;

import com.cda.contenu_seance.DTO.CoordinateurDTO;
import com.cda.contenu_seance.DTO.IntervenantDTO;
import com.cda.contenu_seance.model.Coordinateur;
import com.cda.contenu_seance.model.Formateur;
import com.cda.contenu_seance.model.Intervenant;
import com.cda.contenu_seance.repositories.CoordinateurRepository;
import com.cda.contenu_seance.repositories.FormateurRepository;
import com.cda.contenu_seance.repositories.IntervenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervenantService {
    @Autowired
    IntervenantRepository intervenantRepository;
    FormateurRepository formateurRepository;
    CoordinateurRepository coordinateurRepository;

    @Autowired
    public IntervenantService(FormateurRepository formateurRepository, CoordinateurRepository coordinateurRepository) {
        this.formateurRepository = formateurRepository;
        this.coordinateurRepository = coordinateurRepository;
    }
    public List<Intervenant> getIntervenants(){
        return intervenantRepository.findAll();
    }


    //-------------------------------- Formateur  ----------------------


    public List<Formateur> getFormateurs(){
        return formateurRepository.findAll();
    }
    public Formateur getFormateur(Long id){
        return formateurRepository.findById(id).orElse(new Formateur());
    }

    public Formateur getFormateurByEmail(String email){
        return formateurRepository.findFormateurByEmail(email);
    }

    public void saveFormateur(IntervenantDTO intervenantDTO){
        Formateur formateurDB;
        if (null == intervenantDTO.getId()){
            formateurDB = new Formateur();
        }else {
            formateurDB =formateurRepository.findById(intervenantDTO.getId()).orElse(new Formateur());

        }
        formateurDB.setNom(intervenantDTO.getNom().trim().toUpperCase());
        formateurDB.setPrenom(intervenantDTO.getPrenom().trim());
        formateurDB.setEmail(intervenantDTO.getEmail().trim());
        formateurDB.setAdresse(intervenantDTO.getAdresse().trim());
        formateurDB.setVille(intervenantDTO.getVille().trim());
        formateurDB.setCodePostal(intervenantDTO.getCodePostal().trim());
        formateurDB.setTel(intervenantDTO.getTel().trim());
        formateurDB.setMp("{bcrypt}"+new BCryptPasswordEncoder().encode(intervenantDTO.getMp().trim()));
        formateurDB.setSeances(intervenantDTO.getSeances());
        formateurDB.setSessions(intervenantDTO.getSessions());

        formateurRepository.save(formateurDB);
    }
    public void supFormateur(Long id){
        formateurRepository.deleteById(id);
    }

    public IntervenantDTO convertFormateurToIntervenantDTO(Formateur formateur){
        IntervenantDTO intervenantDTO = new IntervenantDTO();
        intervenantDTO.setId(formateur.getId());
        intervenantDTO.setNom(formateur.getNom());
        intervenantDTO.setPrenom(formateur.getPrenom());
        intervenantDTO.setEmail(formateur.getEmail());
        intervenantDTO.setAdresse(formateur.getAdresse());
        intervenantDTO.setVille(formateur.getVille());
        intervenantDTO.setCodePostal(formateur.getCodePostal());
        intervenantDTO.setTel(formateur.getTel());
        intervenantDTO.setMp(formateur.getMp());

        return intervenantDTO;
    }


//    public void updateFormateur(IntervenantDTO formateurDTO){
//        Formateur formateur = formateurRepository.findById(formateurDTO.getId()).orElse(new Formateur());
//        formateur.setNom(formateurDTO.getNom().trim().toUpperCase());
//        formateur.setPrenom(formateurDTO.getPrenom().trim());
//        formateur.setEmail(formateurDTO.getEmail().trim());
//        formateur.setAdresse(formateurDTO.getAdresse().trim());
//        formateur.setVille(formateurDTO.getVille().trim());
//        formateur.setCodePostal(formateurDTO.getCodePostal().trim());
//        formateur.setTel(formateurDTO.getTel().trim());
//        formateur.setMp(formateurDTO.getMp().trim());
//
//        formateurRepository.save(formateur);
//    }

    //-------------------------------- Coordinateur  ----------------------
    public List<Coordinateur> getCoordinateurs(){

        return coordinateurRepository.findAll();
    }
    public Coordinateur getCoordinateur(Long id){
        return coordinateurRepository.findById(id).orElse(new Coordinateur());
    }

    public void saveCoordinateur(CoordinateurDTO coordinateurDTO){
        Coordinateur coordinateurDB;
        if (null == coordinateurDTO.getId()){
            coordinateurDB = new Coordinateur();
        }else {
            coordinateurDB = coordinateurRepository.findById(coordinateurDTO.getId()).orElse(new Coordinateur());

        }
        coordinateurDB.setNom(coordinateurDTO.getNom().trim().toUpperCase());
        coordinateurDB.setPrenom(coordinateurDTO.getPrenom().trim());
        coordinateurDB.setEmail(coordinateurDTO.getEmail().trim());
        coordinateurDB.setAdresse(coordinateurDTO.getAdresse().trim());
        coordinateurDB.setVille(coordinateurDTO.getVille().trim());
        coordinateurDB.setCodePostal(coordinateurDTO.getCodePostal().trim());
        coordinateurDB.setTel(coordinateurDTO.getTel().trim());
        coordinateurDB.setMp("{bcrypt}"+new BCryptPasswordEncoder().encode(coordinateurDTO.getMp().trim()));

        coordinateurDB.setCentres(coordinateurDTO.getCentres());
        coordinateurDB.setSessions(coordinateurDTO.getSessions());

        coordinateurRepository.save(coordinateurDB);
    }

//    public void updateFormateur(CoordinateurDTO coordinateurDTO){
//        Coordinateur coordinateur = coordinateurRepository.findById(coordinateurDTO.getId()).orElse(new Coordinateur());
//        coordinateur.setNom(coordinateurDTO.getNom().trim().toUpperCase());
//        coordinateur.setPrenom(coordinateurDTO.getPrenom().trim());
//        coordinateur.setEmail(coordinateurDTO.getEmail().trim());
//        coordinateur.setAdresse(coordinateurDTO.getAdresse().trim());
//        coordinateur.setVille(coordinateurDTO.getVille().trim());
//        coordinateur.setCodePostal(coordinateurDTO.getCodePostal().trim());
//        coordinateur.setTel(coordinateurDTO.getTel().trim());
//        coordinateur.setMp(coordinateurDTO.getMp().trim());
//
//        coordinateurRepository.save(coordinateur);
//    }

}
