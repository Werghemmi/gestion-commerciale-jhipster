package com.werghemmi.gc.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.FactureAchat} entity.
 */
public class FactureAchatDTO implements Serializable {
    
    private Long id;

    private Instant dateFacture;

    private Float totalHT;

    private Float totalTVA;

    private Float totalTTC;

    private Float totalRemise;

    private Float totalNet;

    private Boolean timbreFiscale;


    private Long reglementAchatId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Instant dateFacture) {
        this.dateFacture = dateFacture;
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

    public Float getTotalRemise() {
        return totalRemise;
    }

    public void setTotalRemise(Float totalRemise) {
        this.totalRemise = totalRemise;
    }

    public Float getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(Float totalNet) {
        this.totalNet = totalNet;
    }

    public Boolean isTimbreFiscale() {
        return timbreFiscale;
    }

    public void setTimbreFiscale(Boolean timbreFiscale) {
        this.timbreFiscale = timbreFiscale;
    }

    public Long getReglementAchatId() {
        return reglementAchatId;
    }

    public void setReglementAchatId(Long reglementAchatId) {
        this.reglementAchatId = reglementAchatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureAchatDTO)) {
            return false;
        }

        return id != null && id.equals(((FactureAchatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureAchatDTO{" +
            "id=" + getId() +
            ", dateFacture='" + getDateFacture() + "'" +
            ", totalHT=" + getTotalHT() +
            ", totalTVA=" + getTotalTVA() +
            ", totalTTC=" + getTotalTTC() +
            ", totalRemise=" + getTotalRemise() +
            ", totalNet=" + getTotalNet() +
            ", timbreFiscale='" + isTimbreFiscale() + "'" +
            ", reglementAchatId=" + getReglementAchatId() +
            "}";
    }
}
