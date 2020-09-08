package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 基于 Java 注解的方式演示 Setter 注入
 * */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {

        //创建 BeanDefinition 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //创建 XML 资源读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-setter-injection.xml";

        //读取 XML 资源文件并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //注册 AnnotationDependencySetterInjectionDemo 作为 Configuration Class
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

    /** 方法的依赖注入 */
    @Bean
    @Primary
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

}
