package com.cda.contenu_seance.service;

import com.cda.contenu_seance.DTO.CentreDTO;
import com.cda.contenu_seance.DTO.FormationDTO;
import com.cda.contenu_seance.DTO.SeanceDTO;
import com.cda.contenu_seance.DTO.SessionDTO;
import com.cda.contenu_seance.model.Centre;
import com.cda.contenu_seance.model.Formation;
import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.model.Session;
import com.cda.contenu_seance.repositories.CentreRepository;
import com.cda.contenu_seance.repositories.FormationRepository;
import com.cda.contenu_seance.repositories.SeanceRepository;
import com.cda.contenu_seance.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FicheSuivieService {

    CentreRepository centreRepository;
    FormationRepository formationRepository;
    SessionRepository sessionRepository;
    SeanceRepository seanceRepository;


    @Autowired
    public FicheSuivieService(CentreRepository centreRepository, FormationRepository formationRepository, SessionRepository sessionRepository, SeanceRepository seanceRepository) {
        this.centreRepository = centreRepository;
        this.formationRepository = formationRepository;
        this.sessionRepository = sessionRepository;
        this.seanceRepository = seanceRepository;

    }

    //------------------------ Centre -----------------------
    public List<Centre> getCentres() {
        return centreRepository.findAll();
    }

    public Centre getCentre(Long id) {
        return centreRepository.findById(id).orElse(new Centre());
    }

    public void saveEntityCentreExcel(Centre centre){
        centreRepository.save(centre);
    }

    public void saveCentre(CentreDTO centreDTO) {
        Centre centreBD;
        if (null == centreDTO.getId()) {
            centreBD = new Centre();
        } else {
            centreBD = centreRepository.findById(centreDTO.getId()).orElse(new Centre());
        }
        centreBD.setNom(centreDTO.getNom().trim());
        centreBD.setAdresse(centreDTO.getAdresse().trim());
        centreBD.setVille(centreDTO.getVille().trim());
        centreBD.setCodesPostal(centreDTO.getCodesPostal().trim());
        centreBD.setCoordinateurs(centreDTO.getCoordinateur());
        centreBD.setSessions(centreDTO.getSessions());

        centreRepository.save(centreBD);

    }

//    public void updateCentre(CentreDTO centreDTO) {
//
//        Centre centreBD = centreRepository.findById(centreDTO.getId()).orElse(new Centre());
//        centreBD.setNom(centreDTO.getNom().trim());
//        centreBD.setAdresse(centreDTO.getAdresse().trim());
//        centreBD.setVille(centreDTO.getVille().trim());
//        centreBD.setCodesPostal(centreDTO.getCodesPostal().trim());
//        centreBD.setCoordinateurs(centreDTO.getCoordinateur());
//
//        centreRepository.save(centreBD);
//
//    }

    public void supCentre(Centre centre) {

        centreRepository.delete(centre);
    }

    public void supCentrebyid(Long id) {
        //Centre centre = centreRepository.findById(id).orElse(null);
        centreRepository.deleteById(id);
    }
    //------------------------ Formation -----------------------

    public List<Formation> getFormations() {
        return formationRepository.findAll();
    }

    public Formation getFormation(Long id) {
        return formationRepository.findById(id).orElse(new Formation());
    }

    public Formation saveEntityFormationExel (Formation formation){

        return formationRepository.save(formation);
    }
    public void saveFormation(FormationDTO formationDTO) {
        Formation formationDB;
        if (null == formationDTO.getId()) {
            formationDB = new Formation();

        } else {
            formationDB = formationRepository.findById(formationDTO.getId()).orElse(new Formation());
        }
        formationDB.setTypeFormation(formationDTO.getTypeFormation().trim());
        formationDB.setSessions(formationDTO.getSessions());
        formationDB.setReac(formationDTO.getReac());
        formationRepository.save(formationDB);
    }

    public void updateFormation(FormationDTO formationDTO) {
        Formation formationDB = formationRepository.findById(formationDTO.getId()).orElse(new Formation());
        formationDB.setTypeFormation(formationDTO.getTypeFormation().trim());
        formationRepository.save(formationDB);

    }

    public void supFormationById(Long id) {
        formationRepository.deleteById(id);
    }

    //------------------------ Session -----------------------
    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    public Session getSession(Long id) {
        return sessionRepository.findById(id).orElse(new Session());
    }

    public void supSessionById(Long id) {
        sessionRepository.deleteById(id);
    }

    public void saveSessionEntityExcel(Session session){

        sessionRepository.save(session);
    }

    public void saveSession(SessionDTO sessionDTO) {
        Session sessionBD;
        if (null == sessionDTO.getId()) {
            sessionBD = new Session();
        } else {
            sessionBD = sessionRepository.findById(sessionDTO.getId()).orElse(new Session());

        }
        sessionBD.setDateDebut(sessionDTO.getDateDebut());
        sessionBD.setDateFin(sessionDTO.getDateFin());
        sessionBD.setDurerTotal(sessionDTO.getDurerTotal());
        sessionBD.setCentre(sessionDTO.getCentre());
        sessionBD.setFormation(sessionDTO.getFormation());
        sessionBD.setCoordinateur(sessionDTO.getCoordinateur());
        sessionBD.setFormateurs(sessionDTO.getFormateurs());
        sessionBD.setSeances(sessionDTO.getSeances());

        sessionRepository.save(sessionBD);
    }


    public void updateSession(SessionDTO sessionDTO) {
        Session sessionBD = sessionRepository.findById(sessionDTO.getId()).orElse(new Session());
        sessionBD.setDateDebut(sessionDTO.getDateDebut());
        sessionBD.setDateFin(sessionDTO.getDateFin());
        sessionBD.setCentre(sessionDTO.getCentre());
        sessionBD.setFormation(sessionDTO.getFormation());
        sessionBD.setCoordinateur(sessionDTO.getCoordinateur());
        sessionRepository.save(sessionBD);
    }

    //------------------------ Seance -----------------------

    /**
     *  Récupère toutes les seances
     * @return une liste de Seance
     */
    public List<Seance> getSeances() {
        return seanceRepository.findAll();
    }

    /**
     * Récupère une Seance
     * @param id ID de la Seance
     * @return un Objet Seance
     */
    public Seance getSeance(Long id) {
        return seanceRepository.findById(id).orElse(new Seance());
    }

    public List<Seance> getSeanceByEmptyAttOrderByDateDuJour() {
        return seanceRepository.seanceWhereAttributInferiorOneOrdeByDateDuJour();
    }

    /**
     *
     * @return une liste de Seance
     */
    public List<Seance> getSeanceNonEmpty(){
        return seanceRepository.findSeanceByFormateurIsNotNullAndSessionIsNotNullAndAtrNoEmpty();
    }

    public List <Seance> getSeanceWhFormateurAndSessionIsNotNull(){
        return seanceRepository.findSeancesByFormateurIsNotNullAndSessionIsNotNull();
    }

    public List<Seance> getSeanceByEmptyAttAndFormateurNotNullOrderByDateDuJour() {
        return seanceRepository.seanceWhereAttributInferiorOneAndFormateurIsNotNullOrdeByDateDuJour();
    }

    /**
     *Récupère une liste de Seance par ID de la session si les attributs de la classe Seance son vide.
     * @return une liste de seance
     */
    public List<Seance> getSeanceByIdSessionAttIsEmpty(Long id){
        return seanceRepository.findBySession_IdWhFormateurIsNotNullOrderByDateDuJourAsc(id);
    }

    /**
     * Supprime une Seance par son ID
     * @param id ID de Seance
     */
    public void supSeance(Long id){
        seanceRepository.deleteById(id);
    }

    /**
     * Crée et mets a jour les données reçut du DTO dans la base donnes.
     * @param seanceDTO les données reçut par dans le post
     */
    public void saveSeance(SeanceDTO seanceDTO) {
        Seance seanceBD;
        if (null == seanceDTO.getId()) {
            seanceBD = new Seance();
        } else {
            seanceBD = seanceRepository.findById(seanceDTO.getId()).orElse(new Seance());
        }
        seanceBD.setDateDuJour(seanceDTO.getDateDuJour());
        seanceBD.setDurer(seanceDTO.getDurer());
        seanceBD.setSupportUse(seanceDTO.getSupportUse().trim());
        seanceBD.setDeroulement(seanceDTO.getDeroulement().trim());
        seanceBD.setObjectifPeda(seanceDTO.getObjectifPeda().trim());
        seanceBD.setMethodeEnvisagee(seanceDTO.getMethodeEnvisagee().trim());
        seanceBD.setFormateur(seanceDTO.getFormateur());
        seanceBD.setSession(seanceDTO.getSession());
        seanceBD.setEvaluation(seanceDTO.getEvaluation());
        seanceBD.setSavoirFaires(seanceDTO.getSavoirFaires());

        seanceRepository.save(seanceBD);
    }

//   ---------------------------------------------- Pagination ----------------------------------------------------------
    public Page<Seance> listWSeanceAttEmptyOrdeByDateDuJour(int pageNum,int pageSize) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return seanceRepository.seanceWhereAttributInferiorOneOrdeByDateDuJourPage(pageable);
    }

    public Page<Seance> listSeancesBySessionId(int pageNum,int pageSize, Long id) {

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return seanceRepository.findSeancesBySessionId(id,pageable);

    }

    public Page<Seance> listAll(int pageNum,int pageSize) {
//        int pageSize = 6;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);


        return seanceRepository.findAll(pageable);
    }

    public Page<Seance> findPaginatedSeance(int pageNum, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.seanceRepository.findAll(pageable);
    }


//    public Page<Formation> listAll(int pageNum) {
//        int pageSize = 5;
//
//        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
//
//
//        return formationRepository.findAll(pageable);
//    }

//    public Page<Formation> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
//                Sort.by(sortField).descending();
//
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
//        return this.formationRepository.findAll(pageable);
//    }


}

