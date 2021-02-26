package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class FactureAchatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactureAchat.class);
        FactureAchat factureAchat1 = new FactureAchat();
        factureAchat1.setId(1L);
        FactureAchat factureAchat2 = new FactureAchat();
        factureAchat2.setId(factureAchat1.getId());
        assertThat(factureAchat1).isEqualTo(factureAchat2);
        factureAchat2.setId(2L);
        assertThat(factureAchat1).isNotEqualTo(factureAchat2);
        factureAchat1.setId(null);
        assertThat(factureAchat1).isNotEqualTo(factureAchat2);
    }
}
