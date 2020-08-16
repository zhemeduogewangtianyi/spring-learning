package org.example.thinking.in.spring.ioc.overview.dependency.container;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * {@link BeanFactory} 作为 IoC 容器示例
 * @author WTY
 * @date 2020/8/16 12:23
 **/
public class BeanFactoryAsIocContainerDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器 怎么创建是个问题。。
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        /*
            加载配置 （找到 XmlBeanDefinitionReader.java 这个类） 看构造方法发现需要 一个 BeanDefinitionRegister 这个类，
            DefaultListableBeanFactory 这个类里面实现了 BeanDefinitionRegister 这个类。
            所以把 DefaultListableBeanFactory 丢给 XmlBeanDefinitionReader 是没啥问题的。
         */
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //加载资源 -> 返回一个 int ，int代表找到了多少个 bean
        int beanDefinitionsCount = beanDefinitionReader.loadBeanDefinitions(location);
        System.out.println(String.format("找到了 %s 个 BeanDefinition !",beanDefinitionsCount));

        /*
            证明不用 ApplicationContext ，也能加载资源文件。。但是没有事件、资源管理啥的那一堆东西。
            场景：
                1：不用ApplicationContext扩展的东西的时候，读取 xml 文件进行 Bean 实例化，可以用这个玩意去除一些不必要的东西。
        */
        lookupCollectionByType(defaultListableBeanFactory);


    }

    private static void lookupCollectionByType(BeanFactory beanFactory){
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.err.println(String.format("找到所有的 User 集合对象 %s",beansOfType.toString()));
        }
    }

}
