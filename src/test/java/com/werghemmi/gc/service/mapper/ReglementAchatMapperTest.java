package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class ReglementAchatMapperTest {

    private ReglementAchatMapper reglementAchatMapper;

    @BeforeEach
    public void setUp() {
        reglementAchatMapper = new ReglementAchatMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(reglementAchatMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(reglementAchatMapper.fromId(null)).isNull();
    }
}
