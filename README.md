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



这里找到 FactoryBean 的源码：

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











## 2：Spring Bean 的别名： 为什么命名 Bean 还需要别名？



### 1：Bean 的别名到底有什么价值？



##### 	· 复用现有的  BeanDefinition

​		因为别名不能无中生有，必须有现行的 BeanDefinition 在上下文里面有配置

​	

##### 	· 更具有场景化的命名方法，比如：

```java
<alias name = "myProject-dataSource" alias = "subsystemA-dataSource" />
<alias name = "myProject-dataSource" alias = "subsystemB-dataSource" />
```

 

##### 本章新增文件来应用别名的场景：

​	1：在 spring-bean 模块下的 resources 目录下建立一个 META-INF 文件夹

​	2：建立一个 bean-definitions-context.xml 文件

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

   <!-- 复用 dependency-lookup-context.xml 文件，相当于第三方 Spring XML 配置文件 -->
    <import resource="classpath:/META-INF/dependency-lookup-context.xml" />

    <!-- 将 dependency-lookup-context.xml 文件中（可以说是 Spring 容器中） 的 user 建立别名为 alias-user -->
    <alias name="user" alias="alias-user" />

</beans>
```

​	3：新增 BeanAliasDemo.java 



```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 别名示例 - 通过依赖查找的方式来进行演示 Spring Bean 的 别名
 * @author WTY
 * @date 2020/8/18 23:02
 **/
public class BeanAliasDemo {

    public static void main(String[] args) {

        //配置 XML 文件
        //启动 Spring 上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");

        // 通过别名 alias-user 查找曾用名为 user 的 User 对象
        User aliasUser = beanFactory.getBean("alias-user", User.class);

        //获取老的 user 对象
        User user = beanFactory.getBean("user", User.class);

        //true 说明这两个对象是同一个对象
        System.out.println("alias-user == user : " + (aliasUser == user));


    }

}

```



#### 	依赖查找也可以通过 #别名# 的方式去进行查找，可想而知，底层肯定有一个 beanName 和 beanAliasName 的一个映射，

#### 	好比找到别名之后就可以找到对应的名称，再通过名称去找到 对应的 Bean，相当于一个间接的关系。

#### 	在 Bean 的生命周期和 Bean 的元信息配置的时候具体展开说明。





#### 总结：

​	通过这个小 demo 可以看出，Bean 的名称和 Bean 的别名用起来并没有什么区别。只不过 BeanAliasName 在场景化方面会更加具体一点。

很多时候，我们的应用会用到一些第三方的 jar 包，或者是相关依赖资源。这时候我们不需要用原始的命名方式从 BeanFactory 中获取 Bean ，

可以根据 aliasName 来进行更场景化的一个依赖查找或者依赖注入。





## 3：注册 Spring Bean ： 如何将 BeanDefinition 注册到 Spring IoC 容器？



问题：有多少种方式注册 BeanDefinition 到 Spring IoC 容器？

答：

##### 	BeanDefinition 注册：

##### 	

##### 		· XML 配置元信息：（还有 Properties 文件的配置方法、YML 配置方法 - 这些后面说）

```java
<bean name="xxx" class="com.xxx.xxx.xxx" />
```



##### 	· Java 注解配置元信息：

##### 		· @Bean

##### 		· @Component

##### 		· @Import

##### 		· @Controller

​		...



##### 	· Java API 配置元信息：（最底层）

##### 		· 命名方式：BeanDefinitionRegister#registerBeanDefinition(String , BeanDefinition)

##### 		· 非命名方式：BeanDefinitionReaderUtils#registerWithGeneratedName( AbstractBeanDefinition , BeanDefinitionRegistry )

##### 		· 配置类方式：AnnotationBeanDefinitionReader#register( Class ... ) 



##### 新增文件：

##### 	AnnotationBeanDefinitionRegisterDemo.java



```java
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
@Import(AnnotationBeanDefinitionRegisterDemo.ConfigurationClass.class)
public class AnnotationBeanDefinitionRegisterDemo {

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
        annotationConfigApplicationContext.register(AnnotationBeanDefinitionRegisterDemo.class);


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

```



#### 总结：

​	1：注册 BeanDefinition 有三种方式：



​		第一种通过 XML 文件配置的方式运用 ClassPathXmlApplicationContext 的方式可以注册一个 BeanDefinition，



​		第二种是通过 JAVA 注解的方式来注册 BeanDefinition，例如：AnnotationConfigApplicationContext 指定注册类为 BeanDefinition，或者 @Bean，

​		@Component，@Import。



​		第三种则是通过 JAVA API 的方式来注册 BeanDefinition，例如：

​			BeanDefinitionReader.registerBeanDefinition( String beanName , BeanDefinition beanDefinition);

​			BeanDefnitionReaderUtils.registerWithGeneratedName( AbstractBeanDefinition beanDefinition , BeanDefinitionRegistry registry );

​			AnnocationConfigApplicationContext#register(Class<?> ... beanClass)



​	2：同时，注册 Bean 又分为命名注册 Bean 和 非命名注册 Bean 。实验证实到非命名 Bean 不会重复注册，另外值得一提的是在同一个 上下文中，BeanName

要保证唯一，例如：	

```java
 AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
```

这就相当于一个上下文，用他来注册 BeanDefinition，Bean 的名称要唯一。



3：假如非命名注册，包名 + # + 阿拉伯数字，链路如下：

```java
	BeanDefinitionReaderUtils.registerWithGeneratorName()
		-> generateBeanName()
			->	uniqueBeanName()
```









## 4：实例化 Spring Bean ： Bean实例化的方式有多少种？



### 1：Bean 的实例化（Instantiation）



#### 	· 常规方式：

##### 		· 通过构造器（配置元信息：XML、Java注解、Java API）

​				包括空参构造和带参构造，可以参照  **AnnotationBeanDefinitionRegisterDemo.java**

##### 		· 通过静态工厂（配置元信息：XML、Java API）

​				通过静态方法实例化 Bean，->  **工厂模式**

##### 		· 通过 Bean 工厂方法（配置元信息：XML、Java API）

​				工厂模式：实例的工厂方法。 -> **抽象工厂模式**

##### 		· 通过 FactoryBean （配置元信息：XML、Java注解、Java API）

​				处理 Bean 的复杂逻并且实例化，比如三方包中的 Bean。



#### 	· 特殊方式：

##### 		· 通过 ServiceLoadFactoryBean （配置元信息：XML、Java注解、Java API）

​				ServiceLoad 是 Java 传统的一个 API，SPI机制，Dubbo注册中心的选择，mybatis 接口具体实现类等，都有用到。

​				通过 Java 的 ServiceLoad API  可以通过一个接口或者类的的方式来加载 classPath 上面的资源文件。

##### 		· 通过 AutowrieCapableBeanFactory#createBean( Class , int , boolean )

​				把 Bean 的类型穿进去，来产生一个Bean

##### 		· 通过 BeanDefinitionBeanFactory#registerBeanFactory( String , BeanDefinition )

​				这个可以参照 **AnnotationBeanDefinitionRegisterDemo.java 的 registerUserDefinition(BeanDefinitionRegistery , String , Class )** 方法





#### 本章新增文件：



##### 普通的 Spring Bean 实例化代码：

​	spring-bean 模块下 resources/META-INF/**bean-instantiation-context.xml**

​	spring-overview 模块下 **User.java** 增加静态工厂方法。

​	根 pom 修改 maven 编译级别：**think-in-spring/pom.xml**

​	spring-bean 模块下 **UserFactory.java**

​	spring-bean 模块下 **DefaultUserFactory.java**

​	spring-bean 模块下 **UserFactoryBean.java**

​	spring-bean 模块下 **BeanInstantiationDemo.java**

​	spring-bean 模块下 **special-bean-instantiation-context.xml**

​	spring-bean 模块下 **SpecialBeanInstantiationDemo.java**



##### 特殊 Spring Bean 实例化代码：

​	spring-bean 模块下 resources/META-INF/**bean-instantiation-context.xml**

​	spring-bean 模块下 **SpecialBeanInstantiationDemo.java**





### 普通的 Spring Bean 实例化方式：



**bean-instantiation-context.xml 代码如下：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 有参构造 创建 Bean -->
    <bean id="constructor-user-arg" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User">
        <constructor-arg name="id" value="2" />
        <constructor-arg name="name" value="constructor-user" />
    </bean>

    <!-- 空参构造 创建 Bean -->
    <bean id="constructor-user-no-arg" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User" />

    <!-- 通过 静态方法创建 bean -->
    <bean id="user-by-static-method" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User" factory-method="createUser" >
        <!-- setter 方法赋值 -->
        <property name="id" value="19" />
        <property name="name" value="小周" />
    </bean>

    <!-- 实例（Bean）方法创建 Bean -->
    <bean id="user-by-instantiation-method" factory-bean="userFactory" factory-method="createUser" />

    <!-- 抽象工厂方法创建 Bean 如果这里想不定义 Bean 的名称，就要保证抽象类 UserFactory 的子类有且只有一个，就是他。但是我就要多个。。。 -->
    <bean id="userFactory" class="org.example.thinking.in.spring.bean.definition.factory.DefaultUserFactory" />

    <!-- FactoryBean 创建 User Bean , UserFactoryBean 里面不是直接去定义一个User Bean ,而是去定义一个 BeanFactory，产生连接 -->
    <bean id="user-by-factory-bean" class="org.example.thinking.in.spring.bean.definition.factory.UserFactoryBean" />

</beans>
```



**User.java 代码如下：**

```java
package org.example.thinking.in.spring.ioc.overview.dependency.domain;

/**
 * 万能的用户类
 * @author WTY
 * @date 2020/8/13 23:31
 **/
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        System.err.println("id setter 方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.err.println("name setter 方法");
    }

    public User(){
        System.err.println("空参构造");
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        System.err.println("有参构造");
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 创建 User ，通过 XML 种 bean 标签 factory-method
     * 具体的 XML 配置见 spring-bean 模块下的  resources/META/INF/bean-creation-context.xml
     *
     * @author WTY
     * @date 2020/8/19 23:43
     * @since Spring Bean 实例化有多少种方式
     * @return org.example.thinking.in.spring.ioc.overview.dependency.domain.User
     **/
    public static User createUser(){
        //bean 标签的属性会盖住这里的属性
        User user = new User();
        user.setId(88L);
        user.setName("沙雕");
        return user;
    }

}

```



**think-in-spring/pom.xml 代码如下：**

```xml
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
        <module>spring-bean</module>
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

    <!-- 修改编译级别，把 jdk1.5 改成 jdk1.8 -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```



**UserFactory.java 代码如下：**

```java
package org.example.thinking.in.spring.bean.definition.factory;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * User 抽象工厂类 {@link User}
 * @author WTY
 * @date 2020/8/20 0:04
 **/
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

}

```



**DefaultUserFactory.java 代码如下：**

```java
package org.example.thinking.in.spring.bean.definition.factory;

/**
 * 默认 UserFactory 的实现 {@link UserFactory}
 * @author WTY
 * @date 2020/8/20 0:11
 **/
public class DefaultUserFactory implements UserFactory {
}

```



 **UserFactoryBean.java 代码如下：**

```java
package org.example.thinking.in.spring.bean.definition.factory;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * {@link User} User FactoryBean 的实现 {@link org.springframework.beans.factory.FactoryBean}
 * @author WTY
 * @date 2020/8/20 0:32
 **/
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    //Spring 5 之后 isSingleton() 用了 java 8 的 default 方法，默认为单例。

}

```



**BeanInstantiationDemo.java 代码如下：**

```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 实例化 示例
 * @author WTY
 * @date 2020/8/19 23:51
 **/
public class BeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 XML 文件 -> bean-instantiation-context.xml

        //启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");

        //有参构造器创建
        User constructorUserArg = beanFactory.getBean("constructor-user-arg", User.class);
        System.out.println("constructorUserArg ：" + constructorUserArg);

        //空载构造器创建
        User constructorUserNoArg = beanFactory.getBean("constructor-user-no-arg", User.class);
        System.out.println("constructorUserNoArg ：" + constructorUserNoArg);

        //获取 静态方法实例的 User Bean 对象
        User userByStaticMethod = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println("userByStaticMethod ：" + userByStaticMethod);

        //获取 实例方法创建的 User Bean 对象
        User userByInstantiationMethod = beanFactory.getBean("user-by-instantiation-method", User.class);
        System.out.println("userByInstantiationMethod ：" + userByInstantiationMethod);


        System.err.println("静态方法实例的 User == 实例方法创建的 Bean 吗 -> " + (userByStaticMethod == userByInstantiationMethod));

        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);

        System.out.println("userByFactoryBean ：" + userByFactoryBean);

        System.out.println("实例方法创建的 User Bean 和 UserFactoryBean 创建的 User 是同一个吗？ -> " + (userByInstantiationMethod == userByFactoryBean));


    }

}

```





### 特殊的 Spring Bean 实例化方式：



**bean-instantiation-context.xml 代码如下：**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- 有参构造 创建 Bean -->
    <bean id="constructor-user-arg" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User">
        <constructor-arg name="id" value="2" />
        <constructor-arg name="name" value="constructor-user" />
    </bean>

    <!-- 空参构造 创建 Bean -->
    <bean id="constructor-user-no-arg" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User" />

    <!-- 通过 静态方法创建 bean -->
    <bean id="user-by-static-method" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User" factory-method="createUser" >
        <!-- setter 方法赋值 -->
        <property name="id" value="19" />
        <property name="name" value="小周" />
    </bean>

    <!-- 实例（Bean）方法创建 Bean -->
    <bean id="user-by-instantiation-method" factory-bean="userFactory" factory-method="createUser" />

    <!-- 抽象工厂方法创建 Bean 如果这里想不定义 Bean 的名称，就要保证抽象类 UserFactory 的子类有且只有一个，就是他。但是我就要多个。。。 -->
    <bean id="userFactory" class="org.example.thinking.in.spring.bean.factory.DefaultUserFactory" />

