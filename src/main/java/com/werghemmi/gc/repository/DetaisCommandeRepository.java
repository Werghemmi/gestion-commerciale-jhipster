package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.DetaisCommande;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetaisCommande entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetaisCommandeRepository extends JpaRepository<DetaisCommande, Long> {
}
