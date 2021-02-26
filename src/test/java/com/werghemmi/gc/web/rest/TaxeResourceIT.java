package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.Taxe;
import com.werghemmi.gc.repository.TaxeRepository;
import com.werghemmi.gc.service.TaxeService;
import com.werghemmi.gc.service.dto.TaxeDTO;
import com.werghemmi.gc.service.mapper.TaxeMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.werghemmi.gc.domain.enumeration.Type;
/**
 * Integration tests for the {@link TaxeResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TaxeResourceIT {

    private static final String DEFAULT_NOM_TAXE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_TAXE = "BBBBBBBBBB";

    private static final Float DEFAULT_TAUX = 1F;
    private static final Float UPDATED_TAUX = 2F;

    private static final Type DEFAULT_TYPE_TAXE = Type.POURCENTAGE;
    private static final Type UPDATED_TYPE_TAXE = Type.PRIX_FIXE;

    @Autowired
    private TaxeRepository taxeRepository;

    @Autowired
    private TaxeMapper taxeMapper;

    @Autowired
    private TaxeService taxeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxeMockMvc;

    private Taxe taxe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taxe createEntity(EntityManager em) {
        Taxe taxe = new Taxe()
            .nomTaxe(DEFAULT_NOM_TAXE)
            .taux(DEFAULT_TAUX)
            .typeTaxe(DEFAULT_TYPE_TAXE);
        return taxe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taxe createUpdatedEntity(EntityManager em) {
        Taxe taxe = new Taxe()
            .nomTaxe(UPDATED_NOM_TAXE)
            .taux(UPDATED_TAUX)
            .typeTaxe(UPDATED_TYPE_TAXE);
        return taxe;
    }

    @BeforeEach
    public void initTest() {
        taxe = createEntity(em);
    }

    @Test
    @Transactional
    public void createTaxe() throws Exception {
        int databaseSizeBeforeCreate = taxeRepository.findAll().size();
        // Create the Taxe
        TaxeDTO taxeDTO = taxeMapper.toDto(taxe);
        restTaxeMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxeDTO)))
            .andExpect(status().isCreated());

        // Validate the Taxe in the database
        List<Taxe> taxeList = taxeRepository.findAll();
        assertThat(taxeList).hasSize(databaseSizeBeforeCreate + 1);
        Taxe testTaxe = taxeList.get(taxeList.size() - 1);
        assertThat(testTaxe.getNomTaxe()).isEqualTo(DEFAULT_NOM_TAXE);
        assertThat(testTaxe.getTaux()).isEqualTo(DEFAULT_TAUX);
        assertThat(testTaxe.getTypeTaxe()).isEqualTo(DEFAULT_TYPE_TAXE);
    }

    @Test
    @Transactional
    public void createTaxeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = taxeRepository.findAll().size();

        // Create the Taxe with an existing ID
        taxe.setId(1L);
        TaxeDTO taxeDTO = taxeMapper.toDto(taxe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxeMockMvc.perform(post("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taxe in the database
        List<Taxe> taxeList = taxeRepository.findAll();
        assertThat(taxeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTaxes() throws Exception {
        // Initialize the database
        taxeRepository.saveAndFlush(taxe);

        // Get all the taxeList
        restTaxeMockMvc.perform(get("/api/taxes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxe.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomTaxe").value(hasItem(DEFAULT_NOM_TAXE)))
            .andExpect(jsonPath("$.[*].taux").value(hasItem(DEFAULT_TAUX.doubleValue())))
            .andExpect(jsonPath("$.[*].typeTaxe").value(hasItem(DEFAULT_TYPE_TAXE.toString())));
    }
    
    @Test
    @Transactional
    public void getTaxe() throws Exception {
        // Initialize the database
        taxeRepository.saveAndFlush(taxe);

        // Get the taxe
        restTaxeMockMvc.perform(get("/api/taxes/{id}", taxe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxe.getId().intValue()))
            .andExpect(jsonPath("$.nomTaxe").value(DEFAULT_NOM_TAXE))
            .andExpect(jsonPath("$.taux").value(DEFAULT_TAUX.doubleValue()))
            .andExpect(jsonPath("$.typeTaxe").value(DEFAULT_TYPE_TAXE.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingTaxe() throws Exception {
        // Get the taxe
        restTaxeMockMvc.perform(get("/api/taxes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTaxe() throws Exception {
        // Initialize the database
        taxeRepository.saveAndFlush(taxe);

        int databaseSizeBeforeUpdate = taxeRepository.findAll().size();

        // Update the taxe
        Taxe updatedTaxe = taxeRepository.findById(taxe.getId()).get();
        // Disconnect from session so that the updates on updatedTaxe are not directly saved in db
        em.detach(updatedTaxe);
        updatedTaxe
            .nomTaxe(UPDATED_NOM_TAXE)
            .taux(UPDATED_TAUX)
            .typeTaxe(UPDATED_TYPE_TAXE);
        TaxeDTO taxeDTO = taxeMapper.toDto(updatedTaxe);

        restTaxeMockMvc.perform(put("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxeDTO)))
            .andExpect(status().isOk());

        // Validate the Taxe in the database
        List<Taxe> taxeList = taxeRepository.findAll();
        assertThat(taxeList).hasSize(databaseSizeBeforeUpdate);
        Taxe testTaxe = taxeList.get(taxeList.size() - 1);
        assertThat(testTaxe.getNomTaxe()).isEqualTo(UPDATED_NOM_TAXE);
        assertThat(testTaxe.getTaux()).isEqualTo(UPDATED_TAUX);
        assertThat(testTaxe.getTypeTaxe()).isEqualTo(UPDATED_TYPE_TAXE);
    }

    @Test
    @Transactional
    public void updateNonExistingTaxe() throws Exception {
        int databaseSizeBeforeUpdate = taxeRepository.findAll().size();

        // Create the Taxe
        TaxeDTO taxeDTO = taxeMapper.toDto(taxe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxeMockMvc.perform(put("/api/taxes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(taxeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Taxe in the database
        List<Taxe> taxeList = taxeRepository.findAll();
        assertThat(taxeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTaxe() throws Exception {
        // Initialize the database
        taxeRepository.saveAndFlush(taxe);

        int databaseSizeBeforeDelete = taxeRepository.findAll().size();

        // Delete the taxe
        restTaxeMockMvc.perform(delete("/api/taxes/{id}", taxe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Taxe> taxeList = taxeRepository.findAll();
        assertThat(taxeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
