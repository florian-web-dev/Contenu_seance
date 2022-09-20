package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Coordinateur;
import com.cda.contenu_seance.model.Session;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CentreDTO {
    private Long id;
    @NotNull
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String nom;
    @NotNull
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String adresse;
    @NotNull
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String ville;
    @NotNull
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String codesPostal;

    private List <Coordinateur> coordinateur;
    private List<Session> sessions;
}
