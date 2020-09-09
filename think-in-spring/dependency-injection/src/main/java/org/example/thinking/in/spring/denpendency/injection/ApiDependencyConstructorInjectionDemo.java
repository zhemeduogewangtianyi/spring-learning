package org.example.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 的方式演示 构造器 依赖注入
 * */
public class ApiDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 ApiDependencyConstructorInjectionDemo 作为 Configuration Class
        applicationContext.registerBeanDefinition("userHolder",createUserHolderBeanDefinition());

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 配置，并且创建 BeanDefinition ，注册到 BeanFactory 容器中
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

    /** 创建 UserHolder BeanDefinition */
    public static BeanDefinition createUserHolderBeanDefinition(){

        //通过 BeanDefinitionBuilder 的方式创建 BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);

        //添加 user 对象到构造方法
        beanDefinitionBuilder.addConstructorArgReference("user");

        //添加 superUser 对象到构造方法
        beanDefinitionBuilder.addConstructorArgReference("superUser");

        //返回 BeanDefinition
        return beanDefinitionBuilder.getBeanDefinition();
    }

}
