package com.cda.contenu_seance.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ReferentielDTO {
    //Reac
    private Long idReac;
    private String lien;
    private Date dateDebut;
    private long durer;

//    private Formation formation;
//    private List<Activite> activites;

    //Activite
    private Long idActivite;
    @NotNull
    private String activitesTypes;
    private int numOdreActivite;

    //@NotEmpty
//    private List<Competence> competences;
//    private Reac reac;



}
