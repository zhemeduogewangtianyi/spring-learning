package org.example.thinking.in.spring.ioc.overview.dependency.domain;

import org.example.thinking.in.spring.ioc.overview.dependency.annotation.Super;

/**
 * 超级用户 - 演示 java 注解获取 Bean
 * @author WTY
 * @date 2020/8/14 0:15
 **/
@Super
public class SuperUser extends User {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /** 这里是 super.toString() ... */
    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
