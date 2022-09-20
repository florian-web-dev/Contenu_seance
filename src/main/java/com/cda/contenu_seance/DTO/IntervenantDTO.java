package com.cda.contenu_seance.DTO;

import com.cda.contenu_seance.model.Seance;
import com.cda.contenu_seance.model.Session;
import com.cda.contenu_seance.validation.PasswordMatches;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@PasswordMatches()
public class IntervenantDTO {

    private Long id;

    @NotBlank(message = "Les espaces ne sont pas des caractères")
    @NotEmpty(message = "Le champ est vide")
    private String nom;

    @NotBlank(message = "Les espaces ne sont pas des caractères")
    @NotEmpty(message = "Le champ est vide")
    private String prenom;

    @NotBlank(message = "Les espaces ne sont pas des caractères")
    @NotEmpty(message = "Le champ est vide")
    @Email(message = "L'\''adresse mail n'est pas valide.")
    private String email;

    @NotBlank(message = "Les espaces ne sont pas des caractères")
    @NotEmpty(message = "Le champ est vide")
    private String adresse;

    @NotBlank(message = "Les espaces ne sont pas des caractères")
    @NotEmpty(message = "Le champ est vide")
    private String ville;

    @NotBlank(message = "Les espaces ne sont pas des caractères")
    @NotEmpty(message = "Le champ est vide")
    @Length(min=5,max = 5,message = "Un code postal est composé de 5 nombres.")
    private String codePostal;

    @NotBlank(message = "Les espaces ne sont pas des caracteres")
    @NotEmpty(message = "Le champ est vide")
    @Length(min = 10,max = 13,message = "Le numéro de téléphone doit au moins comporter 10 nombres et maximum 13.")
    private String tel;

    //    @ValidPassword
    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    @Size(min = 1,max = 30)
    private String mp;

    @NotBlank(message = "Les espace ne sont pas des caractere")
    @NotEmpty(message = "Le champ est vide")
    @Size(min = 1,max = 30)
    private String matchingPassword;

    private List<Seance> seances;

    private List<Session> sessions;

}
