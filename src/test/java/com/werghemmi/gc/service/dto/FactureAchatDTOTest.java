package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class FactureAchatDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactureAchatDTO.class);
        FactureAchatDTO factureAchatDTO1 = new FactureAchatDTO();
        factureAchatDTO1.setId(1L);
        FactureAchatDTO factureAchatDTO2 = new FactureAchatDTO();
        assertThat(factureAchatDTO1).isNotEqualTo(factureAchatDTO2);
        factureAchatDTO2.setId(factureAchatDTO1.getId());
        assertThat(factureAchatDTO1).isEqualTo(factureAchatDTO2);
        factureAchatDTO2.setId(2L);
        assertThat(factureAchatDTO1).isNotEqualTo(factureAchatDTO2);
        factureAchatDTO1.setId(null);
        assertThat(factureAchatDTO1).isNotEqualTo(factureAchatDTO2);
    }
}
