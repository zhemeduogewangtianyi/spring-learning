package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体外部 Bean 注册示例
 * @author WTY
 * @date 2020/8/25 0:42
 **/
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //创建外部对象
        UserFactory userFactory = new DefaultUserFactory();

        //获取到上下文中的 BeanFactory
        ConfigurableListableBeanFactory configurableListableBeanFactory = applicationContext.getBeanFactory();

        // 注册 外部单例对象
        configurableListableBeanFactory.registerSingleton("userFactory",userFactory);

        //启动 Spring 容器上下文
        applicationContext.refresh();

        //通过依赖查找的方式获取到注册进 Spring IoC 容器的 UserFactory 对象
        UserFactory iocUserFactory = applicationContext.getBean("userFactory", UserFactory.class);

        //验证我们创建的外部对象 和 注册进 Spring IoC 容器的外部对象是不是同一个
        System.out.println("userFactory == userFactoryByLookup" + (userFactory == iocUserFactory));

        //如果没有找到就会抛出 NoSuchBeanDefinitionException
        UserFactory noSuchBeanDefinitionException = applicationContext.getBean("userFactory1111", UserFactory.class);

        //关闭 Spring 容器上下文
        applicationContext.close();

        //如果上下文关闭了会抛出 IllegalStateException
        UserFactory IllegalStateException = applicationContext.getBean("userFactory", UserFactory.class);

    }

}