    <!-- FactoryBean 创建 User Bean , UserFactoryBean 里面不是直接去定义一个User Bean ,而是去定义一个 BeanFactory，产生连接 -->
    <bean id="user-by-factory-bean" class="org.example.thinking.in.spring.bean.factory.UserFactoryBean" />

</beans>
```



##### java.util.ServiceLoader 出现于 jdk1.6 ，具体可以看看这些博客的示例：

https://www.cnblogs.com/aspirant/p/10616704.html

https://www.jianshu.com/p/7601ba434ff4

https://www.cnblogs.com/lwbqqyumidi/p/11991748.html



Spring 给出适配 ServiceLoader 实现的类是：**ServiceLoaderFactoryBean.java**

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
 */

package org.springframework.beans.factory.serviceloader;

import java.util.ServiceLoader;

import org.springframework.beans.factory.BeanClassLoaderAware;

/**
 * {@link org.springframework.beans.factory.FactoryBean} that exposes the
 * JDK 1.6 {@link java.util.ServiceLoader} for the configured service class.
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see java.util.ServiceLoader
 */
public class ServiceLoaderFactoryBean extends AbstractServiceLoaderBasedFactoryBean implements BeanClassLoaderAware {

	@Override
	protected Object getObjectToExpose(ServiceLoader<?> serviceLoader) {
		return serviceLoader;
	}

	@Override
	public Class<?> getObjectType() {
		return ServiceLoader.class;
	}

}

```



ServiceLoaderFactoryBean 实例化 UserFactory 需要结合 AbstractFactoryBean 中的 **getObject()** 方法，ServiceLoaderFactoryBean 属于 AbstractFactoryBean 的子类。这里代码太多。。。只看调用链路代码。（想看完整的自己去看源码）

```java
	@Override
	public final T getObject() throws Exception {
        //是单例的话就创建一个 单例
		if (isSingleton()) {
			return (this.initialized ? this.singletonInstance : getEarlySingletonInstance());
		}
		else {
            //不是单例就创建这么个玩意。
			return createInstance();
		}
	}
```



这里我选择看单例的：**getEarlySingletonInstance()**

```java
/**
	 * Determine an 'early singleton' instance, exposed in case of a
	 * circular reference. Not called in a non-circular scenario.
	 */
	@SuppressWarnings("unchecked")
	private T getEarlySingletonInstance() throws Exception {
		Class<?>[] ifcs = getEarlySingletonInterfaces();
		if (ifcs == null) {
			throw new FactoryBeanNotInitializedException(
					getClass().getName() + " does not support circular references");
		}
		if (this.earlySingletonInstance == null) {
            //这里会产生一个代理（动态代理），这个代理会动态的生成一些东西。
			this.earlySingletonInstance = (T) Proxy.newProxyInstance(
					this.beanClassLoader, ifcs, new EarlySingletonInvocationHandler());
		}
		return this.earlySingletonInstance;
	}
```



**EarlySingletonInvocationHandler()**

```java
/**
	 * Reflective InvocationHandler for lazy access to the actual singleton object.
	 */
	private class EarlySingletonInvocationHandler implements InvocationHandler {

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (ReflectionUtils.isEqualsMethod(method)) {
				// Only consider equal when proxies are identical.
				return (proxy == args[0]);
			}
			else if (ReflectionUtils.isHashCodeMethod(method)) {
				// Use hashCode of reference proxy.
				return System.identityHashCode(proxy);
			}
			else if (!initialized && ReflectionUtils.isToStringMethod(method)) {
				return "Early singleton proxy for interfaces " +
						ObjectUtils.nullSafeToString(getEarlySingletonInterfaces());
			}
			try {
				return method.invoke(getSingletonInstance(), args);
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
	}
```



非单例的也看一眼吧。。**createInstance()**

```java
	/**
	 * Template method that subclasses must override to construct
	 * the object returned by this factory.
	 * <p>Invoked on initialization of this FactoryBean in case of
	 * a singleton; else, on each {@link #getObject()} call.
	 * @return the object returned by this factory
	 * @throws Exception if an exception occurred during object creation
	 * @see #getObject()
	 */
	protected abstract T createInstance() throws Exception;
```



ServiceLoadFactoryBean 的话会有一个具体的实现：**AbstractServiceLoaderBasedFactoryBean.java** # createInstance()

```java
	
	@Nullable
	private Class<?> serviceType;

	/**
	 * Return the desired service type.
	 */
	@Nullable
	public Class<?> getServiceType() {
        //这里的 serviceType 是给 UserFactory 用的，需要我们在 xml 中给出具体的配置。
		return this.serviceType;
	}


	/**
	 * Delegates to {@link #getObjectToExpose(java.util.ServiceLoader)}.
	 * @return the object to expose
	 */
	@Override
	protected Object createInstance() {
		Assert.notNull(getServiceType(), "Property 'serviceType' is required");
        //获取我们配置的接口是哪一个，然后通过给的类加载器去加载这个接口下的子类，这个方法里就是一个迭代器的操作。
		return getObjectToExpose(ServiceLoader.load(getServiceType(), this.beanClassLoader));
	}

	
```



**getObjectToExpose(ServiceLoader.load(getServiceType(), this.beanClassLoader));**  这里只是给了一个抽象的定义

```java
	/**
	 * Determine the actual object to expose for the given ServiceLoader.
	 * <p>Left to concrete subclasses.
	 * @param serviceLoader the ServiceLoader for the configured service class
	 * @return the object to expose
	 */
	protected abstract Object getObjectToExpose(ServiceLoader<?> serviceLoader);
```



具体实现看：**ServiceFactoryBean.java**  这个类从 Spring 2.5 开始有的，这个只会读取 UserFactory 的一个子类返回。

```java
package org.springframework.beans.factory.serviceloader;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.lang.Nullable;

/**
 * {@link org.springframework.beans.factory.FactoryBean} that exposes the
 * 'primary' service for the configured service class, obtained through
 * the JDK 1.6 {@link java.util.ServiceLoader} facility.
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see java.util.ServiceLoader
 */
public class ServiceFactoryBean extends AbstractServiceLoaderBasedFactoryBean implements BeanClassLoaderAware {

	@Override
	protected Object getObjectToExpose(ServiceLoader<?> serviceLoader) {
		Iterator<?> it = serviceLoader.iterator();
		//循环的获取我们接口下的子类
        if (!it.hasNext()) {
			throw new IllegalStateException(
					"ServiceLoader could not find service for type [" + getServiceType() + "]");
		}
        //这个地方返回有且仅有一个。
		return it.next();
	}

	@Override
	@Nullable
	public Class<?> getObjectType() {
		return getServiceType();
	}

}

```



问题：我定义的接口有很多个实现，我都想加载怎么办？

答：用 **ServiceListFactoryBean.java** 这个类来做，这个类同样出自 Spring 2.5



```java
package org.springframework.beans.factory.serviceloader;

import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import org.springframework.beans.factory.BeanClassLoaderAware;

/**
 * {@link org.springframework.beans.factory.FactoryBean} that exposes <i>all</i>
 * services for the configured service class, represented as a List of service objects,
 * obtained through the JDK 1.6 {@link java.util.ServiceLoader} facility.
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see java.util.ServiceLoader
 */
public class ServiceListFactoryBean extends AbstractServiceLoaderBasedFactoryBean implements BeanClassLoaderAware {

	@Override
	protected Object getObjectToExpose(ServiceLoader<?> serviceLoader) {
		List<Object> result = new LinkedList<>();
        //循环获取，添加到一个 List 里面，同意返回
		for (Object loaderObject : serviceLoader) {
			result.add(loaderObject);
		}
		return result;
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

}

```



**SpecialBeanInstantiationDemo.java 代码如下：**

```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化示例
 * @author WTY
 * @date 2020/8/20 0:45
 **/
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 XML 文件 -> special-bean-instantiation-context.xml

        //创建 BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        // SPI ServiceLoader 调用演示
        serviceLoaderDemo();

        ServiceLoader<UserFactory> userFactoryByServiceLoader = beanFactory.getBean("userFactoryByServiceLoader", ServiceLoader.class);

        //通过 Spring 实例化 ServiceLoader 来获取 UserFactory
        displayServiceLoaderDemo(userFactoryByServiceLoader);


        //2：通过 AutowireCapableBeanFactory 来实例化 UserFactory
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        //通过 ApplicationContext 来获取 AutowireCapableBeanFactory 对象，因为 ClassPathXmlApplicationContext 无法获取到 AutowireCapableBeanFactory 这个对象
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

        //创建 Spring Bean 的时候一定不要用接口。。。
//        UserFactory esayUserFactoryByAutowireCapableBeanFactory = autowireCapableBeanFactory.createBean(UserFactory.class);

        UserFactory esayUserFactoryByAutowireCapableBeanFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);

        System.out.println(esayUserFactoryByAutowireCapableBeanFactory.createUser());

        UserFactory instantiationUserFactoryByAutowireCapableBeanFactory = (UserFactory)autowireCapableBeanFactory.createBean(DefaultUserFactory.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);

        System.out.println(instantiationUserFactoryByAutowireCapableBeanFactory.createUser());


        //通过 AnnotationConfigApplicationContext 创建 Bean

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DefaultUserFactory.class);
        annotationConfigApplicationContext.refresh();
        DefaultUserFactory bean = annotationConfigApplicationContext.getBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());

    }


    /** 这个会逐一输出多个 实现 */
    public static void serviceLoaderDemo(){

        //通过传入接口 + 类加载器的方式去获得 UserFactory
        ServiceLoader<UserFactory> userFactoryServiceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());

        displayServiceLoaderDemo(userFactoryServiceLoader);
    }

    /** 这里只会输出一个实现 */
    public static void displayServiceLoaderDemo(ServiceLoader<UserFactory> userFactoryServiceLoader){
        //迭代器遍历取出 - 这里会去重的。。。算得上是 jdk 里面的 翻转控制，遵循好莱坞原则 - 你不要来找我，我来找你。
        Iterator<UserFactory> car = userFactoryServiceLoader.iterator();
        while(car.hasNext()){
            UserFactory next = car.next();
            System.out.println(next.createUser());
        }
    }

}

```



##### 补充：

**bean-instantiation-context.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- SPI 机制实例化 userFactory 这个需要结合 AbstractFactoryBean#getObject() -->
    <bean id="userFactoryByServiceLoader" class="org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean">

        <!-- 告诉 AbstractServiceLoaderBasedFactoryBean serviceType ，serviceType 就是指具体的哪个接口 -->
        <property name="serviceType" value="org.example.thinking.in.spring.bean.definition.factory.UserFactory" />

    </bean>

</beans>
```



**SpecialBeanInstantiationDemo.java**

```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.definition.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.definition.factory.UserFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 特殊的 Bean 实例化示例
 * @author WTY
 * @date 2020/8/20 0:45
 **/
public class SpecialBeanInstantiationDemo {

    public static void main(String[] args) {

        //配置 XML 文件 -> special-bean-instantiation-context.xml

        //创建 BeanFactory
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        // SPI ServiceLoader 调用演示
        serviceLoaderDemo();

        //通过 xml 配置的 ServiceLoadFactory 指定 serviceType 加载 META-INF/services 下配置的的 UserFactory 的子类
        ServiceLoader<UserFactory> userFactoryByServiceLoader = beanFactory.getBean("userFactoryByServiceLoader", ServiceLoader.class);

        //通过 Spring 实例化 ServiceLoader 来获取 UserFactory
        displayServiceLoaderDemo(userFactoryByServiceLoader);


        //2：通过 AutowireCapableBeanFactory 来实例化 UserFactory
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/special-bean-instantiation-context.xml");

        //通过 ApplicationContext 来获取 AutowireCapableBeanFactory 对象，因为 ClassPathXmlApplicationContext 无法获取到 AutowireCapableBeanFactory 这个对象
        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();

        //创建 Spring Bean 的时候一定不要用接口。。。
//        UserFactory esayUserFactoryByAutowireCapableBeanFactory = autowireCapableBeanFactory.createBean(UserFactory.class);

        UserFactory esayUserFactoryByAutowireCapableBeanFactory = autowireCapableBeanFactory.createBean(DefaultUserFactory.class);

        System.out.println(esayUserFactoryByAutowireCapableBeanFactory.createUser());

        UserFactory instantiationUserFactoryByAutowireCapableBeanFactory = (UserFactory)autowireCapableBeanFactory.createBean(DefaultUserFactory.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);

        System.out.println(instantiationUserFactoryByAutowireCapableBeanFactory.createUser());


        //通过 AnnotationConfigApplicationContext 创建 Bean

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(DefaultUserFactory.class);
        annotationConfigApplicationContext.refresh();
        DefaultUserFactory bean = annotationConfigApplicationContext.getBean(DefaultUserFactory.class);
        System.out.println(bean.createUser());

    }


    /** 这个会逐一输出多个 实现 */
    public static void serviceLoaderDemo(){

        //通过传入接口 + 类加载器的方式去获得 UserFactory
        ServiceLoader<UserFactory> userFactoryServiceLoader = ServiceLoader.load(UserFactory.class, Thread.currentThread().getContextClassLoader());

        displayServiceLoaderDemo(userFactoryServiceLoader);
    }

    /** 这里只会输出一个实现 */
    public static void displayServiceLoaderDemo(ServiceLoader<UserFactory> userFactoryServiceLoader){
        //迭代器遍历取出 - 这里会去重的。。。算得上是 jdk 里面的 翻转控制，遵循好莱坞原则 - 你不要来找我，我来找你。
        Iterator<UserFactory> car = userFactoryServiceLoader.iterator();
        while(car.hasNext()){
            UserFactory next = car.next();
            System.out.println(next.createUser());
        }
    }



}

```



### 总结：

​	我们通过常规方式和特殊方式来创建 Bean ，相信大家都会对 XML、Java注解、Java API 配置 Bean 有所收获。

常规方式创建 Bean，主要为 构造方法，setter方法，普通方法、静态方法这么四种创建方式，通过 ClassPathXmlApplicationContext 来获取 BeanFactory，从而调用 getBean() 方法获取 Bean。

特殊方式，主要是通过 jdk提供的 Serviceloader 来作为控制翻转的基础，结合 Spring 的 ServiceLoaderFactoryBean 对 jdk 的 ServiceLoader 的适配，通过对 AbstractBasedServiceLoaderFactoryBean 的 createInstantce() 方法，结合 getObjectToExpose() 的具体实现来获取到 FactoryBean，具体又分为了ServiceFactoryBean 只返回一个 FactoryBean 以及 ServiceListFactoryBean 返回集合的 FactoryBean。依赖于AbstractServiceLoaderBasedFactoryBean 的 serviceType（指定的接口）来进行 SPI 的加载。

第二种就是通过 ApplicationContext 中获取到的 AutowireCapableFactoryBean 的 createBean() 来创建 Bean 对象。

第三种最常见也最常用，通过解析注解的方式来创建 Bean ：AnnotationConfigApplicationContext 对象的 register()。



后面将会对如何初始化 开始展开议题。







## 5：初始化 Spring Bean ： Bean 初始化时的方法有哪些？



#### Bean 初始化 （Initialization）



##### 	· @PostConstruct 标注方法

​			Java 的标准注解 ，在 Java 1.6 的时候引入的。

##### 	· 实现 InitializingBean 接口的 afterPropertiesSet() 方法



##### 	· 自定义初始化方法：

##### 		· XML 配置：	 <bean init-method="init"  / >	

##### 		· Java 注解：	 @Bean( initMethod="init" )

##### 		· Java API：	 AbstractBeanDefinition#setInitMethodName( String );



#### 问题：假设以上三种方式全部都在同一个 Bean 中定义，那么这些方法的执行顺序是怎么样的？



#### 答：顺序是 @PostConstruct -> InitializationBean接口 -> @Bean 一系列的自定义初始化方法（XML、@Bean、Java API）



##### 新增文件：

##### 	BeanInitializationDemo.java

##### 	bean-initialization-context.xml

##### 改动文件：

##### 	DefaultUserFactory.java





##### BeanInitializationDemo.java

```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean 初始化 Demo
 * @author WTY
 * @date 2020/8/22 13:59
 **/
//@Configuration //Configuration Class，这里可以加也可以不加
public class BeanInitializationDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class
        applicationContext.register(BeanInitializationDemo.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);

//        System.out.println(userFactory.createUser());



        //关闭 Spring 应用上下文
        applicationContext.close();


        System.err.println("\r\n 演示 XML 配置读取，初始化Bean \r\n");

        //xml 方式实现
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-initialization-context.xml");
        UserFactory bean = classPathXmlApplicationContext.getBean(UserFactory.class);

