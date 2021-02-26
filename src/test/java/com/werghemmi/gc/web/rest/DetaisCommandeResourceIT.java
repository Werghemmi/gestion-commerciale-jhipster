package com.werghemmi.gc.web.rest;

import com.werghemmi.gc.WerghemmiGestionCommerclaieApp;
import com.werghemmi.gc.domain.DetaisCommande;
import com.werghemmi.gc.repository.DetaisCommandeRepository;
import com.werghemmi.gc.service.DetaisCommandeService;
import com.werghemmi.gc.service.dto.DetaisCommandeDTO;
import com.werghemmi.gc.service.mapper.DetaisCommandeMapper;

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
 * Integration tests for the {@link DetaisCommandeResource} REST controller.
 */
@SpringBootTest(classes = WerghemmiGestionCommerclaieApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DetaisCommandeResourceIT {

    private static final Float DEFAULT_QTE_PRODUIT = 1F;
    private static final Float UPDATED_QTE_PRODUIT = 2F;

    private static final Float DEFAULT_PRIX = 1F;
    private static final Float UPDATED_PRIX = 2F;

    @Autowired
    private DetaisCommandeRepository detaisCommandeRepository;

    @Autowired
    private DetaisCommandeMapper detaisCommandeMapper;

    @Autowired
    private DetaisCommandeService detaisCommandeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDetaisCommandeMockMvc;

    private DetaisCommande detaisCommande;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetaisCommande createEntity(EntityManager em) {
        DetaisCommande detaisCommande = new DetaisCommande()
            .qteProduit(DEFAULT_QTE_PRODUIT)
            .prix(DEFAULT_PRIX);
        return detaisCommande;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DetaisCommande createUpdatedEntity(EntityManager em) {
        DetaisCommande detaisCommande = new DetaisCommande()
            .qteProduit(UPDATED_QTE_PRODUIT)
            .prix(UPDATED_PRIX);
        return detaisCommande;
    }

    @BeforeEach
    public void initTest() {
        detaisCommande = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetaisCommande() throws Exception {
        int databaseSizeBeforeCreate = detaisCommandeRepository.findAll().size();
        // Create the DetaisCommande
        DetaisCommandeDTO detaisCommandeDTO = detaisCommandeMapper.toDto(detaisCommande);
        restDetaisCommandeMockMvc.perform(post("/api/detais-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisCommandeDTO)))
            .andExpect(status().isCreated());

        // Validate the DetaisCommande in the database
        List<DetaisCommande> detaisCommandeList = detaisCommandeRepository.findAll();
        assertThat(detaisCommandeList).hasSize(databaseSizeBeforeCreate + 1);
        DetaisCommande testDetaisCommande = detaisCommandeList.get(detaisCommandeList.size() - 1);
        assertThat(testDetaisCommande.getQteProduit()).isEqualTo(DEFAULT_QTE_PRODUIT);
        assertThat(testDetaisCommande.getPrix()).isEqualTo(DEFAULT_PRIX);
    }

    @Test
    @Transactional
    public void createDetaisCommandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detaisCommandeRepository.findAll().size();

        // Create the DetaisCommande with an existing ID
        detaisCommande.setId(1L);
        DetaisCommandeDTO detaisCommandeDTO = detaisCommandeMapper.toDto(detaisCommande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetaisCommandeMockMvc.perform(post("/api/detais-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetaisCommande in the database
        List<DetaisCommande> detaisCommandeList = detaisCommandeRepository.findAll();
        assertThat(detaisCommandeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDetaisCommandes() throws Exception {
        // Initialize the database
        detaisCommandeRepository.saveAndFlush(detaisCommande);

        // Get all the detaisCommandeList
        restDetaisCommandeMockMvc.perform(get("/api/detais-commandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detaisCommande.getId().intValue())))
            .andExpect(jsonPath("$.[*].qteProduit").value(hasItem(DEFAULT_QTE_PRODUIT.doubleValue())))
            .andExpect(jsonPath("$.[*].prix").value(hasItem(DEFAULT_PRIX.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getDetaisCommande() throws Exception {
        // Initialize the database
        detaisCommandeRepository.saveAndFlush(detaisCommande);

        // Get the detaisCommande
        restDetaisCommandeMockMvc.perform(get("/api/detais-commandes/{id}", detaisCommande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(detaisCommande.getId().intValue()))
            .andExpect(jsonPath("$.qteProduit").value(DEFAULT_QTE_PRODUIT.doubleValue()))
            .andExpect(jsonPath("$.prix").value(DEFAULT_PRIX.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDetaisCommande() throws Exception {
        // Get the detaisCommande
        restDetaisCommandeMockMvc.perform(get("/api/detais-commandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetaisCommande() throws Exception {
        // Initialize the database
        detaisCommandeRepository.saveAndFlush(detaisCommande);

        int databaseSizeBeforeUpdate = detaisCommandeRepository.findAll().size();

        // Update the detaisCommande
        DetaisCommande updatedDetaisCommande = detaisCommandeRepository.findById(detaisCommande.getId()).get();
        // Disconnect from session so that the updates on updatedDetaisCommande are not directly saved in db
        em.detach(updatedDetaisCommande);
        updatedDetaisCommande
            .qteProduit(UPDATED_QTE_PRODUIT)
            .prix(UPDATED_PRIX);
        DetaisCommandeDTO detaisCommandeDTO = detaisCommandeMapper.toDto(updatedDetaisCommande);

        restDetaisCommandeMockMvc.perform(put("/api/detais-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisCommandeDTO)))
            .andExpect(status().isOk());

        // Validate the DetaisCommande in the database
        List<DetaisCommande> detaisCommandeList = detaisCommandeRepository.findAll();
        assertThat(detaisCommandeList).hasSize(databaseSizeBeforeUpdate);
        DetaisCommande testDetaisCommande = detaisCommandeList.get(detaisCommandeList.size() - 1);
        assertThat(testDetaisCommande.getQteProduit()).isEqualTo(UPDATED_QTE_PRODUIT);
        assertThat(testDetaisCommande.getPrix()).isEqualTo(UPDATED_PRIX);
    }

    @Test
    @Transactional
    public void updateNonExistingDetaisCommande() throws Exception {
        int databaseSizeBeforeUpdate = detaisCommandeRepository.findAll().size();

        // Create the DetaisCommande
        DetaisCommandeDTO detaisCommandeDTO = detaisCommandeMapper.toDto(detaisCommande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetaisCommandeMockMvc.perform(put("/api/detais-commandes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(detaisCommandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DetaisCommande in the database
        List<DetaisCommande> detaisCommandeList = detaisCommandeRepository.findAll();
        assertThat(detaisCommandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetaisCommande() throws Exception {
        // Initialize the database
        detaisCommandeRepository.saveAndFlush(detaisCommande);

        int databaseSizeBeforeDelete = detaisCommandeRepository.findAll().size();

        // Delete the detaisCommande
        restDetaisCommandeMockMvc.perform(delete("/api/detais-commandes/{id}", detaisCommande.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DetaisCommande> detaisCommandeList = detaisCommandeRepository.findAll();
        assertThat(detaisCommandeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
