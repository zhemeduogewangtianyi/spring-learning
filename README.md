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





## 6：Spring IoC 配置元信息- 2020年8月16日



问：Spring Ioc 的配置元信息都有哪些？



问：他们的进化过程是怎样的？



问：我们能不能通过配置的方式去影响容器的行为？

答：可以。



##### 1：Spring Ioc 配置元信息

· Bean 定义配置

​	· 基于 XML 文件

​	· 基于 Properties 文件

​	· 基于 Java 注解

​	· 基于 Java API （这个后面得搞个专题讨论）

​	· 基于 Groovy 的 方式来进行 DSL配置

· Ioc 容器配置

​	· 基于 XML 文件

​	· 基于 Java 注解 

​	· 基于 Java API （这个后面得搞个专题讨论）

· 外部化属性配置

​	· 基于 Java 注解（比如 @Value）



问题：到底谁才是 Spring IoC 容器的底层？





## 7：BeanFactory 和 ApplicationContext 到底谁才是 Spring IoC 容器 ？



##### 1：BeanFactory 和 ApplicationContext 到底谁才是 Spring IoC 容器 ？

要么都是。。。要么都不是。



DependencyInjectionDemo.java 中 有一段代码

```java
/** 依赖注入（内建依赖） */
System.err.println(userRepository.getBeanFactory().equals(beanFactory));
```

#### 这段代码为什么会返回 false ？



Spring官网这么说的：

```java
The org.springframework.beans and org.springframework.context packages are the basis for Spring Framework’s IoC container. The BeanFactory interface provides an advanced configuration mechanism capable of managing any type of object. ApplicationContext is a sub-interface of BeanFactory. It adds:

Easier integration with Spring’s AOP features

Message resource handling (for use in internationalization)

Event publication

Application-layer specific contexts such as the WebApplicationContext for use in web applications.
    
    In short, the BeanFactory provides the configuration framework and basic functionality, and the ApplicationContext adds more enterprise-specific functionality. The ApplicationContext is a complete superset of the BeanFactory and is used exclusively in this chapter in descriptions of Spring’s IoC container. For more information on using the BeanFactory instead of the ApplicationContext, see The BeanFactory.
```



ApplicationContext is a sub-interface of BeanFactory :

```java

BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

ApplicationContext beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/dependency-injection-context.xml");

```



ConfigurableApplicationContext <- ApplicationContext <- BeanFactory

可以看出 ConfigurableApplicationContext 就是 BeanFactory ，那么 ConfigurableApplicationContext 为什么还要提供一个 getBeanFactory 的方法呢？

##### ConfigurableApplicationContext 里面有个 setParent(ApplicationContext parent);	但是 没有一个叫做 setBeanFactory的方法。



对此我们可以去看一下AbstractApplicationContext 里面 的 getBeanFactory() 是如何实现的

```java
public abstract ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;
```



根据	ClassPathXmlApplicationContext 找对应继承关系的时候，发现有一个 AbstractRefreshableConfigApplicationContext ，刚好是 ConfigurationApplicationContext 的一个子类，恰巧里面有个具体实现。



```java
public final ConfigurableListableBeanFactory getBeanFactory() {
        synchronized(this.beanFactoryMonitor) {
            if (this.beanFactory == null) {
                throw new IllegalStateException("BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext");
            } else {
                return this.beanFactory;
            }
        }
    }
```

这里的 BeanFactory 其实是一个组合的，DefaultListableBeanFactory。这就可以知道之前

UserRepository 里面的 BeanFactory 为什么是 org.springframework.beans.factory.support.DefaultListableBeanFactory

因为 ClassPathXmlApplicationContext 里面的 BeanFactory 是 AbstractRefreshableConfigApplicationContext 里面的 DefaultListableBeanFactory



##### 在上下文里面的实现是组合了一种方式，同时它要在接口上面是 extends（继承）关系。-- （代理模式）



AbstractRefreshableConfigApplicationContext 里面 getBean() 的实现 ，其实就是 AbstractApplicationContext 的 getBean()

```java
public <T> T getBean(Class<T> requiredType) throws BeansException {
        this.assertBeanFactoryActive();
        return this.getBeanFactory().getBean(requiredType);
    }
```

相当于我用代理的对象去查这个东西。它本身并不具备这个能力，而是通过外部（AbstractApplicationContext的子类AbstractRefreshableApplicationContext）组装了一个对象来做这个事情。所以说这里的 this.getBeanFactory() 得到的是子类的 BeanFactory。比如 AbstractRefreshableApplicationContext 的 DefaultListableBeanFactory。



总结：

​	BeanFacotry 和 ApplicationContext 其实是同一类事物，BeanFactory 是 Spring IoC 的一个底层容器，ApplicationContext 是在 BeanFactory 的基础上面做了一些扩展，组合了一个 BeanFactory 的实现，好比BeanFacotry 变成了 DefaultListableBeanFactory ，做了一些 BeanFactory做不到的事情。这也是 BeanFactory 和 ApplicationContext 不是同一个对象的原因。尽管他们复用了同一个接口（BeanFactory）。



所以当获取到了 ApplicationContext 的时候一定要去通过调用 this.getBeanFactory() 这个方法去获取到真正的底层 BeanFactory 对象，不要直接替换BeanFactory。



比如：

​	Easier integration with Spring’s AOP features （AOP 更好的整合）

​	Message resource handling (for use in internationalization) （消息资源处理（国际化的支持））

​	Event publication （事件发布）

​	Application-layer specific contexts such as the WebApplicationContext for use in web applications. （特定于应用程序层的上下文，如用于 web 应用程序	 	的`WebApplicationContext`）





## 8：ApplicationContext 除了 IoC 容器角色，还提供了哪些特性？



###### 上一个章节，我们发现了 BeanFactory 和 ApplicationContext 并不是完全对等的，尽管他们有层次性的继承关系。



##### 1：Spring 应用上下文

##### 	ApplicationContext 除了 IoC 容器角色，还提供了哪些特性？

##### 	· 面向切面（AOP）

##### 	· 配置元信息（Configuration Metadata）

##### 	· 资源管理（Resources）

##### 	· 事件（Events）

##### 	· 国际化（i18n）

##### 	· 注解（Annotation）

##### 	· Environment抽象（Environment Abstraction，环境）



ApplicationContext 是 BeanFactory 的一个超集，就是说 BeanFactory 的所有特有都予以继承。并且在原有的继承基础之上，加入了

 AOP 的特性、

配置元信息（配置的元信息主要是指的 Environment 对象，包括了外化配置、注解方面的元信息 - @Bean 就是需要ApplicationContext 来进行支持的）、

