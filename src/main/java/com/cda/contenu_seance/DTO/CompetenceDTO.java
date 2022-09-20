package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.SavoirFaire;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class CompetenceDTO {
    private Long id;
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String nom;

    @Min(value = 1,message = "Le numéro de la fiche activité type ne commence pas par 0.")
    private int numOdre;

    private Activite activite;

    private List<SavoirFaire> savoirFaires;
}
