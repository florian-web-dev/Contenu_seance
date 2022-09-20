package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Reac;
import com.cda.contenu_seance.model.Session;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class FormationDTO {
    private Long id;

    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String typeFormation;

    private List<Reac> reac;
    private List<Session> sessions;
}
