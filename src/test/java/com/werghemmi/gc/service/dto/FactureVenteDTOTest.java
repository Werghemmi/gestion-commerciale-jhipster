package com.werghemmi.gc.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.werghemmi.gc.web.rest.TestUtil;

public class FactureVenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FactureVenteDTO.class);
        FactureVenteDTO factureVenteDTO1 = new FactureVenteDTO();
        factureVenteDTO1.setId(1L);
        FactureVenteDTO factureVenteDTO2 = new FactureVenteDTO();
        assertThat(factureVenteDTO1).isNotEqualTo(factureVenteDTO2);
        factureVenteDTO2.setId(factureVenteDTO1.getId());
        assertThat(factureVenteDTO1).isEqualTo(factureVenteDTO2);
        factureVenteDTO2.setId(2L);
        assertThat(factureVenteDTO1).isNotEqualTo(factureVenteDTO2);
        factureVenteDTO1.setId(null);
        assertThat(factureVenteDTO1).isNotEqualTo(factureVenteDTO2);
    }
}
