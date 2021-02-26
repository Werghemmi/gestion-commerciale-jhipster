package com.werghemmi.gc.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.DetaisDevis} entity.
 */
public class DetaisDevisDTO implements Serializable {
    
    private Long id;

    private Float qteProduit;

    private Float totalHT;

    private Float totalTVA;

    private Float totalTTC;


    private Long produitId;

    private Long devisId;
    
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

    public Float getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(Float totalHT) {
        this.totalHT = totalHT;
    }

    public Float getTotalTVA() {
        return totalTVA;
    }

    public void setTotalTVA(Float totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Float getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(Float totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getDevisId() {
        return devisId;
    }

    public void setDevisId(Long devisId) {
        this.devisId = devisId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetaisDevisDTO)) {
            return false;
        }

        return id != null && id.equals(((DetaisDevisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetaisDevisDTO{" +
            "id=" + getId() +
            ", qteProduit=" + getQteProduit() +
            ", totalHT=" + getTotalHT() +
            ", totalTVA=" + getTotalTVA() +
            ", totalTTC=" + getTotalTTC() +
            ", produitId=" + getProduitId() +
            ", devisId=" + getDevisId() +
            "}";
    }
}
