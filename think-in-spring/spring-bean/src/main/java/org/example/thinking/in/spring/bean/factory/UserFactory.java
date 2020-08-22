package org.example.thinking.in.spring.bean.factory;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * User 抽象工厂类 {@link User}
 * @author WTY
 * @date 2020/8/20 0:04
 **/
public interface UserFactory {

    default User createUser(){
        System.err.println("抽象工厂方法");
        return User.createUser();
    }

}
