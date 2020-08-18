package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解 BeanDefinition 注册示例 - AnnotationConfigApplicationContext
 * @author WTY
 * @date 2020/8/18 23:42
 **/
@Import(AnnotationBeanDefinitionDemo.ConfigurationClass.class)
public class AnnotationBeanDefinitionDemo {

    /*

            基于 Java 注解配置元信息示例：

            1:通过 @Bean 的方式定义 Bean
            2：通过 @Component 的方式定义 Bean
            3：通过 @Import 导入 Bean

            问题：这三种方式都是 Bean 吗？

            答：是的。

            问题：@Import 和 @Component 同时定义了两个 Bean ，属于重复定义。会不会生成两个 Bean ?

            答：不会重复注册。
            ConfigurationClass 所有 Beans : {org.example.thinking.in.spring.bean.definition.AnnotationBeanDefinitionDemo$ConfigurationClass=org.example.thinking.in.spring.bean.definition.AnnotationBeanDefinitionDemo$ConfigurationClass@16e2f39}

        */

    public static void main(String[] args) {

        //创建 BeanFactory
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class （配置类），这个类代替了我们的 XML 文件
//        annotationConfigApplicationContext.register(ConfigurationClass.class);

        //实验把我当前类作为一个 Bean ，同时把当前类里面的 ConfigurationClass 当做一个 Bean
        annotationConfigApplicationContext.register(AnnotationBeanDefinitionDemo.class);


        //通过 BeanDefinition JAVA API 来实现 BeanDefinition 注册，同一个上下文 beanName 要唯一，不要和下面 User 的 @Bean 的名字重复
        registerUserBeanDefinition(annotationConfigApplicationContext,"byNameUser",User.class);
        //非命名注册 BeanDefinition
        registerUserBeanDefinition(annotationConfigApplicationContext,null,User.class);

        //User 所有 Beans : {byNameUser=User{id=2, name='XXX'}, org.example.thinking.in.spring.ioc.overview.dependency.domain.User#0=User{id=2, name='XXX'}, user=User{id=1, name='xxx'}}

        /*
            这个 org.example.thinking.in.spring.ioc.overview.dependency.domain.User#0 哪里来的？

            BeanDefinitionReaderUtils.generateBeanName(BeanDefinition, BeanDefinitionRegistry, boolean) 里面有个

            // Top-level bean: use plain class name with unique suffix if necessary.
			return uniqueBeanName(generatedBeanName, registry);

			链路 ：
			    BeanDefinitionReaderUtils.registerWithGeneratorName()
			        generateBeanName()
                        uniqueBeanName()

           问题：为什么别名没有出现？

           答：别名并不是 Bean 真正的名称，只是一个映射而已。所谓映射就是通过别名可以找到 beanName 所以只出现了三个，不是四个。

        */

        //启动应用上下文
        annotationConfigApplicationContext.refresh();

        //按照类型依赖查找 Bean 的集合
        Map<String, ConfigurationClass> configBeans = annotationConfigApplicationContext.getBeansOfType(ConfigurationClass.class);
        Map<String, User> userBeans = annotationConfigApplicationContext.getBeansOfType(User.class);

        System.out.println("ConfigurationClass 所有 Beans : " + configBeans);
        System.out.println("User 所有 Beans : " + userBeans);


        //关闭 Spring 容器的应用上下文
        annotationConfigApplicationContext.close();


    }

    /**
     * 命名注册 BeanDefinitionReader#registerBeanDefinition
     *
     * @author WTY
     * @date 2020/8/19 0:23
     * @param registry BeanDefinition 注册器
     * @param beanName Bean 的名称
     * @param beanClass Bean 的 Class
     **/
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry,String beanName,Class<?> beanClass){
        //构建 BeanDefinitionBuilder
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);

        //赋值
        beanDefinitionBuilder.addPropertyValue("id",2L);
        beanDefinitionBuilder.addPropertyValue("name","XXX");

        //获取 BeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //如果 beanName 参数存在时
        if(StringUtils.hasText(beanName)){
            //命名方式注册 BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinition);
        }else{
            //非命名的方式注册 BeanDefinition
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition,registry);
        }


    }

    public static void registerBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry,Class<?> beanClass){
        registerUserBeanDefinition(beanDefinitionRegistry,null,beanClass);
    }

    @Component
    public static class ConfigurationClass{
        /**
         * 通过 java 注解方式定义一个 Bean，
         * 这个方法如何能被 Spring 应用上下文感知
         * 两种方式
         * 1 ： @Configuration
         *
         * */
        @Bean(name = {"user","alias-user"})
        public User user(){
            User user = new User();
            user.setId(1L);
            user.setName("xxx");
            return user;
        }
    }

}
