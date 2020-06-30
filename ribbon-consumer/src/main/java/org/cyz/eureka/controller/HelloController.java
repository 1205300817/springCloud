package org.cyz.eureka.controller;

import org.cyz.eureka.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RequestMapping(value = "hello")
@RestController
public class HelloController {

    @Autowired
    private DiscoveryClient client;
    @Autowired
    private HelloService helloService;

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Object index(){
        Map<String, List<ServiceInstance>> msl = new HashMap<>();
        List<String> services = client.getServices();
        for (String service : services) {
            List<ServiceInstance> sis = client.getInstances(service);
            msl.put(service, sis);
            log.info("service: {}",service);
        }
        return msl;
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello() {
        return helloService.helloService();
    }

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public Object test(){
        // 返回列表视图，不允许修改
        Collections.unmodifiableList(new ArrayList<>());
        return null;
    }

}
