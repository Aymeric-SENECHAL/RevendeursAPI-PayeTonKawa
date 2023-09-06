package com.payetonkawa.payetonkawa.utils;

import com.fasterxml.jackson.core.JsonFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DefaultEntityTest {

    private DefaultEntity defaultEntity;

    @BeforeEach
    void setUp() {
        defaultEntity = new DefaultEntity() {};
    }

    @Test
    void getId() {
        UUID id = UUID.randomUUID();
        defaultEntity.setId(id);
        assertEquals(id, defaultEntity.getId());
    }

    @Test
    void getCreatedAt() {
        Instant createdAt = Instant.now();
        defaultEntity.setCreatedAt(createdAt);
        assertEquals(createdAt, defaultEntity.getCreatedAt());
    }

    @Test
    void setId() {
        UUID id = UUID.randomUUID();
        defaultEntity.setId(id);
        assertEquals(id, defaultEntity.getId());
    }

    @Test
    void setCreatedAt() {
        Instant createdAt = Instant.now();
        defaultEntity.setCreatedAt(createdAt);
        assertEquals(createdAt, defaultEntity.getCreatedAt());
    }
}