        classPathXmlApplicationContext.refresh();
        classPathXmlApplicationContext.close();

    }

    @Bean(initMethod = "initDefaultUserFactory")
    public UserFactory userFactory(){
        return new DefaultUserFactory();
    }

}

```



##### bean-initialization-context.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="initialization-user-factory" class="org.example.thinking.in.spring.bean.factory.DefaultUserFactory" init-method="xmlInitDefaultUserFactory" />


</beans>
```



##### DefaultUserFactory.java

```java
package org.example.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

/**
 * 默认 UserFactory 的实现 {@link UserFactory}
 * @author WTY
 * @date 2020/8/20 0:11
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean {

    // Spring Bean 初始化章节增加
    @PostConstruct
    public void init(){
        System.out.println("--------  @PostConstruct  DefaultUserFactory 初始化  ----------");
    }


    public void initDefaultUserFactory(){
        System.out.println("--------  @Bean 自定义初始化方法：  DefaultUserFactory#initDefaultUserFactory() 初始化  ----------");
    }

    public void xmlInitDefaultUserFactory(){
        System.out.println("--------  XML 自定义初始化方法：  InitializingBean#afterPropertiesSet() 初始化  ----------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------  InitializingBean 初始化方法：  InitializingBean#afterPropertiesSet() 初始化  ----------");
    }


}

```



##### Java API 来定义Spring Bean 的初始化 ->  AbstractBeanDefinition.java



```java
	/**
		这个方法在后面的 Spring 5.1 里面重构了，提到了 BeanDefinition 里面了，但是一定要记住这个实现，这个从 Spring 1.0 就开始有了。
	 * Set the name of the initializer method.
	 * <p>The default is {@code null} in which case there is no initializer method.
	 */
	@Override
	public void setInitMethodName(@Nullable String initMethodName) {
		this.initMethodName = initMethodName;
	}
```



##### 重构的 BeanDefinition.java

```java
/**
	 * Set the name of the initializer method.
	 * @since 5.1
	 */
	void setInitMethodName(@Nullable String initMethodName);
```



这里就是说当你看到这里。。。准备去用学来的 setInitMethodName 初始化的时候，请切记。。看版本



可以打个条件断点，看一下 自定义的 @Bean( initMethod="initDefaultUserFactory" ) 会不会走到 AbstractBeanDefinition#setInitMethodName() 里面来。



### 总结：

​	通过案例分别演示了 @PostConstruct、InitializingBean接口，自定义初始化方法（@Bean、XML、AbstractBeanDefinition#initMethodName()）这三种

Spring Bean 初始化的方法。他的执行顺序是有规律的 @PostConstrct 最先执行，其次是 InitializingBean 接口，最后是自定义的初始化方法（@Bean、XML

、AbstractBeanDefinition#initMethodName(String initMethodName)）。记住这个可以更好的初始化 Bean，并且可以防止初始化的时候依赖倒转问题。





## 6：延迟初始化 Spring Bean ：延迟初始化 Spring Bean 初始化 Bean 会影响依赖注入吗？



这个在 Bean 的生命周期中是比较重要的。设计模式中也有一种延迟加载的模式，和这个差不多。



##### Bean 的延迟初始化（Lazy Initialization）

##### 	· XML 配置： <bean lazy-init="true" / >

##### 	· Java 注解：@Lazy(true)



##### 问题：当某个 Bean 定义为延迟初始化，那么，Spring 容器返回的对象与非延迟的对象存在什么样的差异？



##### 答：以 AnnotationConfigApplicationContext 为例，非延迟加载：会在 AnnotationConfigApplicationContext 调用 refresh() 方法的时候，进行

##### Bean 的初始化操作，如果把 Bean 标记为 lazy （懒加载），那么 Bean 在 AnnotationConfigApplicationContext 调用 refresh 方法的时候，不去

##### 进行初始化加载，一直到我们进行依赖查找的时候才会去初始化 Bean 。例如：AnnotationConfigApplicationContext 调用 getBean() 方法的时候去

##### 加载 Bean。



##### 问题：延迟加载和非延迟加载的实现有什么不同？

##### 答：以 AnnotationConfigApplicationContext 为例，首先看到 refresh() 这个方法。

```java
@Override
	public void refresh() throws BeansException, IllegalStateException {
		synchronized (this.startupShutdownMonitor) {
			// Prepare this context for refreshing.
			prepareRefresh();

			// Tell the subclass to refresh the internal bean factory.
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

			// Prepare the bean factory for use in this context.
			prepareBeanFactory(beanFactory);

			try {
				// Allows post-processing of the bean factory in context subclasses.
				postProcessBeanFactory(beanFactory);

				// Invoke factory processors registered as beans in the context.
				invokeBeanFactoryPostProcessors(beanFactory);

				// Register bean processors that intercept bean creation.
				registerBeanPostProcessors(beanFactory);

				// Initialize message source for this context.
				initMessageSource();

				// Initialize event multicaster for this context.
				initApplicationEventMulticaster();

				// Initialize other special beans in specific context subclasses.
				onRefresh();

				// Check for listener beans and register them.
				registerListeners();

                //在这个地方会说明，只会初始化非懒加载的 单例Bean.
				// Instantiate all remaining (non-lazy-init) singletons.
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



##### // Instantiate all remaining (non-lazy-init) singletons.

##### finishBeanFactoryInitialization(beanFactory); 

这个方法就是说会初始化所有非懒加载的单例 Bean



```java
/**
	 * Finish the initialization of this context's bean factory,
	 * initializing all remaining singleton beans.
	 */
	protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
		// Initialize conversion service for this context.
		if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
				beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
			beanFactory.setConversionService(
					beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
		}

		// Register a default embedded value resolver if no bean post-processor
		// (such as a PropertyPlaceholderConfigurer bean) registered any before:
		// at this point, primarily for resolution in annotation attribute values.
		if (!beanFactory.hasEmbeddedValueResolver()) {
			beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
		}

		// Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
		String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
		for (String weaverAwareName : weaverAwareNames) {
			getBean(weaverAwareName);
		}

		// Stop using the temporary ClassLoader for type matching.
		beanFactory.setTempClassLoader(null);

		// Allow for caching all bean definition metadata, not expecting further changes.
		beanFactory.freezeConfiguration();

        //普通的 Bean 在这里初始化，这里就是 Spring 初始化 Bean 的前置动作。
		// Instantiate all remaining (non-lazy-init) singletons.
		beanFactory.preInstantiateSingletons();
	}
```



##### beanFactory.preInstantiateSingletions() 这个方法里面有个判断逻辑，这个方法是 ConfigurableListBeanFactory 中的 preInstantiateSingletions() 方法，并且在 DefaultListableBeanFactory 这个子类中给了具体的实现。



```java
@Override
	public void preInstantiateSingletons() throws BeansException {
		if (logger.isTraceEnabled()) {
			logger.trace("Pre-instantiating singletons in " + this);
		}

		// Iterate over a copy to allow for init methods which in turn register new bean definitions.
		// While this may not be part of the regular factory bootstrap, it does otherwise work fine.
		List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);

		// Trigger initialization of all non-lazy singleton beans...
		for (String beanName : beanNames) {
			RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
            //这里就是判断 BeanDefinition 不是抽象类 并且不是单例的，不是懒加载的 才会走这里的方式去初始化 Bean
			if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
				if (isFactoryBean(beanName)) {
					Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
					if (bean instanceof FactoryBean) {
						final FactoryBean<?> factory = (FactoryBean<?>) bean;
						boolean isEagerInit;
						if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
							isEagerInit = AccessController.doPrivileged((PrivilegedAction<Boolean>)
											((SmartFactoryBean<?>) factory)::isEagerInit,
									getAccessControlContext());
						}
						else {
							isEagerInit = (factory instanceof SmartFactoryBean &&
									((SmartFactoryBean<?>) factory).isEagerInit());
						}
						if (isEagerInit) {
							getBean(beanName);
						}
					}
				}
				else {
					getBean(beanName);
				}
			}
		}

		// Trigger post-initialization callback for all applicable beans...
		for (String beanName : beanNames) {
			Object singletonInstance = getSingleton(beanName);
			if (singletonInstance instanceof SmartInitializingSingleton) {
				final SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
				if (System.getSecurityManager() != null) {
					AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
						smartSingleton.afterSingletonsInstantiated();
						return null;
					}, getAccessControlContext());
				}
				else {
					smartSingleton.afterSingletonsInstantiated();
				}
			}
		}
	}
