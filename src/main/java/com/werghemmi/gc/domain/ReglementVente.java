package com.werghemmi.gc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.werghemmi.gc.domain.enumeration.TypeReglement;

/**
 * A ReglementVente.
 */
@Entity
@Table(name = "reglement_vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ReglementVente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_reglement")
    private Instant dateReglement;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_reglement")
    private TypeReglement typeReglement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateReglement() {
        return dateReglement;
    }

    public ReglementVente dateReglement(Instant dateReglement) {
        this.dateReglement = dateReglement;
        return this;
    }

    public void setDateReglement(Instant dateReglement) {
        this.dateReglement = dateReglement;
    }

    public TypeReglement getTypeReglement() {
        return typeReglement;
    }

    public ReglementVente typeReglement(TypeReglement typeReglement) {
        this.typeReglement = typeReglement;
        return this;
    }

    public void setTypeReglement(TypeReglement typeReglement) {
        this.typeReglement = typeReglement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReglementVente)) {
            return false;
        }
        return id != null && id.equals(((ReglementVente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReglementVente{" +
            "id=" + getId() +
            ", dateReglement='" + getDateReglement() + "'" +
            ", typeReglement='" + getTypeReglement() + "'" +
            "}";
    }
}
