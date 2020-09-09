package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 基于 Java 注解的方式 演示 构造器 依赖注入
 * */
public class AnnotationDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 AnnotationDependencyConstructorInjectionDemo 作为 Configuration Class
        applicationContext.register(AnnotationConfigApplicationContext.class);

        //创建 XML 读取器，为了实例化我们的 User 对象
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-constructor-injection.xml";

        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

}
