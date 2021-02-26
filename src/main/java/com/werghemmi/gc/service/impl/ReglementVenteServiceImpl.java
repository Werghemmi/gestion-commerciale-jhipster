package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.ReglementVenteService;
import com.werghemmi.gc.domain.ReglementVente;
import com.werghemmi.gc.repository.ReglementVenteRepository;
import com.werghemmi.gc.service.dto.ReglementVenteDTO;
import com.werghemmi.gc.service.mapper.ReglementVenteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ReglementVente}.
 */
@Service
@Transactional
public class ReglementVenteServiceImpl implements ReglementVenteService {

    private final Logger log = LoggerFactory.getLogger(ReglementVenteServiceImpl.class);

    private final ReglementVenteRepository reglementVenteRepository;

    private final ReglementVenteMapper reglementVenteMapper;

    public ReglementVenteServiceImpl(ReglementVenteRepository reglementVenteRepository, ReglementVenteMapper reglementVenteMapper) {
        this.reglementVenteRepository = reglementVenteRepository;
        this.reglementVenteMapper = reglementVenteMapper;
    }

    @Override
    public ReglementVenteDTO save(ReglementVenteDTO reglementVenteDTO) {
        log.debug("Request to save ReglementVente : {}", reglementVenteDTO);
        ReglementVente reglementVente = reglementVenteMapper.toEntity(reglementVenteDTO);
        reglementVente = reglementVenteRepository.save(reglementVente);
        return reglementVenteMapper.toDto(reglementVente);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReglementVenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReglementVentes");
        return reglementVenteRepository.findAll(pageable)
            .map(reglementVenteMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReglementVenteDTO> findOne(Long id) {
        log.debug("Request to get ReglementVente : {}", id);
        return reglementVenteRepository.findById(id)
            .map(reglementVenteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReglementVente : {}", id);
        reglementVenteRepository.deleteById(id);
    }
}
