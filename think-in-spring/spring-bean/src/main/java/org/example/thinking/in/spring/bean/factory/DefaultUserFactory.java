package org.example.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 UserFactory 的实现 {@link UserFactory}
 * @author WTY
 * @date 2020/8/20 0:11
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

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


    //Spring Bean 销毁章节增加
    @PreDestroy
    public void preDestroy(){
        System.err.println("--------  @PreDestroy  DefaultUserFactory 销毁  ----------");
    }


    @Override
    public void destroy() throws Exception {
        System.err.println("--------  DisposableBean  DefaultUserFactory 销毁  ----------");
    }

    public void destroyWithBean(){
        System.err.println("--------  @Bean  DefaultUserFactory 销毁  ----------");
    }

    public void destroyWithXml(){
        System.err.println("--------  XML  DefaultUserFactory 销毁  ----------");
    }

    /** 覆盖 Object 的 finalize()  */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前的 DefaultUserFactory 对象正在被回收！！！");
    }
}
