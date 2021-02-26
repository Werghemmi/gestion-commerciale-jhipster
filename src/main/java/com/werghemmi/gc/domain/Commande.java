package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Commande.
 */
@Entity
@Table(name = "commande")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_commande")
    private Instant dateCommande;

    @OneToOne
    @JoinColumn(unique = true)
    private FactureAchat factureAchat;

    @OneToMany(mappedBy = "commande")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetaisCommande> detaisCommandes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "commandes", allowSetters = true)
    private Fournisseur fournisseur;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCommande() {
        return dateCommande;
    }

    public Commande dateCommande(Instant dateCommande) {
        this.dateCommande = dateCommande;
        return this;
    }

    public void setDateCommande(Instant dateCommande) {
        this.dateCommande = dateCommande;
    }

    public FactureAchat getFactureAchat() {
        return factureAchat;
    }

    public Commande factureAchat(FactureAchat factureAchat) {
        this.factureAchat = factureAchat;
        return this;
    }

    public void setFactureAchat(FactureAchat factureAchat) {
        this.factureAchat = factureAchat;
    }

    public Set<DetaisCommande> getDetaisCommandes() {
        return detaisCommandes;
    }

    public Commande detaisCommandes(Set<DetaisCommande> detaisCommandes) {
        this.detaisCommandes = detaisCommandes;
        return this;
    }

    public Commande addDetaisCommande(DetaisCommande detaisCommande) {
        this.detaisCommandes.add(detaisCommande);
        detaisCommande.setCommande(this);
        return this;
    }

    public Commande removeDetaisCommande(DetaisCommande detaisCommande) {
        this.detaisCommandes.remove(detaisCommande);
        detaisCommande.setCommande(null);
        return this;
    }

    public void setDetaisCommandes(Set<DetaisCommande> detaisCommandes) {
        this.detaisCommandes = detaisCommandes;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public Commande fournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
        return this;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commande)) {
            return false;
        }
        return id != null && id.equals(((Commande) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Commande{" +
            "id=" + getId() +
            ", dateCommande='" + getDateCommande() + "'" +
            "}";
    }
}
