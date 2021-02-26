package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.ReglementAchat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ReglementAchat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReglementAchatRepository extends JpaRepository<ReglementAchat, Long> {
}
