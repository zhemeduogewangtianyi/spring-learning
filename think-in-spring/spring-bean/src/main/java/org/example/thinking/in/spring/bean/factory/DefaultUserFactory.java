package org.example.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * 默认 UserFactory 的实现 {@link UserFactory}
 * @author WTY
 * @date 2020/8/20 0:11
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean {

    // Spring Bean 初始化章节增加
    @PostConstruct
    public void init(){
        System.out.println("--------  @PostConstruct  DefaultUserFactory 初始化  ----------");
    }


    public void initDefaultUserFactory(){
        System.out.println("--------  @Bean 自定义初始化方法：  DefaultUserFactory#initDefaultUserFactory() 初始化  ----------");
    }

    public void xmlInitDefaultUserFactory(){
        System.out.println("--------  XML 自定义初始化方法：  InitializingBean#afterPropertiesSet() 初始化  ----------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------  InitializingBean 初始化方法：  InitializingBean#afterPropertiesSet() 初始化  ----------");
    }


}
