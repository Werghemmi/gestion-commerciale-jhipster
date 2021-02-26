package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.DetaisVenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetaisVente} and its DTO {@link DetaisVenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, VenteMapper.class})
public interface DetaisVenteMapper extends EntityMapper<DetaisVenteDTO, DetaisVente> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "vente.id", target = "venteId")
    DetaisVenteDTO toDto(DetaisVente detaisVente);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "venteId", target = "vente")
    DetaisVente toEntity(DetaisVenteDTO detaisVenteDTO);

    default DetaisVente fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetaisVente detaisVente = new DetaisVente();
        detaisVente.setId(id);
        return detaisVente;
    }
}
