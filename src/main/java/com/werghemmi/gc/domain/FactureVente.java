package com.werghemmi.gc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A FactureVente.
 */
@Entity
@Table(name = "facture_vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FactureVente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_facture")
    private Instant dateFacture;

    @Column(name = "total_ht")
    private Float totalHT;

    @Column(name = "total_tva")
    private Float totalTVA;

    @Column(name = "total_ttc")
    private Float totalTTC;

    @Column(name = "total_remise")
    private Float totalRemise;

    @Column(name = "total_net")
    private Float totalNet;

    @Column(name = "timbre_fiscale")
    private Boolean timbreFiscale;

    @OneToOne
    @JoinColumn(unique = true)
    private ReglementVente reglementVente;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateFacture() {
        return dateFacture;
    }

    public FactureVente dateFacture(Instant dateFacture) {
        this.dateFacture = dateFacture;
        return this;
    }

    public void setDateFacture(Instant dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Float getTotalHT() {
        return totalHT;
    }

    public FactureVente totalHT(Float totalHT) {
        this.totalHT = totalHT;
        return this;
    }

    public void setTotalHT(Float totalHT) {
        this.totalHT = totalHT;
    }

    public Float getTotalTVA() {
        return totalTVA;
    }

    public FactureVente totalTVA(Float totalTVA) {
        this.totalTVA = totalTVA;
        return this;
    }

    public void setTotalTVA(Float totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Float getTotalTTC() {
        return totalTTC;
    }

    public FactureVente totalTTC(Float totalTTC) {
        this.totalTTC = totalTTC;
        return this;
    }

    public void setTotalTTC(Float totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Float getTotalRemise() {
        return totalRemise;
    }

    public FactureVente totalRemise(Float totalRemise) {
        this.totalRemise = totalRemise;
        return this;
    }

    public void setTotalRemise(Float totalRemise) {
        this.totalRemise = totalRemise;
    }

    public Float getTotalNet() {
        return totalNet;
    }

    public FactureVente totalNet(Float totalNet) {
        this.totalNet = totalNet;
        return this;
    }

    public void setTotalNet(Float totalNet) {
        this.totalNet = totalNet;
    }

    public Boolean isTimbreFiscale() {
        return timbreFiscale;
    }

    public FactureVente timbreFiscale(Boolean timbreFiscale) {
        this.timbreFiscale = timbreFiscale;
        return this;
    }

    public void setTimbreFiscale(Boolean timbreFiscale) {
        this.timbreFiscale = timbreFiscale;
    }

    public ReglementVente getReglementVente() {
        return reglementVente;
    }

    public FactureVente reglementVente(ReglementVente reglementVente) {
        this.reglementVente = reglementVente;
        return this;
    }

    public void setReglementVente(ReglementVente reglementVente) {
        this.reglementVente = reglementVente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureVente)) {
            return false;
        }
        return id != null && id.equals(((FactureVente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureVente{" +
            "id=" + getId() +
            ", dateFacture='" + getDateFacture() + "'" +
            ", totalHT=" + getTotalHT() +
            ", totalTVA=" + getTotalTVA() +
            ", totalTTC=" + getTotalTTC() +
            ", totalRemise=" + getTotalRemise() +
            ", totalNet=" + getTotalNet() +
            ", timbreFiscale='" + isTimbreFiscale() + "'" +
            "}";
    }
}