```





### 总结：

​	延迟初始化和非延迟初始化的区别在于，它们在应用上下文生命周期之前或者之后来进行输出。







## 7：销毁 Spring Bean ：销毁 Bean 的基本操作有哪些？



### Bean 销毁（Destroy）



##### 	· @PreDestroy 标注方法：

​			Java 标准注解，字面意思：销毁之前进行操作。

​			这里的销毁和我们这里的销毁有点冲突的地方，这里说的销毁是 Bean 对象被销毁，就是说 Bean 对象在被回收之前（回收是指的 Java GC 的回收），

​			才会被销毁掉。

##### 	· 实现 DisposableBean 接口的 destroy() 方法：



##### 	· 自定义销毁方法：

##### 		· XML 配置：<bean destroy="destroy" / >

##### 		· Java 注解：@Bean(destroy="destroy")

##### 		· Java API ：AbstractBeanDefinition#setDestroyMethodName( String )



##### 问题：假设以上三种方式在同一 Bean 里面定义，那么这些方法的执行顺序是怎样的？

##### 答： 和 Spring 初始化方法的规则一致，只不过名称不同罢了。 @PreDestroy  ->  DisposableBean -> 自定义销毁方法（@Bean、XML、

##### AbstractBeanDefinition#setDestroyMethodName(String)）。真正的关闭是在 AbsratctApplicationContext的 close() 方法来实现的。



##### close()

```java
@Override
	public void close() {
		synchronized (this.startupShutdownMonitor) {
            //销毁的方法
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



##### doClose()

```java
/**
	 * Actually performs context closing: publishes a ContextClosedEvent and
	 * destroys the singletons in the bean factory of this application context.
	 * <p>Called by both {@code close()} and a JVM shutdown hook, if any.
	 * @see org.springframework.context.event.ContextClosedEvent
	 * @see #destroyBeans()
	 * @see #close()
	 * @see #registerShutdownHook()
	 */
	protected void doClose() {
		// Check whether an actual close attempt is necessary...
		if (this.active.get() && this.closed.compareAndSet(false, true)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Closing " + this);
			}

			LiveBeansView.unregisterApplicationContext(this);

			try {
				// Publish shutdown event.
				publishEvent(new ContextClosedEvent(this));
			}
			catch (Throwable ex) {
				logger.warn("Exception thrown from ApplicationListener handling ContextClosedEvent", ex);
			}

			// Stop all Lifecycle beans, to avoid delays during individual destruction.
			if (this.lifecycleProcessor != null) {
				try {
					this.lifecycleProcessor.onClose();
				}
				catch (Throwable ex) {
					logger.warn("Exception thrown from LifecycleProcessor on context close", ex);
				}
			}

            //这个方法销毁所有的缓存单例的上下文中的 BeanFactory
			// Destroy all cached singletons in the context's BeanFactory.
			destroyBeans();

            //这里是关闭上下文本身的状态
			// Close the state of this context itself.
			closeBeanFactory();

			// Let subclasses do some final clean-up if they wish...
			onClose();

			// Reset local application listeners to pre-refresh state.
			if (this.earlyApplicationListeners != null) {
				this.applicationListeners.clear();
				this.applicationListeners.addAll(this.earlyApplicationListeners);
			}

			// Switch to inactive.
			this.active.set(false);
		}
	}
```



##### destoryBeans()

```java
	protected void destroyBeans() {
		getBeanFactory().destroySingletons();
	}
```

获取到 BeanFactory，然后去销毁



##### destroySingletions() 这个方法在 ConfigurableBeanFactory 接口里面，有两个实现类，我们可以看一下 DefaultListableBeanFactory 里面怎么做的。

```java
/**
	 * Destroy all singleton beans in this factory, including inner beans that have
	 * been registered as disposable. To be called on shutdown of a factory.
	 * <p>Any exception that arises during destruction should be caught
	 * and logged instead of propagated to the caller of this method.
	 */
	void destroySingletons();
```



##### DefaultListableBeanFactory # destroySingletons();

```java
@Override
	public void destroySingletons() {
		super.destroySingletons();
		updateManualSingletonNames(Set::clear, set -> !set.isEmpty());
		clearByTypeCache();
	}
```

这里的 **super.destroySingletons()** 方法是继承了 **DefaultSingletonBeanRegistry** 这个类，这个类也是 **ConfigurableBeanFactory** 类的实现类。无奈，只好

看一下 **DefaultSingletonBeanRegistry** 这个类的 **destroySingletons()** 怎么实现的。



##### DefaultSingletonBeanRegisty # destroySingletons();

```java
public void destroySingletons() {
		if (logger.isTraceEnabled()) {
			logger.trace("Destroying singletons in " + this);
		}
    	//加锁。。估计有人多线程调用过这里。锁住单例的对象们
		synchronized (this.singletonObjects) {
            //这是个标识，指示我们当前是否在destroySingletons中的标志。
			this.singletonsCurrentlyInDestruction = true;
		}

	    //要关闭的 BeanName 都在这里面
		String[] disposableBeanNames;
    	//加锁。。锁住要关闭的 Bean 对象们
		synchronized (this.disposableBeans) {
            //把要关闭的对象 Map 的 key 拿出来，弄成一个数组
            //其实就是统计有多少个 Bean 对象实现了 disposableBean 这个接口。
			disposableBeanNames = StringUtils.toStringArray(this.disposableBeans.keySet());
		}
	    // 循环数组去关闭
		for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
			destroySingleton(disposableBeanNames[i]);
		}

	    //一大堆的清空 map
		this.containedBeanMap.clear();
		this.dependentBeanMap.clear();
		this.dependenciesForBeanMap.clear();

	    //把单例的缓存清除掉
		clearSingletonCache();
	}
```



##### destroySingletion();

```java
/**
	 * Destroy the given bean. Delegates to {@code destroyBean}
	 * if a corresponding disposable bean instance is found.
	 * @param beanName the name of the bean
	 * @see #destroyBean
	 */
	public void destroySingleton(String beanName) {
		// Remove a registered singleton of the given name, if any.
		removeSingleton(beanName);

		// Destroy the corresponding DisposableBean instance.
		DisposableBean disposableBean;
        //锁住要清除的 Bean
		synchronized (this.disposableBeans) {
            //从清除的 Map 中删除对象
            //这里为什么敢放心大胆的强转？因为上面只把实现了 DisposableBean 接口的 beanName 装进了数组
			disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
		}
        //销毁 Bean
		destroyBean(beanName, disposableBean);
	}
```



##### destroyBean()

```java
/**
	 * Destroy the given bean. Must destroy beans that depend on the given
	 * bean before the bean itself. Should not throw any exceptions.
	 * @param beanName the name of the bean
	 * @param bean the bean instance to destroy
	 */
	protected void destroyBean(String beanName, @Nullable DisposableBean bean) {
        //先触发销毁 Bean 的依赖
		// Trigger destruction of dependent beans first...
		Set<String> dependencies;
		synchronized (this.dependentBeanMap) {
			//在完全同步内以确保断开连接集，就是删除依赖的 Bean
            // Within full synchronization in order to guarantee a disconnected Set
			dependencies = this.dependentBeanMap.remove(beanName);
		}
        //有删除的 依赖 Bean，递归到这里为空就停了。。
		if (dependencies != null) {
			if (logger.isTraceEnabled()) {
				logger.trace("Retrieved dependent beans for bean '" + beanName + "': " + dependencies);
			}
			for (String dependentBeanName : dependencies) {
                // 销毁单示例对象，这块是个递归。。
				destroySingleton(dependentBeanName);
			}
		}
        // Actually destroy the bean now...
		if (bean != null) {
			try {
                //在这里调用刀片 DisposableBean 接口的 destroy 方法
				bean.destroy();
			}
			catch (Throwable ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Destruction of bean with name '" + beanName + "' threw an exception", ex);
				}
			}
		}

		// Trigger destruction of contained beans...
		Set<String> containedBeans;
		synchronized (this.containedBeanMap) {
			// Within full synchronization in order to guarantee a disconnected Set
			containedBeans = this.containedBeanMap.remove(beanName);
		}
		if (containedBeans != null) {
			for (String containedBeanName : containedBeans) {
				destroySingleton(containedBeanName);
			}
		}

		// Remove destroyed bean from other beans' dependencies.
		synchronized (this.dependentBeanMap) {
			for (Iterator<Map.Entry<String, Set<String>>> it = this.dependentBeanMap.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Set<String>> entry = it.next();
				Set<String> dependenciesToClean = entry.getValue();
				dependenciesToClean.remove(beanName);
				if (dependenciesToClean.isEmpty()) {
					it.remove();
				}
			}
		}

		// Remove destroyed bean's prepared dependency information.
		this.dependenciesForBeanMap.remove(beanName);
	}
```



##### detroySingletion()

```java
/**
	 * Destroy the given bean. Delegates to {@code destroyBean}
	 * if a corresponding disposable bean instance is found.
	 * @param beanName the name of the bean
	 * @see #destroyBean
	 */
	public void destroySingleton(String beanName) {
		// Remove a registered singleton of the given name, if any.
		removeSingleton(beanName);

		// Destroy the corresponding DisposableBean instance.
		DisposableBean disposableBean;
		synchronized (this.disposableBeans) {
            //删除bean ，就是 map 里面根据 key 删 value。这里删除的 依赖的对象Map
			disposableBean = (DisposableBean) this.disposableBeans.remove(beanName);
		}
        //递归上面那个方法
		destroyBean(beanName, disposableBean);
	}
```





那么问题来了。。。这里只说实现了 DisposableBean 接口在哪触发的 Bean 的销毁方法。那么 @PreDestroy 注解标注的类在哪里触发的 Bean 的销毁方法？



##### @PreDestroy

```java
@Documented
@Retention (RUNTIME)
@Target(METHOD)
public @interface PreDestroy {
}

```



找个这个注解，通过 IDEA 的 find Usages ，找到 spring-context 使用 @PreDestroy 的地方 - **CommonAnnotationBeanPostProcessor**

```java
/**
	 * Create a new CommonAnnotationBeanPostProcessor,
	 * with the init and destroy annotation types set to
	 * {@link javax.annotation.PostConstruct} and {@link javax.annotation.PreDestroy},
	 * respectively.
	 */
	public CommonAnnotationBeanPostProcessor() {
		setOrder(Ordered.LOWEST_PRECEDENCE - 3);
		setInitAnnotationType(PostConstruct.class);
        //这里用到的。但是看了下好像是 set 一下。
		setDestroyAnnotationType(PreDestroy.class);
		ignoreResourceType("javax.xml.ws.WebServiceContext");
	}
```



##### setDestroyAnnotationType(PreDestroy.class);

org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor#setDestroyAnnotationType

```java
	public void setDestroyAnnotationType(Class<? extends Annotation> destroyAnnotationType) {
		this.destroyAnnotationType = destroyAnnotationType;
	}
```



这个 **destroyAnnotationType**  用到的地方

org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor#buildLifecycleMetadata

```java
private LifecycleMetadata buildLifecycleMetadata(final Class<?> clazz) {
		if (!AnnotationUtils.isCandidateClass(clazz, Arrays.asList(this.initAnnotationType, this.destroyAnnotationType))) {
			return this.emptyLifecycleMetadata;
		}

		List<LifecycleElement> initMethods = new ArrayList<>();
		List<LifecycleElement> destroyMethods = new ArrayList<>();
		Class<?> targetClass = clazz;

		do {
			final List<LifecycleElement> currInitMethods = new ArrayList<>();
			final List<LifecycleElement> currDestroyMethods = new ArrayList<>();

            // targetClass 指的是当前 Bean 的 class 的类型
            //ReflectionUtils.doWithLocalMethods() 这个方法是 spring 提供的一个工具类，作用是把给到的类里面的所有方法都找到。很吊。
			ReflectionUtils.doWithLocalMethods(targetClass, method -> {
                //这个是初始化标记，比如 @PostConstruct
				if (this.initAnnotationType != null && method.isAnnotationPresent(this.initAnnotationType)) {
					LifecycleElement element = new LifecycleElement(method);
					currInitMethods.add(element);
					if (logger.isTraceEnabled()) {
						logger.trace("Found init method on class [" + clazz.getName() + "]: " + method);
					}
				}
                //找到了目标类下的方法，假如标注了 destroyAnnotationType 这个东西，比如 @PreDestroy
				if (this.destroyAnnotationType != null && method.isAnnotationPresent(this.destroyAnnotationType)) {
					//添加销毁方法到 currDestroyMethod
                    currDestroyMethods.add(new LifecycleElement(method));
					if (logger.isTraceEnabled()) {
						logger.trace("Found destroy method on class [" + clazz.getName() + "]: " + method);
					}
				}
			});

			initMethods.addAll(0, currInitMethods);
            //加入到集合里面，为了给 DefaultListableBeanFactory 的 destroySingletons() 方法执行的时候用。。。
			destroyMethods.addAll(currDestroyMethods);
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);

		return (initMethods.isEmpty() && destroyMethods.isEmpty() ? this.emptyLifecycleMetadata :
				new LifecycleMetadata(clazz, initMethods, destroyMethods));
	}
```





### 总结：

​	通过销毁的三种方式 @PreDestroy、DisposableBean、自定义销毁方法 + 初始化方法的对比，我们可以了解到这三种实现方式怎么来操作，不会的时候可以拿来参照。可以发现 Java 注解的方式优先级最高，Spring 里面的 DisposableBean 接口是老二的，自定义的方式放在第三位。





### 8：回收 Spring Bean ：Spring IOC 容器管理的 Bean 可以被 GC 回收吗？



##### 	· Bean 的垃圾回收（GC）



##### 	1. 关闭 Spring 容器（应用上下文）



##### 	2. 执行 GC（Garbage Collection）



##### 	3. Spring Bean 覆盖 Object 类的 finalize() 方法被回调

​		因为垃圾回收的时候 finalize() 方法会被回调



##### 新增文件：

##### 	BeanGarbageCollectionDemo.java

##### 	DefaultUserFactory.java	覆盖了 Object 类的 finalize() 方法



##### BeanGarbageCollectionDemo.java

```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Bean 垃圾回收
 * @author WTY
 * @date 2020/8/24 23:26
 **/
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Bean Configuration Class
        applicationContext.register(BeanInitializationDemo.class);

        //启动 Spring IOC 容器上下文
        applicationContext.refresh();

        //关闭 Spring IOC 容器上下文
        applicationContext.close();

        //强制执行 GC   加入你在 JVM 的参数中关闭了 full gc，这里是没用的。。。
        System.gc();

    }

}

```



##### DefaultUserFactory.java

```java
package org.example.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 默认 UserFactory 的实现 {@link UserFactory}
 * @author WTY
 * @date 2020/8/20 0:11
 **/
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    // Spring Bean 初始化章节增加
    @PostConstruct
    public void init(){
        System.out.println("--------  @PostConstruct  DefaultUserFactory 初始化  ----------");
    }


    public void initDefaultUserFactory(){
        System.out.println("--------  @Bean 自定义初始化方法：  DefaultUserFactory#initDefaultUserFactory() 初始化  ----------");
    }

    public void xmlInitDefaultUserFactory(){
        System.out.println("--------  XML 自定义初始化方法：  InitializingBean#afterPropertiesSet() 初始化  ----------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--------  InitializingBean 初始化方法：  InitializingBean#afterPropertiesSet() 初始化  ----------");
    }


    //Spring Bean 销毁章节增加
    @PreDestroy
    public void preDestroy(){
        System.err.println("--------  @PreDestroy  DefaultUserFactory 销毁  ----------");
    }


    @Override
    public void destroy() throws Exception {
        System.err.println("--------  DisposableBean  DefaultUserFactory 销毁  ----------");
    }

    public void destroyWithBean(){
        System.err.println("--------  @Bean  DefaultUserFactory 销毁  ----------");
    }

    public void destroyWithXml(){
        System.err.println("--------  XML  DefaultUserFactory 销毁  ----------");
    }

    /** 覆盖 Object 的 finalize()  */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("当前的 DefaultUserFactory 对象正在被回收！！！");
    }
}

```



### 总结：

​	我们发现 Spring 中的 Bean 其实是可以被 JVM 的 垃圾回收期 GC（Garbage Collection）回收的。先决条件是要把 Spring 的上下文通过 Spring 容器来关闭。

第二个是需要使用 System.gc() 来强制执行 gc（不是必须）。第三个是可能有的版本会等待一些时间。。







## 8：Spring Bean 的基础面试题



#### 沙雕面试题：



##### 	如何注册一个 Spring Bean ？

​	答：通过 BeanDefinition 和外部单体来注册。

BeanDefinitionRegisty 的 API 来进行注册，比如 **AnnotationBeanDefinitionRegisterDemo.java** 这个类里面举例的 AnnotationConfigApplicationContext 就是一个 BeanDefinitionRegisty 。



### 外部单体注册：

​	外部单体就是说这个 Bean 的生命周期不由 Spring 容器来管理，但是可以被他托管。



##### 新增文件：

##### 	SingletonBeanRegistrationDemo.java	单体外部 Bean 注册示例



##### SingletonBeanRegistrationDemo.java

```java
package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.example.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 单体外部 Bean 注册示例
 * @author WTY
 * @date 2020/8/25 0:42
 **/
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 对象
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //创建外部对象
        UserFactory userFactory = new DefaultUserFactory();

        //获取到上下文中的 BeanFactory
        ConfigurableListableBeanFactory configurableListableBeanFactory = applicationContext.getBeanFactory();

        // 注册 外部单例对象
        configurableListableBeanFactory.registerSingleton("userFactory",userFactory);

        //启动 Spring 容器上下文
        applicationContext.refresh();

        //通过依赖查找的方式获取到注册进 Spring IoC 容器的 UserFactory 对象
        UserFactory iocUserFactory = applicationContext.getBean("userFactory", UserFactory.class);

        //验证我们创建的外部对象 和 注册进 Spring IoC 容器的外部对象是不是同一个
        System.out.println("userFactory == userFactoryByLookup" + (userFactory == iocUserFactory));

        //如果没有找到就会抛出 NoSuchBeanDefinitionException
        UserFactory noSuchBeanDefinitionException = applicationContext.getBean("userFactory1111", UserFactory.class);

        //关闭 Spring 容器上下文
        applicationContext.close();

        //如果上下文关闭了会抛出 IllegalStateException
        UserFactory IllegalStateException = applicationContext.getBean("userFactory", UserFactory.class);

    }

}

```



ConfigurableListableBeanFactory 是一个综合接口，registerSingleton(String name,Object obj); 这个方法其实是 **SingletonBeanRegistry** 的规范定义。

```java
package org.springframework.beans.factory.config;

import org.springframework.lang.Nullable;

public interface SingletonBeanRegistry {

	//在这里，这个东西可以注册 Bean 进入 BeanDefinitionRegisty 里面，委派 AnnotationConfigApplicationContext 的 getBean()
    //来找到 BeanDefinitionRegisty 进而获取到注册到 BeanFactory 中的 Bean 对象
	void registerSingleton(String beanName, Object singletonObject);

	
	@Nullable
	Object getSingleton(String beanName);

	
	boolean containsSingleton(String beanName);

	
	String[] getSingletonNames();

	
	Object getSingletonMutex();

}

```





### 996面试题：什么是 Spring BeanDefinition ？

​	答：回顾 定义 Spring Bean 和 BeanDefinition 元信息那块的内容



### BeanDefinition 是 Spring Framework 中定义 Bean 的配置元信息接口，包含：



#### · Bean 的类名

 包含包名（全路径类名），对应的类必须是一个具体的实现类。

#### · Bean 行为元素，比如 作用域、自动绑定的模式、生命周期回调等。

 自动绑定就是 @Autowired 这种方式。

 生命周期回调就比如说生命周期初始化、销毁时候的一个回调。比如：InitializingBean，在初始化之前做一些事情。

#### · 其他 Bean 引用，又可称为 合作者（Collaborators）或者 依赖（Dependencies）

 合作者可以认为是 Bean 的引用关系，或者称之为依赖。比如说依赖注入，也是把合作者或者是说把引用的 Bean 注入到自己本身里面来。

 当然依赖注入不仅仅是注入 Bean ，还可以注入其他配置。





##### BeanDefinition 源代码：

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

package org.springframework.beans.factory.config;

import org.springframework.beans.BeanMetadataElement;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.AttributeAccessor;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * A BeanDefinition describes a bean instance, which has property values,
 * constructor argument values, and further information supplied by
 * concrete implementations.
 *
 * <p>This is just a minimal interface: The main intention is to allow a
 * {@link BeanFactoryPostProcessor} to introspect and modify property values
 * and other bean metadata.
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @since 19.03.2004
 * @see ConfigurableListableBeanFactory#getBeanDefinition
 * @see org.springframework.beans.factory.support.RootBeanDefinition
 * @see org.springframework.beans.factory.support.ChildBeanDefinition
 */
public interface BeanDefinition extends AttributeAccessor, BeanMetadataElement {

	/**
		指的是 Spring 的一个作用域的范围，这里是单例
		
	 * Scope identifier for the standard singleton scope: {@value}.
	 * <p>Note that extended bean factories might support further scopes.
	 * @see #setScope
	 * @see ConfigurableBeanFactory#SCOPE_SINGLETON
	 */
	String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

	/**
		原型模式，每次请求都会创建一个新的 Bean 注入到另一个 Bean 中。或者是调用 容器的 getBean() 方法
		才会将 Bean 生成到 BeanFactory 中
		
	 * Scope identifier for the standard prototype scope: {@value}.
	 * <p>Note that extended bean factories might support further scopes.
	 * @see #setScope
	 * @see ConfigurableBeanFactory#SCOPE_PROTOTYPE
	 */
	String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;


	/**
		应用角色
		
	 * Role hint indicating that a {@code BeanDefinition} is a major part
	 * of the application. Typically corresponds to a user-defined bean.
	 */
	int ROLE_APPLICATION = 0;

	/**
		这是一个辅助角色 - 支持
		
	 * Role hint indicating that a {@code BeanDefinition} is a supporting
	 * part of some larger configuration, typically an outer
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 * {@code SUPPORT} beans are considered important enough to be aware
	 * of when looking more closely at a particular
	 * {@link org.springframework.beans.factory.parsing.ComponentDefinition},
	 * but not when looking at the overall configuration of an application.
	 */
	int ROLE_SUPPORT = 1;

	/**
		标识 Bean 可以用于一些基础设施，不是给应用用的，是给框架用的
		
	 * Role hint indicating that a {@code BeanDefinition} is providing an
	 * entirely background role and has no relevance to the end-user. This hint is
	 * used when registering beans that are completely part of the internal workings
	 * of a {@link org.springframework.beans.factory.parsing.ComponentDefinition}.
	 */
	int ROLE_INFRASTRUCTURE = 2;


	// Modifiable attributes

	/**
		这里就是讲 Spring 的 SuperUser 和 User 的继承关系用到的，
		这里和 java 一样，只会有一个双亲，不会有两个
		
	 * Set the name of the parent definition of this bean definition, if any.
	 */
	void setParentName(@Nullable String parentName);

	/**
	 * Return the name of the parent definition of this bean definition, if any.
	 */
	@Nullable
	String getParentName();

	/**
		设置 Bean 的全名称
		
	 * Specify the bean class name of this bean definition.
	 * <p>The class name can be modified during bean factory post-processing,
	 * typically replacing the original class name with a parsed variant of it.
	 * @see #setParentName
	 * @see #setFactoryBeanName
	 * @see #setFactoryMethodName
	 */
	void setBeanClassName(@Nullable String beanClassName);

	/**
		获取 Bean 的全名称
		
	 * Return the current bean class name of this bean definition.
	 * <p>Note that this does not have to be the actual class name used at runtime, in
	 * case of a child definition overriding/inheriting the class name from its parent.
	 * Also, this may just be the class that a factory method is called on, or it may
	 * even be empty in case of a factory bean reference that a method is called on.
	 * Hence, do <i>not</i> consider this to be the definitive bean type at runtime but
	 * rather only use it for parsing purposes at the individual bean definition level.
	 * @see #getParentName()
	 * @see #getFactoryBeanName()
	 * @see #getFactoryMethodName()
	 */
	@Nullable
	String getBeanClassName();

	/**
		设置作用域，后面会专门说 单例、圆形，get Request Application Session 这样的作用域
		
	 * Override the target scope of this bean, specifying a new scope name.
	 * @see #SCOPE_SINGLETON
	 * @see #SCOPE_PROTOTYPE
	 */
	void setScope(@Nullable String scope);

	/**
		获取作用域
		
	 * Return the name of the current target scope for this bean,
	 * or {@code null} if not known yet.
	 */
	@Nullable
	String getScope();

	/**
		设置初始化的模式，决定 上下文 getBean 的时候创建 Bean 还是上下文启动（refresh）的时候创建 Bean
		
	 * Set whether this bean should be lazily initialized.
	 * <p>If {@code false}, the bean will get instantiated on startup by bean
	 * factories that perform eager initialization of singletons.
	 */
	void setLazyInit(boolean lazyInit);

	/**
	 * Return whether this bean should be lazily initialized, i.e. not
	 * eagerly instantiated on startup. Only applicable to a singleton bean.
	 */
	boolean isLazyInit();

	/**
		和依赖有关系，很多场景我们不了解我们的依赖，但是有一种场景，我们非常清楚依赖，比如 UserFactory 依赖 User 对象，
		那么可以把 User 的名称直接关联到这里面来。
		
	 * Set the names of the beans that this bean depends on being initialized.
	 * The bean factory will guarantee that these beans get initialized first.
	 */
	void setDependsOn(@Nullable String... dependsOn);

	/**
	 * Return the bean names that this bean depends on.
	 */
	@Nullable
	String[] getDependsOn();

	/**
		设置自动装配候选人，默认情况 true ,大部分情况都是 true
		
	 * Set whether this bean is a candidate for getting autowired into some other bean.
	 * <p>Note that this flag is designed to only affect type-based autowiring.
	 * It does not affect explicit references by name, which will get resolved even
	 * if the specified bean is not marked as an autowire candidate. As a consequence,
	 * autowiring by name will nevertheless inject a bean if the name matches.
	 */
	void setAutowireCandidate(boolean autowireCandidate);

	/**
		判断自动装配候选人
		
	 * Return whether this bean is a candidate for getting autowired into some other bean.
	 */
	boolean isAutowireCandidate();

	/**
		前面讲 在多个 User 对象的时候，SuperUser 继承了 User,通过 BeanFactory 获取 User 就会说有多个 User
		并且报错了，我们给 SuperUser 在 xml <bean> 配置里面加了一个 primary="true" 属性，来标识通过 BeanFactory
		来 getBean 的时候，我指定 User，Spring 容器应该给我返回哪一个 User。
		
	 * Set whether this bean is a primary autowire candidate.
	 * <p>If this value is {@code true} for exactly one bean among multiple
	 * matching candidates, it will serve as a tie-breaker.
	 */
	void setPrimary(boolean primary);

	/**
	 * Return whether this bean is a primary autowire candidate.
	 */
	boolean isPrimary();

	/**
		通过实例化的方式来创建 Bean。比如：bean-instanstation-context.xml 
		通过 静态方法、普通方法、抽象工厂（FactoryBean）、静态工厂（UserFactoryBean）
		<bean factory-bean="userFactory" 就是代表的这里的名称
		
	 * Specify the factory bean to use, if any.
	 * This the name of the bean to call the specified factory method on.
	 * @see #setFactoryMethodName
	 */
	void setFactoryBeanName(@Nullable String factoryBeanName);

	/**
	 * Return the factory bean name, if any.
	 */
	@Nullable
	String getFactoryBeanName();

	/**
	
		和 FactoryBeanName 同理，这里是设置 <bean factory-method="createUser" > 
		createUser 就被设置在这里了
	
	 * Specify a factory method, if any. This method will be invoked with
	 * constructor arguments, or with no arguments if none are specified.
	 * The method will be invoked on the specified factory bean, if any,
	 * or otherwise as a static method on the local bean class.
	 * @see #setFactoryBeanName
	 * @see #setBeanClassName
	 */
	void setFactoryMethodName(@Nullable String factoryMethodName);

	/**
	 * Return a factory method, if any.
	 */
	@Nullable
	String getFactoryMethodName();

	/**
	
		构造器参数：这个地方比较不好理解。
		
		构造器参数不会去记录构造方法的参数名称，只会按照构造方法的顺序去设置属性 0 1 2 这样。
		因为构造方法不会涉及到 setter 方法的名称。想想反射调用 setter 方法。。
	
	 * Return the constructor argument values for this bean.
	 * <p>The returned instance can be modified during bean factory post-processing.
	 * @return the ConstructorArgumentValues object (never {@code null})
	 */
	ConstructorArgumentValues getConstructorArgumentValues();

	/**
	 * Return if there are constructor argument values defined for this bean.
	 * @since 5.0.2
	 */
	default boolean hasConstructorArgumentValues() {
		return !getConstructorArgumentValues().isEmpty();
	}

	/**
	 * Return the property values to be applied to a new instance of the bean.
	 * <p>The returned instance can be modified during bean factory post-processing.
	 * @return the MutablePropertyValues object (never {@code null})
	 */
	MutablePropertyValues getPropertyValues();

	/**
	 * Return if there are property values values defined for this bean.
	 * @since 5.0.2
	 */
	default boolean hasPropertyValues() {
		return !getPropertyValues().isEmpty();
	}

	/**
	
		初始化方法的设置
	
	 * Set the name of the initializer method.
	 * @since 5.1
	 */
	void setInitMethodName(@Nullable String initMethodName);

	/**
		
		初始化方法的获取
	
	 * Return the name of the initializer method.
	 * @since 5.1
	 */
	@Nullable
	String getInitMethodName();

	/**
	
		销毁方法的设置
	
	 * Set the name of the destroy method.
	 * @since 5.1
	 */
	void setDestroyMethodName(@Nullable String destroyMethodName);

	/**
		
		销毁方法的获取
	
	 * Return the name of the destroy method.
	 * @since 5.1
	 */
	@Nullable
	String getDestroyMethodName();

	/**
	 * Set the role hint for this {@code BeanDefinition}. The role hint
	 * provides the frameworks as well as tools with an indication of
	 * the role and importance of a particular {@code BeanDefinition}.
	 * @since 5.1
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	void setRole(int role);

	/**
	 * Get the role hint for this {@code BeanDefinition}. The role hint
	 * provides the frameworks as well as tools with an indication of
	 * the role and importance of a particular {@code BeanDefinition}.
	 * @see #ROLE_APPLICATION
	 * @see #ROLE_SUPPORT
	 * @see #ROLE_INFRASTRUCTURE
	 */
	int getRole();

	/**
	
		这玩意只要是给人看的描述。
	
	 * Set a human-readable description of this bean definition.
	 * @since 5.1
	 */
	void setDescription(@Nullable String description);

	/**
	 * Return a human-readable description of this bean definition.
	 */
	@Nullable
	String getDescription();


	// Read-only attributes

	/**
	 * Return a resolvable type for this bean definition,
	 * based on the bean class or other specific metadata.
	 * <p>This is typically fully resolved on a runtime-merged bean definition
	 * but not necessarily on a configuration-time definition instance.
	 * @return the resolvable type (potentially {@link ResolvableType#NONE})
	 * @since 5.2
	 * @see ConfigurableBeanFactory#getMergedBeanDefinition
	 */
	ResolvableType getResolvableType();

	/**
	 * Return whether this a <b>Singleton</b>, with a single, shared instance
	 * returned on all calls.
	 * @see #SCOPE_SINGLETON
	 */
	boolean isSingleton();

	/**
	 * Return whether this a <b>Prototype</b>, with an independent instance
	 * returned for each call.
	 * @since 3.0
	 * @see #SCOPE_PROTOTYPE
	 */
	boolean isPrototype();

	/**
	 * Return whether this bean is "abstract", that is, not meant to be instantiated.
	 */
	boolean isAbstract();

	/**
	 * Return a description of the resource that this bean definition
	 * came from (for the purpose of showing context in case of errors).
	 */
	@Nullable
	String getResourceDescription();

	/**
	 * Return the originating BeanDefinition, or {@code null} if none.
	 * Allows for retrieving the decorated bean definition, if any.
	 * <p>Note that this method returns the immediate originator. Iterate through the
	 * originator chain to find the original BeanDefinition as defined by the user.
	 */
	@Nullable
	BeanDefinition getOriginatingBeanDefinition();

}

```



从这里能够看出 Spring 的 BeanDefinition 是一个定义了各种元信息的接口，通过这个接口我们可以允许我们存储数据，不管我们 setter 也好，构造方法也好，

Property 也好，他都提供了 getter 方法让我们进行操作。







### 劝退面试题： Spring 容器是怎么管理注册 Bean 的？

答：这里不先给答案了。。。。这个玩意非常复杂，早期版本得从 XmlBeanFactoty 的读取 xml 开始，现在的5.2.2版本 也得从 

AnnotationConfigApplicationContext 这个类的 refresh 开始看。这里可以思考下，IoC 配置元信息的读取和解析、依赖查找和注入以及 Bean 生命周期等

是怎么做的。

只有知道了 IoC配置元信息额读取和解析、依赖查找和注入以及 Bean 生命周期，我们才能再次论述。这里点到为止。





### 总结：

​	我们看了这么多，大致了解了一些 Spring 的基础，包括 Bean 的定义，以及 BeanDefinition 的元信息，还有一些基本的生命周期

（

​	实例化：四种常见 + 三种特殊、

​	初始化：spring ioc 容器启动初始化、

​	延迟初始化：BeanFactory 开始 getBean 的时候初始化、

​	销毁、

​	gc （garbage collection）回收

）。

##### 学完这些就能够掌握一些基本的 Spring 特性。这些对后面的 IoC 生命周期和 Bean 的生命周期学习非常有用。加油。











# Spring IoC 依赖查找部分（Dependency Lookup）。



## 目录：

### 	1：依赖查找的今世前生

### 	2：单一类型依赖查找

### 	3：集合类型依赖查找

### 	4：层次性依赖查找（上下文层级关系）

### 	5：延迟依赖查找

### 	6：安全依赖查找（可规避异常查找）

### 	7：内建可查找的依赖

### 	8：依赖查找中的经典异常

### 	9：面试题精选







## 1：依赖查找的今世前生：Spring IoC 从 Java 标准中学到了什么？



### · 单一类型依赖查找：

### 	· JNDI - javax.naming.Context#lookup(javax.naming.Name)

​			JNDI 是 Java SE 里面的一个实现。

### 	· JavaBeans - java.beans.beancontext.BeanContext



### · 集合类型依赖查找：

### 	· java.beans.beancontext.BeanContext



### · 层次性依赖查找：

### 	· java.beans.beancontext.BeanContext





#####  javax.naming.Context 源码：

```java
package javax.naming;

import java.util.Hashtable;

public interface Context {

   //根据封装的 Name 进行依赖查找
    public Object lookup(Name name) throws NamingException;

    //根据 String 类型的名称 进行依赖查找。这个 Object是本地的还是远程的，取决于本地的环境
    //好比 EJB 里面有 LocalBean 和 RemoteBean,就是关于本地 Bean 和远程 Bean
    public Object lookup(String name) throws NamingException;

    
    public void bind(Name name, Object obj) throws NamingException;

    
    public void bind(String name, Object obj) throws NamingException;

    
    public void rebind(Name name, Object obj) throws NamingException;

    
    public void rebind(String name, Object obj) throws NamingException;

    
    public void unbind(Name name) throws NamingException;

    
    public void unbind(String name) throws NamingException;

    
    public void rename(Name oldName, Name newName) throws NamingException;

    
    public void rename(String oldName, String newName) throws NamingException;

    
    public NamingEnumeration<NameClassPair> list(Name name)
        throws NamingException;

    
    public NamingEnumeration<NameClassPair> list(String name)
        throws NamingException;

    
    public NamingEnumeration<Binding> listBindings(Name name)
        throws NamingException;

    
    public NamingEnumeration<Binding> listBindings(String name)
        throws NamingException;

    
    public void destroySubcontext(Name name) throws NamingException;

    
    public void destroySubcontext(String name) throws NamingException;

    
    public Context createSubcontext(Name name) throws NamingException;

    
    public Context createSubcontext(String name) throws NamingException;

   
    public Object lookupLink(Name name) throws NamingException;

    
    public Object lookupLink(String name) throws NamingException;

    
    public NameParser getNameParser(Name name) throws NamingException;

    
    public NameParser getNameParser(String name) throws NamingException;

    
    public Name composeName(Name name, Name prefix)
        throws NamingException;

    
    public String composeName(String name, String prefix)
            throws NamingException;

    
    public Object addToEnvironment(String propName, Object propVal)
        throws NamingException;

    
    public Object removeFromEnvironment(String propName)
        throws NamingException;

    
    public Hashtable<?,?> getEnvironment() throws NamingException;

    rows  NamingException if a naming exception is encountered
     */
    public void close() throws NamingException;

    
    public String getNameInNamespace() throws NamingException;

// public static final:  JLS says recommended style is to omit these modifiers
// because they are the default

    
    String INITIAL_CONTEXT_FACTORY = "java.naming.factory.initial";

    
    String OBJECT_FACTORIES = "java.naming.factory.object";

    
    String STATE_FACTORIES = "java.naming.factory.state";

    
    String URL_PKG_PREFIXES = "java.naming.factory.url.pkgs";

    
    String PROVIDER_URL = "java.naming.provider.url";

    
    String DNS_URL = "java.naming.dns.url";

    
    String AUTHORITATIVE = "java.naming.authoritative";

    
    String BATCHSIZE = "java.naming.batchsize";

    
    String REFERRAL = "java.naming.referral";

    
    String SECURITY_PROTOCOL = "java.naming.security.protocol";

    
    String SECURITY_AUTHENTICATION = "java.naming.security.authentication";

    
    String SECURITY_PRINCIPAL = "java.naming.security.principal";

    

    String SECURITY_CREDENTIALS = "java.naming.security.credentials";
    
    
    String LANGUAGE = "java.naming.language";

   
    String APPLET = "java.naming.applet";
};

```



##### BeanContext 源码：

```java
package java.beans.beancontext;

import java.beans.DesignMode;
import java.beans.Visibility;

import java.io.InputStream;
import java.io.IOException;

import java.net.URL;

import java.util.Collection;
import java.util.Locale;

/*
	他是 javaBeans 规范里面比较特殊的一种实现方式
	Spring IoC 里面的 BeanFactory、ApplicationContext 参考了他的实现。
	这个实现一部分给我们基本的一些计算使用，一部分给了 GUI 来使用。
	比如 DesignMode，Visibility（是否可见）这么几种方式
	
	细心观察的话，没有发现什么 依赖查找，但是继承了 Collection 这个接口（集合）。这个集合里面的所有成员就是一个 Bean。
	因此可以通过操作集合的方式进行 CRUD。进而达到一个维护 Bean 的工作
*/

@SuppressWarnings("rawtypes")
public interface BeanContext extends BeanContextChild, Collection, DesignMode, Visibility {

    
    Object instantiateChild(String beanName) throws IOException, ClassNotFoundException;

    
    InputStream getResourceAsStream(String name, BeanContextChild bcc) throws IllegalArgumentException;

    
    URL getResource(String name, BeanContextChild bcc) throws IllegalArgumentException;

     
    void addBeanContextMembershipListener(BeanContextMembershipListener bcml);

     
    void removeBeanContextMembershipListener(BeanContextMembershipListener bcml);

    
    public static final Object globalHierarchyLock = new Object();
}

```



BeanContext 一个子类和两个实现类：

​	接口：BeanContextServices

​	实现类：BeanContextServicesSupport、BeanContextSupport



##### BeanContextServices 源码：

```java
package java.beans.beancontext;

import java.util.Iterator;

import java.util.TooManyListenersException;

import java.beans.beancontext.BeanContext;

import java.beans.beancontext.BeanContextServiceProvider;

import java.beans.beancontext.BeanContextServicesListener;



public interface BeanContextServices extends BeanContext, BeanContextServicesListener {

    //类似于增加一个 service 组件
    boolean addService(Class serviceClass, BeanContextServiceProvider serviceProvider);

    //删除一个 service 组件
    void revokeService(Class serviceClass, BeanContextServiceProvider serviceProvider, boolean revokeCurrentServicesNow);

    //是否有 service 类似于 BeanDefinition
    boolean hasService(Class serviceClass);

   //映射的关系，根据某种方式来获取 Bean
    Object getService(BeanContextChild child, Object requestor, Class serviceClass, Object serviceSelector, BeanContextServiceRevokedListener bcsrl) throws TooManyListenersException;

    
    void releaseService(BeanContextChild child, Object requestor, Object service);

    // 一对多的方式，一个类会返回多个方法，属于依赖查找。
    Iterator getCurrentServiceClasses();

    
    Iterator getCurrentServiceSelectors(Class serviceClass);

    
    void addBeanContextServicesListener(BeanContextServicesListener bcsl);

    
    void removeBeanContextServicesListener(BeanContextServicesListener bcsl);
}

```



##### 什么是层次性查找？

​	需要借助 javaBeans 规范。

​		https://www.cnblogs.com/zsychanpin/p/7127698.html

其实这个 javaBeans 做为一个可操作性的运行时容器， 可分为两个方面，一个是 javaBeans ，一个是 services。例如：Spring 里面所有的对象都是 Bean，并且

分为 Component、Service、Controller、Repository 等。



![image-20200825232845383](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200825232845383.png)



### 总结：

​	通过规范学习 + 源码分析，可以得到一个结论。Spring 在很大程度上抄袭了 JavaBeans 。但是 Spring 的依赖查找或者 传统 Java 的依赖查找是有所根源的。

后面会依次的学习单一类型、集合类型、层次性查找他们是怎么实现的。不过 Spring 的实现相对于 JavaBeans 、JNDI的实现确实好理解很多，这也是我们为

什么用 Spring 的原因，因此我们更应该发现源头，弥补不足，就能创新了。





## 2：单一类型依赖查找：如何查找已知名称或类型的 Bean 对象？



在 IoC 概述的时候提了一点，这里会补充说明。



##### 单一类型依赖查找接口 - BeanFactory

##### 	1：根据 Bean 名称查找：

##### 			getBean( String )

##### 			spring 2.5 覆盖默认参数：getBean( String , Object ... )



##### 	2：根据 Bean类型查找：

##### 			· Bean实时查找：

##### 				· Spring 3.0 	getBean( Class )

##### 				· Spring 4.1 覆盖默认参数 	getBean( Class , Object ... )



##### 			· Spring 5.1 Bean 延迟查找：

##### 				· getBeanProvider( Class )

##### 				· getBeanProvider( ResolableType )



##### 			· 根据 Bean 名称 + Bean 类型查找：

##### 				· getBean( String , Class )



不推荐使用 覆盖默认参数的方式去进行依赖查找。看下 BeanFactory 的 API 就明白了~



##### 根据 type 进行覆盖：

```java
	/**
		这里是说返回一个实例，这个实例或许是单例的，也有可能是原生。。原生还好，假如是单例的，没调用一次就会覆盖一次方法。
		所以说用 BeanFactory 的话，最好用只读的方式去 getBean。如果非要覆盖的话，就得去把原有的 BeanDefinition 删除，然后覆盖。
	 * Return an instance, which may be shared or independent, of the specified bean.
	 * <p>Allows for specifying explicit constructor arguments / factory method arguments,
	 * overriding the specified default arguments (if any) in the bean definition.
	 * <p>This method goes into {@link ListableBeanFactory} by-type lookup territory
	 * but may also be translated into a conventional by-name lookup based on the name
	 * of the given type. For more extensive retrieval operations across sets of beans,
	 * use {@link ListableBeanFactory} and/or {@link BeanFactoryUtils}.
	 * @param requiredType type the bean must match; can be an interface or superclass
	 * @param args arguments to use when creating a bean instance using explicit arguments
	 * (only applied when creating a new instance as opposed to retrieving an existing one)
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException if there is no such bean definition
	 * @throws BeanDefinitionStoreException if arguments have been given but
	 * the affected bean isn't a prototype
	 * @throws BeansException if the bean could not be created
	 * @since 4.1
	 */
	<T> T getBean(Class<T> requiredType, Object... args) throws BeansException;
```



##### 延迟查找是重点：

​	getBeanProvider() 返回一个 ObjectProvider 类，他类似于 ObjectFactory，因为 ObjectProvider 继承了 ObjectFactory。

ObjectFactory 的特性是根据泛型去关联一个 Bean。相关代码在 ioc-container-overview 模块里面。



##### dependency-lookup-context.xml

```xml
<bean id="objectFactoryBean" class="org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean">
        <property name="targetBeanName" value="user" />
    </bean>
```



##### UserRepository.java

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



##### DependencyLookupDemo.java

```java
//延时查找 ObjectFactory
private static void lookupInLazy(BeanFactory beanFactory){

    // ObjectFactory 、 BeanFactory 、FactoryBean有什么区别？
    
    /*
    	ObjectFactory 有一个 FactoryBean的实现 ObjectFactoryCreatingFactoryBean
     * */
    ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactoryBean");
    User user = objectFactory.getObject();
    System.out.println("延时查找 " + user);
}
```







## 3：单一 类型依赖查找：如何查找已知名称或类型的 Bean 集合？



问题：bean 的名称和 bean 的列表哪个比较重要？（生命周期的时候详细说）

bean 名称可以新定义，所以不需要把 Bean 初始化，实例的话不得不把我们的东西来初始化（非延迟）

依赖查找是等待容器启动/加载完成之后，我们来进行的初始化操作。但是 BeanDefinition 是在 Bean 定义的注册完成的阶段就已经有了。

BeanDefinition 里面的 getBeanClassName() 可以返回 Class 的名称，ListableBeanFactory 的 getBeanNamesForType(Class<?> type) 会返回一个给定类的

名称数组，当传来的类型去调用 Class 类里面的 isAssignableFrom 方法来进行判断是否为父子关系，是否是同一个类，然后再去调用 getObjectType，这里的

getObjectType() 是 FactoryBean 里面的方法。这就是FactoryBean 里面为什么还要一个 getObjectType() 方法的原因。这个返回的类型就是用于我们类型判断

的。总之，这里的判断就是判断给定类型和 BeanDefinition 里面获取到的 Bean 得一个匹配情况。这时候还没有涉及到我们的 Bean 初始化。这就是

getBeanName 和 getBean 的一个区别。

ListableBeanFactory 里面的 getBeanOfTypes(Class<?> type) 这个方法就是会触发 Bean 初始化的这个操作。特点就是提前的把类初始化，这就会导致我们的

Bean 初始化并不完全，引发一些未知的错误。

所有在进行判断的时候，首先判断名称，然后再判断类型。



#### · 集合类型依赖查找：	-----	ListableBeanFactory



#### 	· 根据 Bean 类型查找

#### 		· 获取同类型 Bean 名称列表

#### 			· getBeanNameForType( Class )

#### 			· Spring 4.2 getBeanNameForType( ResolvableType )

#### 		· 获取同类型 Bean 示例列表

#### 			· getBeansOfType( Class ) 以及重载方法



#### 	· 通过注解类型查找

#### 		· Spring 3.0 获取标注类型 Bean 名称列表

#### 			· getBeanNamesForAnnotation( Class<? extends Annotation> )

#### 		· Spring 3.0 获取标注类型 Bean 实例列表

#### 			· getBeansWithAnnotation( Class<? extends Annotation> )

#### 		· Spring 3.0 获取指定名称 + 标注类型 Bean 实例





在依赖查找简单版本的时候有介绍 ListableBeanFactory ：**DependencyLookupDemo.java**

```java
//根据类型查找 集合
    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("[实时查找] 根据类型查找 集合 Bean 对象 " + users.toString());
        }
    }

 //通过注解查找 单个 Bean
    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> superUsers = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("[实时查找] 查找 @Super 注解标注 所有 Bean 对象 " + superUsers.toString());
        }
    }