资源管理（Java 标准的 URL、ClassLoading）、

事件（比如状态机，事件驱动状态变更，主态 - 事件 - 次态。。。）、

国际化、

注解（扫描和组件扫描@Component、@ComponentScan、依赖注入的方式@AutoWired 这些功能是在 BeanFactory 或者说是 Bean 模块里面来支持的，实际上需要 ApplicationContext 来驱动）、

Environment抽象（环境抽象包含了两部分，配置和外部化配置，配置主要走了profile；外部化配置主要是为了解决占位符的问题、配置的读取问题，比如：实时的去读取一些阈值开关或者一些相关的配置。阿里的diamond和swich也是这样的。）



具体的一些说明可以去Spring官网看一下 	1.1. Introduction to the Spring IoC Container and Beans  相关的内容

https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core



总结：

​	这一章主要是对 ApplicationContext 和 BeanFactory 的一些差异进行讨论，并且完善了一下Spring 那一堆乍看不大好明白的文档做了一些完善。这些都是看源码总结出来的一些小东西。接下来将对 IoC 的生命周期简单的分析下 Spring IoC 容器是怎么进行初始化的。





## 9：使用 Spring IoC 容器：我们应该选择 BeanFacotry 还是 ApplicationContext？



问：什么时候用 BeanFactory ？ 什么时候用 ApplicationContext ？

答：他们都是 Spring IoC 的底层，但是什么时候用还是有点差别的。

不用 ApplicationContext 也能加载资源文件，但是呢，没有事件、资源管理那一堆东西。

所以我们要根据场景来选择 BeanFactory 和 ApplicationContext 的使用

​	假如说，不需要 ApplicationContext 的扩展内容，只是想单纯的获取到一个 Xml 配置的 Bean，我们可以用 BeanFactory，反之。。。。。。



##### 1：使用 Spring IoC 容器

​	1：BeanFactory 是 Spring 的底层 Ioc 容器



​	2：ApplicationContext 是具备应用特性的 BeanFactory 超集



#### 这一次增加代码：（这次不用 ClassPathXmlApplicationContext 来读取配置的xml了。。。改用注解。）

##### 	BeanFactoryAsIocContainerDemo.java	---	BeanFactory 作为 IoC 容器示例

##### 	AnnotationApplicationContextAsIocContainerDemo.java	---	ApplicationContext 作为 IoC 容器示例



总结：

​	我们通过这两个例子，对比了 XML 通过 BeanFactory 和 Annotation 通过 ApplicationContext 的方式，说明了两者实现的一个不同场景。

​	下一章分析 IoC 容器一个简单的启动脉络。（启动过程中发生了什么事情）

​	





## 10：Spring IoC 容器生命周期：IoC容器启停过程中发生了什么？



##### 问题：Spring IoC 容器生命周期是怎么样的？



##### 问题：为什么 ApplicationContext 要做那么多事情？





##### 1：通过 BeanFactory 和 ApplicationContext 的一个启动过程，了解生命周期。



##### 2：Spring IoC 容器生命周期



##### 	· 启动



##### 	· 运行

​	

##### 	· 停止



```java
		//创建 BeanFactory 容器
        //注解配置 ApplicationContext 的实现 - @姜纪波 大神给的路子。
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        //将当前类 作为 Configuration class 注册 bean  - 相当与游乐 @Configuration
        annotationConfigApplicationContext.register(AnnotationApplicationContextAsIocContainerDemo.class);

        //启动应用上下文
        annotationConfigApplicationContext.refresh();

        //依赖查找 加入没有 @Bean 来实例化对象的话，是找不到 xml 里面配置的 对象的
        lookupCollectionsByType(annotationConfigApplicationContext);
```



##### 为什么上面的代码会调用一个叫做 refresh() 的方法呢？

这个玩意看名字感觉是重新刷新，其实他干的事情是重新启动。他其实是 AbstractApplicationContext 里面一个方法，实现了ConfigurableApplicationContext 接口的 refresh() 方法，并且自己给了个具体实现。。。这也难怪名字起的那么怪。。。



分析下 AbstractApplicationContext 中 refresh 的执行步骤：

```java
	@Override
	public void refresh() throws BeansException, IllegalStateException {

        /*
	        啥也不用说，啥也不用管。。上来先加把锁。
	        原因是 任何一个 ApplicationContext 可以在任意代码里面去创建。
	        那就意味着曾经有个家伙，在多线程里面创建过 ApplicationContext。。。
        */
		synchronized (this.startupShutdownMonitor) {
			/*
				Prepare this context for refreshing.
				顾名思义。。。准备refresh。
				1：记录启动时间。
				2：初始化 initPropertySources。（Environment抽象）
				3：getEnvironment() 获取到 Environment抽象，校验必须的属性。
				4：earlyApplicationListeners 的赋值 和 earlyApplicationEvent 的赋值。。早期的一个方法，和事件挂钩，后面事件部分再说。比较繁琐，				需要特殊的场景才能复现。估计是bug。。
				
			*/ 
			prepareRefresh();

			/*
				Tell the subclass to refresh the internal bean factory.
				通过子类取得到 BeanFactory 
				无非两种情况，一个空实现 或者 一个抽象实现
				AbstractApplicationContext 里面提供了一个不完整的实现
					1：refreshBeanFactory() 这是个抽象实现。需要子类自己去具体实现
						子类有俩，我们看AbstractRefreshableApplicationContext 里面有个 refreshBeanFactory 的具体实现
						1：这个方法里面创建了一个 DefaultListableBeanFactory 这个对象
						2：经过一系列的操作。。最后结果就是得到一个 BeanDefinition -> loadBeanDefinition(beanFactory)
						3：随后加了把锁。。因为这块是在外部。某些狗东西多线程调用 refreshBeanFactory不是在主流程里面调用的， 就会引发bug
					2：getBeanFactory() 获取 BeanFactory 并且返回一个 ConfigurableListableBeanFactory
			*/ 
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			/*
				Prepare the bean factory for use in this context.
				BeanFactory 准备工作。这里知道就行，后面再细说。不信自己点开看看里面有啥。
				剧透一点。。
					1：为什么 Environment 这个内建对象能够依赖查找到，就是因为这段代码的原因。

						beanFactory.registerSingleton() 注册进去的。
		
        			2：为什么我们可以的到 BeanFactory 这个对象呢？
        			
        				beanFactory.registerResolvableDependency() 把 BeanFactory 作为一个非 Bean 的方式注入。
        				这里的非 Bean 指的就是 Spring IoC 的内建对象。

				这块在依赖注入部分会非常详细的以代码来做说明。同样也会去分析 BeanFactory 的底层实现。以及如何区分依赖注入和依赖查找的。
        			
			*/ 
			prepareBeanFactory(beanFactory);

			try {
				/*
					Allows post-processing of the bean factory in context subclasses.
					定制化你的子 BeanFactory 的处理实现
				*/ 
				postProcessBeanFactory(beanFactory);

				/*
					Invoke factory processors registered as beans in the context.
					自定义扩展部分 处理器 - 对容器的调整
				*/ 
				invokeBeanFactoryPostProcessors(beanFactory);

				/*
					Register bean processors that intercept bean creation.
					注册一些用户所控制的 BeanFactory 处理器，相当于对 Bean 进行调整
				*/ 
				registerBeanPostProcessors(beanFactory);

				/*
                	Initialize message source for this context.
                	国际化。想想 BeanFactory 和 ApplicationContext 的不同
                */ 
				initMessageSource();

				/*
					Initialize event multicaster for this context.
					初始化应用的一个事件广播。
				*/ 
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				/*
					Check for listener beans and register them.
					注册监听器
				*/ 
				registerListeners();

				/*
					Instantiate all remaining (non-lazy-init) singletons.
					完成 BeanFactory 的初始化
				*/ 
				finishBeanFactoryInitialization(beanFactory);

				// Last step: publish corresponding event.
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
```



