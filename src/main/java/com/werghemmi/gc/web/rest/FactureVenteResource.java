package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.FactureVenteService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.FactureVenteDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.werghemmi.gc.domain.FactureVente}.
 */
@RestController
@RequestMapping("/api")
public class FactureVenteResource {

    private final Logger log = LoggerFactory.getLogger(FactureVenteResource.class);

    private static final String ENTITY_NAME = "factureVente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactureVenteService factureVenteService;

    public FactureVenteResource(FactureVenteService factureVenteService) {
        this.factureVenteService = factureVenteService;
    }

    /**
     * {@code POST  /facture-ventes} : Create a new factureVente.
     *
     * @param factureVenteDTO the factureVenteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new factureVenteDTO, or with status {@code 400 (Bad Request)} if the factureVente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facture-ventes")
    public ResponseEntity<FactureVenteDTO> createFactureVente(@RequestBody FactureVenteDTO factureVenteDTO) throws URISyntaxException {
        log.debug("REST request to save FactureVente : {}", factureVenteDTO);
        if (factureVenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new factureVente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactureVenteDTO result = factureVenteService.save(factureVenteDTO);
        return ResponseEntity.created(new URI("/api/facture-ventes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facture-ventes} : Updates an existing factureVente.
     *
     * @param factureVenteDTO the factureVenteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated factureVenteDTO,
     * or with status {@code 400 (Bad Request)} if the factureVenteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the factureVenteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facture-ventes")
    public ResponseEntity<FactureVenteDTO> updateFactureVente(@RequestBody FactureVenteDTO factureVenteDTO) throws URISyntaxException {
        log.debug("REST request to update FactureVente : {}", factureVenteDTO);
        if (factureVenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactureVenteDTO result = factureVenteService.save(factureVenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, factureVenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facture-ventes} : get all the factureVentes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factureVentes in body.
     */
    @GetMapping("/facture-ventes")
    public ResponseEntity<List<FactureVenteDTO>> getAllFactureVentes(Pageable pageable) {
        log.debug("REST request to get a page of FactureVentes");
        Page<FactureVenteDTO> page = factureVenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /facture-ventes/:id} : get the "id" factureVente.
     *
     * @param id the id of the factureVenteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factureVenteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facture-ventes/{id}")
    public ResponseEntity<FactureVenteDTO> getFactureVente(@PathVariable Long id) {
        log.debug("REST request to get FactureVente : {}", id);
        Optional<FactureVenteDTO> factureVenteDTO = factureVenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(factureVenteDTO);
    }

    /**
     * {@code DELETE  /facture-ventes/:id} : delete the "id" factureVente.
     *
     * @param id the id of the factureVenteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facture-ventes/{id}")
    public ResponseEntity<Void> deleteFactureVente(@PathVariable Long id) {
        log.debug("REST request to delete FactureVente : {}", id);
        factureVenteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
