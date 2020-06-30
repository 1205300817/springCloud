package org.cyz.eureka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hello")
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    private final Logger log = LoggerFactory.getLogger(HelloController.class);

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

    @RequestMapping(value = "sayHello", method = RequestMethod.GET)
    public String sayHello(@RequestParam("name") String name, @RequestParam("remark") String remark) {
        log.info("name: {}, remark: {}", name, remark);
        return "Hello World！" + name + remark;
    }

}
