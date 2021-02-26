package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.ReglementVenteService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.ReglementVenteDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.ReglementVente}.
 */
@RestController
@RequestMapping("/api")
public class ReglementVenteResource {

    private final Logger log = LoggerFactory.getLogger(ReglementVenteResource.class);

    private static final String ENTITY_NAME = "reglementVente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReglementVenteService reglementVenteService;

    public ReglementVenteResource(ReglementVenteService reglementVenteService) {
        this.reglementVenteService = reglementVenteService;
    }

    /**
     * {@code POST  /reglement-ventes} : Create a new reglementVente.
     *
     * @param reglementVenteDTO the reglementVenteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reglementVenteDTO, or with status {@code 400 (Bad Request)} if the reglementVente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reglement-ventes")
    public ResponseEntity<ReglementVenteDTO> createReglementVente(@RequestBody ReglementVenteDTO reglementVenteDTO) throws URISyntaxException {
        log.debug("REST request to save ReglementVente : {}", reglementVenteDTO);
        if (reglementVenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new reglementVente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReglementVenteDTO result = reglementVenteService.save(reglementVenteDTO);
        return ResponseEntity.created(new URI("/api/reglement-ventes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reglement-ventes} : Updates an existing reglementVente.
     *
     * @param reglementVenteDTO the reglementVenteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reglementVenteDTO,
     * or with status {@code 400 (Bad Request)} if the reglementVenteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reglementVenteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reglement-ventes")
    public ResponseEntity<ReglementVenteDTO> updateReglementVente(@RequestBody ReglementVenteDTO reglementVenteDTO) throws URISyntaxException {
        log.debug("REST request to update ReglementVente : {}", reglementVenteDTO);
        if (reglementVenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReglementVenteDTO result = reglementVenteService.save(reglementVenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reglementVenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reglement-ventes} : get all the reglementVentes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reglementVentes in body.
     */
    @GetMapping("/reglement-ventes")
    public ResponseEntity<List<ReglementVenteDTO>> getAllReglementVentes(Pageable pageable) {
        log.debug("REST request to get a page of ReglementVentes");
        Page<ReglementVenteDTO> page = reglementVenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reglement-ventes/:id} : get the "id" reglementVente.
     *
     * @param id the id of the reglementVenteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reglementVenteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reglement-ventes/{id}")
    public ResponseEntity<ReglementVenteDTO> getReglementVente(@PathVariable Long id) {
        log.debug("REST request to get ReglementVente : {}", id);
        Optional<ReglementVenteDTO> reglementVenteDTO = reglementVenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reglementVenteDTO);
    }

    /**
     * {@code DELETE  /reglement-ventes/:id} : delete the "id" reglementVente.
     *
     * @param id the id of the reglementVenteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reglement-ventes/{id}")
    public ResponseEntity<Void> deleteReglementVente(@PathVariable Long id) {
        log.debug("REST request to delete ReglementVente : {}", id);
        reglementVenteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
