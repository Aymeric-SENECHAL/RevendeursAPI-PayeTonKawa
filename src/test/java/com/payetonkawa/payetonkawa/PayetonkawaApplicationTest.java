package com.payetonkawa.payetonkawa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayetonkawaApplicationTest {

    @Test
    void main() {
        assertDoesNotThrow(() -> PayetonkawaApplication.main(new String[]{}));
    }
}