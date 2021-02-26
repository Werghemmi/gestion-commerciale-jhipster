package com.werghemmi.gc.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TaxeMapperTest {

    private TaxeMapper taxeMapper;

    @BeforeEach
    public void setUp() {
        taxeMapper = new TaxeMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(taxeMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(taxeMapper.fromId(null)).isNull();
    }
}
