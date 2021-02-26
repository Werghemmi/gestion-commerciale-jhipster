package com.werghemmi.gc.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.DetaisCommande} entity.
 */
public class DetaisCommandeDTO implements Serializable {
    
    private Long id;

    private Float qteProduit;

    private Float prix;


    private Long produitId;

    private Long commandeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQteProduit() {
        return qteProduit;
    }

    public void setQteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getCommandeId() {
        return commandeId;
    }

    public void setCommandeId(Long commandeId) {
        this.commandeId = commandeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetaisCommandeDTO)) {
            return false;
        }

        return id != null && id.equals(((DetaisCommandeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetaisCommandeDTO{" +
            "id=" + getId() +
            ", qteProduit=" + getQteProduit() +
            ", prix=" + getPrix() +
            ", produitId=" + getProduitId() +
            ", commandeId=" + getCommandeId() +
            "}";
    }
}
