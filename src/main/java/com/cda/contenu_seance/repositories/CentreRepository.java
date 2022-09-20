package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepository extends JpaRepository <Centre, Long>{
}
