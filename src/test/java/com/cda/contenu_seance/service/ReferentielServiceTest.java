package com.cda.contenu_seance.service;

import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.Competence;
import com.cda.contenu_seance.model.Formation;
import com.cda.contenu_seance.model.Reac;
import com.cda.contenu_seance.repositories.ActiviteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReferentielServiceTest {

    @Autowired
    ReferentielService referentielService;
    @Autowired
    ActiviteRepository activiteRepository;

    @BeforeEach
    void setUp() {
        assertNotNull(referentielService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getActivites() {
        List<Activite> activites = referentielService.getActivites();

        for (Activite uneActivite: activites ) {
            assertNotNull(uneActivite.getActivitesTypes());
            assertNotNull(uneActivite.getCompetences());
        }

    }

    @Test
    void getCompetences() {
        List <Competence> competences = referentielService.getCompetences();

        for (Competence uneCompetence: competences) {
            assertNotNull(uneCompetence.getActivite());
        }

    }

    @Test
    void getActivite() {
        Activite activite = referentielService.getActivite(1L);
        assertNotNull(activite);
        assertEquals("Concevoir et developper des composants ...",activite.getActivitesTypes());
    }

    @Test
    void getCompetence() {
        Competence competence = referentielService.getCompetence(1L);
        assertNotNull(competence);
        assertEquals("Maquetter une aplication",competence.getNom());
    }

    @Test
    void getReact(){
        Reac reac = referentielService.getReac(1L);
        assertNotNull(reac);

//        Date uneDate = new Date(119,00,01); //2019-1900 mois commmence a 0
//
//        assertEquals(uneDate,reac.getDateDebut());
    }


    @Transactional
    @Test
    void saveActiviteUpdate() {
        Activite activite = referentielService.getActivite(3L);
        String exAttribut = activite.getActivitesTypes();


        activite.setActivitesTypes("test saveUpdate");
        activite.setNumOdre(4);
        referentielService.updateActiveEnty(activite);
        Activite activite2 = referentielService.getActivite(3L);
        assertEquals(activite,activite2);

        assertNotEquals(exAttribut,activite2.getActivitesTypes());
        assertEquals("test saveUpdate",activite.getActivitesTypes());


    }

    @Transactional
    @Test
    void saveActiviteCreate() {

        //Activite activite = activiteCompetenceService.getActivite(4L);
        //activite.setId(4L);
        int numActiv = referentielService.getActivites().size();

        Activite activite = new Activite();

        activite.setActivitesTypes("test saveCreate");
        activite.setNumOdre(4);
        Reac reac = referentielService.getReac(1L);
        activite.setReac(reac);

        activite = referentielService.createActiveEnty(activite);
//        ------------------------------------
        Activite act2 = referentielService.getActivite(activite.getId());

        assertEquals(act2,activite);
        assertEquals(act2.getId(),activite.getId());
        assertEquals(act2.getActivitesTypes(),activite.getActivitesTypes());

        assertEquals(reac,activite.getReac());

        int numActiv2 = referentielService.getActivites().size();

        assertEquals(numActiv+1,numActiv2);


    }

    @Transactional
    @Test
    void saveUpdateCompetence(){
        Competence competence = referentielService.getCompetence(4L);
        competence.setNom("test update competance");
        competence.setNumOdre(6);
        referentielService.updateCompetenceEntity(competence);

        competence = referentielService.getCompetence(4L);
        assertEquals("test update competance",competence.getNom());
        assertEquals(6,competence.getNumOdre());


    }
    @Transactional
    @Test
    void saveCreatCompetance(){
        ///Competence competence = activiteCompetenceServiceReact.getCompetence(5L);
        //competence.setId(5L);
        Competence competence = new Competence();
        competence.setId(5L);
        competence.setNom("add competence test");
        competence.setNumOdre(5);

        //Activite activite = competence.getActivite(); //activiteCompetenceServiceReact.getActivite(3L);

        competence.setActivite(referentielService.getActivite(3L));
        competence = referentielService.createCompetenceEntity(competence);

        //competence = activiteCompetenceServiceReact.getCompetence(5L);
        assertEquals("add competence test",competence.getNom());


    }

    @Transactional
    @Test
    void creatReac(){
        Reac reac = referentielService.getReac(1L);
        //reac.setDateDebut(2022-02-18);
        reac.setDurer(4);
        reac.setLien("lien set");
        Formation formation = reac.getFormation();
        reac = referentielService.saveReacEntity(reac);
        assertEquals("lien set",reac.getLien());
    }

}