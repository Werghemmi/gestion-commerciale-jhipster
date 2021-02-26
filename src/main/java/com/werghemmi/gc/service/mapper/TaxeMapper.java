package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.TaxeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Taxe} and its DTO {@link TaxeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TaxeMapper extends EntityMapper<TaxeDTO, Taxe> {


    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "removeCategorie", ignore = true)
    Taxe toEntity(TaxeDTO taxeDTO);

    default Taxe fromId(Long id) {
        if (id == null) {
            return null;
        }
        Taxe taxe = new Taxe();
        taxe.setId(id);
        return taxe;
    }
}
