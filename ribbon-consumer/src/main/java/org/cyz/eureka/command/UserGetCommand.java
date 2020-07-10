package org.cyz.eureka.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import org.cyz.eureka.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserGetCommand extends HystrixCommand {

    private static final HystrixCommandKey getter_key = HystrixCommandKey.Factory.asKey("CommandKey");
    @Autowired
    private RestTemplate restTemplate;
    private Integer id;

    public UserGetCommand(Integer id) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetSetGet")).andCommandKey(getter_key));
        this.id = id;
    }

    @Override
    protected Object run() throws Exception {
        return restTemplate.getForObject("http://hello-service/user/getUserById?id={1}", User.class, id);
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
