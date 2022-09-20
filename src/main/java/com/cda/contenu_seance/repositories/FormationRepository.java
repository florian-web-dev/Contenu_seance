package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Formation;
import com.cda.contenu_seance.model.Reac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepository extends JpaRepository <Formation, Long>{
    long deleteByReac(Reac reac);

}
