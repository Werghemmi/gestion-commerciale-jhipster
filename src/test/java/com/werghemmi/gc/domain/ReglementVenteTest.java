package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class ReglementVenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReglementVente.class);
        ReglementVente reglementVente1 = new ReglementVente();
        reglementVente1.setId(1L);
        ReglementVente reglementVente2 = new ReglementVente();
        reglementVente2.setId(reglementVente1.getId());
        assertThat(reglementVente1).isEqualTo(reglementVente2);
        reglementVente2.setId(2L);
        assertThat(reglementVente1).isNotEqualTo(reglementVente2);
        reglementVente1.setId(null);
        assertThat(reglementVente1).isNotEqualTo(reglementVente2);
    }
}
