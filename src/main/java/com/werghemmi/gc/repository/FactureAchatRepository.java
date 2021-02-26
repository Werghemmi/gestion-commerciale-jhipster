package com.werghemmi.gc.repository;

import com.werghemmi.gc.domain.FactureAchat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FactureAchat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureAchatRepository extends JpaRepository<FactureAchat, Long> {
}
