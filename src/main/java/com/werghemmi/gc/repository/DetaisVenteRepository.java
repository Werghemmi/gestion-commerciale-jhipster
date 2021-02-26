package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.DetaisVente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetaisVente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetaisVenteRepository extends JpaRepository<DetaisVente, Long> {
}
