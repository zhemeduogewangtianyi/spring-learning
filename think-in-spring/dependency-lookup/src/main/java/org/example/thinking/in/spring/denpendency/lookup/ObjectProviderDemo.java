package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 * */
//Configuration 是非必须的注解
public class ObjectProviderDemo {

    public static void main(String[] args) {

        //创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类注册为 AnnotationConfigApplicationContext 的 配置类 Config Class
        applicationContext.register(ObjectProviderDemo.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //通过 ObjectProvider 进行依赖查找
        lookupByObjectProvider(applicationContext);

        //关闭 Spring 应用上下文
        applicationContext.close();


    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        // spring 5.1 引入的。
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        String hello = beanProvider.getObject();
        System.out.println(hello);
    }

    @Bean
    public String helloWorld(){
        //如果 @Bean 没有定义 Bean Name 那么方法名就是 Bean Name
        return "Hello World !";
    }

}
