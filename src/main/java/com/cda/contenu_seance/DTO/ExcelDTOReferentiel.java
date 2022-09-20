package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.Competence;
import com.cda.contenu_seance.model.SavoirFaire;
import com.cda.contenu_seance.model.Seance;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExcelDTOReferentiel {
    private Long id;

    // header du Excel
    private String Formation;
    private String dateDebutSession;
    private String dateFinSession;
    private String centre;


    // Activiter
    private List<Activite> activites;
    private List<Competence> competences;
//    SavoirFaire
    private List<SavoirFaire> nomSavoirFaire;
    private List<Seance> seances;

    //Session

    //formateur


}
