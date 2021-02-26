package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.FactureVenteService;
import com.werghemmi.gc.domain.FactureVente;
import com.werghemmi.gc.repository.FactureVenteRepository;
import com.werghemmi.gc.service.dto.FactureVenteDTO;
import com.werghemmi.gc.service.mapper.FactureVenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FactureVente}.
 */
@Service
@Transactional
public class FactureVenteServiceImpl implements FactureVenteService {

    private final Logger log = LoggerFactory.getLogger(FactureVenteServiceImpl.class);

    private final FactureVenteRepository factureVenteRepository;

    private final FactureVenteMapper factureVenteMapper;

    public FactureVenteServiceImpl(FactureVenteRepository factureVenteRepository, FactureVenteMapper factureVenteMapper) {
        this.factureVenteRepository = factureVenteRepository;
        this.factureVenteMapper = factureVenteMapper;
    }

    @Override
    public FactureVenteDTO save(FactureVenteDTO factureVenteDTO) {
        log.debug("Request to save FactureVente : {}", factureVenteDTO);
        FactureVente factureVente = factureVenteMapper.toEntity(factureVenteDTO);
        factureVente = factureVenteRepository.save(factureVente);
        return factureVenteMapper.toDto(factureVente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FactureVenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FactureVentes");
        return factureVenteRepository.findAll(pageable)
            .map(factureVenteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactureVenteDTO> findOne(Long id) {
        log.debug("Request to get FactureVente : {}", id);
        return factureVenteRepository.findById(id)
            .map(factureVenteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FactureVente : {}", id);
        factureVenteRepository.deleteById(id);
    }
}
