package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.DetaisVenteService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.DetaisVenteDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.DetaisVente}.
 */
@RestController
@RequestMapping("/api")
public class DetaisVenteResource {

    private final Logger log = LoggerFactory.getLogger(DetaisVenteResource.class);

    private static final String ENTITY_NAME = "detaisVente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetaisVenteService detaisVenteService;

    public DetaisVenteResource(DetaisVenteService detaisVenteService) {
        this.detaisVenteService = detaisVenteService;
    }

    /**
     * {@code POST  /detais-ventes} : Create a new detaisVente.
     *
     * @param detaisVenteDTO the detaisVenteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detaisVenteDTO, or with status {@code 400 (Bad Request)} if the detaisVente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detais-ventes")
    public ResponseEntity<DetaisVenteDTO> createDetaisVente(@RequestBody DetaisVenteDTO detaisVenteDTO) throws URISyntaxException {
        log.debug("REST request to save DetaisVente : {}", detaisVenteDTO);
        if (detaisVenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new detaisVente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetaisVenteDTO result = detaisVenteService.save(detaisVenteDTO);
        return ResponseEntity.created(new URI("/api/detais-ventes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detais-ventes} : Updates an existing detaisVente.
     *
     * @param detaisVenteDTO the detaisVenteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detaisVenteDTO,
     * or with status {@code 400 (Bad Request)} if the detaisVenteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detaisVenteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detais-ventes")
    public ResponseEntity<DetaisVenteDTO> updateDetaisVente(@RequestBody DetaisVenteDTO detaisVenteDTO) throws URISyntaxException {
        log.debug("REST request to update DetaisVente : {}", detaisVenteDTO);
        if (detaisVenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetaisVenteDTO result = detaisVenteService.save(detaisVenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, detaisVenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detais-ventes} : get all the detaisVentes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detaisVentes in body.
     */
    @GetMapping("/detais-ventes")
    public ResponseEntity<List<DetaisVenteDTO>> getAllDetaisVentes(Pageable pageable) {
        log.debug("REST request to get a page of DetaisVentes");
        Page<DetaisVenteDTO> page = detaisVenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /detais-ventes/:id} : get the "id" detaisVente.
     *
     * @param id the id of the detaisVenteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detaisVenteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detais-ventes/{id}")
    public ResponseEntity<DetaisVenteDTO> getDetaisVente(@PathVariable Long id) {
        log.debug("REST request to get DetaisVente : {}", id);
        Optional<DetaisVenteDTO> detaisVenteDTO = detaisVenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detaisVenteDTO);
    }

    /**
     * {@code DELETE  /detais-ventes/:id} : delete the "id" detaisVente.
     *
     * @param id the id of the detaisVenteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detais-ventes/{id}")
    public ResponseEntity<Void> deleteDetaisVente(@PathVariable Long id) {
        log.debug("REST request to delete DetaisVente : {}", id);
        detaisVenteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
