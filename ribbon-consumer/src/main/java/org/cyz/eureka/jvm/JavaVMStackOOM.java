package org.cyz.eureka.jvm;

/**
 * 虚拟机栈和本地方法栈溢出
 * 如果是建立多线程导致的内存溢出，在不减少线程数的情况下，就只能通过减少最大堆和减少栈容量来换取更多的线程
 * 很危险，别试了
 *
 * VM Args: -Xss2M
 *
 * @author chengyz
 */
public class JavaVMStackOOM {

    private void doNotStop(){
        while (true){}
    }

    public void stackLeakByThread(){
        while (true){
            Thread thread=new Thread(new Runnable(){
                @Override
                public void run() {
                    doNotStop();
                    System.out.println(Thread.currentThread().getName());
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackOOM oom=new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
