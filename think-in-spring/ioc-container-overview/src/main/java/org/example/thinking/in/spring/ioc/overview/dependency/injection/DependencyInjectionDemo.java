package org.example.thinking.in.spring.ioc.overview.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.example.thinking.in.spring.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入
 * @author WTY
 * @date 2020/8/15 17:28
 **/
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");


        /** 自定义 Bean */
        UserRepository userRepository = (UserRepository)beanFactory.getBean("userRepository");
        System.out.println(userRepository.toString());

        /**
            Question : 依赖查找和依赖注入都属于依赖，那么他们是否同源呢？
            到底依赖注入的来源在哪？是来自于Bean？还是其他地方？
         * */

        /** 依赖注入（内建依赖） */
        System.err.println(userRepository.getBeanFactory().equals(beanFactory));

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        System.err.println("UserRepository 里面的 BeanFactory : " + userRepository.getBeanFactory());
        System.err.println("ApplicationContext : " + applicationContext);
        System.err.println(userRepository.getBeanFactory().equals(applicationContext));

        /** 依赖查找 */
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        //ObjectFactory autowired 的时候，帮我们注入了一个ApplicationContext
        /** Question : 为什么 ApplicationContext 又是一个 BeanFactory 呢？ */
        System.err.println(objectFactory.getObject().equals(beanFactory));

        /** 依赖查找（报错） */
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        //这里看到的答案明显是否定的，依赖查找和依赖注入不同源

        /** 容易内建Bean */
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取 Environment 类型的 Bean" + environment);

        //谁才是 Spring IoC 的容器？
        whoIsIocContainer(userRepository,beanFactory);

    }


    private static void whoIsIocContainer(UserRepository userRepository ,BeanFactory beanFactory){
        System.err.println(userRepository.getBeanFactory().equals(beanFactory));
    }

    private static void whoIsIocContainer(UserRepository userRepository ,ApplicationContext beanFactory){
        System.err.println(userRepository.getBeanFactory().equals(beanFactory));
    }

}
