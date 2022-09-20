package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Formation;
import com.cda.contenu_seance.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository <Session, Long>{
    long deleteByFormation(Formation formation);


}
