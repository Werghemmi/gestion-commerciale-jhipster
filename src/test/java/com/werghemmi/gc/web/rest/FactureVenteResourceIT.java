package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.FactureVente;
import com.werghemmi.gc.repository.FactureVenteRepository;
import com.werghemmi.gc.service.FactureVenteService;
import com.werghemmi.gc.service.dto.FactureVenteDTO;
import com.werghemmi.gc.service.mapper.FactureVenteMapper;

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

/**
 * Integration tests for the {@link FactureVenteResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FactureVenteResourceIT {

    private static final Instant DEFAULT_DATE_FACTURE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_FACTURE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_TOTAL_HT = 1F;
    private static final Float UPDATED_TOTAL_HT = 2F;

    private static final Float DEFAULT_TOTAL_TVA = 1F;
    private static final Float UPDATED_TOTAL_TVA = 2F;

    private static final Float DEFAULT_TOTAL_TTC = 1F;
    private static final Float UPDATED_TOTAL_TTC = 2F;

    private static final Float DEFAULT_TOTAL_REMISE = 1F;
    private static final Float UPDATED_TOTAL_REMISE = 2F;

    private static final Float DEFAULT_TOTAL_NET = 1F;
    private static final Float UPDATED_TOTAL_NET = 2F;

    private static final Boolean DEFAULT_TIMBRE_FISCALE = false;
    private static final Boolean UPDATED_TIMBRE_FISCALE = true;

    @Autowired
    private FactureVenteRepository factureVenteRepository;

    @Autowired
    private FactureVenteMapper factureVenteMapper;

    @Autowired
    private FactureVenteService factureVenteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFactureVenteMockMvc;

    private FactureVente factureVente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactureVente createEntity(EntityManager em) {
        FactureVente factureVente = new FactureVente()
            .dateFacture(DEFAULT_DATE_FACTURE)
            .totalHT(DEFAULT_TOTAL_HT)
            .totalTVA(DEFAULT_TOTAL_TVA)
            .totalTTC(DEFAULT_TOTAL_TTC)
            .totalRemise(DEFAULT_TOTAL_REMISE)
            .totalNet(DEFAULT_TOTAL_NET)
            .timbreFiscale(DEFAULT_TIMBRE_FISCALE);
        return factureVente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactureVente createUpdatedEntity(EntityManager em) {
        FactureVente factureVente = new FactureVente()
            .dateFacture(UPDATED_DATE_FACTURE)
            .totalHT(UPDATED_TOTAL_HT)
            .totalTVA(UPDATED_TOTAL_TVA)
            .totalTTC(UPDATED_TOTAL_TTC)
            .totalRemise(UPDATED_TOTAL_REMISE)
            .totalNet(UPDATED_TOTAL_NET)
            .timbreFiscale(UPDATED_TIMBRE_FISCALE);
        return factureVente;
    }

    @BeforeEach
    public void initTest() {
        factureVente = createEntity(em);
    }

    @Test
    @Transactional
    public void createFactureVente() throws Exception {
        int databaseSizeBeforeCreate = factureVenteRepository.findAll().size();
        // Create the FactureVente
        FactureVenteDTO factureVenteDTO = factureVenteMapper.toDto(factureVente);
        restFactureVenteMockMvc.perform(post("/api/facture-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureVenteDTO)))
            .andExpect(status().isCreated());

        // Validate the FactureVente in the database
        List<FactureVente> factureVenteList = factureVenteRepository.findAll();
        assertThat(factureVenteList).hasSize(databaseSizeBeforeCreate + 1);
        FactureVente testFactureVente = factureVenteList.get(factureVenteList.size() - 1);
        assertThat(testFactureVente.getDateFacture()).isEqualTo(DEFAULT_DATE_FACTURE);
        assertThat(testFactureVente.getTotalHT()).isEqualTo(DEFAULT_TOTAL_HT);
        assertThat(testFactureVente.getTotalTVA()).isEqualTo(DEFAULT_TOTAL_TVA);
        assertThat(testFactureVente.getTotalTTC()).isEqualTo(DEFAULT_TOTAL_TTC);
        assertThat(testFactureVente.getTotalRemise()).isEqualTo(DEFAULT_TOTAL_REMISE);
        assertThat(testFactureVente.getTotalNet()).isEqualTo(DEFAULT_TOTAL_NET);
        assertThat(testFactureVente.isTimbreFiscale()).isEqualTo(DEFAULT_TIMBRE_FISCALE);
    }

    @Test
    @Transactional
    public void createFactureVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = factureVenteRepository.findAll().size();

        // Create the FactureVente with an existing ID
        factureVente.setId(1L);
        FactureVenteDTO factureVenteDTO = factureVenteMapper.toDto(factureVente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFactureVenteMockMvc.perform(post("/api/facture-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactureVente in the database
        List<FactureVente> factureVenteList = factureVenteRepository.findAll();
        assertThat(factureVenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFactureVentes() throws Exception {
        // Initialize the database
        factureVenteRepository.saveAndFlush(factureVente);

        // Get all the factureVenteList
        restFactureVenteMockMvc.perform(get("/api/facture-ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(factureVente.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateFacture").value(hasItem(DEFAULT_DATE_FACTURE.toString())))
            .andExpect(jsonPath("$.[*].totalHT").value(hasItem(DEFAULT_TOTAL_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalTVA").value(hasItem(DEFAULT_TOTAL_TVA.doubleValue())))
            .andExpect(jsonPath("$.[*].totalTTC").value(hasItem(DEFAULT_TOTAL_TTC.doubleValue())))
            .andExpect(jsonPath("$.[*].totalRemise").value(hasItem(DEFAULT_TOTAL_REMISE.doubleValue())))
            .andExpect(jsonPath("$.[*].totalNet").value(hasItem(DEFAULT_TOTAL_NET.doubleValue())))
            .andExpect(jsonPath("$.[*].timbreFiscale").value(hasItem(DEFAULT_TIMBRE_FISCALE.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getFactureVente() throws Exception {
        // Initialize the database
        factureVenteRepository.saveAndFlush(factureVente);

        // Get the factureVente
        restFactureVenteMockMvc.perform(get("/api/facture-ventes/{id}", factureVente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(factureVente.getId().intValue()))
            .andExpect(jsonPath("$.dateFacture").value(DEFAULT_DATE_FACTURE.toString()))
            .andExpect(jsonPath("$.totalHT").value(DEFAULT_TOTAL_HT.doubleValue()))
            .andExpect(jsonPath("$.totalTVA").value(DEFAULT_TOTAL_TVA.doubleValue()))
            .andExpect(jsonPath("$.totalTTC").value(DEFAULT_TOTAL_TTC.doubleValue()))
            .andExpect(jsonPath("$.totalRemise").value(DEFAULT_TOTAL_REMISE.doubleValue()))
            .andExpect(jsonPath("$.totalNet").value(DEFAULT_TOTAL_NET.doubleValue()))
            .andExpect(jsonPath("$.timbreFiscale").value(DEFAULT_TIMBRE_FISCALE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingFactureVente() throws Exception {
        // Get the factureVente
        restFactureVenteMockMvc.perform(get("/api/facture-ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFactureVente() throws Exception {
        // Initialize the database
        factureVenteRepository.saveAndFlush(factureVente);

        int databaseSizeBeforeUpdate = factureVenteRepository.findAll().size();

        // Update the factureVente
        FactureVente updatedFactureVente = factureVenteRepository.findById(factureVente.getId()).get();
        // Disconnect from session so that the updates on updatedFactureVente are not directly saved in db
        em.detach(updatedFactureVente);
        updatedFactureVente
            .dateFacture(UPDATED_DATE_FACTURE)
            .totalHT(UPDATED_TOTAL_HT)
            .totalTVA(UPDATED_TOTAL_TVA)
            .totalTTC(UPDATED_TOTAL_TTC)
            .totalRemise(UPDATED_TOTAL_REMISE)
            .totalNet(UPDATED_TOTAL_NET)
            .timbreFiscale(UPDATED_TIMBRE_FISCALE);
        FactureVenteDTO factureVenteDTO = factureVenteMapper.toDto(updatedFactureVente);

        restFactureVenteMockMvc.perform(put("/api/facture-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureVenteDTO)))
            .andExpect(status().isOk());

        // Validate the FactureVente in the database
        List<FactureVente> factureVenteList = factureVenteRepository.findAll();
        assertThat(factureVenteList).hasSize(databaseSizeBeforeUpdate);
        FactureVente testFactureVente = factureVenteList.get(factureVenteList.size() - 1);
        assertThat(testFactureVente.getDateFacture()).isEqualTo(UPDATED_DATE_FACTURE);
        assertThat(testFactureVente.getTotalHT()).isEqualTo(UPDATED_TOTAL_HT);
        assertThat(testFactureVente.getTotalTVA()).isEqualTo(UPDATED_TOTAL_TVA);
        assertThat(testFactureVente.getTotalTTC()).isEqualTo(UPDATED_TOTAL_TTC);
        assertThat(testFactureVente.getTotalRemise()).isEqualTo(UPDATED_TOTAL_REMISE);
        assertThat(testFactureVente.getTotalNet()).isEqualTo(UPDATED_TOTAL_NET);
        assertThat(testFactureVente.isTimbreFiscale()).isEqualTo(UPDATED_TIMBRE_FISCALE);
    }

    @Test
    @Transactional
    public void updateNonExistingFactureVente() throws Exception {
        int databaseSizeBeforeUpdate = factureVenteRepository.findAll().size();

        // Create the FactureVente
        FactureVenteDTO factureVenteDTO = factureVenteMapper.toDto(factureVente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFactureVenteMockMvc.perform(put("/api/facture-ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureVenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactureVente in the database
        List<FactureVente> factureVenteList = factureVenteRepository.findAll();
        assertThat(factureVenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFactureVente() throws Exception {
        // Initialize the database
        factureVenteRepository.saveAndFlush(factureVente);

        int databaseSizeBeforeDelete = factureVenteRepository.findAll().size();

        // Delete the factureVente
        restFactureVenteMockMvc.perform(delete("/api/facture-ventes/{id}", factureVente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FactureVente> factureVenteList = factureVenteRepository.findAll();
        assertThat(factureVenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
