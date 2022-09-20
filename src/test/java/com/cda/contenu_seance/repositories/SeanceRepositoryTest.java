package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.service.FicheSuivieService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeanceRepositoryTest {
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
    void findSeancesByFormateurIsNotNullAndSessionIsNotNull() {
        List <Seance> seanceList = ficheSuivieService.getSeanceWhFormateurAndSessionIsNotNull();
        for (Seance uneSeance:seanceList) {
            assertNotNull(uneSeance.getSession());
            assertNotNull(uneSeance.getFormateur());

        }
    }

    @Test
    void findSeanceByFormateurIsNotNullAndSessionIsNotNullAndAtrNoEmpty() {
        List <Seance> seanceList = ficheSuivieService.getSeanceNonEmpty();
        for (Seance uneSeance:seanceList) {

            assertFalse(uneSeance.getDeroulement().isEmpty());
            assertFalse(uneSeance.getObjectifPeda().isEmpty());
            assertFalse(uneSeance.getSupportUse().isEmpty());
            assertFalse(uneSeance.getMethodeEnvisagee().isEmpty());


            assertNotNull(uneSeance.getSession());
            assertNotNull(uneSeance.getFormateur());

        }
    }
    @Test
    void findSeanceByFormateurIsNotNullAndSessionIdAndAtrNoEmpty() {
        List <Seance> seanceList = ficheSuivieService.getSeanceByIdSessionAttIsEmpty(1L);

        for (Seance uneSeance:seanceList) {

            assertFalse(uneSeance.getDeroulement().isEmpty());
            assertFalse(uneSeance.getObjectifPeda().isEmpty());
            assertFalse(uneSeance.getSupportUse().isEmpty());
            assertFalse(uneSeance.getMethodeEnvisagee().isEmpty());

            assertNotNull(uneSeance.getFormateur());

            assert uneSeance.getSession() != null;
            assertEquals(1, (long) uneSeance.getSession().getId());

        }
    }


}