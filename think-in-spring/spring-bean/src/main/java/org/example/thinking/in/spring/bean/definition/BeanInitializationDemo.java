package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 初始化 Demo
 * @author WTY
 * @date 2020/8/22 13:59
 **/
//@Configuration //Configuration Class，这里可以加也可以不加
public class BeanInitializationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(BeanInitializationDemo.class);

        System.out.println("Spring 应用上下文开始启动...");

        //启动 Spring 应用上下文
        applicationContext.refresh();

        // 如果 Bean 标注了延迟加载，那么应用上下文启动后，进行依赖查找的时候才会去初始化 Bean，反之，启动的时候初始化 Bean
        System.out.println("Spring 应用上下文启动成功...");

        //依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

//        System.out.println(userFactory.createUser());



        //关闭 Spring 应用上下文
        System.out.println("Spring 应用上下文开始关闭...");
        applicationContext.close();
        System.out.println("Spring 应用上下文关闭完成...");



        System.err.println("\r\n 演示 XML 配置读取，初始化Bean \r\n");

//        //xml 方式实现
//        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-initialization-and-destroy-context.xml");
//        UserFactory bean = classPathXmlApplicationContext.getBean(UserFactory.class);
//
//        classPathXmlApplicationContext.refresh();
//        classPathXmlApplicationContext.close();

    }

    @Bean(initMethod = "initDefaultUserFactory",destroyMethod = "destroyWithBean")
//    @Lazy(value = true)
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
