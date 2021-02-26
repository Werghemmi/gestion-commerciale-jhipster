package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.MouvementService;
import com.werghemmi.gc.domain.Mouvement;
import com.werghemmi.gc.repository.MouvementRepository;
import com.werghemmi.gc.service.dto.MouvementDTO;
import com.werghemmi.gc.service.mapper.MouvementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Mouvement}.
 */
@Service
@Transactional
public class MouvementServiceImpl implements MouvementService {

    private final Logger log = LoggerFactory.getLogger(MouvementServiceImpl.class);

    private final MouvementRepository mouvementRepository;

    private final MouvementMapper mouvementMapper;

    public MouvementServiceImpl(MouvementRepository mouvementRepository, MouvementMapper mouvementMapper) {
        this.mouvementRepository = mouvementRepository;
        this.mouvementMapper = mouvementMapper;
    }

    @Override
    public MouvementDTO save(MouvementDTO mouvementDTO) {
        log.debug("Request to save Mouvement : {}", mouvementDTO);
        Mouvement mouvement = mouvementMapper.toEntity(mouvementDTO);
        mouvement = mouvementRepository.save(mouvement);
        return mouvementMapper.toDto(mouvement);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MouvementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Mouvements");
        return mouvementRepository.findAll(pageable)
            .map(mouvementMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MouvementDTO> findOne(Long id) {
        log.debug("Request to get Mouvement : {}", id);
        return mouvementRepository.findById(id)
            .map(mouvementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mouvement : {}", id);
        mouvementRepository.deleteById(id);
    }
}
