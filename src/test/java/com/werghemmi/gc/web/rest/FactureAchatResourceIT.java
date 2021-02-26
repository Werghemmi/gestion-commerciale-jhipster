package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.FactureAchat;
import com.werghemmi.gc.repository.FactureAchatRepository;
import com.werghemmi.gc.service.FactureAchatService;
import com.werghemmi.gc.service.dto.FactureAchatDTO;
import com.werghemmi.gc.service.mapper.FactureAchatMapper;

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
 * Integration tests for the {@link FactureAchatResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FactureAchatResourceIT {

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
    private FactureAchatRepository factureAchatRepository;

    @Autowired
    private FactureAchatMapper factureAchatMapper;

    @Autowired
    private FactureAchatService factureAchatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFactureAchatMockMvc;

    private FactureAchat factureAchat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactureAchat createEntity(EntityManager em) {
        FactureAchat factureAchat = new FactureAchat()
            .dateFacture(DEFAULT_DATE_FACTURE)
            .totalHT(DEFAULT_TOTAL_HT)
            .totalTVA(DEFAULT_TOTAL_TVA)
            .totalTTC(DEFAULT_TOTAL_TTC)
            .totalRemise(DEFAULT_TOTAL_REMISE)
            .totalNet(DEFAULT_TOTAL_NET)
            .timbreFiscale(DEFAULT_TIMBRE_FISCALE);
        return factureAchat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FactureAchat createUpdatedEntity(EntityManager em) {
        FactureAchat factureAchat = new FactureAchat()
            .dateFacture(UPDATED_DATE_FACTURE)
            .totalHT(UPDATED_TOTAL_HT)
            .totalTVA(UPDATED_TOTAL_TVA)
            .totalTTC(UPDATED_TOTAL_TTC)
            .totalRemise(UPDATED_TOTAL_REMISE)
            .totalNet(UPDATED_TOTAL_NET)
            .timbreFiscale(UPDATED_TIMBRE_FISCALE);
        return factureAchat;
    }

    @BeforeEach
    public void initTest() {
        factureAchat = createEntity(em);
    }

    @Test
    @Transactional
    public void createFactureAchat() throws Exception {
        int databaseSizeBeforeCreate = factureAchatRepository.findAll().size();
        // Create the FactureAchat
        FactureAchatDTO factureAchatDTO = factureAchatMapper.toDto(factureAchat);
        restFactureAchatMockMvc.perform(post("/api/facture-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureAchatDTO)))
            .andExpect(status().isCreated());

        // Validate the FactureAchat in the database
        List<FactureAchat> factureAchatList = factureAchatRepository.findAll();
        assertThat(factureAchatList).hasSize(databaseSizeBeforeCreate + 1);
        FactureAchat testFactureAchat = factureAchatList.get(factureAchatList.size() - 1);
        assertThat(testFactureAchat.getDateFacture()).isEqualTo(DEFAULT_DATE_FACTURE);
        assertThat(testFactureAchat.getTotalHT()).isEqualTo(DEFAULT_TOTAL_HT);
        assertThat(testFactureAchat.getTotalTVA()).isEqualTo(DEFAULT_TOTAL_TVA);
        assertThat(testFactureAchat.getTotalTTC()).isEqualTo(DEFAULT_TOTAL_TTC);
        assertThat(testFactureAchat.getTotalRemise()).isEqualTo(DEFAULT_TOTAL_REMISE);
        assertThat(testFactureAchat.getTotalNet()).isEqualTo(DEFAULT_TOTAL_NET);
        assertThat(testFactureAchat.isTimbreFiscale()).isEqualTo(DEFAULT_TIMBRE_FISCALE);
    }

    @Test
    @Transactional
    public void createFactureAchatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = factureAchatRepository.findAll().size();

        // Create the FactureAchat with an existing ID
        factureAchat.setId(1L);
        FactureAchatDTO factureAchatDTO = factureAchatMapper.toDto(factureAchat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFactureAchatMockMvc.perform(post("/api/facture-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureAchatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactureAchat in the database
        List<FactureAchat> factureAchatList = factureAchatRepository.findAll();
        assertThat(factureAchatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFactureAchats() throws Exception {
        // Initialize the database
        factureAchatRepository.saveAndFlush(factureAchat);

        // Get all the factureAchatList
        restFactureAchatMockMvc.perform(get("/api/facture-achats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(factureAchat.getId().intValue())))
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
    public void getFactureAchat() throws Exception {
        // Initialize the database
        factureAchatRepository.saveAndFlush(factureAchat);

        // Get the factureAchat
        restFactureAchatMockMvc.perform(get("/api/facture-achats/{id}", factureAchat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(factureAchat.getId().intValue()))
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
    public void getNonExistingFactureAchat() throws Exception {
        // Get the factureAchat
        restFactureAchatMockMvc.perform(get("/api/facture-achats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFactureAchat() throws Exception {
        // Initialize the database
        factureAchatRepository.saveAndFlush(factureAchat);

        int databaseSizeBeforeUpdate = factureAchatRepository.findAll().size();

        // Update the factureAchat
        FactureAchat updatedFactureAchat = factureAchatRepository.findById(factureAchat.getId()).get();
        // Disconnect from session so that the updates on updatedFactureAchat are not directly saved in db
        em.detach(updatedFactureAchat);
        updatedFactureAchat
            .dateFacture(UPDATED_DATE_FACTURE)
            .totalHT(UPDATED_TOTAL_HT)
            .totalTVA(UPDATED_TOTAL_TVA)
            .totalTTC(UPDATED_TOTAL_TTC)
            .totalRemise(UPDATED_TOTAL_REMISE)
            .totalNet(UPDATED_TOTAL_NET)
            .timbreFiscale(UPDATED_TIMBRE_FISCALE);
        FactureAchatDTO factureAchatDTO = factureAchatMapper.toDto(updatedFactureAchat);

        restFactureAchatMockMvc.perform(put("/api/facture-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureAchatDTO)))
            .andExpect(status().isOk());

        // Validate the FactureAchat in the database
        List<FactureAchat> factureAchatList = factureAchatRepository.findAll();
        assertThat(factureAchatList).hasSize(databaseSizeBeforeUpdate);
        FactureAchat testFactureAchat = factureAchatList.get(factureAchatList.size() - 1);
        assertThat(testFactureAchat.getDateFacture()).isEqualTo(UPDATED_DATE_FACTURE);
        assertThat(testFactureAchat.getTotalHT()).isEqualTo(UPDATED_TOTAL_HT);
        assertThat(testFactureAchat.getTotalTVA()).isEqualTo(UPDATED_TOTAL_TVA);
        assertThat(testFactureAchat.getTotalTTC()).isEqualTo(UPDATED_TOTAL_TTC);
        assertThat(testFactureAchat.getTotalRemise()).isEqualTo(UPDATED_TOTAL_REMISE);
        assertThat(testFactureAchat.getTotalNet()).isEqualTo(UPDATED_TOTAL_NET);
        assertThat(testFactureAchat.isTimbreFiscale()).isEqualTo(UPDATED_TIMBRE_FISCALE);
    }

    @Test
    @Transactional
    public void updateNonExistingFactureAchat() throws Exception {
        int databaseSizeBeforeUpdate = factureAchatRepository.findAll().size();

        // Create the FactureAchat
        FactureAchatDTO factureAchatDTO = factureAchatMapper.toDto(factureAchat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFactureAchatMockMvc.perform(put("/api/facture-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureAchatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FactureAchat in the database
        List<FactureAchat> factureAchatList = factureAchatRepository.findAll();
        assertThat(factureAchatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFactureAchat() throws Exception {
        // Initialize the database
        factureAchatRepository.saveAndFlush(factureAchat);

        int databaseSizeBeforeDelete = factureAchatRepository.findAll().size();

        // Delete the factureAchat
        restFactureAchatMockMvc.perform(delete("/api/facture-achats/{id}", factureAchat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FactureAchat> factureAchatList = factureAchatRepository.findAll();
        assertThat(factureAchatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
