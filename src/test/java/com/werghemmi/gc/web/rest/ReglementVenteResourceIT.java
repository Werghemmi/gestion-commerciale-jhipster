package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.ReglementVente;
import com.werghemmi.gc.repository.ReglementVenteRepository;
import com.werghemmi.gc.service.ReglementVenteService;
import com.werghemmi.gc.service.dto.ReglementVenteDTO;
import com.werghemmi.gc.service.mapper.ReglementVenteMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.werghemmi.gc.domain.enumeration.TypeReglement;
/**
 * Integration tests for the {@link ReglementVenteResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReglementVenteResourceIT {

    private static final Instant DEFAULT_DATE_REGLEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_REGLEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TypeReglement DEFAULT_TYPE_REGLEMENT = TypeReglement.VIREMENT;
    private static final TypeReglement UPDATED_TYPE_REGLEMENT = TypeReglement.CHEQUE;

    @Autowired
    private ReglementVenteRepository reglementVenteRepository;

    @Autowired
    private ReglementVenteMapper reglementVenteMapper;

    @Autowired
    private ReglementVenteService reglementVenteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReglementVenteMockMvc;

    private ReglementVente reglementVente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReglementVente createEntity(EntityManager em) {
        ReglementVente reglementVente = new ReglementVente()
            .dateReglement(DEFAULT_DATE_REGLEMENT)
            .typeReglement(DEFAULT_TYPE_REGLEMENT);
        return reglementVente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReglementVente createUpdatedEntity(EntityManager em) {
        ReglementVente reglementVente = new ReglementVente()
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .typeReglement(UPDATED_TYPE_REGLEMENT);
        return reglementVente;
    }

    @BeforeEach
    public void initTest() {
        reglementVente = createEntity(em);
    }

    @Test
    @Transactional
    public void createReglementVente() throws Exception {
        int databaseSizeBeforeCreate = reglementVenteRepository.findAll().size();
        // Create the ReglementVente
        ReglementVenteDTO reglementVenteDTO = reglementVenteMapper.toDto(reglementVente);
        restReglementVenteMockMvc.perform(post("/api/reglement-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementVenteDTO)))
            .andExpect(status().isCreated());

        // Validate the ReglementVente in the database
        List<ReglementVente> reglementVenteList = reglementVenteRepository.findAll();
        assertThat(reglementVenteList).hasSize(databaseSizeBeforeCreate + 1);
        ReglementVente testReglementVente = reglementVenteList.get(reglementVenteList.size() - 1);
        assertThat(testReglementVente.getDateReglement()).isEqualTo(DEFAULT_DATE_REGLEMENT);
        assertThat(testReglementVente.getTypeReglement()).isEqualTo(DEFAULT_TYPE_REGLEMENT);
    }

    @Test
    @Transactional
    public void createReglementVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reglementVenteRepository.findAll().size();

        // Create the ReglementVente with an existing ID
        reglementVente.setId(1L);
        ReglementVenteDTO reglementVenteDTO = reglementVenteMapper.toDto(reglementVente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReglementVenteMockMvc.perform(post("/api/reglement-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReglementVente in the database
        List<ReglementVente> reglementVenteList = reglementVenteRepository.findAll();
        assertThat(reglementVenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReglementVentes() throws Exception {
        // Initialize the database
        reglementVenteRepository.saveAndFlush(reglementVente);

        // Get all the reglementVenteList
        restReglementVenteMockMvc.perform(get("/api/reglement-ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reglementVente.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateReglement").value(hasItem(DEFAULT_DATE_REGLEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeReglement").value(hasItem(DEFAULT_TYPE_REGLEMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getReglementVente() throws Exception {
        // Initialize the database
        reglementVenteRepository.saveAndFlush(reglementVente);

        // Get the reglementVente
        restReglementVenteMockMvc.perform(get("/api/reglement-ventes/{id}", reglementVente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reglementVente.getId().intValue()))
            .andExpect(jsonPath("$.dateReglement").value(DEFAULT_DATE_REGLEMENT.toString()))
            .andExpect(jsonPath("$.typeReglement").value(DEFAULT_TYPE_REGLEMENT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingReglementVente() throws Exception {
        // Get the reglementVente
        restReglementVenteMockMvc.perform(get("/api/reglement-ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReglementVente() throws Exception {
        // Initialize the database
        reglementVenteRepository.saveAndFlush(reglementVente);

        int databaseSizeBeforeUpdate = reglementVenteRepository.findAll().size();

        // Update the reglementVente
        ReglementVente updatedReglementVente = reglementVenteRepository.findById(reglementVente.getId()).get();
        // Disconnect from session so that the updates on updatedReglementVente are not directly saved in db
        em.detach(updatedReglementVente);
        updatedReglementVente
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .typeReglement(UPDATED_TYPE_REGLEMENT);
        ReglementVenteDTO reglementVenteDTO = reglementVenteMapper.toDto(updatedReglementVente);

        restReglementVenteMockMvc.perform(put("/api/reglement-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementVenteDTO)))
            .andExpect(status().isOk());

        // Validate the ReglementVente in the database
        List<ReglementVente> reglementVenteList = reglementVenteRepository.findAll();
        assertThat(reglementVenteList).hasSize(databaseSizeBeforeUpdate);
        ReglementVente testReglementVente = reglementVenteList.get(reglementVenteList.size() - 1);
        assertThat(testReglementVente.getDateReglement()).isEqualTo(UPDATED_DATE_REGLEMENT);
        assertThat(testReglementVente.getTypeReglement()).isEqualTo(UPDATED_TYPE_REGLEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingReglementVente() throws Exception {
        int databaseSizeBeforeUpdate = reglementVenteRepository.findAll().size();

        // Create the ReglementVente
        ReglementVenteDTO reglementVenteDTO = reglementVenteMapper.toDto(reglementVente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReglementVenteMockMvc.perform(put("/api/reglement-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReglementVente in the database
        List<ReglementVente> reglementVenteList = reglementVenteRepository.findAll();
        assertThat(reglementVenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReglementVente() throws Exception {
        // Initialize the database
        reglementVenteRepository.saveAndFlush(reglementVente);

        int databaseSizeBeforeDelete = reglementVenteRepository.findAll().size();

        // Delete the reglementVente
        restReglementVenteMockMvc.perform(delete("/api/reglement-ventes/{id}", reglementVente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReglementVente> reglementVenteList = reglementVenteRepository.findAll();
        assertThat(reglementVenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
