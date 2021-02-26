package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.service.TaxeService;
import com.werghemmi.gc.web.rest.errors.BadRequestAlertException;
import com.werghemmi.gc.service.dto.TaxeDTO;

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
 * REST controller for managing {@link com.werghemmi.gc.domain.Taxe}.
 */
@RestController
@RequestMapping("/api")
public class TaxeResource {

    private final Logger log = LoggerFactory.getLogger(TaxeResource.class);

    private static final String ENTITY_NAME = "taxe";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxeService taxeService;

    public TaxeResource(TaxeService taxeService) {
        this.taxeService = taxeService;
    }

    /**
     * {@code POST  /taxes} : Create a new taxe.
     *
     * @param taxeDTO the taxeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxeDTO, or with status {@code 400 (Bad Request)} if the taxe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/taxes")
    public ResponseEntity<TaxeDTO> createTaxe(@RequestBody TaxeDTO taxeDTO) throws URISyntaxException {
        log.debug("REST request to save Taxe : {}", taxeDTO);
        if (taxeDTO.getId() != null) {
            throw new BadRequestAlertException("A new taxe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxeDTO result = taxeService.save(taxeDTO);
        return ResponseEntity.created(new URI("/api/taxes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /taxes} : Updates an existing taxe.
     *
     * @param taxeDTO the taxeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxeDTO,
     * or with status {@code 400 (Bad Request)} if the taxeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/taxes")
    public ResponseEntity<TaxeDTO> updateTaxe(@RequestBody TaxeDTO taxeDTO) throws URISyntaxException {
        log.debug("REST request to update Taxe : {}", taxeDTO);
        if (taxeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaxeDTO result = taxeService.save(taxeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /taxes} : get all the taxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxes in body.
     */
    @GetMapping("/taxes")
    public ResponseEntity<List<TaxeDTO>> getAllTaxes(Pageable pageable) {
        log.debug("REST request to get a page of Taxes");
        Page<TaxeDTO> page = taxeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /taxes/:id} : get the "id" taxe.
     *
     * @param id the id of the taxeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/taxes/{id}")
    public ResponseEntity<TaxeDTO> getTaxe(@PathVariable Long id) {
        log.debug("REST request to get Taxe : {}", id);
        Optional<TaxeDTO> taxeDTO = taxeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxeDTO);
    }

    /**
     * {@code DELETE  /taxes/:id} : delete the "id" taxe.
     *
     * @param id the id of the taxeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/taxes/{id}")
    public ResponseEntity<Void> deleteTaxe(@PathVariable Long id) {
        log.debug("REST request to delete Taxe : {}", id);
        taxeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
