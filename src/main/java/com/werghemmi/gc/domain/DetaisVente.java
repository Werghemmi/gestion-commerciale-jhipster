package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DetaisVente.
 */
@Entity
@Table(name = "detais_vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetaisVente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qte_produit")
    private Float qteProduit;

    @Column(name = "prix")
    private Float prix;

    @ManyToOne
    @JsonIgnoreProperties(value = "detaisVentes", allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = "detaisVentes", allowSetters = true)
    private Vente vente;

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

    public DetaisVente qteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
        return this;
    }

    public void setQteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
    }

    public Float getPrix() {
        return prix;
    }

    public DetaisVente prix(Float prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public DetaisVente produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Vente getVente() {
        return vente;
    }

    public DetaisVente vente(Vente vente) {
        this.vente = vente;
        return this;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetaisVente)) {
            return false;
        }
        return id != null && id.equals(((DetaisVente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetaisVente{" +
            "id=" + getId() +
            ", qteProduit=" + getQteProduit() +
            ", prix=" + getPrix() +
            "}";
    }
}