```





**ListableBeanFactory.java 源码：**

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

import java.lang.annotation.Annotation;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.core.ResolvableType;
import org.springframework.lang.Nullable;

/**
 * Extension of the {@link BeanFactory} interface to be implemented by bean factories
 * that can enumerate all their bean instances, rather than attempting bean lookup
 * by name one by one as requested by clients. BeanFactory implementations that
 * preload all their bean definitions (such as XML-based factories) may implement
 * this interface.
 *
 * <p>If this is a {@link HierarchicalBeanFactory}, the return values will <i>not</i>
 * take any BeanFactory hierarchy into account, but will relate only to the beans
 * defined in the current factory. Use the {@link BeanFactoryUtils} helper class
 * to consider beans in ancestor factories too.
 *
 * <p>The methods in this interface will just respect bean definitions of this factory.
 * They will ignore any singleton beans that have been registered by other means like
 * {@link org.springframework.beans.factory.config.ConfigurableBeanFactory}'s
 * {@code registerSingleton} method, with the exception of
 * {@code getBeanNamesOfType} and {@code getBeansOfType} which will check
 * such manually registered singletons too. Of course, BeanFactory's {@code getBean}
 * does allow transparent access to such special beans as well. However, in typical
 * scenarios, all beans will be defined by external bean definitions anyway, so most
 * applications don't need to worry about this differentiation.
 *
 * <p><b>NOTE:</b> With the exception of {@code getBeanDefinitionCount}
 * and {@code containsBeanDefinition}, the methods in this interface
 * are not designed for frequent invocation. Implementations may be slow.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 16 April 2001
 * @see HierarchicalBeanFactory
 * @see BeanFactoryUtils
 */
public interface ListableBeanFactory extends BeanFactory {

	/**
		是否包含某个 Bean 定义
	 * Check if this bean factory contains a bean definition with the given name.
	 * <p>Does not consider any hierarchy this factory may participate in,
	 * and ignores any singleton beans that have been registered by
	 * other means than bean definitions.
	 * @param beanName the name of the bean to look for
	 * @return if this bean factory contains a bean definition with the given name
	 * @see #containsBean
	 */
	boolean containsBeanDefinition(String beanName);

	/**
		有多少个 Bean 定义
	 * Return the number of beans defined in the factory.
	 * <p>Does not consider any hierarchy this factory may participate in,
	 * and ignores any singleton beans that have been registered by
	 * other means than bean definitions.
	 * @return the number of beans defined in the factory
	 */
	int getBeanDefinitionCount();

	/**
		获取到所有 Bean 定义的名称
	 * Return the names of all beans defined in this factory.
	 * <p>Does not consider any hierarchy this factory may participate in,
	 * and ignores any singleton beans that have been registered by
	 * other means than bean definitions.
	 * @return the names of all beans defined in this factory,
	 * or an empty array if none defined
	 */
	String[] getBeanDefinitionNames();

	/**
		获取某个类型的 Bean 定义名称，ResolveableType 主要是依据泛型来实现的一个接口
	 * Return the names of beans matching the given type (including subclasses),
	 * judging from either bean definitions or the value of {@code getObjectType}
	 * in the case of FactoryBeans.
	 * <p><b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i>
	 * check nested beans which might match the specified type as well.
	 * <p>Does consider objects created by FactoryBeans, which means that FactoryBeans
	 * will get initialized. If the object created by the FactoryBean doesn't match,
	 * the raw FactoryBean itself will be matched against the type.
	 * <p>Does not consider any hierarchy this factory may participate in.
	 * Use BeanFactoryUtils' {@code beanNamesForTypeIncludingAncestors}
	 * to include beans in ancestor factories too.
	 * <p>Note: Does <i>not</i> ignore singleton beans that have been registered
	 * by other means than bean definitions.
	 * <p>This version of {@code getBeanNamesForType} matches all kinds of beans,
	 * be it singletons, prototypes, or FactoryBeans. In most implementations, the
	 * result will be the same as for {@code getBeanNamesForType(type, true, true)}.
	 * <p>Bean names returned by this method should always return bean names <i>in the
	 * order of definition</i> in the backend configuration, as far as possible.
	 * @param type the generically typed class or interface to match
	 * @return the names of beans (or objects created by FactoryBeans) matching
	 * the given object type (including subclasses), or an empty array if none
	 * @since 4.2
	 * @see #isTypeMatch(String, ResolvableType)
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beanNamesForTypeIncludingAncestors(ListableBeanFactory, ResolvableType)
	 */
	String[] getBeanNamesForType(ResolvableType type);

	/**
	 * Return the names of beans matching the given type (including subclasses),
	 * judging from either bean definitions or the value of {@code getObjectType}
	 * in the case of FactoryBeans.
	 * <p><b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i>
	 * check nested beans which might match the specified type as well.
	 * <p>Does consider objects created by FactoryBeans if the "allowEagerInit" flag is set,
	 * which means that FactoryBeans will get initialized. If the object created by the
	 * FactoryBean doesn't match, the raw FactoryBean itself will be matched against the
	 * type. If "allowEagerInit" is not set, only raw FactoryBeans will be checked
	 * (which doesn't require initialization of each FactoryBean).
	 * <p>Does not consider any hierarchy this factory may participate in.
	 * Use BeanFactoryUtils' {@code beanNamesForTypeIncludingAncestors}
	 * to include beans in ancestor factories too.
	 * <p>Note: Does <i>not</i> ignore singleton beans that have been registered
	 * by other means than bean definitions.
	 * <p>Bean names returned by this method should always return bean names <i>in the
	 * order of definition</i> in the backend configuration, as far as possible.
	 * @param type the generically typed class or interface to match
	 * @param includeNonSingletons whether to include prototype or scoped beans too
	 * or just singletons (also applies to FactoryBeans)
	 * @param allowEagerInit whether to initialize <i>lazy-init singletons</i> and
	 * <i>objects created by FactoryBeans</i> (or by factory methods with a
	 * "factory-bean" reference) for the type check. Note that FactoryBeans need to be
	 * eagerly initialized to determine their type: So be aware that passing in "true"
	 * for this flag will initialize FactoryBeans and "factory-bean" references.
	 * @return the names of beans (or objects created by FactoryBeans) matching
	 * the given object type (including subclasses), or an empty array if none
	 * @since 5.2
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beanNamesForTypeIncludingAncestors(ListableBeanFactory, ResolvableType, boolean, boolean)
	 */
	String[] getBeanNamesForType(ResolvableType type, boolean includeNonSingletons, boolean allowEagerInit);

	/**
		给定一个类型，获取到给定类型的所有 BeanDefinition 的名称。当前类和他的子类都可以匹配。
		类似于 Class 类的 isAssignableFrom 
	 * Return the names of beans matching the given type (including subclasses),
	 * judging from either bean definitions or the value of {@code getObjectType}
	 	这里的 BeanDefinition 属于元信息，并不是 Bean 已经初始化了！！！
	 * in the case of FactoryBeans.
	 * <p><b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i>
	 * check nested beans which might match the specified type as well.
	 * <p>Does consider objects created by FactoryBeans, which means that FactoryBeans
	 * will get initialized. If the object created by the FactoryBean doesn't match,
	 * the raw FactoryBean itself will be matched against the type.
	 * <p>Does not consider any hierarchy this factory may participate in.
	 * Use BeanFactoryUtils' {@code beanNamesForTypeIncludingAncestors}
	 * to include beans in ancestor factories too.
	 * <p>Note: Does <i>not</i> ignore singleton beans that have been registered
	 * by other means than bean definitions.
	 * <p>This version of {@code getBeanNamesForType} matches all kinds of beans,
	 * be it singletons, prototypes, or FactoryBeans. In most implementations, the
	 * result will be the same as for {@code getBeanNamesForType(type, true, true)}.
	 * <p>Bean names returned by this method should always return bean names <i>in the
	 * order of definition</i> in the backend configuration, as far as possible.
	 * @param type the class or interface to match, or {@code null} for all bean names
	 * @return the names of beans (or objects created by FactoryBeans) matching
	 * the given object type (including subclasses), or an empty array if none
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beanNamesForTypeIncludingAncestors(ListableBeanFactory, Class)
	 */
	String[] getBeanNamesForType(@Nullable Class<?> type);

	/**
	 * Return the names of beans matching the given type (including subclasses),
	 * judging from either bean definitions or the value of {@code getObjectType}
	 * in the case of FactoryBeans.
	 * <p><b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i>
	 * check nested beans which might match the specified type as well.
	 * <p>Does consider objects created by FactoryBeans if the "allowEagerInit" flag is set,
	 * which means that FactoryBeans will get initialized. If the object created by the
	 * FactoryBean doesn't match, the raw FactoryBean itself will be matched against the
	 * type. If "allowEagerInit" is not set, only raw FactoryBeans will be checked
	 * (which doesn't require initialization of each FactoryBean).
	 * <p>Does not consider any hierarchy this factory may participate in.
	 * Use BeanFactoryUtils' {@code beanNamesForTypeIncludingAncestors}
	 * to include beans in ancestor factories too.
	 * <p>Note: Does <i>not</i> ignore singleton beans that have been registered
	 * by other means than bean definitions.
	 * <p>Bean names returned by this method should always return bean names <i>in the
	 * order of definition</i> in the backend configuration, as far as possible.
	 * @param type the class or interface to match, or {@code null} for all bean names
	 * @param includeNonSingletons whether to include prototype or scoped beans too
	 * or just singletons (also applies to FactoryBeans)
	 * @param allowEagerInit whether to initialize <i>lazy-init singletons</i> and
	 * <i>objects created by FactoryBeans</i> (or by factory methods with a
	 * "factory-bean" reference) for the type check. Note that FactoryBeans need to be
	 * eagerly initialized to determine their type: So be aware that passing in "true"
	 * for this flag will initialize FactoryBeans and "factory-bean" references.
	 * @return the names of beans (or objects created by FactoryBeans) matching
	 * the given object type (including subclasses), or an empty array if none
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beanNamesForTypeIncludingAncestors(ListableBeanFactory, Class, boolean, boolean)
	 */
	String[] getBeanNamesForType(@Nullable Class<?> type, boolean includeNonSingletons, boolean allowEagerInit);

	/**
	 * Return the bean instances that match the given object type (including
	 * subclasses), judging from either bean definitions or the value of
	 * {@code getObjectType} in the case of FactoryBeans.
	 * <p><b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i>
	 * check nested beans which might match the specified type as well.
	 * <p>Does consider objects created by FactoryBeans, which means that FactoryBeans
	 * will get initialized. If the object created by the FactoryBean doesn't match,
	 * the raw FactoryBean itself will be matched against the type.
	 * <p>Does not consider any hierarchy this factory may participate in.
	 * Use BeanFactoryUtils' {@code beansOfTypeIncludingAncestors}
	 * to include beans in ancestor factories too.
	 * <p>Note: Does <i>not</i> ignore singleton beans that have been registered
	 * by other means than bean definitions.
	 * <p>This version of getBeansOfType matches all kinds of beans, be it
	 * singletons, prototypes, or FactoryBeans. In most implementations, the
	 * result will be the same as for {@code getBeansOfType(type, true, true)}.
	 * <p>The Map returned by this method should always return bean names and
	 * corresponding bean instances <i>in the order of definition</i> in the
	 * backend configuration, as far as possible.
	 * @param type the class or interface to match, or {@code null} for all concrete beans
	 * @return a Map with the matching beans, containing the bean names as
	 * keys and the corresponding bean instances as values
	 * @throws BeansException if a bean could not be created
	 * @since 1.1.2
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beansOfTypeIncludingAncestors(ListableBeanFactory, Class)
	 */
	<T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException;

	/**
	 * Return the bean instances that match the given object type (including
	 * subclasses), judging from either bean definitions or the value of
	 * {@code getObjectType} in the case of FactoryBeans.
	 * <p><b>NOTE: This method introspects top-level beans only.</b> It does <i>not</i>
	 * check nested beans which might match the specified type as well.
	 * <p>Does consider objects created by FactoryBeans if the "allowEagerInit" flag is set,
	 * which means that FactoryBeans will get initialized. If the object created by the
	 * FactoryBean doesn't match, the raw FactoryBean itself will be matched against the
	 * type. If "allowEagerInit" is not set, only raw FactoryBeans will be checked
	 * (which doesn't require initialization of each FactoryBean).
	 * <p>Does not consider any hierarchy this factory may participate in.
	 * Use BeanFactoryUtils' {@code beansOfTypeIncludingAncestors}
	 * to include beans in ancestor factories too.
	 * <p>Note: Does <i>not</i> ignore singleton beans that have been registered
	 * by other means than bean definitions.
	 * <p>The Map returned by this method should always return bean names and
	 * corresponding bean instances <i>in the order of definition</i> in the
	 * backend configuration, as far as possible.
	 * @param type the class or interface to match, or {@code null} for all concrete beans
	 * @param includeNonSingletons whether to include prototype or scoped beans too
	 * or just singletons (also applies to FactoryBeans)
	 * @param allowEagerInit whether to initialize <i>lazy-init singletons</i> and
	 * <i>objects created by FactoryBeans</i> (or by factory methods with a
	 * "factory-bean" reference) for the type check. Note that FactoryBeans need to be
	 * eagerly initialized to determine their type: So be aware that passing in "true"
	 * for this flag will initialize FactoryBeans and "factory-bean" references.
	 * @return a Map with the matching beans, containing the bean names as
	 * keys and the corresponding bean instances as values
	 * @throws BeansException if a bean could not be created
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beansOfTypeIncludingAncestors(ListableBeanFactory, Class, boolean, boolean)
	 */
	<T> Map<String, T> getBeansOfType(@Nullable Class<T> type, boolean includeNonSingletons, boolean allowEagerInit)
			throws BeansException;

	/**
	 * Find all names of beans which are annotated with the supplied {@link Annotation}
	 * type, without creating corresponding bean instances yet.
	 * <p>Note that this method considers objects created by FactoryBeans, which means
	 * that FactoryBeans will get initialized in order to determine their object type.
	 * @param annotationType the type of annotation to look for
	 * (at class, interface or factory method level of the specified bean)
	 * @return the names of all matching beans
	 * @since 4.0
	 * @see #findAnnotationOnBean
	 */
	String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType);

	/**
	 * Find all beans which are annotated with the supplied {@link Annotation} type,
	 * returning a Map of bean names with corresponding bean instances.
	 * <p>Note that this method considers objects created by FactoryBeans, which means
	 * that FactoryBeans will get initialized in order to determine their object type.
	 * @param annotationType the type of annotation to look for
	 * (at class, interface or factory method level of the specified bean)
	 * @return a Map with the matching beans, containing the bean names as
	 * keys and the corresponding bean instances as values
	 * @throws BeansException if a bean could not be created
	 * @since 3.0
	 * @see #findAnnotationOnBean
	 */
	Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException;

	/**
	 * Find an {@link Annotation} of {@code annotationType} on the specified bean,
	 * traversing its interfaces and super classes if no annotation can be found on
	 * the given class itself, as well as checking the bean's factory method (if any).
	 * @param beanName the name of the bean to look for annotations on
	 * @param annotationType the type of annotation to look for
	 * (at class, interface or factory method level of the specified bean)
	 * @return the annotation of the given type if found, or {@code null} otherwise
	 * @throws NoSuchBeanDefinitionException if there is no bean with the given name
	 * @since 3.0
	 * @see #getBeanNamesForAnnotation
	 * @see #getBeansWithAnnotation
	 */
	@Nullable
	<A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType)
			throws NoSuchBeanDefinitionException;

}

```



