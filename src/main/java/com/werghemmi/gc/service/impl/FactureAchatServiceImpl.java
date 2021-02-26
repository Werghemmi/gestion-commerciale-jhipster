package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.FactureAchatService;
import com.werghemmi.gc.domain.FactureAchat;
import com.werghemmi.gc.repository.FactureAchatRepository;
import com.werghemmi.gc.service.dto.FactureAchatDTO;
import com.werghemmi.gc.service.mapper.FactureAchatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FactureAchat}.
 */
@Service
@Transactional
public class FactureAchatServiceImpl implements FactureAchatService {

    private final Logger log = LoggerFactory.getLogger(FactureAchatServiceImpl.class);

    private final FactureAchatRepository factureAchatRepository;

    private final FactureAchatMapper factureAchatMapper;

    public FactureAchatServiceImpl(FactureAchatRepository factureAchatRepository, FactureAchatMapper factureAchatMapper) {
        this.factureAchatRepository = factureAchatRepository;
        this.factureAchatMapper = factureAchatMapper;
    }

    @Override
    public FactureAchatDTO save(FactureAchatDTO factureAchatDTO) {
        log.debug("Request to save FactureAchat : {}", factureAchatDTO);
        FactureAchat factureAchat = factureAchatMapper.toEntity(factureAchatDTO);
        factureAchat = factureAchatRepository.save(factureAchat);
        return factureAchatMapper.toDto(factureAchat);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FactureAchatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FactureAchats");
        return factureAchatRepository.findAll(pageable)
            .map(factureAchatMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FactureAchatDTO> findOne(Long id) {
        log.debug("Request to get FactureAchat : {}", id);
        return factureAchatRepository.findById(id)
            .map(factureAchatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FactureAchat : {}", id);
        factureAchatRepository.deleteById(id);
    }
}
