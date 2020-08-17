package org.example.thinking.in.spring.ioc.overview.dependency.container;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * 注解版 {@link ApplicationContext} 作为 IoC 容器
 * @author WTY
 * @date 2020/8/16 12:45
 **/
public class AnnotationApplicationContextAsIocContainerDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        //注解配置 ApplicationContext 的实现 - @纪波 大神给的路子。
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        //将当前类 作为 Configuration class 注册 bean  - 相当于有了 @Configuration
        annotationConfigApplicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);

        //启动应用上下文
        annotationConfigApplicationContext.refresh();

        //依赖查找 加入没有 @Bean 来实例化对象的话，是找不到 xml 里面配置的 对象的
        lookupCollectionsByType(annotationConfigApplicationContext);

        //关闭应用上下文
        annotationConfigApplicationContext.close();

    }

    /**
     * 通过 Java 注解的方式定义了一个 Bean
     * @author WTY
     * @date 2020/8/16 12:55
     * @param
     * @return org.example.thinking.in.spring.ioc.overview.dependency.domain.User
     **/
    @Bean
    public User user(){
        User user = new User();
        user.setId(1L);
        user.setName("name");
        return user;
    }

    private static void lookupCollectionsByType(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.err.println(String.format("查找到的 User 集合为 ： %s",beansOfType.toString()));
        }
    }

}
