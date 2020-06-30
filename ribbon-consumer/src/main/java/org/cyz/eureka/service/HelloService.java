package org.cyz.eureka.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallBack")
    public String helloService() {
        log.info("======================= 调用服务 hello-service 开始 ========================");
        long startTime = System.currentTimeMillis();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://hello-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦", "柯南");
        long endTime = System.currentTimeMillis();
        log.info("开始：{}，结束：{}，总耗时：{}", startTime, endTime, endTime - startTime);
        log.info("======================= 调用服务 hello-service 结束 ========================");
        return responseEntity.getBody();
    }

    public String helloFallBack() {
        return "error";
    }
}
