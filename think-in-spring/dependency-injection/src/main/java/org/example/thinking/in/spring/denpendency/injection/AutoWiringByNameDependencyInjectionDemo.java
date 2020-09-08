package org.example.thinking.in.spring.denpendency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 通过 BeanName 自动绑定代码演示 - xml
 * */
public class AutoWiringByNameDependencyInjectionDemo {

    public static void main(String[] args) {

        //创建一个空的 BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //创建 XML Bean 定义读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        String resourcePath = "classpath:/META-INF/autowiring-dependency-setter-injection.xml";

        //加载 XML 资源文件，并且创建 BeanDefinition 对象
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //进行依赖查找
        UserHolder userHolder = defaultListableBeanFactory.getBean(UserHolder.class);

        /*
            通过名称的方式绑定到了 user 对象。。。通过类型的方式绑定到了 superUser 对象
            为什么通过 type 会绑定到 superUser 呢？
            因为在 dependency-lookup-context.xml 中，superUser 被定义为了 Primary...
         */
        System.out.println(userHolder);

    }

}
