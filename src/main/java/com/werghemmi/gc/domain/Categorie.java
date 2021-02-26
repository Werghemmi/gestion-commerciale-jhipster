package com.werghemmi.gc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titre")
    private String titre;

    @OneToMany(mappedBy = "categorie")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "categorie_taxe",
               joinColumns = @JoinColumn(name = "categorie_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "taxe_id", referencedColumnName = "id"))
    private Set<Taxe> taxes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Categorie titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Categorie produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Categorie addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setCategorie(this);
        return this;
    }

    public Categorie removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setCategorie(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }

    public Set<Taxe> getTaxes() {
        return taxes;
    }

    public Categorie taxes(Set<Taxe> taxes) {
        this.taxes = taxes;
        return this;
    }

    public Categorie addTaxe(Taxe taxe) {
        this.taxes.add(taxe);
        taxe.getCategories().add(this);
        return this;
    }

    public Categorie removeTaxe(Taxe taxe) {
        this.taxes.remove(taxe);
        taxe.getCategories().remove(this);
        return this;
    }

    public void setTaxes(Set<Taxe> taxes) {
        this.taxes = taxes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categorie)) {
            return false;
        }
        return id != null && id.equals(((Categorie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            "}";
    }
}
