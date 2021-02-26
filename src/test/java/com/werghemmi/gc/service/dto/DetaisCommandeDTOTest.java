package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class DetaisCommandeDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaisCommandeDTO.class);
        DetaisCommandeDTO detaisCommandeDTO1 = new DetaisCommandeDTO();
        detaisCommandeDTO1.setId(1L);
        DetaisCommandeDTO detaisCommandeDTO2 = new DetaisCommandeDTO();
        assertThat(detaisCommandeDTO1).isNotEqualTo(detaisCommandeDTO2);
        detaisCommandeDTO2.setId(detaisCommandeDTO1.getId());
        assertThat(detaisCommandeDTO1).isEqualTo(detaisCommandeDTO2);
        detaisCommandeDTO2.setId(2L);
        assertThat(detaisCommandeDTO1).isNotEqualTo(detaisCommandeDTO2);
        detaisCommandeDTO1.setId(null);
        assertThat(detaisCommandeDTO1).isNotEqualTo(detaisCommandeDTO2);
    }
}
