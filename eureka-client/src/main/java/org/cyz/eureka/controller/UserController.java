package org.cyz.eureka.controller;

import org.cyz.eureka.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private DiscoveryClient client;

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public Object index() {
        Map<String, List<ServiceInstance>> msl = new HashMap<>();
        List<String> services = client.getServices();
        for (String service : services) {
            List<ServiceInstance> sis = client.getInstances(service);
            msl.put(service, sis);
            log.info("service: {}", service);
        }
        return msl;
    }

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    @ResponseBody
    public String hello(@RequestBody User user) {
        return String.format("Hello World！%s岁的%s", user.getAge(), user.getName());
    }

}
