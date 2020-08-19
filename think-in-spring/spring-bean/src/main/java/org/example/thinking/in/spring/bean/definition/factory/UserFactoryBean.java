package org.example.thinking.in.spring.bean.definition.factory;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} User FactoryBean 的实现 {@link org.springframework.beans.factory.FactoryBean}
 * @author WTY
 * @date 2020/8/20 0:32
 **/
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    //Spring 5 之后 isSingleton() 用了 java 8 的 default 方法，默认为单例。

}
