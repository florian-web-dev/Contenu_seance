package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Competence;
import com.cda.contenu_seance.model.Seance;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class SavoirFaireDTO {
    private Long id;
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String nom;
    private List<Seance> seances;
    private Competence competence;
}
