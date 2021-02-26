package com.werghemmi.gc.service.dto;

import java.io.Serializable;
import com.werghemmi.gc.domain.enumeration.Type;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Taxe} entity.
 */
public class TaxeDTO implements Serializable {
    
    private Long id;

    private String nomTaxe;

    private Float taux;

    private Type typeTaxe;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTaxe() {
        return nomTaxe;
    }

    public void setNomTaxe(String nomTaxe) {
        this.nomTaxe = nomTaxe;
    }

    public Float getTaux() {
        return taux;
    }

    public void setTaux(Float taux) {
        this.taux = taux;
    }

    public Type getTypeTaxe() {
        return typeTaxe;
    }

    public void setTypeTaxe(Type typeTaxe) {
        this.typeTaxe = typeTaxe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaxeDTO)) {
            return false;
        }

        return id != null && id.equals(((TaxeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TaxeDTO{" +
            "id=" + getId() +
            ", nomTaxe='" + getNomTaxe() + "'" +
            ", taux=" + getTaux() +
            ", typeTaxe='" + getTypeTaxe() + "'" +
            "}";
    }
}
