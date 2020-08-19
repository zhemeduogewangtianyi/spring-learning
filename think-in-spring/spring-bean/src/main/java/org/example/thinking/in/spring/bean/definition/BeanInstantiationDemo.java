package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化 示例
 * @author WTY
 * @date 2020/8/19 23:51
 **/
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 XML 文件 -> bean-instantiation-context.xml

        //启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        //有参构造器创建
        User constructorUserArg = beanFactory.getBean("constructor-user-arg", User.class);
        System.out.println("constructorUserArg ：" + constructorUserArg);

        //空载构造器创建
        User constructorUserNoArg = beanFactory.getBean("constructor-user-no-arg", User.class);
        System.out.println("constructorUserNoArg ：" + constructorUserNoArg);

        //获取 静态方法实例的 User Bean 对象
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println("userByStaticMethod ：" + userByStaticMethod);

        //获取 实例方法创建的 User Bean 对象
        User userByInstantiationMethod = beanFactory.getBean("user-by-instantiation-method", User.class);
        System.out.println("userByInstantiationMethod ：" + userByInstantiationMethod);


        System.err.println("静态方法实例的 User == 实例方法创建的 Bean 吗 -> " + (userByStaticMethod == userByInstantiationMethod));

        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println("userByFactoryBean ：" + userByFactoryBean);

        System.out.println("实例方法创建的 User Bean 和 UserFactoryBean 创建的 User 是同一个吗？ -> " + (userByInstantiationMethod == userByFactoryBean));


    }

}
