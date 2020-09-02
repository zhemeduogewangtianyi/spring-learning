package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * 类型安全的依赖查找示例
 * */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 TypeSafetyDependencyLookupDemo 为配置类 Configuration Class
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        //启动 Spring IoC 容器上下文
        applicationContext.refresh();

        /** 单一类型 */

        //演示 BeanFactory # getBean 方法进行依赖查找的安全性
        displayBeanFactoryGetBean(applicationContext);

        //演示 ObjectBeanFactory # getBean 方法进行依赖查找的安全性
        displayObjectBeanFactoryGetObject(applicationContext.getBeanProvider(Date.class));

        //展示 ObjectProvider # getIfAvailable 方法进行依赖查找的安全性
        displayObjectProviderIfAvailableOps(applicationContext.getBeanProvider(Date.class));


        /** 集合类型 */

        //演示 ListableBeanFactory # getBeansOfType 进行依赖查找的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);

        //演示 ObjectProvider # stream 方法进行依赖查找的安全性
        diaplayObjectProviderStreamOps(applicationContext.getBeanProvider(Date.class));

        //关闭 Spring IoC 容器上下文
        applicationContext.close();

    }

    private static void diaplayObjectProviderStreamOps(ObjectProvider<Date> beanProvider) {

        printBeansException("diaplayObjectProviderStreamOps", () -> {
            beanProvider.forEach(System.out::println);
        });

    }

    /** 演示 ListableBeanFactory # getBeansOfType 进行依赖查找的安全性 */
    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {

        printBeansException("displayListableBeanFactoryGetBeansOfType",() -> listableBeanFactory.getBeansOfType(Date.class).forEach((s, date) -> System.err.println(s + " " + date)));

    }

    /** 展示 ObjectProvider # getIfAvailable 方法进行依赖查找的安全性 */
    private static void displayObjectProviderIfAvailableOps(ObjectProvider<Date> objectProvider) {

        printBeansException("displayObjectProviderIfAvailableOps",() -> System.err.println(objectProvider.getIfAvailable()));
//        printBeansException("displayObjectProviderIfAvailableOps",() -> System.err.println(objectProvider.getIfAvailable(Date::new)));

    }

    /** 演示 BeanFactory # getBean 方法进行依赖查找的安全性  */
    private static void displayBeanFactoryGetBean(BeanFactory beanFactory){

        printBeansException("displayBeanFactoryGetBean",() -> System.out.println(beanFactory.getBean(Date.class)));

    }

    /** 演示 ObjectBeanFactory # getBean 方法进行依赖查找的安全性  */
    private static void displayObjectBeanFactoryGetObject(ObjectFactory<Date> objectFactory){

        printBeansException("displayObjectBeanFactoryGetObject ", () -> System.out.println(objectFactory.getObject()));

    }

    /** 输出 */
    private static void printBeansException(String msg,Runnable runnable){
        System.err.println("\r\n current display : " + msg);
        try{
            runnable.run();
        }catch(Exception e){
            //这玩意是县城安全的，老铁们别在线上玩。。有发生死锁的老铁打来骂人热线。。。
            e.printStackTrace();
        }
    }

}
