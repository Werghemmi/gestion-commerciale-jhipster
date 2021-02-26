package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class ReglementAchatDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReglementAchatDTO.class);
        ReglementAchatDTO reglementAchatDTO1 = new ReglementAchatDTO();
        reglementAchatDTO1.setId(1L);
        ReglementAchatDTO reglementAchatDTO2 = new ReglementAchatDTO();
        assertThat(reglementAchatDTO1).isNotEqualTo(reglementAchatDTO2);
        reglementAchatDTO2.setId(reglementAchatDTO1.getId());
        assertThat(reglementAchatDTO1).isEqualTo(reglementAchatDTO2);
        reglementAchatDTO2.setId(2L);
        assertThat(reglementAchatDTO1).isNotEqualTo(reglementAchatDTO2);
        reglementAchatDTO1.setId(null);
        assertThat(reglementAchatDTO1).isNotEqualTo(reglementAchatDTO2);
    }
}
