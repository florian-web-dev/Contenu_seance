package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Activite;
import com.cda.contenu_seance.model.Formation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReacDTO {
    private Long id;
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String nom;
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    private String lien;

    @NotNull(message = "La date n'a pas bien été renseignée.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;
    @Min(value = 1,message = "La durée ne peut être négative, ou égale a zéro.")
    private int durer;
    private Formation formation;
    private List<Activite> activites;

}
