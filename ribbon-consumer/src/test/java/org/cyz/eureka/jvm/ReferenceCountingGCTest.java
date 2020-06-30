package org.cyz.eureka.jvm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReferenceCountingGCTest {

    @Test
    void testGC() {
        ReferenceCountingGC.testGC();
    }
}