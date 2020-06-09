package org.cyz.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author chengyz
 */
@Slf4j
public class HeapOOM {
    private static class OOMObject{}

    public static void main(String[] args) {
        log.info("----------------开始测试-----------------");
        List<OOMObject> list=new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
