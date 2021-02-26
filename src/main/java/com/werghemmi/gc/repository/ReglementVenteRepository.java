package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.ReglementVente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReglementVente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReglementVenteRepository extends JpaRepository<ReglementVente, Long> {
}
