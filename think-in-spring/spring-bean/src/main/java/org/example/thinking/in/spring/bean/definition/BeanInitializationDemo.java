package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

//        System.out.println(userFactory.createUser());



        //关闭 Spring 应用上下文
        applicationContext.close();


        System.err.println("\r\n 演示 XML 配置读取，初始化Bean \r\n");

        //xml 方式实现
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-initialization-context.xml");
        UserFactory bean = classPathXmlApplicationContext.getBean(UserFactory.class);

        classPathXmlApplicationContext.refresh();
        classPathXmlApplicationContext.close();

    }

    @Bean(initMethod = "initDefaultUserFactory")
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}
