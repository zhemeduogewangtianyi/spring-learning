# 									Spring learning



spring 官方文档：https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core



spring github地址：https://github.com/spring-projects/spring-framework



## 1：构建一个空的项目提交







## 2：建立 thinking-spring 模块

##### pom文件引入SpringFrameWork的相关jar

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>think-in-spring</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>ioc-container-overview</module>
    </modules>

    <properties>
        <spring.version>5.2.2.RELEASE</spring.version>
    </properties>

    <!-- spring 依赖管理 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webflux</artifactId>
                <version>${spring.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!-- spring 依赖 -->
    <dependencies>

         <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
         </dependency>
             
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webflux</artifactId>
        </dependency>

    </dependencies>

</project>
```



## 3：开始研究IOC的依赖查找

##### 1：Spring-IOC 依赖查找

· 根据 Bean 名称查找：

​	· 实时查找

​	· 延时查找

· 根据 Bean 类型查找：

​	· 单个 Bean 对象

​	· 集合 Bean 对象

· 根据 Bean 名称 + 类型查找：

· 根据 Java 注解查找：

​	· 单个 Bean 对象

​	· 集合 Bean 对象



##### 建立  ioc-container-overview  模块,pom文件修改如下



##### 2：thinking-spring：

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>think-in-spring</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>

    <modules>
        <module>ioc-container-overview</module>
    </modules>

    <properties>
        <spring.version>5.2.2.RELEASE</spring.version>
    </properties>

    <!-- spring 依赖管理 -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-context</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-jdbc</artifactId>
                <version>${spring.version}</version>
            </dependency>

            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-webflux</artifactId>
                <version>${spring.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <!-- spring 依赖 -->
    <dependencies>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webflux</artifactId>
        </dependency>

    </dependencies>

</project>
```



##### 3：ioc-container-overview      pom如下

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>think-in-spring</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
        <relativePath>../pom.xml</relativePath>
    </parent>

    <modelVersion>4.0.0</modelVersion>

    <artifactId>ioc-container-overview</artifactId>

    <dependencies>
        <!-- Spring Ioc 核心依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
    </dependencies>

</project>
```



##### 文件见代码：

##### dependency-lookup-context.xml 		--- 	Bean配置文件

##### User.java			----	用户实体

##### DependencyLookupDemo.java		---  启动入口

##### SuperUser.java 	---  超级用户实体，注解获取Bean

##### Super.java			---	自定义注解





## 4：开始研究Ioc依赖注入 - 2020年8月15日

##### 1：Spring Ioc 依赖注入

· 根据 Bean 名称注入

· 根据 Bean 类型注入

​	· 单个 Bean 对象

​	· 集合 Bean 对象

· 注入容器内建 Bean 对象

· 注入非 Bean 对象

· 注入类型

​	· 实施注入

​	· 延迟注入



##### 2：新增代码

建立 injection 目录在 dependency 目录下

建立DependencyInjectionDemo.java

```java
package org.example.thinking.in.spring.ioc.overview.dependency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.example.thinking.in.spring.ioc.overview.dependency.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖注入
 * @author WTY
 * @date 2020/8/15 17:28
 **/
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");
        UserRepository userRepository = (UserRepository)beanFactory.getBean("userRepository");
        System.out.println(userRepository.toString());

        /** Question : 依赖查找和依赖注入都属于依赖，那么他们是否同源呢？ */

        //依赖注入
        System.err.println(userRepository.getBeanFactory().equals(beanFactory));

        //依赖查找
        ObjectFactory<ApplicationContext> objectFactory = userRepository.getObjectFactory();
        System.out.println(objectFactory.getObject());
        //ObjectFactory autowired 的时候，帮我们注入了一个ApplicationContext
        /** Question : 为什么 ApplicationContext 又是一个 BeanFactory 呢？ */
        System.err.println(objectFactory.getObject().equals(beanFactory));

        //依赖查找（一个错误的例子）
//        System.out.println(beanFactory.getBean(BeanFactory.class));

        //这里看到的答案明显是否定的，依赖查找和依赖注入不同源
    }

}

```





建立 repository 目录在 dependency 目录下

建立 UserRepository.java

```java
package org.example.thinking.in.spring.ioc.overview.dependency.repository;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * 用户信息仓库
 * @author WTY
 * @date 2020/8/15 17:37
 **/
public class UserRepository {

    //自定义 Bean
    private Collection<User> users;

    //内建 非 Bean 对象（依赖）
    private BeanFactory beanFactory;

    private ObjectFactory<ApplicationContext> objectFactory;

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public ObjectFactory<ApplicationContext> getObjectFactory() {
        return objectFactory;
    }

    public void setObjectFactory(ObjectFactory<ApplicationContext> objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                '}';
    }
}

```



建立 dependency-injection-context.xml 在 resources/META-INF

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="
        http://www.springframework.org/schema/beans https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd">

    <!-- 通过导入复用 dependency-look-up-context.xml -->
    <import resource="dependency-lookup-context.xml" />

    <bean id="userRepository" class="org.example.thinking.in.spring.ioc.overview.dependency.repository.UserRepository"
    autowire="byType">
        <!-- 自动注入 -->

        <!-- 手动配置 -->
<!--        <property name="users">-->
<!--            <util:list>-->
<!--                <ref bean="user" />-->
<!--                <ref bean="superUser" />-->
<!--            </util:list>-->
<!--        </property>-->
    </bean>

</beans>
```





## 5：依赖注入的来源 - 2020年8月15日



问 1：BeanFactory是不是内建的 Bean 对象？ - 否。为什么否？

答：如果是的话，我们完全可以根据 BeanFactory getBean(BeanFactory.class) 的方式进行依赖查找，得到对象。

BeanFactory其实是容器内建的一些依赖，所以依赖不仅仅来自于我们的自定义Bean，也可能是一个内建的Bean对象，

也有可能是所谓的依赖。



问 2：那么UserRepository 所注入的 BeanFactory 对象是什么？

答：其实是内建依赖，非 Spring 的 Bean。这里可以看出依赖大致可以分为两类：

1：普通 Bean

2：非 Bean （依赖）



问 3 ： 什么是内建的 Bean 对象？之前都没听说过。。。

答：Spring 容器默认为我们初始化的一些 Bean。



Environment 是干什么的？

1：外部化配置 和 profile 的一个综合体。典型的一个Spring 容器 内建 Bean



##### 1：依赖来源

​	· 自定义 Bean



​	· 容器内建Bean对象



​	· 容器内建依赖