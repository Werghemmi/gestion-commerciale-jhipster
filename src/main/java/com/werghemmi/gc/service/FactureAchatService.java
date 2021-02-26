package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.FactureAchatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.FactureAchat}.
 */
public interface FactureAchatService {

    /**
     * Save a factureAchat.
     *
     * @param factureAchatDTO the entity to save.
     * @return the persisted entity.
     */
    FactureAchatDTO save(FactureAchatDTO factureAchatDTO);

    /**
     * Get all the factureAchats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FactureAchatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" factureAchat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FactureAchatDTO> findOne(Long id);

    /**
     * Delete the "id" factureAchat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
