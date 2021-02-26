package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.FactureVenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.FactureVente}.
 */
public interface FactureVenteService {

    /**
     * Save a factureVente.
     *
     * @param factureVenteDTO the entity to save.
     * @return the persisted entity.
     */
    FactureVenteDTO save(FactureVenteDTO factureVenteDTO);

    /**
     * Get all the factureVentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FactureVenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" factureVente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FactureVenteDTO> findOne(Long id);

    /**
     * Delete the "id" factureVente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
