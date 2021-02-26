package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.DetaisDevisService;
import com.werghemmi.gc.domain.DetaisDevis;
import com.werghemmi.gc.repository.DetaisDevisRepository;
import com.werghemmi.gc.service.dto.DetaisDevisDTO;
import com.werghemmi.gc.service.mapper.DetaisDevisMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetaisDevis}.
 */
@Service
@Transactional
public class DetaisDevisServiceImpl implements DetaisDevisService {

    private final Logger log = LoggerFactory.getLogger(DetaisDevisServiceImpl.class);

    private final DetaisDevisRepository detaisDevisRepository;

    private final DetaisDevisMapper detaisDevisMapper;

    public DetaisDevisServiceImpl(DetaisDevisRepository detaisDevisRepository, DetaisDevisMapper detaisDevisMapper) {
        this.detaisDevisRepository = detaisDevisRepository;
        this.detaisDevisMapper = detaisDevisMapper;
    }

    @Override
    public DetaisDevisDTO save(DetaisDevisDTO detaisDevisDTO) {
        log.debug("Request to save DetaisDevis : {}", detaisDevisDTO);
        DetaisDevis detaisDevis = detaisDevisMapper.toEntity(detaisDevisDTO);
        detaisDevis = detaisDevisRepository.save(detaisDevis);
        return detaisDevisMapper.toDto(detaisDevis);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DetaisDevisDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DetaisDevis");
        return detaisDevisRepository.findAll(pageable)
            .map(detaisDevisMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DetaisDevisDTO> findOne(Long id) {
        log.debug("Request to get DetaisDevis : {}", id);
        return detaisDevisRepository.findById(id)
            .map(detaisDevisMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetaisDevis : {}", id);
        detaisDevisRepository.deleteById(id);
    }
}
