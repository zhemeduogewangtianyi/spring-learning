package org.example.thinking.in.spring.ioc.overview.dependency.lookup;

import org.example.thinking.in.spring.ioc.overview.dependency.annotation.Super;
import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找 - 通过名称的方式来查找 -- 1
 * @author WTY
 * @date 2020/8/13 23:18
 **/
public class DependencyLookupDemo {

    public static void main(String[] args) {

        //配置 xml 配置文件
        //启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-lookup-context.xml");
        BeanFactory classPathBeanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-lookup-context.xml");

        //根据名称查找
        //实时查找
        lookupInRealTime(classPathBeanFactory);

        //延时查找
        lookupInLazy(classPathBeanFactory);


        System.out.println();


        //根据类型查找 单个 bean spring3.0开始支持 泛型 ，并且全面支持 java 5
        lookupByType(classPathBeanFactory);

        //根据类型查找 集合 bean
        lookupByCollectionType(classPathBeanFactory);


        System.out.println();


        //根据 Bean 名称和 Bean类型查找
        lookupByBeanNameAndBeanType(classPathBeanFactory);


        System.out.println();


        //通过注解查找 单个 Bean
        lookupByAnnotationType(classPathBeanFactory);

    }




    //实时查找
    private static void lookupInRealTime(BeanFactory beanFactory){
        //实时查找
        User user = (User)beanFactory.getBean("user");
        System.out.println("实时查找 " + user);
    }

    //延时查找 ObjectFactory
    private static void lookupInLazy(BeanFactory beanFactory){

        // ObjectFactory 、 BeanFactory 、FactoryBean有什么区别？
        /*
            ObjectFactory 有一个 FactoryBean的实现 ObjectFactoryCreatingFactoryBean
        * */
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactoryBean");
        User user = objectFactory.getObject();
        System.out.println("延时查找 " + user);
    }

    //根据类型查找 单个
    private static void lookupByType(BeanFactory beanFactory) {
        User bean = beanFactory.getBean(User.class);
        System.out.println("[实时查找] 根据类型查找 单个 Bean 对象 " + bean);
    }

    //根据类型查找 集合
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("[实时查找] 根据类型查找 集合 Bean 对象 " + users.toString());
        }
    }

    //根据 Bean 名称和 Bean类型查找
    private static void lookupByBeanNameAndBeanType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("[实时查找] 根据 Bean 名称和 Bean类型查找 " + user.toString());
    }

    //通过注解查找 单个 Bean
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> superUsers = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("[实时查找] 查找 @Super 注解标注 所有 Bean 对象 " + superUsers.toString());
        }
    }

}
