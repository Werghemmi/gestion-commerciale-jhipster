package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.FactureVenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link FactureVente} and its DTO {@link FactureVenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReglementVenteMapper.class})
public interface FactureVenteMapper extends EntityMapper<FactureVenteDTO, FactureVente> {

    @Mapping(source = "reglementVente.id", target = "reglementVenteId")
    FactureVenteDTO toDto(FactureVente factureVente);

    @Mapping(source = "reglementVenteId", target = "reglementVente")
    FactureVente toEntity(FactureVenteDTO factureVenteDTO);

    default FactureVente fromId(Long id) {
        if (id == null) {
            return null;
        }
        FactureVente factureVente = new FactureVente();
        factureVente.setId(id);
        return factureVente;
    }
}
