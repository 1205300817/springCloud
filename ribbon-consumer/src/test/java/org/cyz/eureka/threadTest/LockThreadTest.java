package org.cyz.eureka.threadTest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class LockThreadTest {

    private int inc = 0;
    Lock lock = new ReentrantLock();
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        final LockThreadTest lockThreadTest = new LockThreadTest();
        for (int i = 0; i < 3; i++) {
//            cachedThreadPool.submit(() -> {
//                for (int j = 0; j < 10; j++) {
//                    lockThreadTest.increase();
//                }
//            });
            final int index = i;
            new Thread(() -> {
                Thread.currentThread().setName("Thread-" + index);
                for (int j = 0; j < 10; j++) {
                    lockThreadTest.increase();
                }
            }).start();
        }
        // 主线程让步，进入Runnable(就绪状态)，等待其他线程执行结束
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println("lockThreadTest.inc=" + lockThreadTest.inc);
    }

    public void increase() {
        lock.lock();
        try {
            inc++;
            System.out.println(Thread.currentThread().getName() + "更新： inc=" + inc);
        } finally {
            lock.unlock();
        }
    }

}