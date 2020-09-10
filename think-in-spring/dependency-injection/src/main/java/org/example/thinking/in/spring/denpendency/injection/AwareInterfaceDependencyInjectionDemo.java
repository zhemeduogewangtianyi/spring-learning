package org.example.thinking.in.spring.denpendency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link Aware} 的方式演示接口回调依赖注入
 * */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        //注册 AwareInterFaceDependencyInjectionDemo 作为 Configuration Class
        context.register(AwareInterfaceDependencyInjectionDemo.class);

        //启动 Spring 上下文
        context.refresh();

        System.out.println(beanFactory);
        System.out.println(applicationContext);
        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);

        //关闭 Spring 上下文
        context.close();


    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
