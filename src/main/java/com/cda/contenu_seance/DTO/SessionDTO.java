package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SessionDTO {
    private Long id;

    @NotNull(message = "le champ est vide")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDebut;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La date n'a pas bien été renseignée.")
    private LocalDate dateFin;
    @Min(value = 1,message = "La durée totale ne peut être inférieure à 1.")
    private int durerTotal;

//    @NotNull(message = "le champ est vide")
    private Centre centre;

//    @NotNull(message = "Une erreur c'est produite")
    private Formation formation;

//    @NotNull(message = "le champ est vide")
    private Coordinateur coordinateur;

    private List<Seance> seances;

    private List <Formateur> formateurs;


}
