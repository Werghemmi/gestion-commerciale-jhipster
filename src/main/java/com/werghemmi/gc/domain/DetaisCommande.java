package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A DetaisCommande.
 */
@Entity
@Table(name = "detais_commande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DetaisCommande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qte_produit")
    private Float qteProduit;

    @Column(name = "prix")
    private Float prix;

    @ManyToOne
    @JsonIgnoreProperties(value = "detaisCommandes", allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = "detaisCommandes", allowSetters = true)
    private Commande commande;

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

    public DetaisCommande qteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
        return this;
    }

    public void setQteProduit(Float qteProduit) {
        this.qteProduit = qteProduit;
    }

    public Float getPrix() {
        return prix;
    }

    public DetaisCommande prix(Float prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public DetaisCommande produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public DetaisCommande commande(Commande commande) {
        this.commande = commande;
        return this;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetaisCommande)) {
            return false;
        }
        return id != null && id.equals(((DetaisCommande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DetaisCommande{" +
            "id=" + getId() +
            ", qteProduit=" + getQteProduit() +
            ", prix=" + getPrix() +
            "}";
    }
}
