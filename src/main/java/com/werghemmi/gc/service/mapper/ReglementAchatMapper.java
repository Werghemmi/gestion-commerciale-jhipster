package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.ReglementAchatDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReglementAchat} and its DTO {@link ReglementAchatDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReglementAchatMapper extends EntityMapper<ReglementAchatDTO, ReglementAchat> {



    default ReglementAchat fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReglementAchat reglementAchat = new ReglementAchat();
        reglementAchat.setId(id);
        return reglementAchat;
    }
}
