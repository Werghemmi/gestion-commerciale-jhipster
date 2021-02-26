package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.ReglementVenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ReglementVente} and its DTO {@link ReglementVenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReglementVenteMapper extends EntityMapper<ReglementVenteDTO, ReglementVente> {



    default ReglementVente fromId(Long id) {
        if (id == null) {
            return null;
        }
        ReglementVente reglementVente = new ReglementVente();
        reglementVente.setId(id);
        return reglementVente;
    }
}
