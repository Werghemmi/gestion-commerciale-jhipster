package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.werghemmi.gc.domain.enumeration.Type;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "description")
    private String description;

    @Column(name = "prix_achat")
    private Float prixAchat;

    @Column(name = "prix_vente")
    private Float prixVente;

    @Column(name = "qte_stock")
    private Float qteStock;

    @Column(name = "marge")
    private Float marge;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_marge")
    private Type typeMarge;

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Mouvement> mouvements = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetaisDevis> detaisDevis = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetaisCommande> detaisCommandes = new HashSet<>();

    @OneToMany(mappedBy = "produit")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetaisVente> detaisVentes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private Categorie categorie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Produit nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public Produit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrixAchat() {
        return prixAchat;
    }

    public Produit prixAchat(Float prixAchat) {
        this.prixAchat = prixAchat;
        return this;
    }

    public void setPrixAchat(Float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Float getPrixVente() {
        return prixVente;
    }

    public Produit prixVente(Float prixVente) {
        this.prixVente = prixVente;
        return this;
    }

    public void setPrixVente(Float prixVente) {
        this.prixVente = prixVente;
    }

    public Float getQteStock() {
        return qteStock;
    }

    public Produit qteStock(Float qteStock) {
        this.qteStock = qteStock;
        return this;
    }

    public void setQteStock(Float qteStock) {
        this.qteStock = qteStock;
    }

    public Float getMarge() {
        return marge;
    }

    public Produit marge(Float marge) {
        this.marge = marge;
        return this;
    }

    public void setMarge(Float marge) {
        this.marge = marge;
    }

    public Type getTypeMarge() {
        return typeMarge;
    }

    public Produit typeMarge(Type typeMarge) {
        this.typeMarge = typeMarge;
        return this;
    }

    public void setTypeMarge(Type typeMarge) {
        this.typeMarge = typeMarge;
    }

    public Set<Mouvement> getMouvements() {
        return mouvements;
    }

    public Produit mouvements(Set<Mouvement> mouvements) {
        this.mouvements = mouvements;
        return this;
    }

    public Produit addMouvement(Mouvement mouvement) {
        this.mouvements.add(mouvement);
        mouvement.setProduit(this);
        return this;
    }

    public Produit removeMouvement(Mouvement mouvement) {
        this.mouvements.remove(mouvement);
        mouvement.setProduit(null);
        return this;
    }

    public void setMouvements(Set<Mouvement> mouvements) {
        this.mouvements = mouvements;
    }

    public Set<DetaisDevis> getDetaisDevis() {
        return detaisDevis;
    }

    public Produit detaisDevis(Set<DetaisDevis> detaisDevis) {
        this.detaisDevis = detaisDevis;
        return this;
    }

    public Produit addDetaisDevis(DetaisDevis detaisDevis) {
        this.detaisDevis.add(detaisDevis);
        detaisDevis.setProduit(this);
        return this;
    }

    public Produit removeDetaisDevis(DetaisDevis detaisDevis) {
        this.detaisDevis.remove(detaisDevis);
        detaisDevis.setProduit(null);
        return this;
    }

    public void setDetaisDevis(Set<DetaisDevis> detaisDevis) {
        this.detaisDevis = detaisDevis;
    }

    public Set<DetaisCommande> getDetaisCommandes() {
        return detaisCommandes;
    }

    public Produit detaisCommandes(Set<DetaisCommande> detaisCommandes) {
        this.detaisCommandes = detaisCommandes;
        return this;
    }

    public Produit addDetaisCommande(DetaisCommande detaisCommande) {
        this.detaisCommandes.add(detaisCommande);
        detaisCommande.setProduit(this);
        return this;
    }

    public Produit removeDetaisCommande(DetaisCommande detaisCommande) {
        this.detaisCommandes.remove(detaisCommande);
        detaisCommande.setProduit(null);
        return this;
    }

    public void setDetaisCommandes(Set<DetaisCommande> detaisCommandes) {
        this.detaisCommandes = detaisCommandes;
    }

    public Set<DetaisVente> getDetaisVentes() {
        return detaisVentes;
    }

    public Produit detaisVentes(Set<DetaisVente> detaisVentes) {
        this.detaisVentes = detaisVentes;
        return this;
    }

    public Produit addDetaisVente(DetaisVente detaisVente) {
        this.detaisVentes.add(detaisVente);
        detaisVente.setProduit(this);
        return this;
    }

    public Produit removeDetaisVente(DetaisVente detaisVente) {
        this.detaisVentes.remove(detaisVente);
        detaisVente.setProduit(null);
        return this;
    }

    public void setDetaisVentes(Set<DetaisVente> detaisVentes) {
        this.detaisVentes = detaisVentes;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Produit categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", prixAchat=" + getPrixAchat() +
            ", prixVente=" + getPrixVente() +
            ", qteStock=" + getQteStock() +
            ", marge=" + getMarge() +
            ", typeMarge='" + getTypeMarge() + "'" +
            "}";
    }
}
