package com.werghemmi.gc.service.dto;

import java.time.Instant;
import java.io.Serializable;
import com.werghemmi.gc.domain.enumeration.TypeReglement;

/**
 * A DTO for the {@link com.werghemmi.gc.domain.ReglementAchat} entity.
 */
public class ReglementAchatDTO implements Serializable {
    
    private Long id;

    private Instant dateReglement;

    private TypeReglement typeReglement;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateReglement() {
        return dateReglement;
    }

    public void setDateReglement(Instant dateReglement) {
        this.dateReglement = dateReglement;
    }

    public TypeReglement getTypeReglement() {
        return typeReglement;
    }

    public void setTypeReglement(TypeReglement typeReglement) {
        this.typeReglement = typeReglement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReglementAchatDTO)) {
            return false;
        }

        return id != null && id.equals(((ReglementAchatDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReglementAchatDTO{" +
            "id=" + getId() +
            ", dateReglement='" + getDateReglement() + "'" +
            ", typeReglement='" + getTypeReglement() + "'" +
            "}";
    }
}
