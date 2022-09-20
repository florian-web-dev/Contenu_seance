package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.SavoirFaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavoirFaireRepository extends JpaRepository<SavoirFaire, Long> {


}