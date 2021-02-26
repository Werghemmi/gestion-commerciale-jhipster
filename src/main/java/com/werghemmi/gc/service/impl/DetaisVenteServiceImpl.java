package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.DetaisVenteService;
import com.werghemmi.gc.domain.DetaisVente;
import com.werghemmi.gc.repository.DetaisVenteRepository;
import com.werghemmi.gc.service.dto.DetaisVenteDTO;
import com.werghemmi.gc.service.mapper.DetaisVenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetaisVente}.
 */
@Service
@Transactional
public class DetaisVenteServiceImpl implements DetaisVenteService {

    private final Logger log = LoggerFactory.getLogger(DetaisVenteServiceImpl.class);

    private final DetaisVenteRepository detaisVenteRepository;

    private final DetaisVenteMapper detaisVenteMapper;

    public DetaisVenteServiceImpl(DetaisVenteRepository detaisVenteRepository, DetaisVenteMapper detaisVenteMapper) {
        this.detaisVenteRepository = detaisVenteRepository;
        this.detaisVenteMapper = detaisVenteMapper;
    }

    @Override
    public DetaisVenteDTO save(DetaisVenteDTO detaisVenteDTO) {
        log.debug("Request to save DetaisVente : {}", detaisVenteDTO);
        DetaisVente detaisVente = detaisVenteMapper.toEntity(detaisVenteDTO);
        detaisVente = detaisVenteRepository.save(detaisVente);
        return detaisVenteMapper.toDto(detaisVente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetaisVenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetaisVentes");
        return detaisVenteRepository.findAll(pageable)
            .map(detaisVenteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DetaisVenteDTO> findOne(Long id) {
        log.debug("Request to get DetaisVente : {}", id);
        return detaisVenteRepository.findById(id)
            .map(detaisVenteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetaisVente : {}", id);
        detaisVenteRepository.deleteById(id);
    }
}
