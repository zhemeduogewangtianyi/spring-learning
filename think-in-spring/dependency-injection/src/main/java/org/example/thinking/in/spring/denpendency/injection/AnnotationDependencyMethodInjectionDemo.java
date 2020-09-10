package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.SuperUser;
import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 通过方法注入的方式演示依赖注入
 * */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder1;

    private UserHolder userHolder2;

    @Autowired
    public void initializingUserHolder1(UserHolder userHolder1) {
        this.userHolder1 = userHolder1;
    }

    @Resource
    public void initializingUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user, SuperUser superUser){
        return new UserHolder(user,superUser);
    }

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 AnnotationDependencyMethodInjectionDemo 作为 Configuration Class
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        UserHolder userHolder1 = demo.userHolder1;
        System.out.println(userHolder1);
        UserHolder userHolder2 = demo.userHolder2;
        System.out.println(userHolder2);
        System.err.println(userHolder1 == userHolder2);

        //关闭 Spring 应用上下文
        applicationContext.close();


    }

}
