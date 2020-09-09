package org.example.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 基于 XML 配置元信息的方式 用 构造器 依赖注入代码示例
 * */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {

        //创建一个空的 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String resourcePath = "classpath:/META-INF/dependency-constructor-injection.xml";

        //读取 XML 文件，并且解析创建成 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //进行依赖查找
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);

        System.out.println(userHolder);



    }

}
