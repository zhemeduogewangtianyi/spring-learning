package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.denpendency.injection.annotation.InjectedUser;
import org.example.thinking.in.spring.denpendency.injection.annotation.MyAutowired;
import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * 依赖注入处理过程示例代码
 * */
@Configuration
public class AnnotationDependencyInjectionResolutionDemo {

//    @Autowired
//    @Lazy
//    private User lazyUser;

    /*
        我们刚才也在 README.md 中说了，一个大概完整的 DependencyDescriptor 是蕴含了一下 6 个要素的
        （required = true） + 实时注入 + User.class（通过类型进行依赖查找） + （eager = true） + user（字段名称） + 是否首要（Primary = true）
    */
    @Autowired
    private User user;

    @Inject
    private User injectedUser;

    // key 就是 beanName，value 就是返回的 User 对象 -> 集合类型的依赖注入
//    @Autowired
//    private Map<String,User> users;

    //这个是有填充的 肯定是选 primary，因此这里返回的 User 是 SuperUser
    @MyAutowired
    private Optional<User> userOptional;

    @InjectedUser
    public User getInjectedUser;

//    @Bean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor injectedUserAutowiredAnnotationBeanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
//        return beanPostProcessor;
//    }

    /** 不安全，某个注解不存在 */
//    @Bean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
//    public static AutowiredAnnotationBeanPostProcessor injectedUserAutowiredAnnotationBeanPostProcessor(){
//        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
//        Set<Class<? extends Annotation>> set = new LinkedHashSet<>(Arrays.asList(Autowired.class,Inject.class,MyAutowired.class,InjectedUser.class));
//        beanPostProcessor.setAutowiredAnnotationTypes(set);
//        return beanPostProcessor;
//    }

    @Bean
    @Order(value = Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor injectedUserAutowiredAnnotationBeanPostProcessor(){
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }


    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 AnnotationDependencyInjectionResolutionDemo 作为 Configuration Class -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 并且创建 BeanDefinition 注册到 BeanDefinitionRegister 里面
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 容器上下文
        applicationContext.refresh();

        //通过依赖查找的方式，从 Spring 上下文中获取 this -> 依赖查找
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        User user = demo.user;

        System.out.println(user);

//        System.out.println();

//        Map<String, User> users = demo.users;

//        System.out.println(users);
//
//        System.out.println();
//
        Optional<User> userOptional = demo.userOptional;

        System.out.println("userOptional.get()  " + userOptional.get());
//
//        System.out.println();
//
//        User lazyUser = demo.lazyUser;
//
//        System.out.println(lazyUser);

        System.out.println("injected user : " + demo.injectedUser);

        System.out.println("getInjectedUser user : " + demo.getInjectedUser);

        //关闭 Spring 容器上下文
        applicationContext.close();

    }

}
