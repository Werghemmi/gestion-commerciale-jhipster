package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.DetaisDevisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetaisDevis} and its DTO {@link DetaisDevisDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, DevisMapper.class})
public interface DetaisDevisMapper extends EntityMapper<DetaisDevisDTO, DetaisDevis> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "devis.id", target = "devisId")
    DetaisDevisDTO toDto(DetaisDevis detaisDevis);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "devisId", target = "devis")
    DetaisDevis toEntity(DetaisDevisDTO detaisDevisDTO);

    default DetaisDevis fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetaisDevis detaisDevis = new DetaisDevis();
        detaisDevis.setId(id);
        return detaisDevis;
    }
}
