package org.example.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 资源的 Setter 依赖注入示例
 * */
public class XmlDependencySetterInjectionDemo {

    public static void main(String[] args) {

        //构造一个空的 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //创建 XML BeanDefinition 阅读器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/dependency-setter-injection.xml";

        //XML BeanDefinition 阅读器加载资源文件，并且生成 Spring 的 BeanDefinition 对象
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        //依赖查找并且创建 Bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);

    }

}
