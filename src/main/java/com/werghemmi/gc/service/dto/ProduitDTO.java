package com.werghemmi.gc.service.dto;

import java.io.Serializable;
import com.werghemmi.gc.domain.enumeration.Type;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {
    
    private Long id;

    private String nom;

    private String description;

    private Float prixAchat;

    private Float prixVente;

    private Float qteStock;

    private Float marge;

    private Type typeMarge;


    private Long categorieId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(Float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public Float getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Float prixVente) {
        this.prixVente = prixVente;
    }

    public Float getQteStock() {
        return qteStock;
    }

    public void setQteStock(Float qteStock) {
        this.qteStock = qteStock;
    }

    public Float getMarge() {
        return marge;
    }

    public void setMarge(Float marge) {
        this.marge = marge;
    }

    public Type getTypeMarge() {
        return typeMarge;
    }

    public void setTypeMarge(Type typeMarge) {
        this.typeMarge = typeMarge;
    }

    public Long getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Long categorieId) {
        this.categorieId = categorieId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((ProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", prixAchat=" + getPrixAchat() +
            ", prixVente=" + getPrixVente() +
            ", qteStock=" + getQteStock() +
            ", marge=" + getMarge() +
            ", typeMarge='" + getTypeMarge() + "'" +
            ", categorieId=" + getCategorieId() +
            "}";
    }
}
