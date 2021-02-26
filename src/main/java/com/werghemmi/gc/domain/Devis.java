package com.werghemmi.gc.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Devis.
 */
@Entity
@Table(name = "devis")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Devis implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_devis")
    private Instant dateDevis;

    @OneToMany(mappedBy = "devis")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetaisDevis> detaisDevis = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateDevis() {
        return dateDevis;
    }

    public Devis dateDevis(Instant dateDevis) {
        this.dateDevis = dateDevis;
        return this;
    }

    public void setDateDevis(Instant dateDevis) {
        this.dateDevis = dateDevis;
    }

    public Set<DetaisDevis> getDetaisDevis() {
        return detaisDevis;
    }

    public Devis detaisDevis(Set<DetaisDevis> detaisDevis) {
        this.detaisDevis = detaisDevis;
        return this;
    }

    public Devis addDetaisDevis(DetaisDevis detaisDevis) {
        this.detaisDevis.add(detaisDevis);
        detaisDevis.setDevis(this);
        return this;
    }

    public Devis removeDetaisDevis(DetaisDevis detaisDevis) {
        this.detaisDevis.remove(detaisDevis);
        detaisDevis.setDevis(null);
        return this;
    }

    public void setDetaisDevis(Set<DetaisDevis> detaisDevis) {
        this.detaisDevis = detaisDevis;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Devis)) {
            return false;
        }
        return id != null && id.equals(((Devis) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Devis{" +
            "id=" + getId() +
            ", dateDevis='" + getDateDevis() + "'" +
            "}";
    }
}
