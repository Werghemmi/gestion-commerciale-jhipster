package com.werghemmi.gc.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Vente} entity.
 */
public class VenteDTO implements Serializable {
    
    private Long id;

    private Instant dateCommande;


    private Long factureVenteId;

    private Long clientId;
    
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

    public Long getFactureVenteId() {
        return factureVenteId;
    }

    public void setFactureVenteId(Long factureVenteId) {
        this.factureVenteId = factureVenteId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenteDTO)) {
            return false;
        }

        return id != null && id.equals(((VenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenteDTO{" +
            "id=" + getId() +
            ", dateCommande='" + getDateCommande() + "'" +
            ", factureVenteId=" + getFactureVenteId() +
            ", clientId=" + getClientId() +
            "}";
    }
}