#### 总结下启动基本流程：

##### 	1：创建我们的 BeanFactory，并且进行初步的初始化，加入一些内建的 Bean 对象和 Bean 依赖，以及内建非 Bean 依赖（Environment）

##### 		prepareRefresh();

##### 		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

##### 		prepareBeanFactory(beanFactory);



##### 	2：BeanFactory 的扩展点，以及 BeanFactory 的一些修改	

##### 		postProcessBeanFactory(beanFactory);

##### 		invokeBeanFactoryPostProcessors(beanFactory);

##### 	

##### 	3：对 Bean 的一些扩展或者修改。这里只是注册。。。具体的调用是在 BeanFactory 里面进行操作的。

##### 		invokeBeanFactoryPostProcessors(beanFactory);



##### 	4：定义国际化、事件一系列的事情，这里提一点资源操作，这个资源操作继承了 ResourceLoader 的类，AbstractApplicationContext 正好实现了一个 ResourceLoader的子类 DefaultResourceLoader ，这个后面细说。。。





#### 那么 Spring IoC 容器怎么关闭的呢？

```java
		//关闭应用上下文
        annotationConfigApplicationContext.close();
```



就是这么简单。。。



简单分析下里面做了什么事情：

还是来到 AbstractApplicationContext 的代码里面：

```java
/**
	 * Close this application context, destroying all beans in its bean factory.
	 * <p>Delegates to {@code doClose()} for the actual closing procedure.
	 * Also removes a JVM shutdown hook, if registered, as it's not needed anymore.
	 * @see #doClose()
	 * @see #registerShutdownHook()
	 */
	@Override
	public void close() {
		synchronized (this.startupShutdownMonitor) {
            /*
            	销毁所有的 Bean 并且 关闭所有的 BeanFactory
            	发布一个上下文销毁事件。
            	然后就是各种 close，各种 destory
            */
			doClose();
			// If we registered a JVM shutdown hook, we don't need it anymore now:
			// We've already explicitly closed the context.
			if (this.shutdownHook != null) {
				try {
					Runtime.getRuntime().removeShutdownHook(this.shutdownHook);
				}
				catch (IllegalStateException ex) {
					// ignore - VM is already shutting down
				}
			}
		}
	}
```



就是去执行了一下 shutdownHook。。。







### 运行是啥样的？

伴随容器运行而运行，tomcat 管理整个线程状态，SpringBoot 内嵌 Tomcat 或者 WebServer（native webServer）来管理线程状态。





## 11：来点 Spring IoC 的面试题。。。



### 1：沙雕面试题：

##### 	1：什么是 Spring IoC 容器？

​	一般我面试的时候直接给面试那人DI（dependency injection）的一个实现，或者说是依赖翻转、再或者谈谈依赖注入的实现方式。

​	这么说的话其实也不算错误。但是根据前面的学习，大家肯定能知道，除了依赖注入还有依赖查找（lookup），当然还有一些还没

​	看到的一些其他特性。这样子才算完整的。其实每次遇到这个问题，我的内心都是笑开了花。



​	官方也有一些回答：

​	

```html
1.1. Introduction to the Spring IoC Container and Beans
This chapter covers the Spring Framework implementation of the Inversion of Control (IoC) principle. IoC is also known as dependency injection (DI). It is a process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method. The container then injects those dependencies when it creates the bean. This process is fundamentally the inverse (hence the name, Inversion of Control) of the bean itself controlling the instantiation or location of its dependencies by using direct construction of classes or a mechanism such as the Service Locator pattern.


1.1。 Spring IoC容器和Bean简介 
本章介绍了反转控制（IoC）原则的Spring框架实现。 IoC也称为依赖注入（DI）。在此过程中，对象仅通过构造函数参数，工厂方法的参数或在构造或从工厂方法返回后在对象实例上设置的属性来定义其依赖项（即，与它们一起使用的其他对象） 。然后，容器在创建bean时注入那些依赖项。此过程从根本上讲是通过使用类的直接构造或诸如服务定位器模式之类的控件来控制其依赖项的实例化或位置的bean本身的逆过程（因此称为Control Inversion）。
```

​	

其实官网有时候也是表述不清楚，导致大家误解。

#### 	IoC is also known as dependency injection (DI) 

​	大意：

##### 		IoC 也成为了依赖注入（DI）。其实 DI 应该说是 IoC 实现的一种。。并不只是 IoC ，因为 IoC 也包括了 依赖查找。



###  It is a process whereby objects define their dependencies (that is, the other objects they work with) only through constructor arguments, arguments to a factory method, or properties that are set on the object instance after it is constructed or returned from a factory method.



大意：

​	在依赖注入（DI）的时候，会伴随着一个状态的依赖。

#### 	状态依赖是指什么？

​		这里指的是我们可以通过构造方法，或者工厂方法，以及属性的 getter/setter 方式，注入一些其他的对象，完成依赖注入。

​	然后容器会把依赖注入（DI）的对象，放进创建的 Bean 里面来。

```java
@Component
public class Test{
    
    //这样可以
    @Autowired
    private ApplicationContext;
    
    //这样也可以
    @Autowired
    public Test(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    
    public ApplicationContext getApplicationContext(){
        return this.applicationContext;
    }
    
    //或者这样。。。
    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
    
}
```



#### 其实这里Spring 为什么只说依赖注入不说依赖查找？

