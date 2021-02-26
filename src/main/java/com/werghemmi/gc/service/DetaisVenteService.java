package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.DetaisVenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.DetaisVente}.
 */
public interface DetaisVenteService {

    /**
     * Save a detaisVente.
     *
     * @param detaisVenteDTO the entity to save.
     * @return the persisted entity.
     */
    DetaisVenteDTO save(DetaisVenteDTO detaisVenteDTO);

    /**
     * Get all the detaisVentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetaisVenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detaisVente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetaisVenteDTO> findOne(Long id);

    /**
     * Delete the "id" detaisVente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
