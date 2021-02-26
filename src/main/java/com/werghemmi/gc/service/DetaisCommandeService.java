package com.werghemmi.gc.service;

import com.werghemmi.gc.service.dto.DetaisCommandeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.werghemmi.gc.domain.DetaisCommande}.
 */
public interface DetaisCommandeService {

    /**
     * Save a detaisCommande.
     *
     * @param detaisCommandeDTO the entity to save.
     * @return the persisted entity.
     */
    DetaisCommandeDTO save(DetaisCommandeDTO detaisCommandeDTO);

    /**
     * Get all the detaisCommandes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetaisCommandeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" detaisCommande.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetaisCommandeDTO> findOne(Long id);

    /**
     * Delete the "id" detaisCommande.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
