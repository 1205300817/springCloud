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
@RequestMapping("/aop")
public class AopController {

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(String name) {
//        System.out.println(1 / 0);
        return "hello " + name;
    }

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    public R addStudent(@RequestBody @Valid Student student) {
        return R.ok(student.getName() + " 添加成功");
    }

}
