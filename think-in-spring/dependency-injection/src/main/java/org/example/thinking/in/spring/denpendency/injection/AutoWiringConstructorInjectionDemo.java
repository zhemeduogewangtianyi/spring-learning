package org.example.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 自动绑定 - 通过构造方法的方式 演示
 * */
public class AutoWiringConstructorInjectionDemo {

    public static void main(String[] args) {

        //创建一个空的 BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //创建 XML 配置读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        String resourcePath = "classpath:/META-INF/autowiring-dependency-constructor-injection.xml";

        //读取 XML 信息，并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //进行依赖查找
        UserHolder userHolder = defaultListableBeanFactory.getBean(UserHolder.class);

        System.out.println(userHolder);

    }

}
