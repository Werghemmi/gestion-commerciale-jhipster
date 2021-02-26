package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.DetaisVente;
import com.werghemmi.gc.repository.DetaisVenteRepository;
import com.werghemmi.gc.service.DetaisVenteService;
import com.werghemmi.gc.service.dto.DetaisVenteDTO;
import com.werghemmi.gc.service.mapper.DetaisVenteMapper;

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
 * Integration tests for the {@link DetaisVenteResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetaisVenteResourceIT {

    private static final Float DEFAULT_QTE_PRODUIT = 1F;
    private static final Float UPDATED_QTE_PRODUIT = 2F;

    private static final Float DEFAULT_PRIX = 1F;
    private static final Float UPDATED_PRIX = 2F;

    @Autowired
    private DetaisVenteRepository detaisVenteRepository;

    @Autowired
    private DetaisVenteMapper detaisVenteMapper;

    @Autowired
    private DetaisVenteService detaisVenteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetaisVenteMockMvc;

    private DetaisVente detaisVente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetaisVente createEntity(EntityManager em) {
        DetaisVente detaisVente = new DetaisVente()
            .qteProduit(DEFAULT_QTE_PRODUIT)
            .prix(DEFAULT_PRIX);
        return detaisVente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetaisVente createUpdatedEntity(EntityManager em) {
        DetaisVente detaisVente = new DetaisVente()
            .qteProduit(UPDATED_QTE_PRODUIT)
            .prix(UPDATED_PRIX);
        return detaisVente;
    }

    @BeforeEach
    public void initTest() {
        detaisVente = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetaisVente() throws Exception {
        int databaseSizeBeforeCreate = detaisVenteRepository.findAll().size();
        // Create the DetaisVente
        DetaisVenteDTO detaisVenteDTO = detaisVenteMapper.toDto(detaisVente);
        restDetaisVenteMockMvc.perform(post("/api/detais-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisVenteDTO)))
            .andExpect(status().isCreated());

        // Validate the DetaisVente in the database
        List<DetaisVente> detaisVenteList = detaisVenteRepository.findAll();
        assertThat(detaisVenteList).hasSize(databaseSizeBeforeCreate + 1);
        DetaisVente testDetaisVente = detaisVenteList.get(detaisVenteList.size() - 1);
        assertThat(testDetaisVente.getQteProduit()).isEqualTo(DEFAULT_QTE_PRODUIT);
        assertThat(testDetaisVente.getPrix()).isEqualTo(DEFAULT_PRIX);
    }

    @Test
    @Transactional
    public void createDetaisVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detaisVenteRepository.findAll().size();

        // Create the DetaisVente with an existing ID
        detaisVente.setId(1L);
        DetaisVenteDTO detaisVenteDTO = detaisVenteMapper.toDto(detaisVente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetaisVenteMockMvc.perform(post("/api/detais-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetaisVente in the database
        List<DetaisVente> detaisVenteList = detaisVenteRepository.findAll();
        assertThat(detaisVenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDetaisVentes() throws Exception {
        // Initialize the database
        detaisVenteRepository.saveAndFlush(detaisVente);

        // Get all the detaisVenteList
        restDetaisVenteMockMvc.perform(get("/api/detais-ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detaisVente.getId().intValue())))
            .andExpect(jsonPath("$.[*].qteProduit").value(hasItem(DEFAULT_QTE_PRODUIT.doubleValue())))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDetaisVente() throws Exception {
        // Initialize the database
        detaisVenteRepository.saveAndFlush(detaisVente);

        // Get the detaisVente
        restDetaisVenteMockMvc.perform(get("/api/detais-ventes/{id}", detaisVente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detaisVente.getId().intValue()))
            .andExpect(jsonPath("$.qteProduit").value(DEFAULT_QTE_PRODUIT.doubleValue()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDetaisVente() throws Exception {
        // Get the detaisVente
        restDetaisVenteMockMvc.perform(get("/api/detais-ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetaisVente() throws Exception {
        // Initialize the database
        detaisVenteRepository.saveAndFlush(detaisVente);

        int databaseSizeBeforeUpdate = detaisVenteRepository.findAll().size();

        // Update the detaisVente
        DetaisVente updatedDetaisVente = detaisVenteRepository.findById(detaisVente.getId()).get();
        // Disconnect from session so that the updates on updatedDetaisVente are not directly saved in db
        em.detach(updatedDetaisVente);
        updatedDetaisVente
            .qteProduit(UPDATED_QTE_PRODUIT)
            .prix(UPDATED_PRIX);
        DetaisVenteDTO detaisVenteDTO = detaisVenteMapper.toDto(updatedDetaisVente);

        restDetaisVenteMockMvc.perform(put("/api/detais-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisVenteDTO)))
            .andExpect(status().isOk());

        // Validate the DetaisVente in the database
        List<DetaisVente> detaisVenteList = detaisVenteRepository.findAll();
        assertThat(detaisVenteList).hasSize(databaseSizeBeforeUpdate);
        DetaisVente testDetaisVente = detaisVenteList.get(detaisVenteList.size() - 1);
        assertThat(testDetaisVente.getQteProduit()).isEqualTo(UPDATED_QTE_PRODUIT);
        assertThat(testDetaisVente.getPrix()).isEqualTo(UPDATED_PRIX);
    }

    @Test
    @Transactional
    public void updateNonExistingDetaisVente() throws Exception {
        int databaseSizeBeforeUpdate = detaisVenteRepository.findAll().size();

        // Create the DetaisVente
        DetaisVenteDTO detaisVenteDTO = detaisVenteMapper.toDto(detaisVente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetaisVenteMockMvc.perform(put("/api/detais-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetaisVente in the database
        List<DetaisVente> detaisVenteList = detaisVenteRepository.findAll();
        assertThat(detaisVenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetaisVente() throws Exception {
        // Initialize the database
        detaisVenteRepository.saveAndFlush(detaisVente);

        int databaseSizeBeforeDelete = detaisVenteRepository.findAll().size();

        // Delete the detaisVente
        restDetaisVenteMockMvc.perform(delete("/api/detais-ventes/{id}", detaisVente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetaisVente> detaisVenteList = detaisVenteRepository.findAll();
        assertThat(detaisVenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
