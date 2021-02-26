package com.werghemmi.gc.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Commande} entity.
 */
public class CommandeDTO implements Serializable {
    
    private Long id;

    private Instant dateCommande;


    private Long factureAchatId;

    private Long fournisseurId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Instant dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Long getFactureAchatId() {
        return factureAchatId;
    }

    public void setFactureAchatId(Long factureAchatId) {
        this.factureAchatId = factureAchatId;
    }

    public Long getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Long fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((CommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommandeDTO{" +
            "id=" + getId() +
            ", dateCommande='" + getDateCommande() + "'" +
            ", factureAchatId=" + getFactureAchatId() +
            ", fournisseurId=" + getFournisseurId() +
            "}";
    }
}
