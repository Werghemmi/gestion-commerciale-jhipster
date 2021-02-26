package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.DevisDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.Devis}.
 */
public interface DevisService {

    /**
     * Save a devis.
     *
     * @param devisDTO the entity to save.
     * @return the persisted entity.
     */
    DevisDTO save(DevisDTO devisDTO);

    /**
     * Get all the devis.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DevisDTO> findAll(Pageable pageable);


    /**
     * Get the "id" devis.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DevisDTO> findOne(Long id);

    /**
     * Delete the "id" devis.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
