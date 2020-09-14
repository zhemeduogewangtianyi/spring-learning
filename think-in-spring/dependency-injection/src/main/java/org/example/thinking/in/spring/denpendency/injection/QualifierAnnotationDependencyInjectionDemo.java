package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.denpendency.injection.annotation.UserGroup;
import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * 演示 {@link Qualifier} 的方式进行限定注入
 * @see Qualifier
 * */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User defaultUser;

    @Qualifier(value = "user")
    @Autowired
    private User user;

    /** 分组限定注入 */

    //我们总共有 4 个 User 对象 User SuperUser user1 user2 ，其中 user1 和 user2 标注了 @Qualifier ，所以只会找到 User 和 SuperUser

    @Autowired
    private Collection<User> allUser;

    //限定注入 user1 和 user2
    @Qualifier
    @Autowired
    private Collection<User> qualifierUser;

    @Bean
    @Qualifier //进行逻辑分组
    public User User1(){
        return new User(8L,"88");
    }

    @Bean
    @Qualifier //进行逻辑分组
    public User User2(){
        return new User(9L,"99");
    }

    //扩展 @Qualifier

    @Autowired
    @UserGroup
    private Collection<User> groupUser;

    @Bean
    @UserGroup
    public User user3(){
        return createUser(10L);
    }

    @Bean
    @UserGroup
    public User user4(){
        return createUser(11L);
    }

    /** 静态工厂方法 */
    private static User createUser(Long id){
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 QualifierAnnotationDependencyInjectionDemo 作为 Configuration Class
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 文件，并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);


        System.out.println(demo.defaultUser);

        System.out.println(demo.user);

        //限定查找

        System.out.println(demo.allUser);

        System.out.println(demo.qualifierUser);

        System.out.println(demo.groupUser);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

}
