package org.cyz.aop.concert;

import lombok.extern.slf4j.Slf4j;
import org.cyz.aop.pojo.R;
import org.springframework.stereotype.Component;

/**
 * @author chengyz
 */
@Component
@Slf4j
public class Performance {

    public R perform() {
        Integer num = null;
        System.out.println(num.intValue());
        System.out.println("hello world! sugar");
        return R.ok();
    }

    public R perform2() {
        Integer num = 1 / 0;
        System.out.println("hello world! sugar");
        return R.ok();
    }


}
