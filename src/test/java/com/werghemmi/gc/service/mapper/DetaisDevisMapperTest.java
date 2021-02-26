package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DetaisDevisMapperTest {

    private DetaisDevisMapper detaisDevisMapper;

    @BeforeEach
    public void setUp() {
        detaisDevisMapper = new DetaisDevisMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(detaisDevisMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(detaisDevisMapper.fromId(null)).isNull();
    }
}
