package org.example.thinking.in.spring.denpendency.lookup;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

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

        //依赖查找，当对象不存在的时候如何处理
        lookupAvailable(applicationContext);

        //依赖查找，通过 Stream 嗲代器的方式
        lookupByStreamOps(applicationContext);

        //关闭 Spring 应用上下文
        applicationContext.close();


    }

    /** 依赖查找,通过 Stream 操作的方式 */
    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        Supplier<Stream<String>> beanProviderStream = new Supplier<Stream<String>>() {
            @Override
            public Stream<String> get() {
                return beanProvider.stream();
            }
        };

        //比较基本的一个 stream
        beanProviderStream.get().forEach(beanName -> System.out.println("base : " + beanName));

        //比较 low 的一种方式
        Iterable<String> iterable = beanProvider;

        for(Iterator<String> str = iterable.iterator() ; str.hasNext() ;  ){
            String next = str.next();
            System.out.println("low : " + next);
        }

        //看上比较吊
        beanProviderStream.get().forEach(System.out::println);

    }

    /** 依赖查找，假设类不存在的补偿操作 */
    private static void lookupAvailable(AnnotationConfigApplicationContext applicationContext){

        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);

        //没有就创建一个 最简单的 lambda 表达式方式
//        User ifAvailable = beanProvider.getIfAvailable(() -> User.createUser());

        //有点 low 的方式。
//        User ifAvailable = beanProvider.getIfAvailable(new Supplier<User>() {
//            @Override
//            public User get() {
//                return User.createUser();
//            }
//        });

        //看上去比较吊的方式
        User ifAvailable = beanProvider.getIfAvailable(User::createUser);

        System.out.println(ifAvailable);
    }

    /** 依赖查找 调用 ObjectProvider 的父类 ObjectFactory 的 getObject() 方法 */
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        // spring 5.1 引入的。
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        String hello = beanProvider.getObject();
        System.out.println(hello);
    }

    @Primary
    @Bean
    public String helloWorld(){
        //如果 @Bean 没有定义 Bean Name 那么方法名就是 Bean Name
        return "Hello World !";
    }

    @Bean
    public String ddMessage(){
        return "ding ding message";
    }

}
