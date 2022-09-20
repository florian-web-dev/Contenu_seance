package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormateurRepository extends JpaRepository <Formateur, Long>{
    //dsl Domain Specifique Language
    Formateur findFormateurByEmail(String email);
}
