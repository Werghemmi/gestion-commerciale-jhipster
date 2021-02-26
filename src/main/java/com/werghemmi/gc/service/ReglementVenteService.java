package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.ReglementVenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.ReglementVente}.
 */
public interface ReglementVenteService {

    /**
     * Save a reglementVente.
     *
     * @param reglementVenteDTO the entity to save.
     * @return the persisted entity.
     */
    ReglementVenteDTO save(ReglementVenteDTO reglementVenteDTO);

    /**
     * Get all the reglementVentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReglementVenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reglementVente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReglementVenteDTO> findOne(Long id);

    /**
     * Delete the "id" reglementVente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
