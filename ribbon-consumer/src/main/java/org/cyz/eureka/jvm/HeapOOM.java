package org.cyz.eureka.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆溢出
 *
 * VM Args: -Xms20m -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 * @author chengyz
 */
public class HeapOOM {
    static class OOMObject{}
    public static void main(String[] args) throws Throwable{
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
