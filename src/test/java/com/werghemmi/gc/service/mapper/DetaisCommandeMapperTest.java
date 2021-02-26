package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetaisCommandeMapperTest {

    private DetaisCommandeMapper detaisCommandeMapper;

    @BeforeEach
    public void setUp() {
        detaisCommandeMapper = new DetaisCommandeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detaisCommandeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detaisCommandeMapper.fromId(null)).isNull();
    }
}
