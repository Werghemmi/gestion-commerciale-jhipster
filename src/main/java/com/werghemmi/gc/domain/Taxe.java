package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.werghemmi.gc.domain.enumeration.Type;

/**
 * A Taxe.
 */
@Entity
@Table(name = "taxe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Taxe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_taxe")
    private String nomTaxe;

    @Column(name = "taux")
    private Float taux;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_taxe")
    private Type typeTaxe;

    @ManyToMany(mappedBy = "taxes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Categorie> categories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTaxe() {
        return nomTaxe;
    }

    public Taxe nomTaxe(String nomTaxe) {
        this.nomTaxe = nomTaxe;
        return this;
    }

    public void setNomTaxe(String nomTaxe) {
        this.nomTaxe = nomTaxe;
    }

    public Float getTaux() {
        return taux;
    }

    public Taxe taux(Float taux) {
        this.taux = taux;
        return this;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    public Type getTypeTaxe() {
        return typeTaxe;
    }

    public Taxe typeTaxe(Type typeTaxe) {
        this.typeTaxe = typeTaxe;
        return this;
    }

    public void setTypeTaxe(Type typeTaxe) {
        this.typeTaxe = typeTaxe;
    }

    public Set<Categorie> getCategories() {
        return categories;
    }

    public Taxe categories(Set<Categorie> categories) {
        this.categories = categories;
        return this;
    }

    public Taxe addCategorie(Categorie categorie) {
        this.categories.add(categorie);
        categorie.getTaxes().add(this);
        return this;
    }

    public Taxe removeCategorie(Categorie categorie) {
        this.categories.remove(categorie);
        categorie.getTaxes().remove(this);
        return this;
    }

    public void setCategories(Set<Categorie> categories) {
        this.categories = categories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Taxe)) {
            return false;
        }
        return id != null && id.equals(((Taxe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Taxe{" +
            "id=" + getId() +
            ", nomTaxe='" + getNomTaxe() + "'" +
            ", taux=" + getTaux() +
            ", typeTaxe='" + getTypeTaxe() + "'" +
            "}";
    }
}
