package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository <Competence, Long>{
}
