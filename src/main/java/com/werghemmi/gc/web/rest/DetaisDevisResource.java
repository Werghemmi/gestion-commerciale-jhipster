package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.DetaisDevisService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.DetaisDevisDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.DetaisDevis}.
 */
@RestController
@RequestMapping("/api")
public class DetaisDevisResource {

    private final Logger log = LoggerFactory.getLogger(DetaisDevisResource.class);

    private static final String ENTITY_NAME = "detaisDevis";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetaisDevisService detaisDevisService;

    public DetaisDevisResource(DetaisDevisService detaisDevisService) {
        this.detaisDevisService = detaisDevisService;
    }

    /**
     * {@code POST  /detais-devis} : Create a new detaisDevis.
     *
     * @param detaisDevisDTO the detaisDevisDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detaisDevisDTO, or with status {@code 400 (Bad Request)} if the detaisDevis has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detais-devis")
    public ResponseEntity<DetaisDevisDTO> createDetaisDevis(@RequestBody DetaisDevisDTO detaisDevisDTO) throws URISyntaxException {
        log.debug("REST request to save DetaisDevis : {}", detaisDevisDTO);
        if (detaisDevisDTO.getId() != null) {
            throw new BadRequestAlertException("A new detaisDevis cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetaisDevisDTO result = detaisDevisService.save(detaisDevisDTO);
        return ResponseEntity.created(new URI("/api/detais-devis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detais-devis} : Updates an existing detaisDevis.
     *
     * @param detaisDevisDTO the detaisDevisDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detaisDevisDTO,
     * or with status {@code 400 (Bad Request)} if the detaisDevisDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detaisDevisDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detais-devis")
    public ResponseEntity<DetaisDevisDTO> updateDetaisDevis(@RequestBody DetaisDevisDTO detaisDevisDTO) throws URISyntaxException {
        log.debug("REST request to update DetaisDevis : {}", detaisDevisDTO);
        if (detaisDevisDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetaisDevisDTO result = detaisDevisService.save(detaisDevisDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, detaisDevisDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detais-devis} : get all the detaisDevis.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detaisDevis in body.
     */
    @GetMapping("/detais-devis")
    public ResponseEntity<List<DetaisDevisDTO>> getAllDetaisDevis(Pageable pageable) {
        log.debug("REST request to get a page of DetaisDevis");
        Page<DetaisDevisDTO> page = detaisDevisService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /detais-devis/:id} : get the "id" detaisDevis.
     *
     * @param id the id of the detaisDevisDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detaisDevisDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detais-devis/{id}")
    public ResponseEntity<DetaisDevisDTO> getDetaisDevis(@PathVariable Long id) {
        log.debug("REST request to get DetaisDevis : {}", id);
        Optional<DetaisDevisDTO> detaisDevisDTO = detaisDevisService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detaisDevisDTO);
    }

    /**
     * {@code DELETE  /detais-devis/:id} : delete the "id" detaisDevis.
     *
     * @param id the id of the detaisDevisDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detais-devis/{id}")
    public ResponseEntity<Void> deleteDetaisDevis(@PathVariable Long id) {
        log.debug("REST request to delete DetaisDevis : {}", id);
        detaisDevisService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
