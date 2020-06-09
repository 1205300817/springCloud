package org.cyz.aop.controller;

import lombok.extern.slf4j.Slf4j;
import org.cyz.aop.concert.Performance;
import org.cyz.aop.pojo.R;
import org.cyz.aop.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author chengyz
 */
@Slf4j
@RestController
@RequestMapping("/perform")
public class PerformController {

    @Autowired
    private Performance performance;

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    public R test1() {
        return performance.perform();
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public R test2() {
        return performance.perform2();
    }


}
