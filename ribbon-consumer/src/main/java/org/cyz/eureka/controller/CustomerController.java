package org.cyz.eureka.controller;

import org.cyz.eureka.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦","柯南");
//        return responseEntity.getBody();

        // getForObject
        // 三种重载方式
        // 少一个获取body的步骤
//        return restTemplate.getForObject("http://hello-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦1","柯南1");
//        return restTemplate.getForObject("http://user-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦1","柯南1");

        User user = new User("陈芊芊", 19);
        ResponseEntity<String> responseEntity= restTemplate.postForEntity("http://user-service/user/hello", user, String.class);
        return responseEntity.getBody();
    }

}
