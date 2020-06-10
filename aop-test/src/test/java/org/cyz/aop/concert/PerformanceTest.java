package org.cyz.aop.concert;

import org.cyz.aop.pojo.R;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PerformanceTest {

    @Autowired
    private Performance performance;

    @Test
    void perform() {
        R r = performance.perform();
        System.out.println(r.toString());
    }

    @Test
    void perform2() {
        R r = performance.perform2();
    }
}