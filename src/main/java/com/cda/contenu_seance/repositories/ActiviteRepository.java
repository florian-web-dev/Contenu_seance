package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository <Activite, Long>{
    Activite findByNumOdre(int numOdre);
}
