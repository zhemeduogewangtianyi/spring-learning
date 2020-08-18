package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例 - 通过依赖查找的方式来进行演示 Spring Bean 的 别名
 * @author WTY
 * @date 2020/8/18 23:02
 **/
public class BeanAliasDemo {

    public static void main(String[] args) {

        //配置 XML 文件
        //启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        // 通过别名 alias-user 查找曾用名为 user 的 User 对象
        User aliasUser = beanFactory.getBean("alias-user", User.class);

        //获取老的 user 对象
        User user = beanFactory.getBean("user", User.class);

        //true 说明这两个对象是同一个对象
        System.out.println("alias-user == user : " + (aliasUser == user));


        /*
            依赖查找也可以通过 #别名# 的方式去进行查找，
            可想而知，底层肯定有一个 beanName 和 beanAliasName 的一个映射，
            好比找到别名之后就可以找到对应的名称，再通过名称去找到 对应的 Bean，相当于一个间接的关系

            在 Bean 的生命周期和 Bean 的元信息配置的时候具体展开说明
        */

    }

}