### 总结：

​	ListableBeanFactory 是针对某个类型去查找一个集合列表，查找集合列表会有两种情况。

​	1：查询 Bean 名称 

​	2：查询 Bean 实例（所谓的类，new 好的对象）

​	

​	推荐使用 Bean 的名称去判断这个 Bean 是否存在，这就相当于变相的判断了 BeanDefinition 是否存在。这种方式会避免提前初始化我们定义的

Bean 从而产生的一系列不稳定因素。



为什么会产生不稳定的因素？

Bean 的生命周期里面作答。





## 4：层次性依赖查找：依赖查找也有双亲委派？



##### 层次性依赖查找：将单一类型和集合类型合并查找！



### 层次性依赖查找接口：--	HierarchicalBeanFactory



### 	· 双亲 BeanFactory ：getParentBeanFactory()



### 	· 层次性查找：

### 		· 根据 Bean 名称查找

### 			· 基于 containsLocalBean 方法实现

​		containsLocalBean 字面意思是 包含本地 Bean。什么是本地 Bean ？就是我们 Spring IoC 容器的 BeanFactory 当前类里面所包含的 Bean，但是

不包含他父类里面的 Bean。所以说拿父类的 Bean 和 当前类里面的 Bean 会有一些区别。



### 		· 根据 Bean 类型查找实例列表

