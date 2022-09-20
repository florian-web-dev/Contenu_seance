package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Centre;
import com.cda.contenu_seance.model.Session;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoordinateurDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String ville;
    private String codePostal;
    private String tel;
    private String mp;

    private List<Centre> centres;
    private List<Session> sessions;
}
