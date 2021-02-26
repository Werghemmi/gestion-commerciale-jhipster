package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.ReglementAchatService;
import com.werghemmi.gc.domain.ReglementAchat;
import com.werghemmi.gc.repository.ReglementAchatRepository;
import com.werghemmi.gc.service.dto.ReglementAchatDTO;
import com.werghemmi.gc.service.mapper.ReglementAchatMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ReglementAchat}.
 */
@Service
@Transactional
public class ReglementAchatServiceImpl implements ReglementAchatService {

    private final Logger log = LoggerFactory.getLogger(ReglementAchatServiceImpl.class);

    private final ReglementAchatRepository reglementAchatRepository;

    private final ReglementAchatMapper reglementAchatMapper;

    public ReglementAchatServiceImpl(ReglementAchatRepository reglementAchatRepository, ReglementAchatMapper reglementAchatMapper) {
        this.reglementAchatRepository = reglementAchatRepository;
        this.reglementAchatMapper = reglementAchatMapper;
    }

    @Override
    public ReglementAchatDTO save(ReglementAchatDTO reglementAchatDTO) {
        log.debug("Request to save ReglementAchat : {}", reglementAchatDTO);
        ReglementAchat reglementAchat = reglementAchatMapper.toEntity(reglementAchatDTO);
        reglementAchat = reglementAchatRepository.save(reglementAchat);
        return reglementAchatMapper.toDto(reglementAchat);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ReglementAchatDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReglementAchats");
        return reglementAchatRepository.findAll(pageable)
            .map(reglementAchatMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ReglementAchatDTO> findOne(Long id) {
        log.debug("Request to get ReglementAchat : {}", id);
        return reglementAchatRepository.findById(id)
            .map(reglementAchatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReglementAchat : {}", id);
        reglementAchatRepository.deleteById(id);
    }
}
