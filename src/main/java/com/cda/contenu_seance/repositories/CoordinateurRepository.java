package com.cda.contenu_seance.repositories;

import com.cda.contenu_seance.model.Coordinateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateurRepository extends JpaRepository<Coordinateur, Long> {
//    public Coordinateur findByEmail(String email);
//    public Coordinateur findByName(String name);
}
