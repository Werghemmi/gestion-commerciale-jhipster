package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class DetaisDevisDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DetaisDevisDTO.class);
        DetaisDevisDTO detaisDevisDTO1 = new DetaisDevisDTO();
        detaisDevisDTO1.setId(1L);
        DetaisDevisDTO detaisDevisDTO2 = new DetaisDevisDTO();
        assertThat(detaisDevisDTO1).isNotEqualTo(detaisDevisDTO2);
        detaisDevisDTO2.setId(detaisDevisDTO1.getId());
        assertThat(detaisDevisDTO1).isEqualTo(detaisDevisDTO2);
        detaisDevisDTO2.setId(2L);
        assertThat(detaisDevisDTO1).isNotEqualTo(detaisDevisDTO2);
        detaisDevisDTO1.setId(null);
        assertThat(detaisDevisDTO1).isNotEqualTo(detaisDevisDTO2);
    }
}
