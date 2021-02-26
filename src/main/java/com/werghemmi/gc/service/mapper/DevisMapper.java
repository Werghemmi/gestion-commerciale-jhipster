package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.DevisDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Devis} and its DTO {@link DevisDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DevisMapper extends EntityMapper<DevisDTO, Devis> {


    @Mapping(target = "detaisDevis", ignore = true)
    @Mapping(target = "removeDetaisDevis", ignore = true)
    Devis toEntity(DevisDTO devisDTO);

    default Devis fromId(Long id) {
        if (id == null) {
            return null;
        }
        Devis devis = new Devis();
        devis.setId(id);
        return devis;
    }
}
