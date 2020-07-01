package org.cyz.eureka.threadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chengyz
 */
@Slf4j
public class BooleansThread implements Runnable {
    /**
     * 使用标志
     */
    private static AtomicBoolean useFlag = new AtomicBoolean(false);
    private static boolean useFlag2 = false;
    private String name;

    public BooleansThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        //region test1
        // 因为useFlag.compareAndSet(false, true)提供了原子性操作
        // 比较和赋值操作组成了一个原子操作, 中间不会提供可乘之机。
        if (useFlag.compareAndSet(false, true)) {
            try {
                log.info("起床啦，{}", name);
                Thread.sleep(1000);
                log.info("上班啦，{}", name);
                Thread.sleep(1000);
                log.info("下班啦，{}", name);
                useFlag.set(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            log.info("房间有人在使用，{}无法进入", name);
        }
        //endregion

        //region test2
//        if (!useFlag2) {
//            try {
//                log.info("起床啦，{}", name);
//                Thread.sleep(1000);
//
//                useFlag2 = true;
//                log.info("上班啦，{}", name);
//                Thread.sleep(1000);
//                log.info("下班啦，{}", name);
//                useFlag2 = false;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        } else {
//            log.info("房间有人在使用，{}无法进入", name);
//        }
        //endregion


    }

}
