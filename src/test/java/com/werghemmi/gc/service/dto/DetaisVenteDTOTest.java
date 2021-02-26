package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class DetaisVenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaisVenteDTO.class);
        DetaisVenteDTO detaisVenteDTO1 = new DetaisVenteDTO();
        detaisVenteDTO1.setId(1L);
        DetaisVenteDTO detaisVenteDTO2 = new DetaisVenteDTO();
        assertThat(detaisVenteDTO1).isNotEqualTo(detaisVenteDTO2);
        detaisVenteDTO2.setId(detaisVenteDTO1.getId());
        assertThat(detaisVenteDTO1).isEqualTo(detaisVenteDTO2);
        detaisVenteDTO2.setId(2L);
        assertThat(detaisVenteDTO1).isNotEqualTo(detaisVenteDTO2);
        detaisVenteDTO1.setId(null);
        assertThat(detaisVenteDTO1).isNotEqualTo(detaisVenteDTO2);
    }
}
