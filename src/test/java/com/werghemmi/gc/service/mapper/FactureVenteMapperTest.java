package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FactureVenteMapperTest {

    private FactureVenteMapper factureVenteMapper;

    @BeforeEach
    public void setUp() {
        factureVenteMapper = new FactureVenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(factureVenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(factureVenteMapper.fromId(null)).isNull();
    }
}
