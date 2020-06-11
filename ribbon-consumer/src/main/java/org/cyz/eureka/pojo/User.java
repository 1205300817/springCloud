package org.cyz.eureka.pojo;

import lombok.Data;

@Data
public class User {

    private String name;
    private Integer age;
    private String remark;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
