package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.DetaisDevisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.DetaisDevis}.
 */
public interface DetaisDevisService {

    /**
     * Save a detaisDevis.
     *
     * @param detaisDevisDTO the entity to save.
     * @return the persisted entity.
     */
    DetaisDevisDTO save(DetaisDevisDTO detaisDevisDTO);

    /**
     * Get all the detaisDevis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetaisDevisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detaisDevis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetaisDevisDTO> findOne(Long id);

    /**
     * Delete the "id" detaisDevis.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
