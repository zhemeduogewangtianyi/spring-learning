package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 配置元信息的方式 演示 Setter 注入
 * */
public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {

        //创建 BeanDefinition 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 UserHolder 为 Configuration Class
//        applicationContext.register(UserHolder.class);

        //创建 UserHolderBeanDefinition
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();

        //注册 UserHolderBeanDefinition 到 容器中
        applicationContext.registerBeanDefinition("userHolder",userHolderBeanDefinition);

        //创建 XML Bean 定义读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //加载 XML 资源，并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);


        //关闭 Spring 应用上下文
        applicationContext.close();



    }

    /** 创建 UserHolder BeanDefinition 方法 */
    public static BeanDefinition createUserHolderBeanDefinition(){

        //创建 UserHolder Bean 定义建造器
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);

        //为 UserHolder 中的 user 对象赋值，superUser 为 xml 加载到的对象
        beanDefinitionBuilder.addPropertyReference("user","superUser");

        //返回 Bean 定义
        return beanDefinitionBuilder.getBeanDefinition();

    }

}
