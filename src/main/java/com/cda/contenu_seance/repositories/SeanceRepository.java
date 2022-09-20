package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Formateur;
import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.model.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeanceRepository extends JpaRepository <Seance, Long>{

    @Query("select s from Seance s " +
            "where s.session.id = ?1 and s.formateur is not null and length(s.supportUse)>0  and length(s.deroulement)>0 and length(s.objectifPeda)>0 and length(s.methodeEnvisagee)>0 " +
            "order by s.dateDuJour")
    List<Seance> findBySession_IdWhFormateurIsNotNullOrderByDateDuJourAsc(Long id);

    @Query(value = "SELECT * FROM SEANCE where LENGTH(METHODE_ENVISAGEE)>0 and LENGTH(DEROULEMENT)>0 and LENGTH(OBJECTIF_PEDA)>0 and LENGTH(SUPPORT_USE)>0 and FORMATEUR_ID is not null and SESSION_ID is not null order by DATE_DU_JOUR",nativeQuery = true)
    List <Seance> findSeanceByFormateurIsNotNullAndSessionIsNotNullAndAtrNoEmpty();

    List<Seance> findSeancesByFormateurEmail(String formateur_email);

    Page<Seance> findSeancesBySessionId(Long session_id, Pageable pageable);


    List<Seance> findSeancesByFormateurAndSession(Formateur formateur, Session session);


    //List<Seance> findBySupportUseIsEmptyAndDeroulementIsEmptyAndObjectifPedaIsEmptyAndMethodeEnvisageeIsEmptyAndFormateurIsNotNullOrderByDateDuJourAsc();


    List<Seance> findSeancesByFormateurIsNotNullAndSessionIsNotNull();

//    @Query("select s from Seance s where s.session.id = ?1")
//    List<Seance> findBySession_Id(Long id);


//    @Query(value = "SELECT * FROM SEANCE where LENGTH(METHODE_ENVISAGEE)>0 and LENGTH(DEROULEMENT)>0 and LENGTH(OBJECTIF_PEDA)>0 and LENGTH(SUPPORT_USE)>0 and FORMATEUR_ID is not null and SESSION_ID = ?1 order by DATE_DU_JOUR",nativeQuery = true)
//    List <Seance> findSeanceByFormateurIsNotNullAndSessionIdAndAtrNoEmpty(Long id);

//    List <Seance> findSeanceBySessionIdAndFormateurIsNotNullAndStringAttIsEmpty(Long session_id, boolean stringAttIsEmpty);



    List<Seance> findSeancesByFormateurId(Long formateur_id);



    List<Seance> findSeancesByFormateurEmailAndSessionIdOrderByDateDuJour(String formateur_email, Long session_id);


    List<Seance> findSeancesByFormateurIdAndSessionId(Long formateur_id, Long session_id);
    List<Seance> findSeancesByFormateurIdAndSessionIdOrderByDateDuJour(Long formateur_id, Long session_id);


    List<Seance> findBySavoirFairesIsNull();

    @Query(value = "SELECT * FROM SEANCE where LENGTH(METHODE_ENVISAGEE)<1 or LENGTH(DEROULEMENT)<1 or LENGTH(OBJECTIF_PEDA)<1 or LENGTH(SUPPORT_USE)<1 order by DATE_DU_JOUR" ,nativeQuery = true)
    List<Seance> seanceWhereAttributInferiorOneOrdeByDateDuJour();

    @Query(value = "SELECT * FROM SEANCE where LENGTH(METHODE_ENVISAGEE)<1 or LENGTH(DEROULEMENT)<1 or LENGTH(OBJECTIF_PEDA)<1 or LENGTH(SUPPORT_USE)<1 order by DATE_DU_JOUR" ,nativeQuery = true)
    Page<Seance> seanceWhereAttributInferiorOneOrdeByDateDuJourPage(Pageable pageable);

    @Query(value = "SELECT * FROM SEANCE where (LENGTH(METHODE_ENVISAGEE)<1 or LENGTH(DEROULEMENT)<1 or LENGTH(OBJECTIF_PEDA)<1 or LENGTH(SUPPORT_USE)<1) and FORMATEUR_ID is not null order by DATE_DU_JOUR" ,nativeQuery = true)
    List<Seance> seanceWhereAttributInferiorOneAndFormateurIsNotNullOrdeByDateDuJour();



}
