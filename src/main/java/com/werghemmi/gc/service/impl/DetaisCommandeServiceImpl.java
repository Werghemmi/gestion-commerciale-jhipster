package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.DetaisCommandeService;
import com.werghemmi.gc.domain.DetaisCommande;
import com.werghemmi.gc.repository.DetaisCommandeRepository;
import com.werghemmi.gc.service.dto.DetaisCommandeDTO;
import com.werghemmi.gc.service.mapper.DetaisCommandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetaisCommande}.
 */
@Service
@Transactional
public class DetaisCommandeServiceImpl implements DetaisCommandeService {

    private final Logger log = LoggerFactory.getLogger(DetaisCommandeServiceImpl.class);

    private final DetaisCommandeRepository detaisCommandeRepository;

    private final DetaisCommandeMapper detaisCommandeMapper;

    public DetaisCommandeServiceImpl(DetaisCommandeRepository detaisCommandeRepository, DetaisCommandeMapper detaisCommandeMapper) {
        this.detaisCommandeRepository = detaisCommandeRepository;
        this.detaisCommandeMapper = detaisCommandeMapper;
    }

    @Override
    public DetaisCommandeDTO save(DetaisCommandeDTO detaisCommandeDTO) {
        log.debug("Request to save DetaisCommande : {}", detaisCommandeDTO);
        DetaisCommande detaisCommande = detaisCommandeMapper.toEntity(detaisCommandeDTO);
        detaisCommande = detaisCommandeRepository.save(detaisCommande);
        return detaisCommandeMapper.toDto(detaisCommande);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetaisCommandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetaisCommandes");
        return detaisCommandeRepository.findAll(pageable)
            .map(detaisCommandeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DetaisCommandeDTO> findOne(Long id) {
        log.debug("Request to get DetaisCommande : {}", id);
        return detaisCommandeRepository.findById(id)
            .map(detaisCommandeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetaisCommande : {}", id);
        detaisCommandeRepository.deleteById(id);
    }
}
