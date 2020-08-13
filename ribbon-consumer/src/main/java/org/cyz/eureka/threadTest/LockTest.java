package org.cyz.eureka.threadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockTest {
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new LockTest.MyThread(lock));
        }
        executorService.shutdown();
    }

    static class MyThread implements Runnable {
        private Lock lock;

        public MyThread(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {

            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        log.info("线程【{}】获取锁",Thread.currentThread().getName());
                        // manipulate protected state
                    } finally {
                        log.info("线程【{}】释放锁",Thread.currentThread().getName());
                        lock.unlock();
                    }
                } else {
                    // perform alternative actions
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
