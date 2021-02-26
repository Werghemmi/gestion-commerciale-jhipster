package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.ReglementAchatDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.ReglementAchat}.
 */
public interface ReglementAchatService {

    /**
     * Save a reglementAchat.
     *
     * @param reglementAchatDTO the entity to save.
     * @return the persisted entity.
     */
    ReglementAchatDTO save(ReglementAchatDTO reglementAchatDTO);

    /**
     * Get all the reglementAchats.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReglementAchatDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reglementAchat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReglementAchatDTO> findOne(Long id);

    /**
     * Delete the "id" reglementAchat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
