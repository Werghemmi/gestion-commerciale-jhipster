package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class FactureVenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactureVente.class);
        FactureVente factureVente1 = new FactureVente();
        factureVente1.setId(1L);
        FactureVente factureVente2 = new FactureVente();
        factureVente2.setId(factureVente1.getId());
        assertThat(factureVente1).isEqualTo(factureVente2);
        factureVente2.setId(2L);
        assertThat(factureVente1).isNotEqualTo(factureVente2);
        factureVente1.setId(null);
        assertThat(factureVente1).isNotEqualTo(factureVente2);
    }
}
