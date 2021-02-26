package com.werghemmi.gc.config;

import io.github.jhipster.config.JHipsterProperties;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import java.time.Duration;
import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration =
            Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder
                    .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                    .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                    .build()
            );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.werghemmi.gc.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.werghemmi.gc.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.werghemmi.gc.domain.User.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Authority.class.getName());
            createCache(cm, com.werghemmi.gc.domain.User.class.getName() + ".authorities");
            createCache(cm, com.werghemmi.gc.domain.Produit.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Produit.class.getName() + ".mouvements");
            createCache(cm, com.werghemmi.gc.domain.Produit.class.getName() + ".detaisDevis");
            createCache(cm, com.werghemmi.gc.domain.Produit.class.getName() + ".detaisCommandes");
            createCache(cm, com.werghemmi.gc.domain.Produit.class.getName() + ".detaisVentes");
            createCache(cm, com.werghemmi.gc.domain.Fournisseur.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Fournisseur.class.getName() + ".commandes");
            createCache(cm, com.werghemmi.gc.domain.Client.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Client.class.getName() + ".ventes");
            createCache(cm, com.werghemmi.gc.domain.Categorie.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Categorie.class.getName() + ".produits");
            createCache(cm, com.werghemmi.gc.domain.Categorie.class.getName() + ".taxes");
            createCache(cm, com.werghemmi.gc.domain.Taxe.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Taxe.class.getName() + ".categories");
            createCache(cm, com.werghemmi.gc.domain.Devis.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Devis.class.getName() + ".detaisDevis");
            createCache(cm, com.werghemmi.gc.domain.DetaisDevis.class.getName());
            createCache(cm, com.werghemmi.gc.domain.DetaisCommande.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Commande.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Commande.class.getName() + ".detaisCommandes");
            createCache(cm, com.werghemmi.gc.domain.FactureAchat.class.getName());
            createCache(cm, com.werghemmi.gc.domain.ReglementAchat.class.getName());
            createCache(cm, com.werghemmi.gc.domain.DetaisVente.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Vente.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Vente.class.getName() + ".detaisVentes");
            createCache(cm, com.werghemmi.gc.domain.FactureVente.class.getName());
            createCache(cm, com.werghemmi.gc.domain.ReglementVente.class.getName());
            createCache(cm, com.werghemmi.gc.domain.Mouvement.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
