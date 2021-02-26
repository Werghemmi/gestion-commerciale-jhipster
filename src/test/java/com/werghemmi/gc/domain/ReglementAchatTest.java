package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class ReglementAchatTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReglementAchat.class);
        ReglementAchat reglementAchat1 = new ReglementAchat();
        reglementAchat1.setId(1L);
        ReglementAchat reglementAchat2 = new ReglementAchat();
        reglementAchat2.setId(reglementAchat1.getId());
        assertThat(reglementAchat1).isEqualTo(reglementAchat2);
        reglementAchat2.setId(2L);
        assertThat(reglementAchat1).isNotEqualTo(reglementAchat2);
        reglementAchat1.setId(null);
        assertThat(reglementAchat1).isNotEqualTo(reglementAchat2);
    }
}