​	因为在 Java EE 时代，依赖查找就已经被实现掉了。DI 其实也部分实现了，只不过要和 Java EE 进行区分。

```java
//在这。。
BeanInfo beanInfo = Introspector.getBeanInfo(Student.class,Object.class);
```









### 2：BeanFactory 和 FactoryBean 的区别？



我记得这是当年让菜的开花的我，无比苦恼的一个问题。一般都是什么 996 面试大厂喜欢问。。

我第一次回答的时候，直接告诉对方 **这俩单词不一样**。 然后对方那边就信号不好，然后就没有然后了。。。

其实这里我们可以这样回答，毕竟我们前面看过了 BeanFactory 和 ApplicationContext。虽然不太明白 FactoryBean是啥，相信大家

也有梁静茹给的勇气胡说八道一番。

先说知道的：

##### 	首先呢 BeanFactory 是 IoC 的一个底层容器。（但是这里会有人否定。。BeanFactory 什么时候成了 IoC 的底层容器了？？？！！）

​	如果他这么问你了。。。那么把我的 github 地址给他，我来教他做人。（本人专业劝退）

不知道的我来告诉你：

##### 	FactoryBean 是创建 Bean 的一种方式，帮助实现复杂的初始化逻辑。



这里找到 BeanFactory 的源码：

```java
/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory;

import org.springframework.lang.Nullable;

/**
 * Interface to be implemented by objects used within a {@link BeanFactory} which
 * are themselves factories for individual objects. If a bean implements this
 * interface, it is used as a factory for an object to expose, not directly as a
 * bean instance that will be exposed itself.
 *
 	这是一个实现了 Object 的接口，这个Object 里面有这么几个特性。这个特性就是为了帮助你去暴露
 	一个 Bean 。这个 Bean 通常来说不是一个正常的 Bean ,或者不是一个能够简单处理的 Bean 。
 	
 	例如：
 	其实可以看我们的 User.java 这个类，他就一个默认的空参构造。那么通过反射的方式就能实现一个
 	调用。就算有构造器参数我们也能通过反射的方式，把构造器参数注入进来。但是现实中有时候没那么简单。
 	假设 User.java 是通过第三方来进行创建的，这时候就没法反射获取构造方法进行初始化。所以这个时候
 	就可以选择 FactoryBean 的方式来操作
 
 * <p><b>NB: A bean that implements this interface cannot be used as a normal bean.</b>
 * A FactoryBean is defined in a bean style, but the object exposed for bean
 * references ({@link #getObject()}) is always the object that it creates.
 *
 * <p>FactoryBeans can support singletons and prototypes, and can either create
 * objects lazily on demand or eagerly on startup. The {@link SmartFactoryBean}
 * interface allows for exposing more fine-grained behavioral metadata.
 *
 * <p>This interface is heavily used within the framework itself, for example for
 * the AOP {@link org.springframework.aop.framework.ProxyFactoryBean} or the
 * {@link org.springframework.jndi.JndiObjectFactoryBean}. It can be used for
 * custom components as well; however, this is only common for infrastructure code.
 *
 * <p><b>{@code FactoryBean} is a programmatic contract. Implementations are not
 * supposed to rely on annotation-driven injection or other reflective facilities.</b>
 * {@link #getObjectType()} {@link #getObject()} invocations may arrive early in
 * the bootstrap process, even ahead of any post-processor setup. If you need access
 * other beans, implement {@link BeanFactoryAware} and obtain them programmatically.
 *
 * <p>Finally, FactoryBean objects participate in the containing BeanFactory's
 * synchronization of bean creation. There is usually no need for internal
 * synchronization other than for purposes of lazy initialization within the
 * FactoryBean itself (or the like).
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 08.03.2003
 * @param <T> the bean type
 * @see org.springframework.beans.factory.BeanFactory
 * @see org.springframework.aop.framework.ProxyFactoryBean
 * @see org.springframework.jndi.JndiObjectFactoryBean
 */
public interface FactoryBean<T> {

	/**
	 * The name of an attribute that can be
	 * {@link org.springframework.core.AttributeAccessor#setAttribute set} on a
	 * {@link org.springframework.beans.factory.config.BeanDefinition} so that
	 * factory beans can signal their object type when it can't be deduced from
	 * the factory bean class.
	 * @since 5.2
	 */
	String OBJECT_TYPE_ATTRIBUTE = "factoryBeanObjectType";


	/**
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * <p>As with a {@link BeanFactory}, this allows support for both the
	 * Singleton and Prototype design pattern.
	 * <p>If this FactoryBean is not fully initialized yet at the time of
	 * the call (for example because it is involved in a circular reference),
	 * throw a corresponding {@link FactoryBeanNotInitializedException}.
	 * <p>As of Spring 2.0, FactoryBeans are allowed to return {@code null}
	 * objects. The factory will consider this as normal value to be used; it
	 * will not throw a FactoryBeanNotInitializedException in this case anymore.
	 * FactoryBean implementations are encouraged to throw
	 * FactoryBeanNotInitializedException themselves now, as appropriate.
	 * @return an instance of the bean (can be {@code null})
	 * @throws Exception in case of creation errors
	 * @see FactoryBeanNotInitializedException
	 
	 这个方法会被容器调用，容器怎么知道这个方法要被调用？
	 前提就是 getObjectType()
	 
	 问题:这个方法是不是每次都会被调用？
	 答：不是。。
	 
	 */
	@Nullable
	T getObject() throws Exception;

	/**
	 * Return the type of object that this FactoryBean creates,
	 * or {@code null} if not known in advance.
	 * <p>This allows one to check for specific types of beans without
	 * instantiating objects, for example on autowiring.
	 * <p>In the case of implementations that are creating a singleton object,
	 * this method should try to avoid singleton creation as far as possible;
	 * it should rather estimate the type in advance.
	 * For prototypes, returning a meaningful type here is advisable too.
	 * <p>This method can be called <i>before</i> this FactoryBean has
	 * been fully initialized. It must not rely on state created during
	 * initialization; of course, it can still use such state if available.
	 * <p><b>NOTE:</b> Autowiring will simply ignore FactoryBeans that return
	 * {@code null} here. Therefore it is highly recommended to implement
	 * this method properly, using the current state of the FactoryBean.
	 * @return the type of object that this FactoryBean creates,
	 * or {@code null} if not known at the time of the call
	 * @see ListableBeanFactory#getBeansOfType
	 
	 决定哪个对象要去做事情。
	 问题来了。。。加入我对象类型相同怎么办？
	 那么就通过是不是单利的方式来进行区分。。。下面的哪个 isSingleton()
	 如果每次获取的都是同一个对象（isSingleton() 始终返回 true ），说明
	 是同一个对象，否则就不是同一个对象。
	 
	 */
	@Nullable
	Class<?> getObjectType();

	/**
	 * Is the object managed by this factory a singleton? That is,
	 * will {@link #getObject()} always return the same object
	 * (a reference that can be cached)?
	 * <p><b>NOTE:</b> If a FactoryBean indicates to hold a singleton object,
	 * the object returned from {@code getObject()} might get cached
	 * by the owning BeanFactory. Hence, do not return {@code true}
	 * unless the FactoryBean always exposes the same reference.
	 * <p>The singleton status of the FactoryBean itself will generally
	 * be provided by the owning BeanFactory; usually, it has to be
	 * defined as singleton there.
	 * <p><b>NOTE:</b> This method returning {@code false} does not
	 * necessarily indicate that returned objects are independent instances.
	 * An implementation of the extended {@link SmartFactoryBean} interface
	 * may explicitly indicate independent instances through its
	 * {@link SmartFactoryBean#isPrototype()} method. Plain {@link FactoryBean}
	 * implementations which do not implement this extended interface are
	 * simply assumed to always return independent instances if the
	 * {@code isSingleton()} implementation returns {@code false}.
	 * <p>The default implementation returns {@code true}, since a
	 * {@code FactoryBean} typically manages a singleton instance.
	 * @return whether the exposed object is a singleton
	 * @see #getObject()
	 * @see SmartFactoryBean#isPrototype()
	 */
	default boolean isSingleton() {
		return true;
	}

}

```



