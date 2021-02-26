package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class MouvementDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MouvementDTO.class);
        MouvementDTO mouvementDTO1 = new MouvementDTO();
        mouvementDTO1.setId(1L);
        MouvementDTO mouvementDTO2 = new MouvementDTO();
        assertThat(mouvementDTO1).isNotEqualTo(mouvementDTO2);
        mouvementDTO2.setId(mouvementDTO1.getId());
        assertThat(mouvementDTO1).isEqualTo(mouvementDTO2);
        mouvementDTO2.setId(2L);
        assertThat(mouvementDTO1).isNotEqualTo(mouvementDTO2);
        mouvementDTO1.setId(null);
        assertThat(mouvementDTO1).isNotEqualTo(mouvementDTO2);
    }
}
