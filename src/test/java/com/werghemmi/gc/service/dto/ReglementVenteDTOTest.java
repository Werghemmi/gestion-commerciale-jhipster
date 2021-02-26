package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class ReglementVenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReglementVenteDTO.class);
        ReglementVenteDTO reglementVenteDTO1 = new ReglementVenteDTO();
        reglementVenteDTO1.setId(1L);
        ReglementVenteDTO reglementVenteDTO2 = new ReglementVenteDTO();
        assertThat(reglementVenteDTO1).isNotEqualTo(reglementVenteDTO2);
        reglementVenteDTO2.setId(reglementVenteDTO1.getId());
        assertThat(reglementVenteDTO1).isEqualTo(reglementVenteDTO2);
        reglementVenteDTO2.setId(2L);
        assertThat(reglementVenteDTO1).isNotEqualTo(reglementVenteDTO2);
        reglementVenteDTO1.setId(null);
        assertThat(reglementVenteDTO1).isNotEqualTo(reglementVenteDTO2);
    }
}