这里可以看到 FactoryBean 是创建 Bean 的一个方式，这种方式可以解决复杂的构造场景或者初始化场景这么一个问题。。



#### 估计这个时候面试官会有第二个问题：

##### 	FactoryBean 创建的 Bean 会不会再去经过 Bean 的生命周期呢？

​	记住这个问题。。。后面 Bean 生命周期部分深入讨论。。







### 3：Spring IoC 容器启动时做了哪些准备工作？（当时差点被劝退）

​	

​	刚才只是做了一些非常浅显的学习。真正的重点这个时候说不出来的。

​	比如说：

​		BeanFactory 的一个扩展功能，这个扩展功能里面就是千变万化的。。。比如说增加了注解驱动，

包括了 Aware 接口的回调，都在里面操作的。现在还没学到。（**容器初始化、回调，着重进行说明**）



目前阶段可以这么说：

#### 	答：IoC 配置元信息读取和解析（xml 读取、Bean 读取）、IoC 容器生命周期、Spring 事件发布（事件可以停止）、国际化等等。











-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------









# Spring Bean 的基础部分。



#### 	1：定义 Spring Bean

​			Spring Bean 和 Java Bean 的区别

#### 	2：BeanDefinition 元信息

​			定义 Spring Bean 的时候会有一些元信息，包括 BeanDefinition 这样的元信息是怎么呈现的。

#### 	3：命名 Spring Bean

​			Spring Bean 的名字是不是必须的？

#### 	4：Spring Bean 别名

​			Bean 的别名为什么一定需要？什么场景需要 Bean 的别名？

#### 	5：注册 Spring Bean

​			如何注册一个 Spring Bean，他和 BeanDefinition 有什么区别？

#### 	6：实例化 Spring Bean

​			如何把 Spring Bean 从 BeanDefinition 实例化成为一个 Spring Bean

#### 	7：初始化 Spring Bean

​			有哪些手段进行初始化？

#### 	8：延迟初始化 Spring Bean

​			如何延迟初始化 Spring Bean？

#### 	9：销毁 Spring Bean

​			如何销毁 Spring Bean？比如线程池，数据库连接池。

#### 	10：垃圾回收 Spring Bean

​			怎么回收 Spring Bean ？

#### 	11：面试题









## 1：定义 Bean：什么是BeanDefinition ？



### 1：什么是 BeanDefinition ？



### 2：BeanDefinition 是 Spring Framework 中定义 Bean 的配置元信息接口，包含：

​	

#### 		· Bean 的类名

​				包含包名（全路径类名），对应的类必须是一个具体的实现类。

#### 		· Bean 行为元素，比如 作用域、自动绑定的模式、生命周期回调等。

​				自动绑定就是 @Autowired 这种方式。

​				生命周期回调就比如说生命周期初始化、销毁时候的一个回调。比如：InitializingBean，在初始化之前做一些事情。

#### 		· 其他 Bean 引用，又可称为 合作者（Collaborators）或者 依赖（Dependencies）

​				合作者可以认为是 Bean 的引用关系，或者称之为依赖。比如说依赖注入，也是把合作者或者是说把引用的 Bean 注入到自己本身里面来。

​				当然依赖注入不仅仅是注入 Bean ，还可以注入其他配置。

​				

#### 		· 配置设置，比如 Bean 属性（Properties）

​				假设 Bean 正好对应一个线程池，或者数据库连接池。这时候这个 Bean 可能包含一些大小或者一些相关的属性。









## 2：BeanDefinition 元信息：除了 Bean 名称和类名，还有哪些 Bean 元信息值得关注？



#### 1：BeanDefinition 的属性：



|     属性（Property）     |                             说明                             |
| :----------------------: | :----------------------------------------------------------: |
|          Class           |        Bean 全类名，必须是具体类，不能用抽象类或接口         |
|           Name           |                       Bean 的名称或 ID                       |
|          Scope           |        Bean 的作用域（如：Singleton、propertype 等）         |
|  Constractor arguments   |        Bean 构造器参数（用于依赖注入，比如第三方API）        |
|        Properties        |         Bean 属性设置（用于依赖注入，setter/getter）         |
|     Autowiring mode      | Bean 自动绑定模式（如：通过名称 byName，byType，byConstructor）<br />把属性和外面的引用自动的关联，或者自动绑定（容器或者 Bean 的配置的元信息） |
| Lazy initialization mode |      Bean 延迟加载模式（延迟和非延迟）<br />默认非延迟       |
|  initialization method   |                   Bean 初始化回调方法名称                    |
|    Destruction method    |                    Bean 销毁回调方法名称                     |





#### 2：如何构建 BeanDefinition？



#### 	· 通过 BeanDefinitionBuilder

​			这种方式通过 xml 配置的方式会比较多一点。



#### 	· 通过 AbstractBeanDefinition 以及派生类





##### 本次新增文件：

​	1：创建一个新的模块在 thinking-in-spring 下，命名为 spring-bean。

​	2：pom 相关依赖

​	3：BeanDefinitionCreationDemo.java



