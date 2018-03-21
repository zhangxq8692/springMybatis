package com.zhangxq.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhangxq.modules.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zhangxq
 * @version: 1.0
 * @date: 2018/2/9 10:56
 * @description:
 */
public class Tentity extends BaseEntity implements Serializable{
    @JsonProperty("myName")
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
