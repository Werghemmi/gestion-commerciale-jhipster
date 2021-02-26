package com.werghemmi.gc.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class DetaisDevisTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaisDevis.class);
        DetaisDevis detaisDevis1 = new DetaisDevis();
        detaisDevis1.setId(1L);
        DetaisDevis detaisDevis2 = new DetaisDevis();
        detaisDevis2.setId(detaisDevis1.getId());
        assertThat(detaisDevis1).isEqualTo(detaisDevis2);
        detaisDevis2.setId(2L);
        assertThat(detaisDevis1).isNotEqualTo(detaisDevis2);
        detaisDevis1.setId(null);
        assertThat(detaisDevis1).isNotEqualTo(detaisDevis2);
    }
}
