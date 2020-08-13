package org.cyz.eureka.threadTest;

public class ReentrantLockTest {
    public static void main(String[] args) {
        // true 编译成 class 文件时，能够识别为同一字符串的, JVM 会将其自动优化成字符串常量,引用自同一 String 对象
        String str = "Hello";
        System.out.println((str == ("Hel" + "lo")));

        // false 运行时创建的字符串具有独立的内存地址,所以不引用自同一 String 对象。
        String loStr = "lo";
        System.out.println((str == ("Hel" + loStr)));

        // true 是因为 String 的 intern() 方法会查找在常量池中是否存在一个相等(调用 equals() 方法结果相等)的字符串,
        // 如果有则返回该字符串的引用,如果没有则添加自己的字符串进入常量池。
        System.out.println(str == ("Hel" + loStr).intern());
//        System.gc();
        doSomething();
    }
    public synchronized static void doSomething(){
        System.out.println("执行方法一。。");
        doOthers();
    }
    public synchronized static void doOthers(){

        System.out.println("执行方法二。。");
    }
}
