package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class DetaisCommandeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaisCommande.class);
        DetaisCommande detaisCommande1 = new DetaisCommande();
        detaisCommande1.setId(1L);
        DetaisCommande detaisCommande2 = new DetaisCommande();
        detaisCommande2.setId(detaisCommande1.getId());
        assertThat(detaisCommande1).isEqualTo(detaisCommande2);
        detaisCommande2.setId(2L);
        assertThat(detaisCommande1).isNotEqualTo(detaisCommande2);
        detaisCommande1.setId(null);
        assertThat(detaisCommande1).isNotEqualTo(detaisCommande2);
    }
}
