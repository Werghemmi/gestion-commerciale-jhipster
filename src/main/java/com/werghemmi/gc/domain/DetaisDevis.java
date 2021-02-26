package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DetaisDevis.
 */
@Entity
@Table(name = "detais_devis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetaisDevis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qte_produit")
    private Float qteProduit;

    @Column(name = "total_ht")
    private Float totalHT;

    @Column(name = "total_tva")
    private Float totalTVA;

    @Column(name = "total_ttc")
    private Float totalTTC;

    @ManyToOne
    @JsonIgnoreProperties(value = "detaisDevis", allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = "detaisDevis", allowSetters = true)
    private Devis devis;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQteProduit() {
        return qteProduit;
    }

    public DetaisDevis qteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
        return this;
    }

    public void setQteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
    }

    public Float getTotalHT() {
        return totalHT;
    }

    public DetaisDevis totalHT(Float totalHT) {
        this.totalHT = totalHT;
        return this;
    }

    public void setTotalHT(Float totalHT) {
        this.totalHT = totalHT;
    }

    public Float getTotalTVA() {
        return totalTVA;
    }

    public DetaisDevis totalTVA(Float totalTVA) {
        this.totalTVA = totalTVA;
        return this;
    }

    public void setTotalTVA(Float totalTVA) {
        this.totalTVA = totalTVA;
    }

    public Float getTotalTTC() {
        return totalTTC;
    }

    public DetaisDevis totalTTC(Float totalTTC) {
        this.totalTTC = totalTTC;
        return this;
    }

    public void setTotalTTC(Float totalTTC) {
        this.totalTTC = totalTTC;
    }

    public Produit getProduit() {
        return produit;
    }

    public DetaisDevis produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Devis getDevis() {
        return devis;
    }

    public DetaisDevis devis(Devis devis) {
        this.devis = devis;
        return this;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetaisDevis)) {
            return false;
        }
        return id != null && id.equals(((DetaisDevis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetaisDevis{" +
            "id=" + getId() +
            ", qteProduit=" + getQteProduit() +
            ", totalHT=" + getTotalHT() +
            ", totalTVA=" + getTotalTVA() +
            ", totalTTC=" + getTotalTTC() +
            "}";
    }
}
