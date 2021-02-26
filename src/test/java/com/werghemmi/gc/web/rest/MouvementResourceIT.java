package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.Mouvement;
import com.werghemmi.gc.repository.MouvementRepository;
import com.werghemmi.gc.service.MouvementService;
import com.werghemmi.gc.service.dto.MouvementDTO;
import com.werghemmi.gc.service.mapper.MouvementMapper;

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

import com.werghemmi.gc.domain.enumeration.TypeMouvement;
/**
 * Integration tests for the {@link MouvementResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MouvementResourceIT {

    private static final Instant DEFAULT_DATE_MVT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_MVT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TypeMouvement DEFAULT_TYPE_MVT = TypeMouvement.ACHAT;
    private static final TypeMouvement UPDATED_TYPE_MVT = TypeMouvement.VENTE;

    private static final Float DEFAULT_QTE_MVT = 1F;
    private static final Float UPDATED_QTE_MVT = 2F;

    private static final Float DEFAULT_ANCIEN_QTE = 1F;
    private static final Float UPDATED_ANCIEN_QTE = 2F;

    private static final Float DEFAULT_NV_QTE = 1F;
    private static final Float UPDATED_NV_QTE = 2F;

    private static final Float DEFAULT_ANCIEN_PRIX = 1F;
    private static final Float UPDATED_ANCIEN_PRIX = 2F;

    private static final Float DEFAULT_NV_PRIX = 1F;
    private static final Float UPDATED_NV_PRIX = 2F;

    private static final Float DEFAULT_PRIX = 1F;
    private static final Float UPDATED_PRIX = 2F;

    @Autowired
    private MouvementRepository mouvementRepository;

    @Autowired
    private MouvementMapper mouvementMapper;

    @Autowired
    private MouvementService mouvementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMouvementMockMvc;

    private Mouvement mouvement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mouvement createEntity(EntityManager em) {
        Mouvement mouvement = new Mouvement()
            .dateMvt(DEFAULT_DATE_MVT)
            .typeMvt(DEFAULT_TYPE_MVT)
            .qteMvt(DEFAULT_QTE_MVT)
            .ancienQte(DEFAULT_ANCIEN_QTE)
            .nvQte(DEFAULT_NV_QTE)
            .ancienPrix(DEFAULT_ANCIEN_PRIX)
            .nvPrix(DEFAULT_NV_PRIX)
            .prix(DEFAULT_PRIX);
        return mouvement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mouvement createUpdatedEntity(EntityManager em) {
        Mouvement mouvement = new Mouvement()
            .dateMvt(UPDATED_DATE_MVT)
            .typeMvt(UPDATED_TYPE_MVT)
            .qteMvt(UPDATED_QTE_MVT)
            .ancienQte(UPDATED_ANCIEN_QTE)
            .nvQte(UPDATED_NV_QTE)
            .ancienPrix(UPDATED_ANCIEN_PRIX)
            .nvPrix(UPDATED_NV_PRIX)
            .prix(UPDATED_PRIX);
        return mouvement;
    }

    @BeforeEach
    public void initTest() {
        mouvement = createEntity(em);
    }

    @Test
    @Transactional
    public void createMouvement() throws Exception {
        int databaseSizeBeforeCreate = mouvementRepository.findAll().size();
        // Create the Mouvement
        MouvementDTO mouvementDTO = mouvementMapper.toDto(mouvement);
        restMouvementMockMvc.perform(post("/api/mouvements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mouvementDTO)))
            .andExpect(status().isCreated());

        // Validate the Mouvement in the database
        List<Mouvement> mouvementList = mouvementRepository.findAll();
        assertThat(mouvementList).hasSize(databaseSizeBeforeCreate + 1);
        Mouvement testMouvement = mouvementList.get(mouvementList.size() - 1);
        assertThat(testMouvement.getDateMvt()).isEqualTo(DEFAULT_DATE_MVT);
        assertThat(testMouvement.getTypeMvt()).isEqualTo(DEFAULT_TYPE_MVT);
        assertThat(testMouvement.getQteMvt()).isEqualTo(DEFAULT_QTE_MVT);
        assertThat(testMouvement.getAncienQte()).isEqualTo(DEFAULT_ANCIEN_QTE);
        assertThat(testMouvement.getNvQte()).isEqualTo(DEFAULT_NV_QTE);
        assertThat(testMouvement.getAncienPrix()).isEqualTo(DEFAULT_ANCIEN_PRIX);
        assertThat(testMouvement.getNvPrix()).isEqualTo(DEFAULT_NV_PRIX);
        assertThat(testMouvement.getPrix()).isEqualTo(DEFAULT_PRIX);
    }

    @Test
    @Transactional
    public void createMouvementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mouvementRepository.findAll().size();

        // Create the Mouvement with an existing ID
        mouvement.setId(1L);
        MouvementDTO mouvementDTO = mouvementMapper.toDto(mouvement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMouvementMockMvc.perform(post("/api/mouvements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mouvementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mouvement in the database
        List<Mouvement> mouvementList = mouvementRepository.findAll();
        assertThat(mouvementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMouvements() throws Exception {
        // Initialize the database
        mouvementRepository.saveAndFlush(mouvement);

        // Get all the mouvementList
        restMouvementMockMvc.perform(get("/api/mouvements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mouvement.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateMvt").value(hasItem(DEFAULT_DATE_MVT.toString())))
            .andExpect(jsonPath("$.[*].typeMvt").value(hasItem(DEFAULT_TYPE_MVT.toString())))
            .andExpect(jsonPath("$.[*].qteMvt").value(hasItem(DEFAULT_QTE_MVT.doubleValue())))
            .andExpect(jsonPath("$.[*].ancienQte").value(hasItem(DEFAULT_ANCIEN_QTE.doubleValue())))
            .andExpect(jsonPath("$.[*].nvQte").value(hasItem(DEFAULT_NV_QTE.doubleValue())))
            .andExpect(jsonPath("$.[*].ancienPrix").value(hasItem(DEFAULT_ANCIEN_PRIX.doubleValue())))
            .andExpect(jsonPath("$.[*].nvPrix").value(hasItem(DEFAULT_NV_PRIX.doubleValue())))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMouvement() throws Exception {
        // Initialize the database
        mouvementRepository.saveAndFlush(mouvement);

        // Get the mouvement
        restMouvementMockMvc.perform(get("/api/mouvements/{id}", mouvement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(mouvement.getId().intValue()))
            .andExpect(jsonPath("$.dateMvt").value(DEFAULT_DATE_MVT.toString()))
            .andExpect(jsonPath("$.typeMvt").value(DEFAULT_TYPE_MVT.toString()))
            .andExpect(jsonPath("$.qteMvt").value(DEFAULT_QTE_MVT.doubleValue()))
            .andExpect(jsonPath("$.ancienQte").value(DEFAULT_ANCIEN_QTE.doubleValue()))
            .andExpect(jsonPath("$.nvQte").value(DEFAULT_NV_QTE.doubleValue()))
            .andExpect(jsonPath("$.ancienPrix").value(DEFAULT_ANCIEN_PRIX.doubleValue()))
            .andExpect(jsonPath("$.nvPrix").value(DEFAULT_NV_PRIX.doubleValue()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingMouvement() throws Exception {
        // Get the mouvement
        restMouvementMockMvc.perform(get("/api/mouvements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMouvement() throws Exception {
        // Initialize the database
        mouvementRepository.saveAndFlush(mouvement);

        int databaseSizeBeforeUpdate = mouvementRepository.findAll().size();

        // Update the mouvement
        Mouvement updatedMouvement = mouvementRepository.findById(mouvement.getId()).get();
        // Disconnect from session so that the updates on updatedMouvement are not directly saved in db
        em.detach(updatedMouvement);
        updatedMouvement
            .dateMvt(UPDATED_DATE_MVT)
            .typeMvt(UPDATED_TYPE_MVT)
            .qteMvt(UPDATED_QTE_MVT)
            .ancienQte(UPDATED_ANCIEN_QTE)
            .nvQte(UPDATED_NV_QTE)
            .ancienPrix(UPDATED_ANCIEN_PRIX)
            .nvPrix(UPDATED_NV_PRIX)
            .prix(UPDATED_PRIX);
        MouvementDTO mouvementDTO = mouvementMapper.toDto(updatedMouvement);

        restMouvementMockMvc.perform(put("/api/mouvements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mouvementDTO)))
            .andExpect(status().isOk());

        // Validate the Mouvement in the database
        List<Mouvement> mouvementList = mouvementRepository.findAll();
        assertThat(mouvementList).hasSize(databaseSizeBeforeUpdate);
        Mouvement testMouvement = mouvementList.get(mouvementList.size() - 1);
        assertThat(testMouvement.getDateMvt()).isEqualTo(UPDATED_DATE_MVT);
        assertThat(testMouvement.getTypeMvt()).isEqualTo(UPDATED_TYPE_MVT);
        assertThat(testMouvement.getQteMvt()).isEqualTo(UPDATED_QTE_MVT);
        assertThat(testMouvement.getAncienQte()).isEqualTo(UPDATED_ANCIEN_QTE);
        assertThat(testMouvement.getNvQte()).isEqualTo(UPDATED_NV_QTE);
        assertThat(testMouvement.getAncienPrix()).isEqualTo(UPDATED_ANCIEN_PRIX);
        assertThat(testMouvement.getNvPrix()).isEqualTo(UPDATED_NV_PRIX);
        assertThat(testMouvement.getPrix()).isEqualTo(UPDATED_PRIX);
    }

    @Test
    @Transactional
    public void updateNonExistingMouvement() throws Exception {
        int databaseSizeBeforeUpdate = mouvementRepository.findAll().size();

        // Create the Mouvement
        MouvementDTO mouvementDTO = mouvementMapper.toDto(mouvement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMouvementMockMvc.perform(put("/api/mouvements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(mouvementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mouvement in the database
        List<Mouvement> mouvementList = mouvementRepository.findAll();
        assertThat(mouvementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMouvement() throws Exception {
        // Initialize the database
        mouvementRepository.saveAndFlush(mouvement);

        int databaseSizeBeforeDelete = mouvementRepository.findAll().size();

        // Delete the mouvement
        restMouvementMockMvc.perform(delete("/api/mouvements/{id}", mouvement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mouvement> mouvementList = mouvementRepository.findAll();
        assertThat(mouvementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
