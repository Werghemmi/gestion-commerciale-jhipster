package com.werghemmi.gc.service.mapper;


import com.werghemmi.gc.domain.*;
import com.werghemmi.gc.service.dto.CommandeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Commande} and its DTO {@link CommandeDTO}.
 */
@Mapper(componentModel = "spring", uses = {FactureAchatMapper.class, FournisseurMapper.class})
public interface CommandeMapper extends EntityMapper<CommandeDTO, Commande> {

    @Mapping(source = "factureAchat.id", target = "factureAchatId")
    @Mapping(source = "fournisseur.id", target = "fournisseurId")
    CommandeDTO toDto(Commande commande);

    @Mapping(source = "factureAchatId", target = "factureAchat")
    @Mapping(target = "detaisCommandes", ignore = true)
    @Mapping(target = "removeDetaisCommande", ignore = true)
    @Mapping(source = "fournisseurId", target = "fournisseur")
    Commande toEntity(CommandeDTO commandeDTO);

    default Commande fromId(Long id) {
        if (id == null) {
            return null;
        }
        Commande commande = new Commande();
        commande.setId(id);
        return commande;
    }
}