##### pom 文件

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>think-in-spring</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-bean</artifactId>
    <description>Spring Bean 基础</description>

    <dependencies>
        <!-- Spring Ioc 核心依赖 -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>

        <!-- 复用之前的 Spring IoC 部分内容 -->
        <dependency>
            <groupId>${groupId}</groupId>
            <artifactId>ioc-container-overview</artifactId>
            <version>${version}</version>
        </dependency>

    </dependencies>

</project>
```



首先看到 BeanDefinitionBuilder 的构建方式，这个类里面大量的静态方法，有两种构建 BeanDefinition 的方法：

```java
genericBeanDefinition() 构建一个普通的 BeanDefinition
rootBeanDefinition()    构建一个 root BeanDefinition
```

这个类里面的静态方法是帮我们构建一个 BeanDefinitionBuilder 用的，仔细看一下，这玩意全部返回的都是 BeanDefinitionBuilder。

但是也有一些实例方法，让我们挑着用。假如我们要构造他的一个构造方法，或者是 parent 的名称，他这里全部都有实现。

例1：

​	添加一个构造方法的值：

```java
/**
	 * Add an indexed constructor arg value. The current index is tracked internally
	 * and all additions are at the present point.
	 */
	public BeanDefinitionBuilder addConstructorArgValue(@Nullable Object value) {
		this.beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(
				this.constructorArgIndex++, value);
		return this;
	}
```



例2：

​	构造方法中的参数用一个 Bean 的方式去引用：

```java
/**
	 * Add a reference to a named bean as a constructor arg.
	 * @see #addConstructorArgValue(Object)
	 */
	public BeanDefinitionBuilder addConstructorArgReference(String beanName) {
		this.beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(
				this.constructorArgIndex++, new RuntimeBeanReference(beanName));
		return this;
	}
```



### 那么 genericBeanDefinition 和 rootBeanDefinition 有什么区别？

​	genericBeanDefinitionBuilder 是创建一个非根，非顶层的 BeanDefinition，他可以有 parent。

​	rootBeanDefinitionBuilder 不可以有 parent 的 BeanDefinition，因为他根。





## 总结：

#### 	如何定义 BeanDefinition 有两种方式：

#### 		第一种是通过 BeanDefinitionBuilder 里面的静态方法来创建 BeanDefinition。

#### 		BeanDefinitionBuilder 里面主要有两种构建 BeanDefinition 的方法（其实三种，还一个 children 的。。），一个是普通的BeanDefinition 通过 genericBeanDefination() 的方式来创建，另外一个是 rootBeanDefinition() 的方式创建一个根 BeanDefinition。并且支持链式添加属性给 Bean 对象



#### 		第二种是通过 AbstractBeanDefinition 以及他的派生子类来创建 BeanDefinition，比如：GenericBeanDefinition。

#### 这个也支持链式调用的方式给 Bean 对象添加属性，并且还能批量。













## 1：命名 Spring Bean： id 属性和 name 属性命名 Bean，哪个更好？



### 1：命名 Spring Bean



#### 	Bean 的名称：

#### 			每个 Bean 拥有一个或者多个标识符（identifiers），这些标识符在 Bean 所在的容器必须是唯一的。

#### 			这里指的唯一是这个 Bean 在 BeanFactory 或者 BeanDefinition 里面必须要是唯一的。

#### 			这些标识符会用到两个方面，一个是依赖查找，一个就是我们之前说的那个依赖注入。

#### 			通常，一个 Bean 仅有一个标识符，如果需要额外的，可以考虑使用别名（Alias）来扩展。

#### 



#### 			在基于 XML 的配置元信息中（不一定是本地的 XML ，也可以是网络上的一些资源，比如前段时间有个

#### 			朋友就问到我 CDN 是什么。。。），开发人员可以用 id 或者 name 属性来规定 Bean 的标识符（identifiers）。

#### 		（这里的 id 或者 name 指的就是 XML 标签里面的元素属性，来鉴定 Bean 的识别符）。



#### 			通常 Bean 的标识符由字母组成，允许出现特殊符号（没有正则表达式，没有正则表达式，没有正则表达式。）。

#### 			如果想引用 Bean 的别名的话，可以在 name 属性使用半角英文 （ , ） 或者 （ ; ）来分隔。



#### 			Bean 的 id 或 name 属性并非必须制定，如果留空的话（这里指在 XML 中没有定义 id 或者 name 属性的话），

#### 			容器会为 Bean 自动生成一个唯一的名称。

#### 			Bean 的命名尽管没有限制，不过官方建议采用驼峰命名，因为驼峰命名更符合 Java 的命名规范。



#### 	



#### · Bean 名称生成器（BeanNameGenerator）



#### 			· 由 Spring Framework 2.0.3 引入，框架内建两种实现方式：



##### 				· DefaultBeanNameGenerator：

##### 						默认引用 BeanNameGenerator 的实现。



##### 				· AnnotationBeanNameGenerator：

##### 						基于注解扫描的 BeanNameGenerator 实现，始于 Spring Framework 2.5。比如：@Component 就是 Spring Framework 2.5 开始有的。

##### 

#### 关联的官方文档：[1.3.1. Naming Beans](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-beanname)

```html

With component scanning in the classpath, Spring generates bean names for unnamed components, following the rules described earlier: essentially, taking the simple class name and turning its initial character to lower-case. However, in the (unusual) special case when there is more than one character and both the first and second characters are upper case, the original casing gets preserved. These are the same rules as defined by java.beans.Introspector.decapitalize (which Spring uses here).

通过在类路径中进行组件扫描，Spring会按照前面描述的规则为未命名的组件生成Bean名称：本质上，采用简单的类名称并将其初始字符转换为小写。但是，在（不寻常的）特殊情况下，如果有多个字符并且第一个和第二个字符均为大写字母，则会保留原始大小写。这些规则与java.beans.Introspector.decapitalize（Spring在此使用）定义的规则相同。
```



#### BeanNameGenerator 源码部分：

##### 接口定义：

```java
/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 
	 帮助我们没有指定 id 和 name 的 Bean 来生成 beanName 的规范。
 
 */

package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * Strategy interface for generating bean names for bean definitions.
 *
 * @author Juergen Hoeller
 * @since 2.0.3		<- 出现版本
 */
public interface BeanNameGenerator {

	/**
	 * Generate a bean name for the given bean definition.
	 * @param definition the bean definition to generate a name for
	 * @param registry the bean definition registry that the given definition
	 * is supposed to be registered with
	 * @return the generated bean name
	 
	 需要两个参数，一个是 BeanDefinition（Bean 的定义），另外一个是 BeanDefinitionRegistry（Bean 定义的一个注册中心）
	 
	 最终返回一个 String , 你猜猜这个 String 是什么。。。
	 
	 */
	String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);

}

