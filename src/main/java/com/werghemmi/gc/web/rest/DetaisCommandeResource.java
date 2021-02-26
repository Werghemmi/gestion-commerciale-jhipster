package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.DetaisCommandeService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.DetaisCommandeDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.DetaisCommande}.
 */
@RestController
@RequestMapping("/api")
public class DetaisCommandeResource {

    private final Logger log = LoggerFactory.getLogger(DetaisCommandeResource.class);

    private static final String ENTITY_NAME = "detaisCommande";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetaisCommandeService detaisCommandeService;

    public DetaisCommandeResource(DetaisCommandeService detaisCommandeService) {
        this.detaisCommandeService = detaisCommandeService;
    }

    /**
     * {@code POST  /detais-commandes} : Create a new detaisCommande.
     *
     * @param detaisCommandeDTO the detaisCommandeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detaisCommandeDTO, or with status {@code 400 (Bad Request)} if the detaisCommande has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detais-commandes")
    public ResponseEntity<DetaisCommandeDTO> createDetaisCommande(@RequestBody DetaisCommandeDTO detaisCommandeDTO) throws URISyntaxException {
        log.debug("REST request to save DetaisCommande : {}", detaisCommandeDTO);
        if (detaisCommandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new detaisCommande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetaisCommandeDTO result = detaisCommandeService.save(detaisCommandeDTO);
        return ResponseEntity.created(new URI("/api/detais-commandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detais-commandes} : Updates an existing detaisCommande.
     *
     * @param detaisCommandeDTO the detaisCommandeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detaisCommandeDTO,
     * or with status {@code 400 (Bad Request)} if the detaisCommandeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detaisCommandeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detais-commandes")
    public ResponseEntity<DetaisCommandeDTO> updateDetaisCommande(@RequestBody DetaisCommandeDTO detaisCommandeDTO) throws URISyntaxException {
        log.debug("REST request to update DetaisCommande : {}", detaisCommandeDTO);
        if (detaisCommandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetaisCommandeDTO result = detaisCommandeService.save(detaisCommandeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, detaisCommandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detais-commandes} : get all the detaisCommandes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detaisCommandes in body.
     */
    @GetMapping("/detais-commandes")
    public ResponseEntity<List<DetaisCommandeDTO>> getAllDetaisCommandes(Pageable pageable) {
        log.debug("REST request to get a page of DetaisCommandes");
        Page<DetaisCommandeDTO> page = detaisCommandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /detais-commandes/:id} : get the "id" detaisCommande.
     *
     * @param id the id of the detaisCommandeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detaisCommandeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detais-commandes/{id}")
    public ResponseEntity<DetaisCommandeDTO> getDetaisCommande(@PathVariable Long id) {
        log.debug("REST request to get DetaisCommande : {}", id);
        Optional<DetaisCommandeDTO> detaisCommandeDTO = detaisCommandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detaisCommandeDTO);
    }

    /**
     * {@code DELETE  /detais-commandes/:id} : delete the "id" detaisCommande.
     *
     * @param id the id of the detaisCommandeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detais-commandes/{id}")
    public ResponseEntity<Void> deleteDetaisCommande(@PathVariable Long id) {
        log.debug("REST request to delete DetaisCommande : {}", id);
        detaisCommandeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
