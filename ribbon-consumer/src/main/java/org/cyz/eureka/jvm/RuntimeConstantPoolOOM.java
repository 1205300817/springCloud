package org.cyz.eureka.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出
 *
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * @author chengyz
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) throws Throwable{
        List<String> list = new ArrayList<>();
        // 10MB的PermSize再Integer范围内足够产生OOM了
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
            System.out.println(i);
        }
    }
}