```





##### 默认实现：DefaultBeanNameGenerator

```java
/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * Default implementation of the {@link BeanNameGenerator} interface, delegating to
 * {@link BeanDefinitionReaderUtils#generateBeanName(BeanDefinition, BeanDefinitionRegistry)}.
 *
 * @author Juergen Hoeller
 * @since 2.0.3		<- 版本在这里。。low 一眼知道就行。
 */
public class DefaultBeanNameGenerator implements BeanNameGenerator {

	/**
	 * A convenient constant for a default {@code DefaultBeanNameGenerator} instance,
	 * as used for {@link AbstractBeanDefinitionReader} setup.
	 * @since 5.2 	<-  后面这家伙改版了，换成了一个单例的方式来做。估计是节约内存开销。
	 
	 这里有个问题：为什么用单例了，没有把构造器变成 private 私有化的？这不像是单例啊 ！
	 
	 因为这玩意最开始在2.0.3版本就写毁了，肯定很多低版本的人再用，这时候低版本的人生了个级。改了一个5.2.2。可想而知，类注释上这人会被人喷死。
	 所以为了版本兼容，private 想用却又不敢用。所以说我们再用这个类的时候，有了两种选择，第一：一错到底，我就用那个老的。。
	 第二：用这个单例的。怎么做，大家自己看心情就好了。
	 
	 */
	public static final DefaultBeanNameGenerator INSTANCE = new DefaultBeanNameGenerator();


    /**
    	这是先前的方式，用 BeanDefinitionReaderUtils 类里的静态方法来做的。
    */
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return BeanDefinitionReaderUtils.generateBeanName(definition, registry);
	}

}

```



##### BeanDefinitionReaderUtils.generateBeanName( definition , registry ) 源码部分：

```java
/**
	 * Generate a bean name for the given top-level bean definition,
	 * unique within the given bean factory.
	 * @param beanDefinition the bean definition to generate a bean name for
	 * @param registry the bean factory that the definition is going to be
	 * registered with (to check for existing bean names)
	 * @return the generated bean name
	 * @throws BeanDefinitionStoreException if no unique name can be generated
	 * for the given bean definition
	 * @see #generateBeanName(BeanDefinition, BeanDefinitionRegistry, boolean)
	 */
	public static String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry registry)
			throws BeanDefinitionStoreException {

		return generateBeanName(beanDefinition, registry, false);
	}
```



##### generateBeanName( beanDefinition , registry , false ); 源码部分：

```java
/**
	 * Generate a bean name for the given bean definition, unique within the
	 * given bean factory.
	 * @param definition the bean definition to generate a bean name for
	 * @param registry the bean factory that the definition is going to be
	 * registered with (to check for existing bean names)
	 * @param isInnerBean whether the given bean definition will be registered
	 * as inner bean or as top-level bean (allowing for special name generation
	 * for inner beans versus top-level beans)
	 * @return the generated bean name
	 * @throws BeanDefinitionStoreException if no unique name can be generated
	 * for the given bean definition
	 */
	public static String generateBeanName(
			BeanDefinition definition, BeanDefinitionRegistry registry, boolean isInnerBean)
			throws BeanDefinitionStoreException {

        //拿到 Bean 的 class。
		String generatedBeanName = definition.getBeanClassName();
		if (generatedBeanName == null) {
			if (definition.getParentName() != null) {
				generatedBeanName = definition.getParentName() + "$child";
			}
			else if (definition.getFactoryBeanName() != null) {
				generatedBeanName = definition.getFactoryBeanName() + "$created";
			}
		}
		if (!StringUtils.hasText(generatedBeanName)) {
			throw new BeanDefinitionStoreException("Unnamed bean definition specifies neither " +
					"'class' nor 'parent' nor 'factory-bean' - can't generate bean name");
		}

		String id = generatedBeanName;
        
        // Bean 里面嵌套 Bean 的情况，这样处理。
		if (isInnerBean) {
			// Inner bean: generate identity hashcode suffix. 
            //这里就是你 debugger 的时候为什么会看到你的 Bean 有 # 分割并且带个数字或者字符的原因。
			id = generatedBeanName + GENERATED_BEAN_NAME_SEPARATOR + ObjectUtils.getIdentityHexString(definition);
		}
		else {
			// Top-level bean: use plain class name with unique suffix if necessary.
            //这个就简单了，这个就是你的 beanName 是唯一的情况
			return uniqueBeanName(generatedBeanName, registry);
		}
		return id;
	}
```



##### uniqueBeanName( generatedBeanName , registry ); 源码部分：

```java
/**
	 * Turn the given bean name into a unique bean name for the given bean factory,
	 * appending a unique counter as suffix if necessary.
	 * @param beanName the original bean name
	 * @param registry the bean factory that the definition is going to be
	 * registered with (to check for existing bean names)
	 * @return the unique bean name to use
	 * @since 5.1
	 */
	public static String uniqueBeanName(String beanName, BeanDefinitionRegistry registry) {
		String id = beanName;
		int counter = -1;


        //数字从 0 开始累加，这里的限定就是 统计一下我的注册中心里面到底有多少个相同的 Bean。 registry.containsBeanDefinition(id)
        //代码不贴了。。。看一个 DefaultListableBeanFactory的源码  this.beanDefinitionMap.containsKey(beanName);
        
		// Increase counter until the id is unique.        
		while (counter == -1 || registry.containsBeanDefinition(id)) {
			counter++;
            // beanName + # + 阿拉伯数字。
			id = beanName + GENERATED_BEAN_NAME_SEPARATOR + counter;
		}
		return id;
	}
