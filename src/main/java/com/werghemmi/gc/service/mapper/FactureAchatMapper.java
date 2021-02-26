package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.FactureAchatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FactureAchat} and its DTO {@link FactureAchatDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReglementAchatMapper.class})
public interface FactureAchatMapper extends EntityMapper<FactureAchatDTO, FactureAchat> {

    @Mapping(source = "reglementAchat.id", target = "reglementAchatId")
    FactureAchatDTO toDto(FactureAchat factureAchat);

    @Mapping(source = "reglementAchatId", target = "reglementAchat")
    FactureAchat toEntity(FactureAchatDTO factureAchatDTO);

    default FactureAchat fromId(Long id) {
        if (id == null) {
            return null;
        }
        FactureAchat factureAchat = new FactureAchat();
        factureAchat.setId(id);
        return factureAchat;
    }
}
