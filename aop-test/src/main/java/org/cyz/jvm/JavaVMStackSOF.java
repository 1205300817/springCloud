package org.cyz.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xss128k
 *
 * @author chengyz
 */
@Slf4j
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            log.error("stack length: {}", oom.stackLength);
            throw e;
        }
    }
}
