package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.werghemmi.gc.domain.enumeration.TypeMouvement;

/**
 * A Mouvement.
 */
@Entity
@Table(name = "mouvement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Mouvement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_mvt")
    private Instant dateMvt;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_mvt")
    private TypeMouvement typeMvt;

    @Column(name = "qte_mvt")
    private Float qteMvt;

    @Column(name = "ancien_qte")
    private Float ancienQte;

    @Column(name = "nv_qte")
    private Float nvQte;

    @Column(name = "ancien_prix")
    private Float ancienPrix;

    @Column(name = "nv_prix")
    private Float nvPrix;

    @Column(name = "prix")
    private Float prix;

    @ManyToOne
    @JsonIgnoreProperties(value = "mouvements", allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateMvt() {
        return dateMvt;
    }

    public Mouvement dateMvt(Instant dateMvt) {
        this.dateMvt = dateMvt;
        return this;
    }

    public void setDateMvt(Instant dateMvt) {
        this.dateMvt = dateMvt;
    }

    public TypeMouvement getTypeMvt() {
        return typeMvt;
    }

    public Mouvement typeMvt(TypeMouvement typeMvt) {
        this.typeMvt = typeMvt;
        return this;
    }

    public void setTypeMvt(TypeMouvement typeMvt) {
        this.typeMvt = typeMvt;
    }

    public Float getQteMvt() {
        return qteMvt;
    }

    public Mouvement qteMvt(Float qteMvt) {
        this.qteMvt = qteMvt;
        return this;
    }

    public void setQteMvt(Float qteMvt) {
        this.qteMvt = qteMvt;
    }

    public Float getAncienQte() {
        return ancienQte;
    }

    public Mouvement ancienQte(Float ancienQte) {
        this.ancienQte = ancienQte;
        return this;
    }

    public void setAncienQte(Float ancienQte) {
        this.ancienQte = ancienQte;
    }

    public Float getNvQte() {
        return nvQte;
    }

    public Mouvement nvQte(Float nvQte) {
        this.nvQte = nvQte;
        return this;
    }

    public void setNvQte(Float nvQte) {
        this.nvQte = nvQte;
    }

    public Float getAncienPrix() {
        return ancienPrix;
    }

    public Mouvement ancienPrix(Float ancienPrix) {
        this.ancienPrix = ancienPrix;
        return this;
    }

    public void setAncienPrix(Float ancienPrix) {
        this.ancienPrix = ancienPrix;
    }

    public Float getNvPrix() {
        return nvPrix;
    }

    public Mouvement nvPrix(Float nvPrix) {
        this.nvPrix = nvPrix;
        return this;
    }

    public void setNvPrix(Float nvPrix) {
        this.nvPrix = nvPrix;
    }

    public Float getPrix() {
        return prix;
    }

    public Mouvement prix(Float prix) {
        this.prix = prix;
        return this;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Produit getProduit() {
        return produit;
    }

    public Mouvement produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mouvement)) {
            return false;
        }
        return id != null && id.equals(((Mouvement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Mouvement{" +
            "id=" + getId() +
            ", dateMvt='" + getDateMvt() + "'" +
            ", typeMvt='" + getTypeMvt() + "'" +
            ", qteMvt=" + getQteMvt() +
            ", ancienQte=" + getAncienQte() +
            ", nvQte=" + getNvQte() +
            ", ancienPrix=" + getAncienPrix() +
            ", nvPrix=" + getNvPrix() +
            ", prix=" + getPrix() +
            "}";
    }
}
