package com.werghemmi.gc.service.impl;

import com.werghemmi.gc.service.TaxeService;
import com.werghemmi.gc.domain.Taxe;
import com.werghemmi.gc.repository.TaxeRepository;
import com.werghemmi.gc.service.dto.TaxeDTO;
import com.werghemmi.gc.service.mapper.TaxeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Taxe}.
 */
@Service
@Transactional
public class TaxeServiceImpl implements TaxeService {

    private final Logger log = LoggerFactory.getLogger(TaxeServiceImpl.class);

    private final TaxeRepository taxeRepository;

    private final TaxeMapper taxeMapper;

    public TaxeServiceImpl(TaxeRepository taxeRepository, TaxeMapper taxeMapper) {
        this.taxeRepository = taxeRepository;
        this.taxeMapper = taxeMapper;
    }

    @Override
    public TaxeDTO save(TaxeDTO taxeDTO) {
        log.debug("Request to save Taxe : {}", taxeDTO);
        Taxe taxe = taxeMapper.toEntity(taxeDTO);
        taxe = taxeRepository.save(taxe);
        return taxeMapper.toDto(taxe);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Taxes");
        return taxeRepository.findAll(pageable)
            .map(taxeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TaxeDTO> findOne(Long id) {
        log.debug("Request to get Taxe : {}", id);
        return taxeRepository.findById(id)
            .map(taxeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Taxe : {}", id);
        taxeRepository.deleteById(id);
    }
}
