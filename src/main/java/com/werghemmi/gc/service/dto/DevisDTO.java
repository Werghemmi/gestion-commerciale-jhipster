package com.werghemmi.gc.service.dto;

import java.time.Instant;
import java.io.Serializable;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.Devis} entity.
 */
public class DevisDTO implements Serializable {
    
    private Long id;

    private Instant dateDevis;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(Instant dateDevis) {
        this.dateDevis = dateDevis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DevisDTO)) {
            return false;
        }

        return id != null && id.equals(((DevisDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DevisDTO{" +
            "id=" + getId() +
            ", dateDevis='" + getDateDevis() + "'" +
            "}";
    }
}
