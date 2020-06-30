package org.cyz.eureka.controller;

import org.cyz.eureka.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "customer")
@RestController
public class CustomerController {

    @Autowired
    RestTemplate restTemplate;

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/ribbon-consumer", method = RequestMethod.GET)
    public String index() {
        return restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {

        // getForEntity
        // 三种重载方式
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦", "柯南");
        return responseEntity.getBody();

        // getForObject
        // 三种重载方式
        // 少一个获取body的步骤
//        return restTemplate.getForObject("http://hello-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦1","柯南1");
//        return restTemplate.getForObject("http://user-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦1","柯南1");

    }

    @RequestMapping(value = "/sayHello2", method = RequestMethod.GET)
    public String sayHello2() {
        return restTemplate.getForObject("http://hello-service/user/sayHello?name={1}&remark={2}", String.class, "多来梦2", "柯南2");
    }

    @RequestMapping(value = "/sayHello3", method = RequestMethod.POST)
    public String sayHello3() {
        User user = new User("陈芊芊", 19);
        Map<String,String> params=new HashMap<>(1);
        params.put("name", "陈千千");
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://hello-service/user/hello", params, String.class);
        return responseEntity.getBody();
    }

}
