package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.ReglementAchat;
import com.werghemmi.gc.repository.ReglementAchatRepository;
import com.werghemmi.gc.service.ReglementAchatService;
import com.werghemmi.gc.service.dto.ReglementAchatDTO;
import com.werghemmi.gc.service.mapper.ReglementAchatMapper;

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
 * Integration tests for the {@link ReglementAchatResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ReglementAchatResourceIT {

    private static final Instant DEFAULT_DATE_REGLEMENT = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE_REGLEMENT = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final TypeReglement DEFAULT_TYPE_REGLEMENT = TypeReglement.VIREMENT;
    private static final TypeReglement UPDATED_TYPE_REGLEMENT = TypeReglement.CHEQUE;

    @Autowired
    private ReglementAchatRepository reglementAchatRepository;

    @Autowired
    private ReglementAchatMapper reglementAchatMapper;

    @Autowired
    private ReglementAchatService reglementAchatService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReglementAchatMockMvc;

    private ReglementAchat reglementAchat;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReglementAchat createEntity(EntityManager em) {
        ReglementAchat reglementAchat = new ReglementAchat()
            .dateReglement(DEFAULT_DATE_REGLEMENT)
            .typeReglement(DEFAULT_TYPE_REGLEMENT);
        return reglementAchat;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ReglementAchat createUpdatedEntity(EntityManager em) {
        ReglementAchat reglementAchat = new ReglementAchat()
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .typeReglement(UPDATED_TYPE_REGLEMENT);
        return reglementAchat;
    }

    @BeforeEach
    public void initTest() {
        reglementAchat = createEntity(em);
    }

    @Test
    @Transactional
    public void createReglementAchat() throws Exception {
        int databaseSizeBeforeCreate = reglementAchatRepository.findAll().size();
        // Create the ReglementAchat
        ReglementAchatDTO reglementAchatDTO = reglementAchatMapper.toDto(reglementAchat);
        restReglementAchatMockMvc.perform(post("/api/reglement-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementAchatDTO)))
            .andExpect(status().isCreated());

        // Validate the ReglementAchat in the database
        List<ReglementAchat> reglementAchatList = reglementAchatRepository.findAll();
        assertThat(reglementAchatList).hasSize(databaseSizeBeforeCreate + 1);
        ReglementAchat testReglementAchat = reglementAchatList.get(reglementAchatList.size() - 1);
        assertThat(testReglementAchat.getDateReglement()).isEqualTo(DEFAULT_DATE_REGLEMENT);
        assertThat(testReglementAchat.getTypeReglement()).isEqualTo(DEFAULT_TYPE_REGLEMENT);
    }

    @Test
    @Transactional
    public void createReglementAchatWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reglementAchatRepository.findAll().size();

        // Create the ReglementAchat with an existing ID
        reglementAchat.setId(1L);
        ReglementAchatDTO reglementAchatDTO = reglementAchatMapper.toDto(reglementAchat);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReglementAchatMockMvc.perform(post("/api/reglement-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementAchatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReglementAchat in the database
        List<ReglementAchat> reglementAchatList = reglementAchatRepository.findAll();
        assertThat(reglementAchatList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReglementAchats() throws Exception {
        // Initialize the database
        reglementAchatRepository.saveAndFlush(reglementAchat);

        // Get all the reglementAchatList
        restReglementAchatMockMvc.perform(get("/api/reglement-achats?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reglementAchat.getId().intValue())))
            .andExpect(jsonPath("$.[*].dateReglement").value(hasItem(DEFAULT_DATE_REGLEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeReglement").value(hasItem(DEFAULT_TYPE_REGLEMENT.toString())));
    }
    
    @Test
    @Transactional
    public void getReglementAchat() throws Exception {
        // Initialize the database
        reglementAchatRepository.saveAndFlush(reglementAchat);

        // Get the reglementAchat
        restReglementAchatMockMvc.perform(get("/api/reglement-achats/{id}", reglementAchat.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(reglementAchat.getId().intValue()))
            .andExpect(jsonPath("$.dateReglement").value(DEFAULT_DATE_REGLEMENT.toString()))
            .andExpect(jsonPath("$.typeReglement").value(DEFAULT_TYPE_REGLEMENT.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingReglementAchat() throws Exception {
        // Get the reglementAchat
        restReglementAchatMockMvc.perform(get("/api/reglement-achats/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReglementAchat() throws Exception {
        // Initialize the database
        reglementAchatRepository.saveAndFlush(reglementAchat);

        int databaseSizeBeforeUpdate = reglementAchatRepository.findAll().size();

        // Update the reglementAchat
        ReglementAchat updatedReglementAchat = reglementAchatRepository.findById(reglementAchat.getId()).get();
        // Disconnect from session so that the updates on updatedReglementAchat are not directly saved in db
        em.detach(updatedReglementAchat);
        updatedReglementAchat
            .dateReglement(UPDATED_DATE_REGLEMENT)
            .typeReglement(UPDATED_TYPE_REGLEMENT);
        ReglementAchatDTO reglementAchatDTO = reglementAchatMapper.toDto(updatedReglementAchat);

        restReglementAchatMockMvc.perform(put("/api/reglement-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementAchatDTO)))
            .andExpect(status().isOk());

        // Validate the ReglementAchat in the database
        List<ReglementAchat> reglementAchatList = reglementAchatRepository.findAll();
        assertThat(reglementAchatList).hasSize(databaseSizeBeforeUpdate);
        ReglementAchat testReglementAchat = reglementAchatList.get(reglementAchatList.size() - 1);
        assertThat(testReglementAchat.getDateReglement()).isEqualTo(UPDATED_DATE_REGLEMENT);
        assertThat(testReglementAchat.getTypeReglement()).isEqualTo(UPDATED_TYPE_REGLEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingReglementAchat() throws Exception {
        int databaseSizeBeforeUpdate = reglementAchatRepository.findAll().size();

        // Create the ReglementAchat
        ReglementAchatDTO reglementAchatDTO = reglementAchatMapper.toDto(reglementAchat);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReglementAchatMockMvc.perform(put("/api/reglement-achats")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(reglementAchatDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ReglementAchat in the database
        List<ReglementAchat> reglementAchatList = reglementAchatRepository.findAll();
        assertThat(reglementAchatList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReglementAchat() throws Exception {
        // Initialize the database
        reglementAchatRepository.saveAndFlush(reglementAchat);

        int databaseSizeBeforeDelete = reglementAchatRepository.findAll().size();

        // Delete the reglementAchat
        restReglementAchatMockMvc.perform(delete("/api/reglement-achats/{id}", reglementAchat.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ReglementAchat> reglementAchatList = reglementAchatRepository.findAll();
        assertThat(reglementAchatList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
