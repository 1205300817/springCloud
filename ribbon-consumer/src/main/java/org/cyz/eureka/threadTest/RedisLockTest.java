package org.cyz.eureka.threadTest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RedisLockTest {
    private int count = 10;

    @Test
    public void test() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            cachedThreadPool.submit(() -> {
                kill();
            });
        }
    }

    private void kill() {
        String threadName = Thread.currentThread().getName();
        if (count > 0) {
            System.out.println(threadName + "线程" + "获得秒杀商品，剩余库存：" + count--);
        } else {
            System.out.println(threadName + "线程秒杀失败");
        }
    }
}
