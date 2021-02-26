package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetaisVenteMapperTest {

    private DetaisVenteMapper detaisVenteMapper;

    @BeforeEach
    public void setUp() {
        detaisVenteMapper = new DetaisVenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detaisVenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detaisVenteMapper.fromId(null)).isNull();
    }
}
