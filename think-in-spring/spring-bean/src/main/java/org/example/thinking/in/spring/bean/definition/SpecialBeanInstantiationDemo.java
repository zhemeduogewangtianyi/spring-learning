package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化示例
 * @author WTY
 * @date 2020/8/20 0:45
 **/
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 XML 文件 -> special-bean-instantiation-context.xml

        //创建 BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        // SPI ServiceLoader 调用演示
        serviceLoaderDemo();

        //通过 xml 配置的 ServiceLoadFactory 指定 serviceType 加载 META-INF/services 下配置的的 UserFactory 的子类
        ServiceLoader<UserFactory> userFactoryByServiceLoader = beanFactory.getBean("userFactoryByServiceLoader", ServiceLoader.class);

        //通过 Spring 实例化 ServiceLoader 来获取 UserFactory
        displayServiceLoaderDemo(userFactoryByServiceLoader);


        //2：通过 AutowireCapableBeanFactory 来实例化 UserFactory
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        //通过 ApplicationContext 来获取 AutowireCapableBeanFactory 对象，因为 ClassPathXmlApplicationContext 无法获取到 AutowireCapableBeanFactory 这个对象
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

        //创建 Spring Bean 的时候一定不要用接口。。。
//        UserFactory esayUserFactoryByAutowireCapableBeanFactory = autowireCapableBeanFactory.createBean(UserFactory.class);

        UserFactory esayUserFactoryByAutowireCapableBeanFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);

        System.out.println(esayUserFactoryByAutowireCapableBeanFactory.createUser());

        UserFactory instantiationUserFactoryByAutowireCapableBeanFactory = (UserFactory)autowireCapableBeanFactory.createBean(DefaultUserFactory.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);

        System.out.println(instantiationUserFactoryByAutowireCapableBeanFactory.createUser());


        //通过 AnnotationConfigApplicationContext 创建 Bean

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DefaultUserFactory.class);
        annotationConfigApplicationContext.refresh();
        DefaultUserFactory bean = annotationConfigApplicationContext.getBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());

    }


    /** 这个会逐一输出多个 实现 */
    public static void serviceLoaderDemo(){

        //通过传入接口 + 类加载器的方式去获得 UserFactory
        ServiceLoader<UserFactory> userFactoryServiceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());

        displayServiceLoaderDemo(userFactoryServiceLoader);
    }

    /** 这里只会输出一个实现 */
    public static void displayServiceLoaderDemo(ServiceLoader<UserFactory> userFactoryServiceLoader){
        //迭代器遍历取出 - 这里会去重的。。。算得上是 jdk 里面的 翻转控制，遵循好莱坞原则 - 你不要来找我，我来找你。
        Iterator<UserFactory> car = userFactoryServiceLoader.iterator();
        while(car.hasNext()){
            UserFactory next = car.next();
            System.out.println(next.createUser());
        }
    }



}
