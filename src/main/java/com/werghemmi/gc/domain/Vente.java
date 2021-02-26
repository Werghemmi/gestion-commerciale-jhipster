package com.werghemmi.gc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Vente.
 */
@Entity
@Table(name = "vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_commande")
    private Instant dateCommande;

    @OneToOne
    @JoinColumn(unique = true)
    private FactureVente factureVente;

    @OneToMany(mappedBy = "vente")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<DetaisVente> detaisVentes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateCommande() {
        return dateCommande;
    }

    public Vente dateCommande(Instant dateCommande) {
        this.dateCommande = dateCommande;
        return this;
    }

    public void setDateCommande(Instant dateCommande) {
        this.dateCommande = dateCommande;
    }

    public FactureVente getFactureVente() {
        return factureVente;
    }

    public Vente factureVente(FactureVente factureVente) {
        this.factureVente = factureVente;
        return this;
    }

    public void setFactureVente(FactureVente factureVente) {
        this.factureVente = factureVente;
    }

    public Set<DetaisVente> getDetaisVentes() {
        return detaisVentes;
    }

    public Vente detaisVentes(Set<DetaisVente> detaisVentes) {
        this.detaisVentes = detaisVentes;
        return this;
    }

    public Vente addDetaisVente(DetaisVente detaisVente) {
        this.detaisVentes.add(detaisVente);
        detaisVente.setVente(this);
        return this;
    }

    public Vente removeDetaisVente(DetaisVente detaisVente) {
        this.detaisVentes.remove(detaisVente);
        detaisVente.setVente(null);
        return this;
    }

    public void setDetaisVentes(Set<DetaisVente> detaisVentes) {
        this.detaisVentes = detaisVentes;
    }

    public Client getClient() {
        return client;
    }

    public Vente client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vente)) {
            return false;
        }
        return id != null && id.equals(((Vente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vente{" +
            "id=" + getId() +
            ", dateCommande='" + getDateCommande() + "'" +
            "}";
    }
}
