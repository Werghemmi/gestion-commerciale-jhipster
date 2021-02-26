package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MouvementMapperTest {

    private MouvementMapper mouvementMapper;

    @BeforeEach
    public void setUp() {
        mouvementMapper = new MouvementMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(mouvementMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(mouvementMapper.fromId(null)).isNull();
    }
}