```





##### GeneratorBeanName 注解实现 AnnotationBeanNameGenerator：

```java
/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.beans.Introspector;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**

	这里写了一下。我们常用的 
					@Component
					@Repository
					@Service
					@Controller
					都在用这种方式默默地为咱们生成没有定义 id 和 name 的 beanName，再次致敬。

	这里，@Repository 这个注解点进去，会吓死你。里面加了个 @Component，可以理解为 @Repository 是一个 @Component
	这玩意存在的意义是啥？
	为了我们注解的方式来服务的。后面讲。
	
	因为 @Component 注解是 Spring Framework 2.5 开始支持的，所以说这里 AnnotationBeanNameGenerator 最起码也是2.5开始支持的
	
	

 * {@link org.springframework.beans.factory.support.BeanNameGenerator}
 * implementation for bean classes annotated with the
 * {@link org.springframework.stereotype.Component @Component} annotation
 * or with another annotation that is itself annotated with
 * {@link org.springframework.stereotype.Component @Component} as a
 * meta-annotation. For example, Spring's stereotype annotations (such as
 * {@link org.springframework.stereotype.Repository @Repository}) are
 * themselves annotated with
 * {@link org.springframework.stereotype.Component @Component}.
 *
 * <p>Also supports Java EE 6's {@link javax.annotation.ManagedBean} and
 * JSR-330's {@link javax.inject.Named} annotations, if available. Note that
 * Spring component annotations always override such standard annotations.
 *
 * <p>If the annotation's value doesn't indicate a bean name, an appropriate
 * name will be built based on the short name of the class (with the first
 * letter lower-cased). For example:
 *
 * <pre class="code">com.xyz.FooServiceImpl -&gt; fooServiceImpl</pre>
 *
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @since 2.5
 * @see org.springframework.stereotype.Component#value()
 * @see org.springframework.stereotype.Repository#value()
 * @see org.springframework.stereotype.Service#value()
 * @see org.springframework.stereotype.Controller#value()
 * @see javax.inject.Named#value()
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {

	/**
	 * A convenient constant for a default {@code AnnotationBeanNameGenerator} instance,
	 * as used for component scanning purposes.
	 * @since 5.2
	 
	 5.2这个版本，就在这个类里面，写代码这家伙继续用同样的方式写了个单例（和 DefaultBeanNameGenerator 一样的方式）
	 
	 为什么这个地方不用 private 的构造？ 我相信你知道的。
	 
	 */
	public static final AnnotationBeanNameGenerator INSTANCE = new AnnotationBeanNameGenerator();

	private static final String COMPONENT_ANNOTATION_CLASSNAME = "org.springframework.stereotype.Component";


	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        
        //如果是注解方式创建 BeanDefinition 的话，这里的 BeanDefinition 会被标识称一个 AnnotationBeanDefinition
        
		if (definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}
        
        //如果不是新出现的 AnnotationDefinition ，就会采用这种补偿的机制来生成 beanName.主要是为了兼容。
        
		// Fallback: generate a unique default bean name.
		return buildDefaultBeanName(definition, registry);
	}

	/**
	 * Derive a bean name from one of the annotations on the class.
	 * @param annotatedDef the annotation-aware bean definition
	 * @return the bean name, or {@code null} if none is found
	 */
	@Nullable
	protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		AnnotationMetadata amd = annotatedDef.getMetadata();
		Set<String> types = amd.getAnnotationTypes();
		String beanName = null;
		for (String type : types) {
			AnnotationAttributes attributes = AnnotationConfigUtils.attributesFor(amd, type);
			if (attributes != null && isStereotypeWithNameValue(type, amd.getMetaAnnotationTypes(type), attributes)) {
				Object value = attributes.get("value");
				if (value instanceof String) {
					String strVal = (String) value;
					if (StringUtils.hasLength(strVal)) {
						if (beanName != null && !strVal.equals(beanName)) {
							throw new IllegalStateException("Stereotype annotations suggest inconsistent " +
									"component names: '" + beanName + "' versus '" + strVal + "'");
						}
						beanName = strVal;
					}
				}
			}
		}
		return beanName;
	}

	/**
	 * Check whether the given annotation is a stereotype that is allowed
	 * to suggest a component name through its annotation {@code value()}.
	 * @param annotationType the name of the annotation class to check
	 * @param metaAnnotationTypes the names of meta-annotations on the given annotation
	 * @param attributes the map of attributes for the given annotation
	 * @return whether the annotation qualifies as a stereotype with component name
	 */
	protected boolean isStereotypeWithNameValue(String annotationType,
			Set<String> metaAnnotationTypes, @Nullable Map<String, Object> attributes) {

		boolean isStereotype = annotationType.equals(COMPONENT_ANNOTATION_CLASSNAME) ||
				metaAnnotationTypes.contains(COMPONENT_ANNOTATION_CLASSNAME) ||
				annotationType.equals("javax.annotation.ManagedBean") ||
				annotationType.equals("javax.inject.Named");

		return (isStereotype && attributes != null && attributes.containsKey("value"));
	}

	/**
	 * Derive a default bean name from the given bean definition.
	 * <p>The default implementation delegates to {@link #buildDefaultBeanName(BeanDefinition)}.
	 * @param definition the bean definition to build a bean name for
	 * @param registry the registry that the given bean definition is being registered with
	 * @return the default bean name (never {@code null})
	 */
	protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		return buildDefaultBeanName(definition);
	}

	/**
	
	这个就是 java beans 里面的哪个 Introspecor 的那个处理 Abbb 或者 AnBs 这种命名的方法。
	也就是那个把你的包名去掉，然后首字母开头小写那玩意。
	
	 * Derive a default bean name from the given bean definition.
	 * <p>The default implementation simply builds a decapitalized version
	 * of the short class name: e.g. "mypackage.MyJdbcDao" -> "myJdbcDao".
	 * <p>Note that inner classes will thus have names of the form
	 * "outerClassName.InnerClassName", which because of the period in the
	 * name may be an issue if you are autowiring by name.
	 * @param definition the bean definition to build a bean name for
	 * @return the default bean name (never {@code null})
	 */
	protected String buildDefaultBeanName(BeanDefinition definition) {
		String beanClassName = definition.getBeanClassName();
		Assert.state(beanClassName != null, "No bean class name set");
		String shortClassName = ClassUtils.getShortName(beanClassName);
		return Introspector.decapitalize(shortClassName);
	}

}

```





### 总结：

​	通过看这些源码，相信大家都能知道 Spring Bean 是怎么命名的了，他有两种方式：

​		1：容器通过 BeanNameGenerator 接口的实现类给我们帮忙生成 beanName，他有两个实现类：

​				1：DefaultBeanNameGenerator 出现在 2.0.3版本，被一个沙雕写了个坑，在5.2版本修复了，但是单例不敢用 private。

​				2：AnnotationBeanNameGenerator 出现在 2.5 版本，跟随 @Component 他们一起出现的，这个里面加入了对 AnnotationBeanDefinition 的支持，

​				与此同时兼容了老版本，另外还加入了 java.beans 包下的 Introspector 类，来帮忙生成把全类名生成为一个 首字母小写的 beanName。

​	

​	标题说的 id 和 name 那种命名方式好呢？

​		都用到注解了。。。你就都别写得了。。你要是非用 xml 的方式定义 Bean 的话，并且还是2.0.3那块的版本，建议你自己写一个 beanName，id 或者 name 你随意。。但是要看你版本有多低，id 要求全局唯一，name 要求当前应用上下文唯一。