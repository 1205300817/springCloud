package org.cyz.eureka.jvm;

/**
 * 虚拟机栈和本地方法栈溢出
 * 如果是建立多线程导致的内存溢出，在不减少线程数的情况下，就只能通过减少最大堆和减少栈容量来换取更多的线程
 *
 * VM Args: -Xss128k
 *
 * @author chengyz
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
