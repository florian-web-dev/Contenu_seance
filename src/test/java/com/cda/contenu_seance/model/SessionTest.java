package com.cda.contenu_seance.model;

import com.cda.contenu_seance.service.FicheSuivieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SessionTest {
    @Autowired
    FicheSuivieService ficheSuivieService;


    @BeforeEach
    void setUp() {
        assertNotNull(ficheSuivieService);
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void calSumSeance() {
       Session session = ficheSuivieService.getSession(1L);
       Session session2 = ficheSuivieService.getSession(2L);
//       session.calSumSeance();
       assertEquals(18,session.calSumSeance());
       assertEquals(12,session2.calSumSeance());
    }

    @Test
    void calSumSeanceFormateur() {
    }

    @Test
    void dureeTotalSession() {
        Session session = ficheSuivieService.getSession(1L);
        LocalDate dateDebut = session.getDateDebut(); // 14-07-2019
        LocalDate dateFin =session.getDateFin();  // 06-11-2020
//        -----------------------------------------------------
        long nbJourTotal = ChronoUnit.DAYS.between(dateDebut,dateFin);
        System.out.println("nombre de jour : " + nbJourTotal);
//        -----------------------------------------------------
        long total = session.dureeTotalSession(dateDebut,dateFin,10,1);
//        Calendar calendar = new Calendar.Builder().build();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));

        int nbWeek = calendar.getWeeksInWeekYear();
        System.out.println("nombre de semaine : " + nbWeek);
        System.out.println("Année : " + calendar.getWeekYear());
        System.out.println("detTime Date : " + calendar.getTime());

        Session sessionTest = new Session();
        sessionTest.setDurerTotal((int) total);

        assertEquals(sessionTest.getDurerTotal(),total);
        //assertEquals(214,total);
    }
}