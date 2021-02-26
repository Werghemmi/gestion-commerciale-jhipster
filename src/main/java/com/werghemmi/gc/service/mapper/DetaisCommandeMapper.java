package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.DetaisCommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DetaisCommande} and its DTO {@link DetaisCommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, CommandeMapper.class})
public interface DetaisCommandeMapper extends EntityMapper<DetaisCommandeDTO, DetaisCommande> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "commande.id", target = "commandeId")
    DetaisCommandeDTO toDto(DetaisCommande detaisCommande);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "commandeId", target = "commande")
    DetaisCommande toEntity(DetaisCommandeDTO detaisCommandeDTO);

    default DetaisCommande fromId(Long id) {
        if (id == null) {
            return null;
        }
        DetaisCommande detaisCommande = new DetaisCommande();
        detaisCommande.setId(id);
        return detaisCommande;
    }
}
