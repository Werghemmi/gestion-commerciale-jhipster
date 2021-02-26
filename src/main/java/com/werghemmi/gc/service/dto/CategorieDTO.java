package com.werghemmi.gc.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Categorie} entity.
 */
public class CategorieDTO implements Serializable {
    
    private Long id;

    private String titre;

    private Set<TaxeDTO> taxes = new HashSet<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Set<TaxeDTO> getTaxes() {
        return taxes;
    }

    public void setTaxes(Set<TaxeDTO> taxes) {
        this.taxes = taxes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategorieDTO)) {
            return false;
        }

        return id != null && id.equals(((CategorieDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategorieDTO{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", taxes='" + getTaxes() + "'" +
            "}";
    }
}
