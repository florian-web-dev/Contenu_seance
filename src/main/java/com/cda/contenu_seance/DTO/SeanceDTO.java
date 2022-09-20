package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Formateur;
import com.cda.contenu_seance.model.SavoirFaire;
import com.cda.contenu_seance.model.Session;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SeanceDTO {
    private Long id;
    //    @Future
    @NotNull(message = "La date n'a pas bien été renseignée.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDuJour;

    //@Min(value = 3,message = "La durée minimum est de 3.")
    @Max(value = 4,message = "La durée maximum d'\''une séance est de 4h.")
    private int durer;


    //@NotBlank(message = "Les espace ne sont pas des caractere")
    //@NotEmpty(message = "Le champ est vide")
    private String supportUse;

    //@NotBlank(message = "Les espace ne sont pas des caractere")
    //@NotEmpty(message = "Le champ est vide")
    private String deroulement;

    //@NotBlank(message = "Les espace ne sont pas des caractere")
    //@NotEmpty(message = "Le champ est vide")
    private String objectifPeda;

    //@NotBlank(message = "Les espace ne sont pas des caractere")
    //@NotEmpty(message = "Le champ est vide")
    private String methodeEnvisagee;


    private String evaluation;

    private List<SavoirFaire> savoirFaires;

    @Nullable
    private Formateur formateur;
    @Nullable
    private Session session;

//    @NotEmpty
//    private Competence competence;


}
