package org.cyz.eureka.threadTest;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BooleansThreadTest {

    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        BooleansThread t1=new BooleansThread("张无忌");
        BooleansThread t2=new BooleansThread("赵敏");
        cachedThreadPool.submit(t1);
        cachedThreadPool.submit(t2);
    }

}