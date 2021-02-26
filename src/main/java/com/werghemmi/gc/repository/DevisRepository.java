package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.Devis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Devis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevisRepository extends JpaRepository<Devis, Long> {
}
