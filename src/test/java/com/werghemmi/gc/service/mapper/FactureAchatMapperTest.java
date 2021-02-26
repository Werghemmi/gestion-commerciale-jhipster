package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FactureAchatMapperTest {

    private FactureAchatMapper factureAchatMapper;

    @BeforeEach
    public void setUp() {
        factureAchatMapper = new FactureAchatMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(factureAchatMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(factureAchatMapper.fromId(null)).isNull();
    }
}
