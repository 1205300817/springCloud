package org.cyz.eureka.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.cyz.eureka.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import rx.Observable;

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

    public Observable<User> getUserById(Integer id) {
        return Observable.create(subscriber -> {
            try {
                if (subscriber.isUnsubscribed()) {
                    String ret = restTemplate.getForObject("http://hello-service/hello/sayHello?name={1}&remark={2}", String.class, "多来梦", id);
                    User user = new User();
                    user.setRemark(ret);
                    subscriber.onNext(user);
                    subscriber.onCompleted();
                }
            } catch (RestClientException e) {
                subscriber.onError(e);
            }
        });
    }

    @CacheResult(cacheKeyMethod = "getUserByAgeCacheKey")
    @HystrixCommand(
            commandKey = "commandKey1",
            groupKey = "UserGroup",
            threadPoolKey = "getUserByIdThread",
            commandProperties = {
                    @HystrixProperty(name = "requestCache.enabled", value = "true")
            })
    public User getUserByAge(Integer age) {
        System.out.println("执行方法：getUserByAge()");
        return restTemplate.getForObject("http://hello-service/user/getUserById?id={1}", User.class, age);
    }

    public String getUserByAgeCacheKey(Integer age) {
        return String.valueOf(age);
    }

}
