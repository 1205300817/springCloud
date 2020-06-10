package org.cyz.aop.pojo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author chengyz
 */
@Data
public class Student {

    private Integer id;

    @NotEmpty(message = "姓名不可为空")
    private String name;

    @NotNull(message = "年龄不可为空")
    private Integer age;

}
