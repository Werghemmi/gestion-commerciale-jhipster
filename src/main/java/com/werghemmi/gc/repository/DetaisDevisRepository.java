package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.DetaisDevis;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetaisDevis entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetaisDevisRepository extends JpaRepository<DetaisDevis, Long> {
}
