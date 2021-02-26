package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReglementVenteMapperTest {

    private ReglementVenteMapper reglementVenteMapper;

    @BeforeEach
    public void setUp() {
        reglementVenteMapper = new ReglementVenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(reglementVenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reglementVenteMapper.fromId(null)).isNull();
    }
}
