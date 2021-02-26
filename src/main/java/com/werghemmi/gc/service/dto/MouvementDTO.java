package com.werghemmi.gc.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.werghemmi.gc.domain.enumeration.TypeMouvement;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Mouvement} entity.
 */
public class MouvementDTO implements Serializable {
    
    private Long id;

    private Instant dateMvt;

    private TypeMouvement typeMvt;

    private Float qteMvt;

    private Float ancienQte;

    private Float nvQte;

    private Float ancienPrix;

    private Float nvPrix;

    private Float prix;


    private Long produitId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateMvt() {
        return dateMvt;
    }

    public void setDateMvt(Instant dateMvt) {
        this.dateMvt = dateMvt;
    }

    public TypeMouvement getTypeMvt() {
        return typeMvt;
    }

    public void setTypeMvt(TypeMouvement typeMvt) {
        this.typeMvt = typeMvt;
    }

    public Float getQteMvt() {
        return qteMvt;
    }

    public void setQteMvt(Float qteMvt) {
        this.qteMvt = qteMvt;
    }

    public Float getAncienQte() {
        return ancienQte;
    }

    public void setAncienQte(Float ancienQte) {
        this.ancienQte = ancienQte;
    }

    public Float getNvQte() {
        return nvQte;
    }

    public void setNvQte(Float nvQte) {
        this.nvQte = nvQte;
    }

    public Float getAncienPrix() {
        return ancienPrix;
    }

    public void setAncienPrix(Float ancienPrix) {
        this.ancienPrix = ancienPrix;
    }

    public Float getNvPrix() {
        return nvPrix;
    }

    public void setNvPrix(Float nvPrix) {
        this.nvPrix = nvPrix;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MouvementDTO)) {
            return false;
        }

        return id != null && id.equals(((MouvementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MouvementDTO{" +
            "id=" + getId() +
            ", dateMvt='" + getDateMvt() + "'" +
            ", typeMvt='" + getTypeMvt() + "'" +
            ", qteMvt=" + getQteMvt() +
            ", ancienQte=" + getAncienQte() +
            ", nvQte=" + getNvQte() +
            ", ancienPrix=" + getAncienPrix() +
            ", nvPrix=" + getNvPrix() +
            ", prix=" + getPrix() +
            ", produitId=" + getProduitId() +
            "}";
    }
}
