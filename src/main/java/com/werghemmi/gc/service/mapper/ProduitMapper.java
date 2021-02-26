package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategorieMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "categorie.id", target = "categorieId")
    ProduitDTO toDto(Produit produit);

    @Mapping(target = "mouvements", ignore = true)
    @Mapping(target = "removeMouvement", ignore = true)
    @Mapping(target = "detaisDevis", ignore = true)
    @Mapping(target = "removeDetaisDevis", ignore = true)
    @Mapping(target = "detaisCommandes", ignore = true)
    @Mapping(target = "removeDetaisCommande", ignore = true)
    @Mapping(target = "detaisVentes", ignore = true)
    @Mapping(target = "removeDetaisVente", ignore = true)
    @Mapping(source = "categorieId", target = "categorie")
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
