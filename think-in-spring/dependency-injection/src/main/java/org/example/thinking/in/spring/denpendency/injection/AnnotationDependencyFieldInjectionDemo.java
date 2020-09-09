package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 基于注解进行 字段 依赖注入演示
 * */
public class AnnotationDependencyFieldInjectionDemo {

//    @Resource
//    private static UserHolder userHolder;

//    @Autowired
//    private static UserHolder userHolder;

    @Autowired
    private UserHolder userHolder;

    @Resource
    private UserHolder userHolder2;

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 AnnotationDependencyFieldInjectionDemo 作为 Configuration Class  ->  Java Bean
        applicationContext.register(AnnotationDependencyFieldInjectionDemo.class);

        //创建 XML 文件读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 配置元信息，并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //获取到 AnnotationDependencyFieldInjectionDemo bean
        AnnotationDependencyFieldInjectionDemo demo = applicationContext.getBean(AnnotationDependencyFieldInjectionDemo.class);

        //获取 userHolder 字段
        UserHolder userHolder = demo.userHolder;

        System.out.println(userHolder);

        //获取 userHolder 字段
        UserHolder userHolder2 = demo.userHolder2;

        System.out.println(userHolder2);

        System.err.println(userHolder == userHolder2);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user, SuperUser superUser){
        return new UserHolder(user,superUser);
    }

}
