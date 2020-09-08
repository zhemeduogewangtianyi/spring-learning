package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {

        //创建 Spring BeanDefinition IoC 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // BeanDefinition 的方式注册 Configuration Class 配置类
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(BeanInstantiationExceptionDemo.class).getBeanDefinition();
        applicationContext.registerBeanDefinition("beanInstantiationExceptionDemo", beanDefinition);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        String bean = applicationContext.getBean(String.class);
        System.out.println(bean);

        //通过 BeanDefinition 的方式注册个接口
        BeanDefinitionBuilder registerInterfaceBean = BeanDefinitionBuilder.genericBeanDefinition(List.class);

        applicationContext.registerBeanDefinition("list",registerInterfaceBean.getBeanDefinition());

        //这里报错 BeanInstantiationException
        Object list = applicationContext.getBean("list");
        System.out.println(list);

        // 关闭 Spring 应用上下文
        applicationContext.close();

    }

    @Bean
    public String bean1(){
        return "bean1";
    }

}
