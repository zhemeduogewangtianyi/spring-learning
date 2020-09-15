package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

/**
 * 通过 {@link ObjectProvider} 的方式演示延迟依赖注入
 * */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Autowired
    private ObjectFactory<Collection<User>> userObjectFactory;

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 LazyAnnotationDependencyInjectionDemo 作为 Configuration Class
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);

        System.out.println(demo.userObjectProvider.getIfAvailable());

        System.out.println();

        //通过 ObjectProvider 输出 dependency-lookup-context.xml 里面的所有配置的 user 对象 -- ObjectProvider 继承了 ObjectFactory
        demo.userObjectProvider.forEach(System.out::println);

        System.out.println();

        //通过 ObjectFactory 进行集合依赖输入
        demo.userObjectFactory.getObject().forEach(System.out::println);

        //关闭 Spring 应用上下文
        applicationContext.close();


    }

}