### 			· 单一类型：BeanFactoryUtils#beanOfType

### 			· 集合类型：BeanFactoryUtils#beansOfTypeIncludingAncestors

​		

### 		· 根据 Java 注解查找名称列表：这个时候只返回 Bean 的名称。

### 			· BeanFactoryUtils#beanNamesForTypeIncludingAncestors





##### HierarchicalBeanFactory 源码：层次性 Bean 查找

```java
/*
 * Copyright 2002-2012 the original author or authors.
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
 * Sub-interface implemented by bean factories that can be part
 * of a hierarchy.
 *
 * <p>The corresponding {@code setParentBeanFactory} method for bean
 * factories that allow setting the parent in a configurable
 * fashion can be found in the ConfigurableBeanFactory interface.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 07.07.2003
 * @see org.springframework.beans.factory.config.ConfigurableBeanFactory#setParentBeanFactory
 */
public interface HierarchicalBeanFactory extends BeanFactory {

	/**
		获得到父亲或者双亲的 BeanFactory
	 * Return the parent bean factory, or {@code null} if there is none.
	 */
	@Nullable
	BeanFactory getParentBeanFactory();

	/**
	 * Return whether the local bean factory contains a bean of the given name,
	 * ignoring beans defined in ancestor contexts.
	 * <p>This is an alternative to {@code containsBean}, ignoring a bean
	 * of the given name from an ancestor bean factory.
	 * @param name the name of the bean to query
	 * @return whether a bean with the given name is defined in the local factory
	 * @see BeanFactory#containsBean
	 */
	boolean containsLocalBean(String name);

}

```

这里并不包含ListableBeanFactory ，我们知道 ListableBeanFactory 是帮助我们查找多类型的。HierarchicalBeanFactory 里面没有看到 ListableBeanFactory。

这里在类上面我么们可以看到有一个 ConfigurableBeanFactory ，这个是 HierarchicalBeanFactory 的一个子接口。ConfigurableBeanFacotry 里面既包括了

HierarchichalBeanFactory 这个类，也包含了 SinletonBeanRegistry 这个 Bean 注册，进而组成了一个组合类。ConfigurableBeanFactory（可以修改可以配置的） 还有一个子类，叫做ConfigurableListableBeanFactory ，它继承了 ListableBeanFactory 、AutowireCapableBeanFactory、ConfigurableBeanFactory，

三个接口。可以看出，他既组合了我们的层次性 BeanFactory - ConfigurableBeanFactory，本身又是一个 ListableBeanFactory - 多类型查找的 BeanFactory，

他更是一个可以注入外部 Bean 的 BeanFactory - AutowireCapableBeanFactory。



##### 新增示例代码：

##### HierarchicalDependencyLookupDemo.java



##### HierarchicalDependencyLookupDemo.java

```java

```





##### BeanFactoryUtils.java 源码 - 只看我们现在需要的。。。太长了。

