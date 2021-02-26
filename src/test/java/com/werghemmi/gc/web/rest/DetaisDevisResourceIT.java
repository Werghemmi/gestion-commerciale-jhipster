package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.DetaisDevis;
import com.werghemmi.gc.repository.DetaisDevisRepository;
import com.werghemmi.gc.service.DetaisDevisService;
import com.werghemmi.gc.service.dto.DetaisDevisDTO;
import com.werghemmi.gc.service.mapper.DetaisDevisMapper;

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

/**
 * Integration tests for the {@link DetaisDevisResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetaisDevisResourceIT {

    private static final Float DEFAULT_QTE_PRODUIT = 1F;
    private static final Float UPDATED_QTE_PRODUIT = 2F;

    private static final Float DEFAULT_TOTAL_HT = 1F;
    private static final Float UPDATED_TOTAL_HT = 2F;

    private static final Float DEFAULT_TOTAL_TVA = 1F;
    private static final Float UPDATED_TOTAL_TVA = 2F;

    private static final Float DEFAULT_TOTAL_TTC = 1F;
    private static final Float UPDATED_TOTAL_TTC = 2F;

    @Autowired
    private DetaisDevisRepository detaisDevisRepository;

    @Autowired
    private DetaisDevisMapper detaisDevisMapper;

    @Autowired
    private DetaisDevisService detaisDevisService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetaisDevisMockMvc;

    private DetaisDevis detaisDevis;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetaisDevis createEntity(EntityManager em) {
        DetaisDevis detaisDevis = new DetaisDevis()
            .qteProduit(DEFAULT_QTE_PRODUIT)
            .totalHT(DEFAULT_TOTAL_HT)
            .totalTVA(DEFAULT_TOTAL_TVA)
            .totalTTC(DEFAULT_TOTAL_TTC);
        return detaisDevis;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetaisDevis createUpdatedEntity(EntityManager em) {
        DetaisDevis detaisDevis = new DetaisDevis()
            .qteProduit(UPDATED_QTE_PRODUIT)
            .totalHT(UPDATED_TOTAL_HT)
            .totalTVA(UPDATED_TOTAL_TVA)
            .totalTTC(UPDATED_TOTAL_TTC);
        return detaisDevis;
    }

    @BeforeEach
    public void initTest() {
        detaisDevis = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetaisDevis() throws Exception {
        int databaseSizeBeforeCreate = detaisDevisRepository.findAll().size();
        // Create the DetaisDevis
        DetaisDevisDTO detaisDevisDTO = detaisDevisMapper.toDto(detaisDevis);
        restDetaisDevisMockMvc.perform(post("/api/detais-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisDevisDTO)))
            .andExpect(status().isCreated());

        // Validate the DetaisDevis in the database
        List<DetaisDevis> detaisDevisList = detaisDevisRepository.findAll();
        assertThat(detaisDevisList).hasSize(databaseSizeBeforeCreate + 1);
        DetaisDevis testDetaisDevis = detaisDevisList.get(detaisDevisList.size() - 1);
        assertThat(testDetaisDevis.getQteProduit()).isEqualTo(DEFAULT_QTE_PRODUIT);
        assertThat(testDetaisDevis.getTotalHT()).isEqualTo(DEFAULT_TOTAL_HT);
        assertThat(testDetaisDevis.getTotalTVA()).isEqualTo(DEFAULT_TOTAL_TVA);
        assertThat(testDetaisDevis.getTotalTTC()).isEqualTo(DEFAULT_TOTAL_TTC);
    }

    @Test
    @Transactional
    public void createDetaisDevisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detaisDevisRepository.findAll().size();

        // Create the DetaisDevis with an existing ID
        detaisDevis.setId(1L);
        DetaisDevisDTO detaisDevisDTO = detaisDevisMapper.toDto(detaisDevis);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetaisDevisMockMvc.perform(post("/api/detais-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisDevisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetaisDevis in the database
        List<DetaisDevis> detaisDevisList = detaisDevisRepository.findAll();
        assertThat(detaisDevisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDetaisDevis() throws Exception {
        // Initialize the database
        detaisDevisRepository.saveAndFlush(detaisDevis);

        // Get all the detaisDevisList
        restDetaisDevisMockMvc.perform(get("/api/detais-devis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detaisDevis.getId().intValue())))
            .andExpect(jsonPath("$.[*].qteProduit").value(hasItem(DEFAULT_QTE_PRODUIT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalHT").value(hasItem(DEFAULT_TOTAL_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalTVA").value(hasItem(DEFAULT_TOTAL_TVA.doubleValue())))
            .andExpect(jsonPath("$.[*].totalTTC").value(hasItem(DEFAULT_TOTAL_TTC.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDetaisDevis() throws Exception {
        // Initialize the database
        detaisDevisRepository.saveAndFlush(detaisDevis);

        // Get the detaisDevis
        restDetaisDevisMockMvc.perform(get("/api/detais-devis/{id}", detaisDevis.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detaisDevis.getId().intValue()))
            .andExpect(jsonPath("$.qteProduit").value(DEFAULT_QTE_PRODUIT.doubleValue()))
            .andExpect(jsonPath("$.totalHT").value(DEFAULT_TOTAL_HT.doubleValue()))
            .andExpect(jsonPath("$.totalTVA").value(DEFAULT_TOTAL_TVA.doubleValue()))
            .andExpect(jsonPath("$.totalTTC").value(DEFAULT_TOTAL_TTC.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDetaisDevis() throws Exception {
        // Get the detaisDevis
        restDetaisDevisMockMvc.perform(get("/api/detais-devis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetaisDevis() throws Exception {
        // Initialize the database
        detaisDevisRepository.saveAndFlush(detaisDevis);

        int databaseSizeBeforeUpdate = detaisDevisRepository.findAll().size();

        // Update the detaisDevis
        DetaisDevis updatedDetaisDevis = detaisDevisRepository.findById(detaisDevis.getId()).get();
        // Disconnect from session so that the updates on updatedDetaisDevis are not directly saved in db
        em.detach(updatedDetaisDevis);
        updatedDetaisDevis
            .qteProduit(UPDATED_QTE_PRODUIT)
            .totalHT(UPDATED_TOTAL_HT)
            .totalTVA(UPDATED_TOTAL_TVA)
            .totalTTC(UPDATED_TOTAL_TTC);
        DetaisDevisDTO detaisDevisDTO = detaisDevisMapper.toDto(updatedDetaisDevis);

        restDetaisDevisMockMvc.perform(put("/api/detais-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisDevisDTO)))
            .andExpect(status().isOk());

        // Validate the DetaisDevis in the database
        List<DetaisDevis> detaisDevisList = detaisDevisRepository.findAll();
        assertThat(detaisDevisList).hasSize(databaseSizeBeforeUpdate);
        DetaisDevis testDetaisDevis = detaisDevisList.get(detaisDevisList.size() - 1);
        assertThat(testDetaisDevis.getQteProduit()).isEqualTo(UPDATED_QTE_PRODUIT);
        assertThat(testDetaisDevis.getTotalHT()).isEqualTo(UPDATED_TOTAL_HT);
        assertThat(testDetaisDevis.getTotalTVA()).isEqualTo(UPDATED_TOTAL_TVA);
        assertThat(testDetaisDevis.getTotalTTC()).isEqualTo(UPDATED_TOTAL_TTC);
    }

    @Test
    @Transactional
    public void updateNonExistingDetaisDevis() throws Exception {
        int databaseSizeBeforeUpdate = detaisDevisRepository.findAll().size();

        // Create the DetaisDevis
        DetaisDevisDTO detaisDevisDTO = detaisDevisMapper.toDto(detaisDevis);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetaisDevisMockMvc.perform(put("/api/detais-devis")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisDevisDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetaisDevis in the database
        List<DetaisDevis> detaisDevisList = detaisDevisRepository.findAll();
        assertThat(detaisDevisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetaisDevis() throws Exception {
        // Initialize the database
        detaisDevisRepository.saveAndFlush(detaisDevis);

        int databaseSizeBeforeDelete = detaisDevisRepository.findAll().size();

        // Delete the detaisDevis
        restDetaisDevisMockMvc.perform(delete("/api/detais-devis/{id}", detaisDevis.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetaisDevis> detaisDevisList = detaisDevisRepository.findAll();
        assertThat(detaisDevisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
