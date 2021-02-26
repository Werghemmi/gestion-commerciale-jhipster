package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.FactureAchatService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.FactureAchatDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.FactureAchat}.
 */
@RestController
@RequestMapping("/api")
public class FactureAchatResource {

    private final Logger log = LoggerFactory.getLogger(FactureAchatResource.class);

    private static final String ENTITY_NAME = "factureAchat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FactureAchatService factureAchatService;

    public FactureAchatResource(FactureAchatService factureAchatService) {
        this.factureAchatService = factureAchatService;
    }

    /**
     * {@code POST  /facture-achats} : Create a new factureAchat.
     *
     * @param factureAchatDTO the factureAchatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new factureAchatDTO, or with status {@code 400 (Bad Request)} if the factureAchat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/facture-achats")
    public ResponseEntity<FactureAchatDTO> createFactureAchat(@RequestBody FactureAchatDTO factureAchatDTO) throws URISyntaxException {
        log.debug("REST request to save FactureAchat : {}", factureAchatDTO);
        if (factureAchatDTO.getId() != null) {
            throw new BadRequestAlertException("A new factureAchat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FactureAchatDTO result = factureAchatService.save(factureAchatDTO);
        return ResponseEntity.created(new URI("/api/facture-achats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /facture-achats} : Updates an existing factureAchat.
     *
     * @param factureAchatDTO the factureAchatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated factureAchatDTO,
     * or with status {@code 400 (Bad Request)} if the factureAchatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the factureAchatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/facture-achats")
    public ResponseEntity<FactureAchatDTO> updateFactureAchat(@RequestBody FactureAchatDTO factureAchatDTO) throws URISyntaxException {
        log.debug("REST request to update FactureAchat : {}", factureAchatDTO);
        if (factureAchatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FactureAchatDTO result = factureAchatService.save(factureAchatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, factureAchatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /facture-achats} : get all the factureAchats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of factureAchats in body.
     */
    @GetMapping("/facture-achats")
    public ResponseEntity<List<FactureAchatDTO>> getAllFactureAchats(Pageable pageable) {
        log.debug("REST request to get a page of FactureAchats");
        Page<FactureAchatDTO> page = factureAchatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /facture-achats/:id} : get the "id" factureAchat.
     *
     * @param id the id of the factureAchatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the factureAchatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/facture-achats/{id}")
    public ResponseEntity<FactureAchatDTO> getFactureAchat(@PathVariable Long id) {
        log.debug("REST request to get FactureAchat : {}", id);
        Optional<FactureAchatDTO> factureAchatDTO = factureAchatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(factureAchatDTO);
    }

    /**
     * {@code DELETE  /facture-achats/:id} : delete the "id" factureAchat.
     *
     * @param id the id of the factureAchatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/facture-achats/{id}")
    public ResponseEntity<Void> deleteFactureAchat(@PathVariable Long id) {
        log.debug("REST request to delete FactureAchat : {}", id);
        factureAchatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
