package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.MouvementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Mouvement} and its DTO {@link MouvementDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class})
public interface MouvementMapper extends EntityMapper<MouvementDTO, Mouvement> {

    @Mapping(source = "produit.id", target = "produitId")
    MouvementDTO toDto(Mouvement mouvement);

    @Mapping(source = "produitId", target = "produit")
    Mouvement toEntity(MouvementDTO mouvementDTO);

    default Mouvement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Mouvement mouvement = new Mouvement();
        mouvement.setId(id);
        return mouvement;
    }
}
