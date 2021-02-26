package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.MouvementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.Mouvement}.
 */
public interface MouvementService {

    /**
     * Save a mouvement.
     *
     * @param mouvementDTO the entity to save.
     * @return the persisted entity.
     */
    MouvementDTO save(MouvementDTO mouvementDTO);

    /**
     * Get all the mouvements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MouvementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" mouvement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MouvementDTO> findOne(Long id);

    /**
     * Delete the "id" mouvement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
