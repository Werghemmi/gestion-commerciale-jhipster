package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.ReglementAchatService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.ReglementAchatDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.ReglementAchat}.
 */
@RestController
@RequestMapping("/api")
public class ReglementAchatResource {

    private final Logger log = LoggerFactory.getLogger(ReglementAchatResource.class);

    private static final String ENTITY_NAME = "reglementAchat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReglementAchatService reglementAchatService;

    public ReglementAchatResource(ReglementAchatService reglementAchatService) {
        this.reglementAchatService = reglementAchatService;
    }

    /**
     * {@code POST  /reglement-achats} : Create a new reglementAchat.
     *
     * @param reglementAchatDTO the reglementAchatDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new reglementAchatDTO, or with status {@code 400 (Bad Request)} if the reglementAchat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/reglement-achats")
    public ResponseEntity<ReglementAchatDTO> createReglementAchat(@RequestBody ReglementAchatDTO reglementAchatDTO) throws URISyntaxException {
        log.debug("REST request to save ReglementAchat : {}", reglementAchatDTO);
        if (reglementAchatDTO.getId() != null) {
            throw new BadRequestAlertException("A new reglementAchat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReglementAchatDTO result = reglementAchatService.save(reglementAchatDTO);
        return ResponseEntity.created(new URI("/api/reglement-achats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /reglement-achats} : Updates an existing reglementAchat.
     *
     * @param reglementAchatDTO the reglementAchatDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated reglementAchatDTO,
     * or with status {@code 400 (Bad Request)} if the reglementAchatDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the reglementAchatDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/reglement-achats")
    public ResponseEntity<ReglementAchatDTO> updateReglementAchat(@RequestBody ReglementAchatDTO reglementAchatDTO) throws URISyntaxException {
        log.debug("REST request to update ReglementAchat : {}", reglementAchatDTO);
        if (reglementAchatDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReglementAchatDTO result = reglementAchatService.save(reglementAchatDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, reglementAchatDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /reglement-achats} : get all the reglementAchats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of reglementAchats in body.
     */
    @GetMapping("/reglement-achats")
    public ResponseEntity<List<ReglementAchatDTO>> getAllReglementAchats(Pageable pageable) {
        log.debug("REST request to get a page of ReglementAchats");
        Page<ReglementAchatDTO> page = reglementAchatService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /reglement-achats/:id} : get the "id" reglementAchat.
     *
     * @param id the id of the reglementAchatDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the reglementAchatDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/reglement-achats/{id}")
    public ResponseEntity<ReglementAchatDTO> getReglementAchat(@PathVariable Long id) {
        log.debug("REST request to get ReglementAchat : {}", id);
        Optional<ReglementAchatDTO> reglementAchatDTO = reglementAchatService.findOne(id);
        return ResponseUtil.wrapOrNotFound(reglementAchatDTO);
    }

    /**
     * {@code DELETE  /reglement-achats/:id} : delete the "id" reglementAchat.
     *
     * @param id the id of the reglementAchatDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/reglement-achats/{id}")
    public ResponseEntity<Void> deleteReglementAchat(@PathVariable Long id) {
        log.debug("REST request to delete ReglementAchat : {}", id);
        reglementAchatService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
