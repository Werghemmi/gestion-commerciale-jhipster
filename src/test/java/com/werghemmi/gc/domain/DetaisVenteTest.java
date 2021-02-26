package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class DetaisVenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaisVente.class);
        DetaisVente detaisVente1 = new DetaisVente();
        detaisVente1.setId(1L);
        DetaisVente detaisVente2 = new DetaisVente();
        detaisVente2.setId(detaisVente1.getId());
        assertThat(detaisVente1).isEqualTo(detaisVente2);
        detaisVente2.setId(2L);
        assertThat(detaisVente1).isNotEqualTo(detaisVente2);
        detaisVente1.setId(null);
        assertThat(detaisVente1).isNotEqualTo(detaisVente2);
    }
}
