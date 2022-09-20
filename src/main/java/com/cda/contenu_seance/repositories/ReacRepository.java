package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Reac;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacRepository extends JpaRepository<Reac, Long> {
}
