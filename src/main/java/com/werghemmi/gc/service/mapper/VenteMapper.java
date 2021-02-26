package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.VenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vente} and its DTO {@link VenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {FactureVenteMapper.class, ClientMapper.class})
public interface VenteMapper extends EntityMapper<VenteDTO, Vente> {

    @Mapping(source = "factureVente.id", target = "factureVenteId")
    @Mapping(source = "client.id", target = "clientId")
    VenteDTO toDto(Vente vente);

    @Mapping(source = "factureVenteId", target = "factureVente")
    @Mapping(target = "detaisVentes", ignore = true)
    @Mapping(target = "removeDetaisVente", ignore = true)
    @Mapping(source = "clientId", target = "client")
    Vente toEntity(VenteDTO venteDTO);

    default Vente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vente vente = new Vente();
        vente.setId(id);
        return vente;
    }
}
