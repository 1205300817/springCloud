package org.cyz.eureka.threadTest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;

import java.lang.management.ManagementFactory;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class DeadlockExample {

    static ReentrantLock reentrantLockA = new ReentrantLock();
    static ReentrantLock reentrantLockB = new ReentrantLock();

    public static void main(String[] args) {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
        log.info("Pid is:" + pid);
        log.info("jstack " + pid);

        log.info("new Random(1) = {}", new Random(1).nextInt());

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> {
            try {
                while (reentrantLockA.tryLock(10, TimeUnit.SECONDS)) {
                    log.info("A任务重新初始化");
                    TimeUnit.SECONDS.sleep(1);
                    boolean locked = reentrantLockB.isLocked();
                    if (locked) {
                        reentrantLockA.unlock();
                        //避免活锁
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(2000));
                        continue;
                    }
                    boolean b = reentrantLockB.tryLock(10, TimeUnit.SECONDS);
                    if (!b) {
                        reentrantLockA.unlock();
                        continue;
                    }
                    log.info("线程A处理任务");
                    break;
                }
                log.info("线程A处理完成");
            } catch (InterruptedException e) {
                log.error("线程中断", e);
            } finally {
                reentrantLockB.unlock();
                reentrantLockA.unlock();
            }
        });

        executorService.submit(() -> {
            try {
                while (reentrantLockB.tryLock(10, TimeUnit.SECONDS)) {
                    log.info("B任务重新初始化");
                    TimeUnit.SECONDS.sleep(2);
                    boolean locked = reentrantLockA.isLocked();
                    if (locked) {
                        //避免活锁
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(2000));
                        reentrantLockB.unlock();
                        continue;
                    }
                    boolean b = reentrantLockA.tryLock(10, TimeUnit.SECONDS);
                    if (!b) {
                        reentrantLockB.unlock();
                        continue;
                    }
                    log.info("线程B处理任务");
                    break;
                }
                log.info("线程B处理完成");
            } catch (InterruptedException e) {
                log.error("线程中断", e);
            } finally {
                reentrantLockA.unlock();
                reentrantLockB.unlock();
            }
        });
        executorService.shutdown();
    }

}