```java
/**
	 * Return a single bean of the given type or subtypes, also picking up beans
	 * defined in ancestor bean factories if the current bean factory is a
	 * HierarchicalBeanFactory. Useful convenience method when we expect a
	 * single bean and don't care about the bean name.
	 * <p>Does consider objects created by FactoryBeans, which means that FactoryBeans
	 * will get initialized. If the object created by the FactoryBean doesn't match,
	 * the raw FactoryBean itself will be matched against the type.
	 * <p>This version of {@code beanOfTypeIncludingAncestors} automatically includes
	 * prototypes and FactoryBeans.
	 * <p><b>Note: Beans of the same name will take precedence at the 'lowest' factory level,
	 * i.e. such beans will be returned from the lowest factory that they are being found in,
	 * hiding corresponding beans in ancestor factories.</b> This feature allows for
	 * 'replacing' beans by explicitly choosing the same bean name in a child factory;
	 * the bean in the ancestor factory won't be visible then, not even for by-type lookups.
	 * @param lbf the bean factory
	 * @param type type of bean to match
	 * @return the matching bean instance
	 * @throws NoSuchBeanDefinitionException if no bean of the given type was found
	 * @throws NoUniqueBeanDefinitionException if more than one bean of the given type was found
	 * @throws BeansException if the bean could not be created
	 * @see #beansOfTypeIncludingAncestors(ListableBeanFactory, Class)
	 */
	public static <T> T beanOfTypeIncludingAncestors(ListableBeanFactory lbf, Class<T> type)
			throws BeansException {

		Map<String, T> beansOfType = beansOfTypeIncludingAncestors(lbf, type);
		return uniqueBean(type, beansOfType);
	}


	/**
	 * Return all beans of the given type or subtypes, also picking up beans defined in
	 * ancestor bean factories if the current bean factory is a HierarchicalBeanFactory.
	 * The returned Map will only contain beans of this type.
	 * <p>Does consider objects created by FactoryBeans, which means that FactoryBeans
	 * will get initialized. If the object created by the FactoryBean doesn't match,
	 * the raw FactoryBean itself will be matched against the type.
	 * <p><b>Note: Beans of the same name will take precedence at the 'lowest' factory level,
	 * i.e. such beans will be returned from the lowest factory that they are being found in,
	 * hiding corresponding beans in ancestor factories.</b> This feature allows for
	 * 'replacing' beans by explicitly choosing the same bean name in a child factory;
	 * the bean in the ancestor factory won't be visible then, not even for by-type lookups.
	 * @param lbf the bean factory
	 * @param type type of bean to match
	 * @return the Map of matching bean instances, or an empty Map if none
	 * @throws BeansException if a bean could not be created
	 * @see ListableBeanFactory#getBeansOfType(Class)
	 */
	public static <T> Map<String, T> beansOfTypeIncludingAncestors(ListableBeanFactory lbf, Class<T> type)
			throws BeansException {

		Assert.notNull(lbf, "ListableBeanFactory must not be null");
		Map<String, T> result = new LinkedHashMap<>(4);
		result.putAll(lbf.getBeansOfType(type));
		if (lbf instanceof HierarchicalBeanFactory) {
			HierarchicalBeanFactory hbf = (HierarchicalBeanFactory) lbf;
			if (hbf.getParentBeanFactory() instanceof ListableBeanFactory) {
				Map<String, T> parentResult = beansOfTypeIncludingAncestors(
						(ListableBeanFactory) hbf.getParentBeanFactory(), type);
				parentResult.forEach((beanName, beanInstance) -> {
                    //这里是个去重操作。。都用了lambda 了，为什么不用 filter 过滤。。明显炫技。
					if (!result.containsKey(beanName) && !hbf.containsLocalBean(beanName)) {
						result.put(beanName, beanInstance);
					}
				});
			}
		}
		return result;
	}


/**
	 * Extract a unique bean for the given type from the given Map of matching beans.
	 * @param type type of bean to match
	 * @param matchingBeans all matching beans found
	 * @return the unique bean instance
	 * @throws NoSuchBeanDefinitionException if no bean of the given type was found
	 * @throws NoUniqueBeanDefinitionException if more than one bean of the given type was found
	 */
	private static <T> T uniqueBean(Class<T> type, Map<String, T> matchingBeans) {
		int count = matchingBeans.size();
		if (count == 1) {
			return matchingBeans.values().iterator().next();
		}
		else if (count > 1) {
			throw new NoUniqueBeanDefinitionException(type, matchingBeans.keySet());
		}
		else {
			throw new NoSuchBeanDefinitionException(type);
		}
	}
```





### 总结：

​	HierarchicalBeanFactory 能够提供层次性的依赖查找（向上找父类要 Bean），HierachicalBeanFactory 是继承了 BeanFactory 的，

 而 ConfiguableBeanFactory 又是 继承了 HierarchicalBeanFactory 和 SinletonBeanFacotry 的，ConfigurableBeanFactory 的子类，

ConfiguableListableBeanFactory 又是继承了 ListableBeanFactory、ConfiguableBeanFactory、AutowireCapableBeanFactory 的。

所以说 ConfigurableBeanFactory 具有了 HierachiBeanFactory 的 getParentBeanFactory( ) 的方法。进而 ConfigurableBeanFactory

里面提供了一个 setParentBeanFactory( BeanFactory ) 的方法。这样就形成了一个读，一个写。

​	通过我们递归的案例，可以发现我们能够通过 HierarchicalBeanFactory 进行层次性的找整个上下文里面有没有相应的 Bean。





## 5：延迟依赖查找：非延迟初始化 Bean 也能进行延迟查找？



### 延迟依赖查找

### 	·	Bean 延迟依赖查找接口

### 		·	org.springframework.beans.factory.ObjectFactory

​					通过 getObject() 的方式返回一个泛型所关联的 Bean

### 		·	org.springframework.beans.factory.ObjectProvider

​					ObjectProvider 继承于 ObjectFactory 这个接口，因此ObjectProvider 可以调用 ObjectFactory 的 getObject() 方法来获取

​					当前所关联的 Bean 对象

### 			·	Spring 5 对 java 8 特性扩展

### 				·	函数式接口 （生产消费）

### 					·	getIfAvailable( Supplier )

### 					·	ifAvailable( Comsumer )

### 				·	Stream 扩展 - stream()

​								有序的 stream 是 sorted.stream 



ObjectProvider 是 Spring 4.3 提供的一个接口，比如 Spring Boot 里面某些 Bean 没有被完全加载，可以使用 ObjectProvider 通过在某些阶段

来进行加载，和 BeanFactory 的延迟加载非常类似。但是这里可以不需要 Bean 去定义他的 LazyInitialization = true，这些都是 Spring 来帮助

我们做这些事情。



##### ObjectProvider.java

```java
/*
 * Copyright 2002-2018 the original author or authors.
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

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * A variant of {@link ObjectFactory} designed specifically for injection points,
 * allowing for programmatic optionality and lenient not-unique handling.
 *
 * <p>As of 5.1, this interface extends {@link Iterable} and provides {@link Stream}
 * support. It can be therefore be used in {@code for} loops, provides {@link #forEach}
 * iteration and allows for collection-style {@link #stream} access.
 *
 * @author Juergen Hoeller
 * @since 4.3
 * @param <T> the object type
 * @see BeanFactory#getBeanProvider
 * @see org.springframework.beans.factory.annotation.Autowired
 */
public interface ObjectProvider<T> extends ObjectFactory<T>, Iterable<T> {

	/**
		这是一个重载方法，和 BeanFactory 的 getBean() 相呼应。通常都是用 ObjectFactory 里面的 getObject() 方法来操作。
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * <p>Allows for specifying explicit construction arguments, along the
	 * lines of {@link BeanFactory#getBean(String, Object...)}.
	 * @param args arguments to use when creating a corresponding instance
	 * @return an instance of the bean
	 * @throws BeansException in case of creation errors
	 * @see #getObject()
	 */
	T getObject(Object... args) throws BeansException;

	/**
		这是一种比较安全的方式。后面详细说明
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * @return an instance of the bean, or {@code null} if not available
	 * @throws BeansException in case of creation errors
	 * @see #getObject()
	 */
	@Nullable
	T getIfAvailable() throws BeansException;

	/**
		另外一种比较安全的实现。如果当前的 Bean 不存在，这里会返回一个 null，，
		这里默认允许提供一个 Supplier 来给定默认值。
		例如：想获取一个 DataSource，但是 DataSource 不存在，可以提供一个新的方式来操作。
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * @param defaultSupplier a callback for supplying a default object
	 * if none is present in the factory
	 * @return an instance of the bean, or the supplied default object
	 * if no such bean is available
	 * @throws BeansException in case of creation errors
	 * @since 5.0
	 * @see #getIfAvailable()
	 */
	default T getIfAvailable(Supplier<T> defaultSupplier) throws BeansException {
		T dependency = getIfAvailable();
		return (dependency != null ? dependency : defaultSupplier.get());
	}

	/**
		这里是消费的方式
	 * Consume an instance (possibly shared or independent) of the object
	 * managed by this factory, if available.
	 * @param dependencyConsumer a callback for processing the target object
	 * if available (not called otherwise)
	 * @throws BeansException in case of creation errors
	 * @since 5.0
	 * @see #getIfAvailable()
	 */
	default void ifAvailable(Consumer<T> dependencyConsumer) throws BeansException {
		T dependency = getIfAvailable();
		if (dependency != null) {
			dependencyConsumer.accept(dependency);
		}
	}

	/**
		这个也是类型安全的，后面详细说。
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * @return an instance of the bean, or {@code null} if not available or
	 * not unique (i.e. multiple candidates found with none marked as primary)
	 * @throws BeansException in case of creation errors
	 * @see #getObject()
	 */
	@Nullable
	T getIfUnique() throws BeansException;

	/**
	 * Return an instance (possibly shared or independent) of the object
	 * managed by this factory.
	 * @param defaultSupplier a callback for supplying a default object
	 * if no unique candidate is present in the factory
	 * @return an instance of the bean, or the supplied default object
	 * if no such bean is available or if it is not unique in the factory
	 * (i.e. multiple candidates found with none marked as primary)
	 * @throws BeansException in case of creation errors
	 * @since 5.0
	 * @see #getIfUnique()
	 */
	default T getIfUnique(Supplier<T> defaultSupplier) throws BeansException {
		T dependency = getIfUnique();
		return (dependency != null ? dependency : defaultSupplier.get());
	}

	/**
	 * Consume an instance (possibly shared or independent) of the object
	 * managed by this factory, if unique.
	 * @param dependencyConsumer a callback for processing the target object
	 * if unique (not called otherwise)
	 * @throws BeansException in case of creation errors
	 * @since 5.0
	 * @see #getIfAvailable()
	 */
	default void ifUnique(Consumer<T> dependencyConsumer) throws BeansException {
		T dependency = getIfUnique();
		if (dependency != null) {
			dependencyConsumer.accept(dependency);
		}
	}

	/**
		支持 stream() 的 嗲代器
	 * Return an {@link Iterator} over all matching object instances,
	 * without specific ordering guarantees (but typically in registration order).
	 * @since 5.1
	 * @see #stream()
	 */
	@Override
	default Iterator<T> iterator() {
		return stream().iterator();
	}

	/**
		stream 的一些支持。
	 * Return a sequential {@link Stream} over all matching object instances,
	 * without specific ordering guarantees (but typically in registration order).
	 * @since 5.1
	 * @see #iterator()
	 * @see #orderedStream()
	 */
	default Stream<T> stream() {
		throw new UnsupportedOperationException("Multi element access not supported");
	}

	/**
	 * Return a sequential {@link Stream} over all matching object instances,
	 * pre-ordered according to the factory's common order comparator.
	 * <p>In a standard Spring application context, this will be ordered
	 * according to {@link org.springframework.core.Ordered} conventions,
	 * and in case of annotation-based configuration also considering the
	 * {@link org.springframework.core.annotation.Order} annotation,
	 * analogous to multi-element injection points of list/array type.
	 * @since 5.1
	 * @see #stream()
	 * @see org.springframework.core.OrderComparator
	 */
	default Stream<T> orderedStream() {
		throw new UnsupportedOperationException("Ordered element access not supported");
	}

}

```



其实在之前 **ObjectProviderDemo.java** 里面已经做出了一个简要的说明。。。初始化一个 String 类型的 Hello World。。。

当时我们通过 AnnotationConfigApplicationContext 的方式来获取到 ObjectProvider 这个接口，进而调用了 ObjectProvider 的

getObject() 方法获取到的 Bean（String 类型的 Hello World）。这里请思考一个问题，假设我的 String 类型的 Hello World 不存在，

咋办？



##### ObjectProvider.java

```java
package org.example.thinking.in.spring.denpendency.lookup;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Iterator;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 通过 {@link ObjectProvider} 进行依赖查找
 * */
//Configuration 是非必须的注解
public class ObjectProviderDemo {

    public static void main(String[] args) {

        //创建应用上下文
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类注册为 AnnotationConfigApplicationContext 的 配置类 Config Class
        applicationContext.register(ObjectProviderDemo.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //通过 ObjectProvider 进行依赖查找
        lookupByObjectProvider(applicationContext);

        //依赖查找，当对象不存在的时候如何处理
        lookupAvailable(applicationContext);

        //依赖查找，通过 Stream 嗲代器的方式
        lookupByStreamOps(applicationContext);

        //关闭 Spring 应用上下文
        applicationContext.close();


    }

    /** 依赖查找,通过 Stream 操作的方式 */
    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext){
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        Supplier<Stream<String>> beanProviderStream = new Supplier<Stream<String>>() {
            @Override
            public Stream<String> get() {
                return beanProvider.stream();
            }
        };

        //比较基本的一个 stream
        beanProviderStream.get().forEach(beanName -> System.out.println("base : " + beanName));

        //比较 low 的一种方式
        Iterable<String> iterable = beanProvider;

        for(Iterator<String> str = iterable.iterator() ; str.hasNext() ;  ){
            String next = str.next();
            System.out.println("low : " + next);
        }

        //看上比较吊
        beanProviderStream.get().forEach(System.out::println);

    }

    /** 依赖查找，假设类不存在的补偿操作 */
    private static void lookupAvailable(AnnotationConfigApplicationContext applicationContext){

        ObjectProvider<User> beanProvider = applicationContext.getBeanProvider(User.class);

        //没有就创建一个 最简单的 lambda 表达式方式
//        User ifAvailable = beanProvider.getIfAvailable(() -> User.createUser());

        //有点 low 的方式。
//        User ifAvailable = beanProvider.getIfAvailable(new Supplier<User>() {
//            @Override
//            public User get() {
//                return User.createUser();
//            }
//        });

        //看上去比较吊的方式
        User ifAvailable = beanProvider.getIfAvailable(User::createUser);

        System.out.println(ifAvailable);
    }

    /** 依赖查找 调用 ObjectProvider 的父类 ObjectFactory 的 getObject() 方法 */
    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        // spring 5.1 引入的。
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        String hello = beanProvider.getObject();
        System.out.println(hello);
    }

    @Primary
    @Bean
    public String helloWorld(){
        //如果 @Bean 没有定义 Bean Name 那么方法名就是 Bean Name
        return "Hello World !";
    }

    @Bean
    public String ddMessage(){
        return "ding ding message";
    }

}

```





### 总结：

​	通过例子，我们知道了 ObjectProvider 对 java 1.8 的支持，和对非延迟初始化 Bean 的延迟加载是如何操作的。

接下来将会对遗留的安全查找进行讨论，这和之前说的单一、集合类型依赖查找是有关联的。