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







## 6：安全依赖查找



##### 本章新增文件：

##### TypeSafetyDependencyLookupDemo.java



TypeSafetyDependencyLookupDemo.java

```java
package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

/**
 * 类型安全的依赖查找示例
 * */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 TypeSafetyDependencyLookupDemo 为配置类 Configuration Class
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);

        //启动 Spring IoC 容器上下文
        applicationContext.refresh();

        /** 单一类型 */

        //演示 BeanFactory # getBean 方法进行依赖查找的安全性
        displayBeanFactoryGetBean(applicationContext);

        //演示 ObjectBeanFactory # getBean 方法进行依赖查找的安全性
        displayObjectBeanFactoryGetObject(applicationContext.getBeanProvider(Date.class));

        //展示 ObjectProvider # getIfAvailable 方法进行依赖查找的安全性
        displayObjectProviderIfAvailableOps(applicationContext.getBeanProvider(Date.class));


        /** 集合类型 */

        //演示 ListableBeanFactory # getBeansOfType 进行依赖查找的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);

        //演示 ObjectProvider # stream 方法进行依赖查找的安全性
        diaplayObjectProviderStreamOps(applicationContext.getBeanProvider(Date.class));

        //关闭 Spring IoC 容器上下文
        applicationContext.close();

    }

    private static void diaplayObjectProviderStreamOps(ObjectProvider<Date> beanProvider) {

        printBeansException("diaplayObjectProviderStreamOps", () -> {
            beanProvider.forEach(System.out::println);
        });

    }

    /** 演示 ListableBeanFactory # getBeansOfType 进行依赖查找的安全性 */
    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory listableBeanFactory) {

        printBeansException("displayListableBeanFactoryGetBeansOfType",() -> listableBeanFactory.getBeansOfType(Date.class).forEach((s, date) -> System.err.println(s + " " + date)));

    }

    /** 展示 ObjectProvider # getIfAvailable 方法进行依赖查找的安全性 */
    private static void displayObjectProviderIfAvailableOps(ObjectProvider<Date> objectProvider) {

        printBeansException("displayObjectProviderIfAvailableOps",() -> System.err.println(objectProvider.getIfAvailable()));
//        printBeansException("displayObjectProviderIfAvailableOps",() -> System.err.println(objectProvider.getIfAvailable(Date::new)));

    }

    /** 演示 BeanFactory # getBean 方法进行依赖查找的安全性  */
    private static void displayBeanFactoryGetBean(BeanFactory beanFactory){

        printBeansException("displayBeanFactoryGetBean",() -> System.out.println(beanFactory.getBean(Date.class)));

    }

    /** 演示 ObjectBeanFactory # getBean 方法进行依赖查找的安全性  */
    private static void displayObjectBeanFactoryGetObject(ObjectFactory<Date> objectFactory){

        printBeansException("displayObjectBeanFactoryGetObject ", () -> System.out.println(objectFactory.getObject()));

    }

    /** 输出 */
    private static void printBeansException(String msg,Runnable runnable){
        System.err.println("\r\n current display : " + msg);
        try{
            runnable.run();
        }catch(Exception e){
            //这玩意是县城安全的，老铁们别在线上玩。。有发生死锁的老铁打来骂人热线。。。
            e.printStackTrace();
        }
    }

}

```



### ·	依赖查找安全性对比 - 又分为实时查找和非实时查找

| 依赖查找类型     | 代表实现                             | 是否安全 | 查找类型 |
| :--------------- | ------------------------------------ | -------- | -------- |
| 单一类型依赖查找 | BeanFactory # getBean                | 否       | 实时查找 |
|                  | ObjectFactory # getObject            | 否       | 延迟查找 |
|                  | ObjectProvider # getIfAvailable      | 是       | 延迟查找 |
|                  |                                      |          |          |
| 集合类型依赖查找 | ListableBeanFactory # getBeansOfType | 是       |          |
|                  | ObjectProvider # stream              | 是       |          |

##### 注意哦：层次性依赖查找的安全性取决于其扩展的单一或者集合类型的 BeanFactory 接口哦。

##### 因为 HierarchicalBeanFactory 是层次性依赖的接口，单一的继承了 BeanFactory ，但是 ConfigurableBeanFactory 继承了 HierarchicalBeanFactory，

##### ConfiguableBeanFactory 的子类 ConfigurableListableBeanFactory 复合了 ListableBeanFactory、AutowireCapableBeanFactory、

##### ConfigurableBeanFactory 这么三个接口，ListableBeanFactory 又继承了 BeanFactory ，支持单一类型依赖查找。所以说层次依赖性查找的安全性，取决于 

##### HierarchicalBeanFactory 的子类是怎么实现的。



##### 例如：DefaultListableBeanFactory 就是实现了 ConfigurableListableBeanFactory，因此这个既接口支持单一类型查找，又支持集合类型查找，

##### 同时也支持层次性依赖查找。因此 DefaultListableBeanFactory 作为了 Spring 的一个兜底方案，因此内建的依赖查找复合接口仅此一个。





##### 建议：如果用延迟加载的话，建议用 ObjectProvider 这个接口 ，因为他既可以表示单一类，又可以表示集合类，

##### 所以 Spring Boot 和 Spring Cloud 里面大量的用了这玩意。



##### BeanFactory # getBean 源代码：

```java
/**
	 * Return the bean instance that uniquely matches the given object type, if any.
	 * <p>This method goes into {@link ListableBeanFactory} by-type lookup territory
	 * but may also be translated into a conventional by-name lookup based on the name
	 * of the given type. For more extensive retrieval operations across sets of beans,
	 * use {@link ListableBeanFactory} and/or {@link BeanFactoryUtils}.
	 * @param requiredType type the bean must match; can be an interface or superclass
	 * @return an instance of the single bean matching the required type
	 
	 这里说有两种异常的情况属于 BeansException
	 1：就是 没有找到 Bean 定义会抛出 NoSuchBeanDefinitionException 的异常
	 2：你要找的 Bean 定义不是唯一的，会抛出 NoUniqueBeanDefinitionException
	 
	 * @throws NoSuchBeanDefinitionException if no bean of the given type was found
	 * @throws NoUniqueBeanDefinitionException if more than one bean of the given type was found
	 * @throws BeansException if the bean could not be created
	 * @since 3.0
	 * @see ListableBeanFactory
	 */
	<T> T getBean(Class<T> requiredType) throws BeansException;
```



##### ListableBeanFactory # getBeansOfType 源代码：

```java
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
	 
	 	这里抛出的异常就比较单一了。
	 	例如：当 Bean 创建的时候就有问题，好比 Bean 定于成了一个抽象类，这玩意本身就不能初始化。
	 
	 * @throws BeansException if a bean could not be created
	 * @since 1.1.2
	 * @see FactoryBean#getObjectType
	 * @see BeanFactoryUtils#beansOfTypeIncludingAncestors(ListableBeanFactory, Class)
	 */
	<T> Map<String, T> getBeansOfType(@Nullable Class<T> type) throws BeansException;
```



### 总结：

​	分别通过单一类型、集合类型的依赖查找判断他的安全性，其中我们发现单一类型依赖查找，相对于集合类型的依赖查找来说是不安全的。尤其像

BeanFactory 的 getBean() 和 ObjectBeanFactory 的 getObject() 在 Bean 不存在的时候会抛出异常 BeansException（NoSuchBeanDefinitionException、

NoUniqueBeanDefinitionException）。这个 Bean 一旦出现异常，我们会非常棘手，所以我们可以通过 ObjectProvider 的 getIfAvailable() 方法来进行

兜底或者修复，以及排查。所以我们在 Spring Boot 和 Spring Cloud 场景中会经常遇到这样的实现。

​	与此同时，集合类型依赖查找是比较安全的，所以他会返回一个 Map ，如果没有 Map 就是 null。真正实战的时候要根据场景去实现，推荐用

ObjectProvider 这种方式来进行依赖查找，因为这样的方式 单一类型、集合类型都能进行依赖查找。









## 7：内建可查找的依赖：哪些 Spring IoC 容器内建依赖可供查找？



### 内建可查询依赖：ApplicationContext 内建依赖

| Bean 名称                   | Bean 实例                        | 使用场景                                                     |
| --------------------------- | -------------------------------- | ------------------------------------------------------------ |
| environment                 | Environment 对象                 | 外部化配置以及 Profiles<br />environment 其实就是 Environment 接口的一个实现<br />外部化配置主要是指的 -D 这些参数之类的东西<br />这个配置可以在不同的阶段生成不同的行为，例如开发环境和生产环境之间的不同 |
| systemProperties            | java.util.Properties 对象        | Java 系统属性<br />也算是 environment 的一部分配置。比如获取系统的路径 user.home、user.dll 这些东西 |
| systemEnvironment           | java.util.Map 对象               | 操作系统环境变量<br />Java 的系统属性和 Java 的当前进程启动是有关系的，也就是说不同的 Java 进程，他们之间的系统属性是可以不同的。一般来说一个系统的系统参数都是统一的，当然环境变量也会区分当前用户的环境变量和整体系统的全局环境变量，这里主要是指当前用户的环境变量 |
| messageSource               | MessageSource 对象               | 国际化文案<br />在 Spring MVC 里面，会有一个国际网文案的交互，比如表单验证失败了，他就会提醒我们表达是否唯一或者格式是否非法 |
| lifecycleProcessor          | LifecycleProcessor 对象          | Lifecycle Bean 处理<br />在 Spring 里面有一个 Lifecycle 的接口，这个接口实际上是每个 Spring 的 Bean 都能实现的，lifecycleProcessor 就是去处理这些东西，因此就会有一些生命周期，比如启动（start）、停止（stop）。这些生命周期可以帮助我们去实现一些更细粒度化的生命周期管理。生命周期不仅仅是之前讨论过的 @PostConstract @Destory 等东西。 |
| applicationEventMulticaster | ApplicationEventMulticaster 对象 | Spring 事件广播<br />当我 Spring 发布一个事件的时候，那么会有很多监听者监听这个事件，事件和监听者一般都是一对多的关系，这种方式就称之为广播的方式。在 Spring 里面不支持单一的广播（点对点）。 |

不管 Spring Boot 还是 Spring cloud ，在 Spring 的应用上下文里面有一个抽象类叫做 AbstractApplicationContext ，这个抽象类是所有的应用上下文的一个

基类，基本上所有应用上下文的实现，包括 注解的 AnnotationConfiguration、ApplicationContext， 以及 WEB 的实现都是基于 AbstractApplicationContext 

来进行实现的。因此这个抽象类会在构建的时候（上下文启动 AnnotationConfigApplicationContext 的 refresh() 方法调用的时候），初始化一些相关的内部的

一些依赖。那么这种内部依赖，我们就称之为内建的可查询的依赖。





### 注解驱动 Spring 应用上下文内建可查找的依赖（部分）

| Bean 名称                                                    | Bean 实例                              | 使用场景                                                     |
| :----------------------------------------------------------- | -------------------------------------- | ------------------------------------------------------------ |
| org.springframework.context.annotation.internal.ConfigurationAnnotationProcessor | ConfigurationAnnotationProcessor 对象  | 处理 Spring 配置，Bean 的后置处理。<br />主要是在 BeanFactroy 的生命周期中去做的，他是处理 Spring 配置类的一个很核心的要素。 |
| org.springframework.context.annotation.internal.AutowiredAnnotationProcessor | AutowiredAnnotationProcessor 对象      | 处理 @Autowired 注解以及 @Value 注解                         |
| org.springframework.context.annotation.internal.CommonAnnotationBeanPostProcessor | CommonAnnotationBeanPostProcessor 对象 | （条件激活）处理 JSR-250 注解，例如：@PostConstract 等注解   |
| org.springframework.context.annotation.internal.EventListenerMethodProcessor | EventListenerMethodProcessor 对象      | 处理标注 @EventListener 的 Spring 事件监听方法               |



internal 说明是内部的上下文 Bean 依赖，这个依赖只有当激活的时候（ xml 里面激活 Component-scan）才生效。



##### ConfigurationClass.java 源码：

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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.core.io.DescriptiveResource;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * Represents a user-defined {@link Configuration @Configuration} class.
 * Includes a set of {@link Bean} methods, including all such methods
 * defined in the ancestry of the class, in a 'flattened-out' manner.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @author Phillip Webb
 * @since 3.0
 * @see BeanMethod
 * @see ConfigurationClassParser
 */
final class ConfigurationClass {

	private final AnnotationMetadata metadata;

	private final Resource resource;

	@Nullable
	private String beanName;

	private final Set<ConfigurationClass> importedBy = new LinkedHashSet<>(1);

	private final Set<BeanMethod> beanMethods = new LinkedHashSet<>();

	private final Map<String, Class<? extends BeanDefinitionReader>> importedResources =
			new LinkedHashMap<>();

	private final Map<ImportBeanDefinitionRegistrar, AnnotationMetadata> importBeanDefinitionRegistrars =
			new LinkedHashMap<>();

	final Set<String> skippedBeanMethods = new HashSet<>();


	/**
	 * Create a new {@link ConfigurationClass} with the given name.
	 * @param metadataReader reader used to parse the underlying {@link Class}
	 * @param beanName must not be {@code null}
	 * @see ConfigurationClass#ConfigurationClass(Class, ConfigurationClass)
	 */
	public ConfigurationClass(MetadataReader metadataReader, String beanName) {
		Assert.notNull(beanName, "Bean name must not be null");
		this.metadata = metadataReader.getAnnotationMetadata();
		this.resource = metadataReader.getResource();
		this.beanName = beanName;
	}

	/**
	 * Create a new {@link ConfigurationClass} representing a class that was imported
	 * using the {@link Import} annotation or automatically processed as a nested
	 * configuration class (if importedBy is not {@code null}).
	 * @param metadataReader reader used to parse the underlying {@link Class}
	 * @param importedBy the configuration class importing this one or {@code null}
	 * @since 3.1.1
	 */
	public ConfigurationClass(MetadataReader metadataReader, @Nullable ConfigurationClass importedBy) {
		this.metadata = metadataReader.getAnnotationMetadata();
		this.resource = metadataReader.getResource();
		this.importedBy.add(importedBy);
	}

	/**
	 * Create a new {@link ConfigurationClass} with the given name.
	 * @param clazz the underlying {@link Class} to represent
	 * @param beanName name of the {@code @Configuration} class bean
	 * @see ConfigurationClass#ConfigurationClass(Class, ConfigurationClass)
	 */
	public ConfigurationClass(Class<?> clazz, String beanName) {
		Assert.notNull(beanName, "Bean name must not be null");
		this.metadata = AnnotationMetadata.introspect(clazz);
		this.resource = new DescriptiveResource(clazz.getName());
		this.beanName = beanName;
	}

	/**
	 * Create a new {@link ConfigurationClass} representing a class that was imported
	 * using the {@link Import} annotation or automatically processed as a nested
	 * configuration class (if imported is {@code true}).
	 * @param clazz the underlying {@link Class} to represent
	 * @param importedBy the configuration class importing this one (or {@code null})
	 * @since 3.1.1
	 */
	public ConfigurationClass(Class<?> clazz, @Nullable ConfigurationClass importedBy) {
		this.metadata = AnnotationMetadata.introspect(clazz);
		this.resource = new DescriptiveResource(clazz.getName());
		this.importedBy.add(importedBy);
	}

	/**
	 * Create a new {@link ConfigurationClass} with the given name.
	 * @param metadata the metadata for the underlying class to represent
	 * @param beanName name of the {@code @Configuration} class bean
	 * @see ConfigurationClass#ConfigurationClass(Class, ConfigurationClass)
	 */
	public ConfigurationClass(AnnotationMetadata metadata, String beanName) {
		Assert.notNull(beanName, "Bean name must not be null");
		this.metadata = metadata;
		this.resource = new DescriptiveResource(metadata.getClassName());
		this.beanName = beanName;
	}


	public AnnotationMetadata getMetadata() {
		return this.metadata;
	}

	public Resource getResource() {
		return this.resource;
	}

	public String getSimpleName() {
		return ClassUtils.getShortName(getMetadata().getClassName());
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	@Nullable
	public String getBeanName() {
		return this.beanName;
	}

	/**
	 * Return whether this configuration class was registered via @{@link Import} or
	 * automatically registered due to being nested within another configuration class.
	 * @since 3.1.1
	 * @see #getImportedBy()
	 */
	public boolean isImported() {
		return !this.importedBy.isEmpty();
	}

	/**
	 * Merge the imported-by declarations from the given configuration class into this one.
	 * @since 4.0.5
	 */
	public void mergeImportedBy(ConfigurationClass otherConfigClass) {
		this.importedBy.addAll(otherConfigClass.importedBy);
	}

	/**
	 * Return the configuration classes that imported this class,
	 * or an empty Set if this configuration was not imported.
	 * @since 4.0.5
	 * @see #isImported()
	 */
	public Set<ConfigurationClass> getImportedBy() {
		return this.importedBy;
	}

	public void addBeanMethod(BeanMethod method) {
		this.beanMethods.add(method);
	}

	public Set<BeanMethod> getBeanMethods() {
		return this.beanMethods;
	}

	public void addImportedResource(String importedResource, Class<? extends BeanDefinitionReader> readerClass) {
		this.importedResources.put(importedResource, readerClass);
	}

	public void addImportBeanDefinitionRegistrar(ImportBeanDefinitionRegistrar registrar, AnnotationMetadata importingClassMetadata) {
		this.importBeanDefinitionRegistrars.put(registrar, importingClassMetadata);
	}

	public Map<ImportBeanDefinitionRegistrar, AnnotationMetadata> getImportBeanDefinitionRegistrars() {
		return this.importBeanDefinitionRegistrars;
	}

	public Map<String, Class<? extends BeanDefinitionReader>> getImportedResources() {
		return this.importedResources;
	}

	public void validate(ProblemReporter problemReporter) {
		// A configuration class may not be final (CGLIB limitation) unless it declares proxyBeanMethods=false
		Map<String, Object> attributes = this.metadata.getAnnotationAttributes(Configuration.class.getName());
		if (attributes != null && (Boolean) attributes.get("proxyBeanMethods")) {
			if (this.metadata.isFinal()) {
				problemReporter.error(new FinalConfigurationProblem());
			}
			for (BeanMethod beanMethod : this.beanMethods) {
				beanMethod.validate(problemReporter);
			}
		}
	}

	@Override
	public boolean equals(@Nullable Object other) {
		return (this == other || (other instanceof ConfigurationClass &&
				getMetadata().getClassName().equals(((ConfigurationClass) other).getMetadata().getClassName())));
	}

	@Override
	public int hashCode() {
		return getMetadata().getClassName().hashCode();
	}

	@Override
	public String toString() {
		return "ConfigurationClass: beanName '" + this.beanName + "', " + this.resource;
	}


	/**
	 * Configuration classes must be non-final to accommodate CGLIB subclassing.
	 */
	private class FinalConfigurationProblem extends Problem {

		public FinalConfigurationProblem() {
			super(String.format("@Configuration class '%s' may not be final. Remove the final modifier to continue.",
					getSimpleName()), new Location(getResource(), getMetadata()));
		}
	}

}

```

这个类包含了我们的 @Configuration 注解：



##### Configuration.java 源码：

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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * Indicates that a class declares one or more {@link Bean @Bean} methods and
 * may be processed by the Spring container to generate bean definitions and
 * service requests for those beans at runtime, for example:
 *
 * <pre class="code">
 * &#064;Configuration
 * public class AppConfig {
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         // instantiate, configure and return bean ...
 *     }
 * }</pre>
 *
 * <h2>Bootstrapping {@code @Configuration} classes</h2>
 *
 * <h3>Via {@code AnnotationConfigApplicationContext}</h3>
 *
 * <p>{@code @Configuration} classes are typically bootstrapped using either
 * {@link AnnotationConfigApplicationContext} or its web-capable variant,
 * {@link org.springframework.web.context.support.AnnotationConfigWebApplicationContext
 * AnnotationConfigWebApplicationContext}. A simple example with the former follows:
 *
 * <pre class="code">
 * AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
 * ctx.register(AppConfig.class);
 * ctx.refresh();
 * MyBean myBean = ctx.getBean(MyBean.class);
 * // use myBean ...
 * </pre>
 *
 * <p>See the {@link AnnotationConfigApplicationContext} javadocs for further details, and see
 * {@link org.springframework.web.context.support.AnnotationConfigWebApplicationContext
 * AnnotationConfigWebApplicationContext} for web configuration instructions in a
 * {@code Servlet} container.
 *
 * <h3>Via Spring {@code <beans>} XML</h3>
 *
 * <p>As an alternative to registering {@code @Configuration} classes directly against an
 * {@code AnnotationConfigApplicationContext}, {@code @Configuration} classes may be
 * declared as normal {@code <bean>} definitions within Spring XML files:
 *
 * <pre class="code">
 * &lt;beans&gt;
 *    &lt;context:annotation-config/&gt;
 *    &lt;bean class="com.acme.AppConfig"/&gt;
 * &lt;/beans&gt;
 * </pre>
 *
 * <p>In the example above, {@code <context:annotation-config/>} is required in order to
 * enable {@link ConfigurationClassPostProcessor} and other annotation-related
 * post processors that facilitate handling {@code @Configuration} classes.
 *
 * <h3>Via component scanning</h3>
 *
 * <p>{@code @Configuration} is meta-annotated with {@link Component @Component}, therefore
 * {@code @Configuration} classes are candidates for component scanning (typically using
 * Spring XML's {@code <context:component-scan/>} element) and therefore may also take
 * advantage of {@link Autowired @Autowired}/{@link javax.inject.Inject @Inject}
 * like any regular {@code @Component}. In particular, if a single constructor is present
 * autowiring semantics will be applied transparently for that constructor:
 *
 * <pre class="code">
 * &#064;Configuration
 * public class AppConfig {
 *
 *     private final SomeBean someBean;
 *
 *     public AppConfig(SomeBean someBean) {
 *         this.someBean = someBean;
 *     }
 *
 *     // &#064;Bean definition using "SomeBean"
 *
 * }</pre>
 *
 * <p>{@code @Configuration} classes may not only be bootstrapped using
 * component scanning, but may also themselves <em>configure</em> component scanning using
 * the {@link ComponentScan @ComponentScan} annotation:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;ComponentScan("com.acme.app.services")
 * public class AppConfig {
 *     // various &#064;Bean definitions ...
 * }</pre>
 *
 * <p>See the {@link ComponentScan @ComponentScan} javadocs for details.
 *
 * <h2>Working with externalized values</h2>
 *
 * <h3>Using the {@code Environment} API</h3>
 *
 * <p>Externalized values may be looked up by injecting the Spring
 * {@link org.springframework.core.env.Environment} into a {@code @Configuration}
 * class &mdash; for example, using the {@code @Autowired} annotation:
 *
 * <pre class="code">
 * &#064;Configuration
 * public class AppConfig {
 *
 *     &#064Autowired Environment env;
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         MyBean myBean = new MyBean();
 *         myBean.setName(env.getProperty("bean.name"));
 *         return myBean;
 *     }
 * }</pre>
 *
 * <p>Properties resolved through the {@code Environment} reside in one or more "property
 * source" objects, and {@code @Configuration} classes may contribute property sources to
 * the {@code Environment} object using the {@link PropertySource @PropertySource}
 * annotation:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;PropertySource("classpath:/com/acme/app.properties")
 * public class AppConfig {
 *
 *     &#064Inject Environment env;
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         return new MyBean(env.getProperty("bean.name"));
 *     }
 * }</pre>
 *
 * <p>See the {@link org.springframework.core.env.Environment Environment}
 * and {@link PropertySource @PropertySource} javadocs for further details.
 *
 * <h3>Using the {@code @Value} annotation</h3>
 *
 * <p>Externalized values may be injected into {@code @Configuration} classes using
 * the {@link Value @Value} annotation:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;PropertySource("classpath:/com/acme/app.properties")
 * public class AppConfig {
 *
 *     &#064Value("${bean.name}") String beanName;
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         return new MyBean(beanName);
 *     }
 * }</pre>
 *
 * <p>This approach is often used in conjunction with Spring's
 * {@link org.springframework.context.support.PropertySourcesPlaceholderConfigurer
 * PropertySourcesPlaceholderConfigurer} that can be enabled <em>automatically</em>
 * in XML configuration via {@code <context:property-placeholder/>} or <em>explicitly</em>
 * in a {@code @Configuration} class via a dedicated {@code static} {@code @Bean} method
 * (see "a note on BeanFactoryPostProcessor-returning {@code @Bean} methods" of
 * {@link Bean @Bean}'s javadocs for details). Note, however, that explicit registration
 * of a {@code PropertySourcesPlaceholderConfigurer} via a {@code static} {@code @Bean}
 * method is typically only required if you need to customize configuration such as the
 * placeholder syntax, etc. Specifically, if no bean post-processor (such as a
 * {@code PropertySourcesPlaceholderConfigurer}) has registered an <em>embedded value
 * resolver</em> for the {@code ApplicationContext}, Spring will register a default
 * <em>embedded value resolver</em> which resolves placeholders against property sources
 * registered in the {@code Environment}. See the section below on composing
 * {@code @Configuration} classes with Spring XML using {@code @ImportResource}; see
 * the {@link Value @Value} javadocs; and see the {@link Bean @Bean} javadocs for details
 * on working with {@code BeanFactoryPostProcessor} types such as
 * {@code PropertySourcesPlaceholderConfigurer}.
 *
 * <h2>Composing {@code @Configuration} classes</h2>
 *
 * <h3>With the {@code @Import} annotation</h3>
 *
 * <p>{@code @Configuration} classes may be composed using the {@link Import @Import} annotation,
 * similar to the way that {@code <import>} works in Spring XML. Because
 * {@code @Configuration} objects are managed as Spring beans within the container,
 * imported configurations may be injected &mdash; for example, via constructor injection:
 *
 * <pre class="code">
 * &#064;Configuration
 * public class DatabaseConfig {
 *
 *     &#064;Bean
 *     public DataSource dataSource() {
 *         // instantiate, configure and return DataSource
 *     }
 * }
 *
 * &#064;Configuration
 * &#064;Import(DatabaseConfig.class)
 * public class AppConfig {
 *
 *     private final DatabaseConfig dataConfig;
 *
 *     public AppConfig(DatabaseConfig dataConfig) {
 *         this.dataConfig = dataConfig;
 *     }
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         // reference the dataSource() bean method
 *         return new MyBean(dataConfig.dataSource());
 *     }
 * }</pre>
 *
 * <p>Now both {@code AppConfig} and the imported {@code DatabaseConfig} can be bootstrapped
 * by registering only {@code AppConfig} against the Spring context:
 *
 * <pre class="code">
 * new AnnotationConfigApplicationContext(AppConfig.class);</pre>
 *
 * <h3>With the {@code @Profile} annotation</h3>
 *
 * <p>{@code @Configuration} classes may be marked with the {@link Profile @Profile} annotation to
 * indicate they should be processed only if a given profile or profiles are <em>active</em>:
 *
 * <pre class="code">
 * &#064;Profile("development")
 * &#064;Configuration
 * public class EmbeddedDatabaseConfig {
 *
 *     &#064;Bean
 *     public DataSource dataSource() {
 *         // instantiate, configure and return embedded DataSource
 *     }
 * }
 *
 * &#064;Profile("production")
 * &#064;Configuration
 * public class ProductionDatabaseConfig {
 *
 *     &#064;Bean
 *     public DataSource dataSource() {
 *         // instantiate, configure and return production DataSource
 *     }
 * }</pre>
 *
 * <p>Alternatively, you may also declare profile conditions at the {@code @Bean} method level
 * &mdash; for example, for alternative bean variants within the same configuration class:
 *
 * <pre class="code">
 * &#064;Configuration
 * public class ProfileDatabaseConfig {
 *
 *     &#064;Bean("dataSource")
 *     &#064;Profile("development")
 *     public DataSource embeddedDatabase() { ... }
 *
 *     &#064;Bean("dataSource")
 *     &#064;Profile("production")
 *     public DataSource productionDatabase() { ... }
 * }</pre>
 *
 * <p>See the {@link Profile @Profile} and {@link org.springframework.core.env.Environment}
 * javadocs for further details.
 *
 * <h3>With Spring XML using the {@code @ImportResource} annotation</h3>
 *
 * <p>As mentioned above, {@code @Configuration} classes may be declared as regular Spring
 * {@code <bean>} definitions within Spring XML files. It is also possible to
 * import Spring XML configuration files into {@code @Configuration} classes using
 * the {@link ImportResource @ImportResource} annotation. Bean definitions imported from
 * XML can be injected &mdash; for example, using the {@code @Inject} annotation:
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;ImportResource("classpath:/com/acme/database-config.xml")
 * public class AppConfig {
 *
 *     &#064Inject DataSource dataSource; // from XML
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         // inject the XML-defined dataSource bean
 *         return new MyBean(this.dataSource);
 *     }
 * }</pre>
 *
 * <h3>With nested {@code @Configuration} classes</h3>
 *
 * <p>{@code @Configuration} classes may be nested within one another as follows:
 *
 * <pre class="code">
 * &#064;Configuration
 * public class AppConfig {
 *
 *     &#064;Inject DataSource dataSource;
 *
 *     &#064;Bean
 *     public MyBean myBean() {
 *         return new MyBean(dataSource);
 *     }
 *
 *     &#064;Configuration
 *     static class DatabaseConfig {
 *         &#064;Bean
 *         DataSource dataSource() {
 *             return new EmbeddedDatabaseBuilder().build();
 *         }
 *     }
 * }</pre>
 *
 * <p>When bootstrapping such an arrangement, only {@code AppConfig} need be registered
 * against the application context. By virtue of being a nested {@code @Configuration}
 * class, {@code DatabaseConfig} <em>will be registered automatically</em>. This avoids
 * the need to use an {@code @Import} annotation when the relationship between
 * {@code AppConfig} and {@code DatabaseConfig} is already implicitly clear.
 *
 * <p>Note also that nested {@code @Configuration} classes can be used to good effect
 * with the {@code @Profile} annotation to provide two options of the same bean to the
 * enclosing {@code @Configuration} class.
 *
 * <h2>Configuring lazy initialization</h2>
 *
 * <p>By default, {@code @Bean} methods will be <em>eagerly instantiated</em> at container
 * bootstrap time.  To avoid this, {@code @Configuration} may be used in conjunction with
 * the {@link Lazy @Lazy} annotation to indicate that all {@code @Bean} methods declared
 * within the class are by default lazily initialized. Note that {@code @Lazy} may be used
 * on individual {@code @Bean} methods as well.
 *
 * <h2>Testing support for {@code @Configuration} classes</h2>
 *
 * <p>The Spring <em>TestContext framework</em> available in the {@code spring-test} module
 * provides the {@code @ContextConfiguration} annotation which can accept an array of
 * <em>component class</em> references &mdash; typically {@code @Configuration} or
 * {@code @Component} classes.
 *
 * <pre class="code">
 * &#064;RunWith(SpringRunner.class)
 * &#064;ContextConfiguration(classes = {AppConfig.class, DatabaseConfig.class})
 * public class MyTests {
 *
 *     &#064;Autowired MyBean myBean;
 *
 *     &#064;Autowired DataSource dataSource;
 *
 *     &#064;Test
 *     public void test() {
 *         // assertions against myBean ...
 *     }
 * }</pre>
 *
 * <p>See the
 * <a href="https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#testcontext-framework">TestContext framework</a>
 * reference documentation for details.
 *
 * <h2>Enabling built-in Spring features using {@code @Enable} annotations</h2>
 *
 * <p>Spring features such as asynchronous method execution, scheduled task execution,
 * annotation driven transaction management, and even Spring MVC can be enabled and
 * configured from {@code @Configuration} classes using their respective "{@code @Enable}"
 * annotations. See
 * {@link org.springframework.scheduling.annotation.EnableAsync @EnableAsync},
 * {@link org.springframework.scheduling.annotation.EnableScheduling @EnableScheduling},
 * {@link org.springframework.transaction.annotation.EnableTransactionManagement @EnableTransactionManagement},
 * {@link org.springframework.context.annotation.EnableAspectJAutoProxy @EnableAspectJAutoProxy},
 * and {@link org.springframework.web.servlet.config.annotation.EnableWebMvc @EnableWebMvc}
 * for details.
 *
 * <h2>Constraints when authoring {@code @Configuration} classes</h2>
 *
 * <ul>
 * <li>Configuration classes must be provided as classes (i.e. not as instances returned
 * from factory methods), allowing for runtime enhancements through a generated subclass.
 * <li>Configuration classes must be non-final (allowing for subclasses at runtime),
 * unless the {@link #proxyBeanMethods() proxyBeanMethods} flag is set to {@code false}
 * in which case no runtime-generated subclass is necessary.
 * <li>Configuration classes must be non-local (i.e. may not be declared within a method).
 * <li>Any nested configuration classes must be declared as {@code static}.
 * <li>{@code @Bean} methods may not in turn create further configuration classes
 * (any such instances will be treated as regular beans, with their configuration
 * annotations remaining undetected).
 * </ul>
 *
 * @author Rod Johnson
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.0
 * @see Bean
 * @see Profile
 * @see Import
 * @see ImportResource
 * @see ComponentScan
 * @see Lazy
 * @see PropertySource
 * @see AnnotationConfigApplicationContext
 	这里有引导配置类的后置处理器
 * @see ConfigurationClassPostProcessor
 * @see org.springframework.core.env.Environment
 * @see org.springframework.test.context.ContextConfiguration
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {

	/**
	 * Explicitly specify the name of the Spring bean definition associated with the
	 * {@code @Configuration} class. If left unspecified (the common case), a bean
	 * name will be automatically generated.
	 * <p>The custom name applies only if the {@code @Configuration} class is picked
	 * up via component scanning or supplied directly to an
	 * {@link AnnotationConfigApplicationContext}. If the {@code @Configuration} class
	 * is registered as a traditional XML bean definition, the name/id of the bean
	 * element will take precedence.
	 * @return the explicit component name, if any (or empty String otherwise)
	 * @see AnnotationBeanNameGenerator
	 */
	@AliasFor(annotation = Component.class)
	String value() default "";

	/**
	 * Specify whether {@code @Bean} methods should get proxied in order to enforce
	 * bean lifecycle behavior, e.g. to return shared singleton bean instances even
	 * in case of direct {@code @Bean} method calls in user code. This feature
	 * requires method interception, implemented through a runtime-generated CGLIB
	 * subclass which comes with limitations such as the configuration class and
	 * its methods not being allowed to declare {@code final}.
	 * <p>The default is {@code true}, allowing for 'inter-bean references' within
	 * the configuration class as well as for external calls to this configuration's
	 * {@code @Bean} methods, e.g. from another configuration class. If this is not
	 * needed since each of this particular configuration's {@code @Bean} methods
	 * is self-contained and designed as a plain factory method for container use,
	 * switch this flag to {@code false} in order to avoid CGLIB subclass processing.
	 * <p>Turning off bean method interception effectively processes {@code @Bean}
	 * methods individually like when declared on non-{@code @Configuration} classes,
	 * a.k.a. "@Bean Lite Mode" (see {@link Bean @Bean's javadoc}). It is therefore
	 * behaviorally equivalent to removing the {@code @Configuration} stereotype.
	 * @since 5.2
	 */
	boolean proxyBeanMethods() default true;

}

```



可以看出从 Spring 3.0 开始我们用户用到的配置类注解是 @Configuration 这个注解，内部其实使用的 ConfigurationClass 这个类进行封装的。通常来说，

我们标注了 @Configuration 注解的类，就成为了一个配置类。但是我们 ConfigurationClass 并不一定要标注这个 @Configuration 注解。



##### ConfigurationClassPostProcessor.java 源代码：

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.parsing.FailFastProblemReporter;
import org.springframework.beans.factory.parsing.PassThroughSourceExtractor;
import org.springframework.beans.factory.parsing.ProblemReporter;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ConfigurationClassEnhancer.EnhancedConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * {@link BeanFactoryPostProcessor} used for bootstrapping processing of
 * {@link Configuration @Configuration} classes.
 *
 * <p>Registered by default when using {@code <context:annotation-config/>} or
 * {@code <context:component-scan/>}. Otherwise, may be declared manually as
 * with any other BeanFactoryPostProcessor.
 *
 * <p>This post processor is priority-ordered as it is important that any
 * {@link Bean} methods declared in {@code @Configuration} classes have
 * their corresponding bean definitions registered before any other
 * {@link BeanFactoryPostProcessor} executes.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @author Phillip Webb
 * @since 3.0
 */
public class ConfigurationClassPostProcessor implements BeanDefinitionRegistryPostProcessor,
		PriorityOrdered, ResourceLoaderAware, BeanClassLoaderAware, EnvironmentAware {

	/**
	 * A {@code BeanNameGenerator} using fully qualified class names as default bean names.
	 * <p>This default for configuration-level import purposes may be overridden through
	 * {@link #setBeanNameGenerator}. Note that the default for component scanning purposes
	 * is a plain {@link AnnotationBeanNameGenerator#INSTANCE}, unless overridden through
	 * {@link #setBeanNameGenerator} with a unified user-level bean name generator.
	 * @since 5.2
	 * @see #setBeanNameGenerator
	 */
	public static final AnnotationBeanNameGenerator IMPORT_BEAN_NAME_GENERATOR = new AnnotationBeanNameGenerator() {
		@Override
		protected String buildDefaultBeanName(BeanDefinition definition) {
			String beanClassName = definition.getBeanClassName();
			Assert.state(beanClassName != null, "No bean class name set");
			return beanClassName;
		}
	};

	private static final String IMPORT_REGISTRY_BEAN_NAME =
			ConfigurationClassPostProcessor.class.getName() + ".importRegistry";


	private final Log logger = LogFactory.getLog(getClass());

	private SourceExtractor sourceExtractor = new PassThroughSourceExtractor();

	private ProblemReporter problemReporter = new FailFastProblemReporter();

	@Nullable
	private Environment environment;

	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	@Nullable
	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

	private boolean setMetadataReaderFactoryCalled = false;

	private final Set<Integer> registriesPostProcessed = new HashSet<>();

	private final Set<Integer> factoriesPostProcessed = new HashSet<>();

	@Nullable
	private ConfigurationClassBeanDefinitionReader reader;

	private boolean localBeanNameGeneratorSet = false;

	/* Using short class names as default bean names by default. */
	private BeanNameGenerator componentScanBeanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;

	/* Using fully qualified class names as default bean names by default. */
	private BeanNameGenerator importBeanNameGenerator = IMPORT_BEAN_NAME_GENERATOR;


	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;  // within PriorityOrdered
	}

	/**
	 * Set the {@link SourceExtractor} to use for generated bean definitions
	 * that correspond to {@link Bean} factory methods.
	 */
	public void setSourceExtractor(@Nullable SourceExtractor sourceExtractor) {
		this.sourceExtractor = (sourceExtractor != null ? sourceExtractor : new PassThroughSourceExtractor());
	}

	/**
	 * Set the {@link ProblemReporter} to use.
	 * <p>Used to register any problems detected with {@link Configuration} or {@link Bean}
	 * declarations. For instance, an @Bean method marked as {@code final} is illegal
	 * and would be reported as a problem. Defaults to {@link FailFastProblemReporter}.
	 */
	public void setProblemReporter(@Nullable ProblemReporter problemReporter) {
		this.problemReporter = (problemReporter != null ? problemReporter : new FailFastProblemReporter());
	}

	/**
	 * Set the {@link MetadataReaderFactory} to use.
	 * <p>Default is a {@link CachingMetadataReaderFactory} for the specified
	 * {@linkplain #setBeanClassLoader bean class loader}.
	 */
	public void setMetadataReaderFactory(MetadataReaderFactory metadataReaderFactory) {
		Assert.notNull(metadataReaderFactory, "MetadataReaderFactory must not be null");
		this.metadataReaderFactory = metadataReaderFactory;
		this.setMetadataReaderFactoryCalled = true;
	}

	/**
	 * Set the {@link BeanNameGenerator} to be used when triggering component scanning
	 * from {@link Configuration} classes and when registering {@link Import}'ed
	 * configuration classes. The default is a standard {@link AnnotationBeanNameGenerator}
	 * for scanned components (compatible with the default in {@link ClassPathBeanDefinitionScanner})
	 * and a variant thereof for imported configuration classes (using unique fully-qualified
	 * class names instead of standard component overriding).
	 * <p>Note that this strategy does <em>not</em> apply to {@link Bean} methods.
	 * <p>This setter is typically only appropriate when configuring the post-processor as a
	 * standalone bean definition in XML, e.g. not using the dedicated {@code AnnotationConfig*}
	 * application contexts or the {@code <context:annotation-config>} element. Any bean name
	 * generator specified against the application context will take precedence over any set here.
	 * @since 3.1.1
	 * @see AnnotationConfigApplicationContext#setBeanNameGenerator(BeanNameGenerator)
	 * @see AnnotationConfigUtils#CONFIGURATION_BEAN_NAME_GENERATOR
	 */
	public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
		Assert.notNull(beanNameGenerator, "BeanNameGenerator must not be null");
		this.localBeanNameGeneratorSet = true;
		this.componentScanBeanNameGenerator = beanNameGenerator;
		this.importBeanNameGenerator = beanNameGenerator;
	}

	@Override
	public void setEnvironment(Environment environment) {
		Assert.notNull(environment, "Environment must not be null");
		this.environment = environment;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		Assert.notNull(resourceLoader, "ResourceLoader must not be null");
		this.resourceLoader = resourceLoader;
		if (!this.setMetadataReaderFactoryCalled) {
			this.metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
		}
	}

	@Override
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
		if (!this.setMetadataReaderFactoryCalled) {
			this.metadataReaderFactory = new CachingMetadataReaderFactory(beanClassLoader);
		}
	}


	/**
	 * Derive further bean definitions from the configuration classes in the registry.
	 */
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) {
		int registryId = System.identityHashCode(registry);
		if (this.registriesPostProcessed.contains(registryId)) {
			throw new IllegalStateException(
					"postProcessBeanDefinitionRegistry already called on this post-processor against " + registry);
		}
		if (this.factoriesPostProcessed.contains(registryId)) {
			throw new IllegalStateException(
					"postProcessBeanFactory already called on this post-processor against " + registry);
		}
		this.registriesPostProcessed.add(registryId);

		processConfigBeanDefinitions(registry);
	}

	/**
	 * Prepare the Configuration classes for servicing bean requests at runtime
	 * by replacing them with CGLIB-enhanced subclasses.
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		int factoryId = System.identityHashCode(beanFactory);
		if (this.factoriesPostProcessed.contains(factoryId)) {
			throw new IllegalStateException(
					"postProcessBeanFactory already called on this post-processor against " + beanFactory);
		}
		this.factoriesPostProcessed.add(factoryId);
		if (!this.registriesPostProcessed.contains(factoryId)) {
			// BeanDefinitionRegistryPostProcessor hook apparently not supported...
			// Simply call processConfigurationClasses lazily at this point then.
			processConfigBeanDefinitions((BeanDefinitionRegistry) beanFactory);
		}

		enhanceConfigurationClasses(beanFactory);
		beanFactory.addBeanPostProcessor(new ImportAwareBeanPostProcessor(beanFactory));
	}

	/**
	 * Build and validate a configuration model based on the registry of
	 * {@link Configuration} classes.
	 */
	public void processConfigBeanDefinitions(BeanDefinitionRegistry registry) {
		List<BeanDefinitionHolder> configCandidates = new ArrayList<>();
		String[] candidateNames = registry.getBeanDefinitionNames();

		for (String beanName : candidateNames) {
			BeanDefinition beanDef = registry.getBeanDefinition(beanName);
			if (beanDef.getAttribute(ConfigurationClassUtils.CONFIGURATION_CLASS_ATTRIBUTE) != null) {
				if (logger.isDebugEnabled()) {
					logger.debug("Bean definition has already been processed as a configuration class: " + beanDef);
				}
			}
			else if (ConfigurationClassUtils.checkConfigurationClassCandidate(beanDef, this.metadataReaderFactory)) {
				configCandidates.add(new BeanDefinitionHolder(beanDef, beanName));
			}
		}

		// Return immediately if no @Configuration classes were found
		if (configCandidates.isEmpty()) {
			return;
		}

		// Sort by previously determined @Order value, if applicable
		configCandidates.sort((bd1, bd2) -> {
			int i1 = ConfigurationClassUtils.getOrder(bd1.getBeanDefinition());
			int i2 = ConfigurationClassUtils.getOrder(bd2.getBeanDefinition());
			return Integer.compare(i1, i2);
		});

		// Detect any custom bean name generation strategy supplied through the enclosing application context
		SingletonBeanRegistry sbr = null;
		if (registry instanceof SingletonBeanRegistry) {
			sbr = (SingletonBeanRegistry) registry;
			if (!this.localBeanNameGeneratorSet) {
				BeanNameGenerator generator = (BeanNameGenerator) sbr.getSingleton(
						AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR);
				if (generator != null) {
					this.componentScanBeanNameGenerator = generator;
					this.importBeanNameGenerator = generator;
				}
			}
		}

		if (this.environment == null) {
			this.environment = new StandardEnvironment();
		}

		// Parse each @Configuration class
		ConfigurationClassParser parser = new ConfigurationClassParser(
				this.metadataReaderFactory, this.problemReporter, this.environment,
				this.resourceLoader, this.componentScanBeanNameGenerator, registry);

		Set<BeanDefinitionHolder> candidates = new LinkedHashSet<>(configCandidates);
		Set<ConfigurationClass> alreadyParsed = new HashSet<>(configCandidates.size());
		do {
			parser.parse(candidates);
			parser.validate();

			Set<ConfigurationClass> configClasses = new LinkedHashSet<>(parser.getConfigurationClasses());
			configClasses.removeAll(alreadyParsed);

			// Read the model and create bean definitions based on its content
			if (this.reader == null) {
				this.reader = new ConfigurationClassBeanDefinitionReader(
						registry, this.sourceExtractor, this.resourceLoader, this.environment,
						this.importBeanNameGenerator, parser.getImportRegistry());
			}
			this.reader.loadBeanDefinitions(configClasses);
			alreadyParsed.addAll(configClasses);

			candidates.clear();
			if (registry.getBeanDefinitionCount() > candidateNames.length) {
				String[] newCandidateNames = registry.getBeanDefinitionNames();
				Set<String> oldCandidateNames = new HashSet<>(Arrays.asList(candidateNames));
				Set<String> alreadyParsedClasses = new HashSet<>();
				for (ConfigurationClass configurationClass : alreadyParsed) {
					alreadyParsedClasses.add(configurationClass.getMetadata().getClassName());
				}
				for (String candidateName : newCandidateNames) {
					if (!oldCandidateNames.contains(candidateName)) {
						BeanDefinition bd = registry.getBeanDefinition(candidateName);
						if (ConfigurationClassUtils.checkConfigurationClassCandidate(bd, this.metadataReaderFactory) &&
								!alreadyParsedClasses.contains(bd.getBeanClassName())) {
							candidates.add(new BeanDefinitionHolder(bd, candidateName));
						}
					}
				}
				candidateNames = newCandidateNames;
			}
		}
		while (!candidates.isEmpty());

		// Register the ImportRegistry as a bean in order to support ImportAware @Configuration classes
		if (sbr != null && !sbr.containsSingleton(IMPORT_REGISTRY_BEAN_NAME)) {
			sbr.registerSingleton(IMPORT_REGISTRY_BEAN_NAME, parser.getImportRegistry());
		}

		if (this.metadataReaderFactory instanceof CachingMetadataReaderFactory) {
			// Clear cache in externally provided MetadataReaderFactory; this is a no-op
			// for a shared cache since it'll be cleared by the ApplicationContext.
			((CachingMetadataReaderFactory) this.metadataReaderFactory).clearCache();
		}
	}

	/**
	 * Post-processes a BeanFactory in search of Configuration class BeanDefinitions;
	 * any candidates are then enhanced by a {@link ConfigurationClassEnhancer}.
	 * Candidate status is determined by BeanDefinition attribute metadata.
	 * @see ConfigurationClassEnhancer
	 */
	public void enhanceConfigurationClasses(ConfigurableListableBeanFactory beanFactory) {
		Map<String, AbstractBeanDefinition> configBeanDefs = new LinkedHashMap<>();
		for (String beanName : beanFactory.getBeanDefinitionNames()) {
			BeanDefinition beanDef = beanFactory.getBeanDefinition(beanName);
			Object configClassAttr = beanDef.getAttribute(ConfigurationClassUtils.CONFIGURATION_CLASS_ATTRIBUTE);
			MethodMetadata methodMetadata = null;
			if (beanDef instanceof AnnotatedBeanDefinition) {
				methodMetadata = ((AnnotatedBeanDefinition) beanDef).getFactoryMethodMetadata();
			}
			if ((configClassAttr != null || methodMetadata != null) && beanDef instanceof AbstractBeanDefinition) {
				// Configuration class (full or lite) or a configuration-derived @Bean method
				// -> resolve bean class at this point...
				AbstractBeanDefinition abd = (AbstractBeanDefinition) beanDef;
				if (!abd.hasBeanClass()) {
					try {
						abd.resolveBeanClass(this.beanClassLoader);
					}
					catch (Throwable ex) {
						throw new IllegalStateException(
								"Cannot load configuration class: " + beanDef.getBeanClassName(), ex);
					}
				}
			}
			if (ConfigurationClassUtils.CONFIGURATION_CLASS_FULL.equals(configClassAttr)) {
				if (!(beanDef instanceof AbstractBeanDefinition)) {
					throw new BeanDefinitionStoreException("Cannot enhance @Configuration bean definition '" +
							beanName + "' since it is not stored in an AbstractBeanDefinition subclass");
				}
				else if (logger.isInfoEnabled() && beanFactory.containsSingleton(beanName)) {
					logger.info("Cannot enhance @Configuration bean definition '" + beanName +
							"' since its singleton instance has been created too early. The typical cause " +
							"is a non-static @Bean method with a BeanDefinitionRegistryPostProcessor " +
							"return type: Consider declaring such methods as 'static'.");
				}
				configBeanDefs.put(beanName, (AbstractBeanDefinition) beanDef);
			}
		}
		if (configBeanDefs.isEmpty()) {
			// nothing to enhance -> return immediately
			return;
		}

		ConfigurationClassEnhancer enhancer = new ConfigurationClassEnhancer();
		for (Map.Entry<String, AbstractBeanDefinition> entry : configBeanDefs.entrySet()) {
			AbstractBeanDefinition beanDef = entry.getValue();
			// If a @Configuration class gets proxied, always proxy the target class
			beanDef.setAttribute(AutoProxyUtils.PRESERVE_TARGET_CLASS_ATTRIBUTE, Boolean.TRUE);
			// Set enhanced subclass of the user-specified bean class
			Class<?> configClass = beanDef.getBeanClass();
			Class<?> enhancedClass = enhancer.enhance(configClass, this.beanClassLoader);
			if (configClass != enhancedClass) {
				if (logger.isTraceEnabled()) {
					logger.trace(String.format("Replacing bean definition '%s' existing class '%s' with " +
							"enhanced class '%s'", entry.getKey(), configClass.getName(), enhancedClass.getName()));
				}
				beanDef.setBeanClass(enhancedClass);
			}
		}
	}


	private static class ImportAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

		private final BeanFactory beanFactory;

		public ImportAwareBeanPostProcessor(BeanFactory beanFactory) {
			this.beanFactory = beanFactory;
		}

		@Override
		public PropertyValues postProcessProperties(@Nullable PropertyValues pvs, Object bean, String beanName) {
			// Inject the BeanFactory before AutowiredAnnotationBeanPostProcessor's
			// postProcessProperties method attempts to autowire other configuration beans.
			if (bean instanceof EnhancedConfiguration) {
				((EnhancedConfiguration) bean).setBeanFactory(this.beanFactory);
			}
			return pvs;
		}

		@Override
		public Object postProcessBeforeInitialization(Object bean, String beanName) {
			if (bean instanceof ImportAware) {
				ImportRegistry ir = this.beanFactory.getBean(IMPORT_REGISTRY_BEAN_NAME, ImportRegistry.class);
				AnnotationMetadata importingClass = ir.getImportingClassFor(ClassUtils.getUserClass(bean).getName());
				if (importingClass != null) {
					((ImportAware) bean).setImportMetadata(importingClass);
				}
			}
			return bean;
		}
	}

}

```

这里临时先有个印象，后面会细说。这里简要说一点启发性的东西。这个 ConfigurationClassPostProcessor 类实现了 BeanDefinitionRegistryPostProcessor，

这个 BeanDefinitionRegistryPostProcessor 其实就是扩展了我们 BeanFactoryPostProcessor 的实现。



##### BeanDefinitionPostProcessor.java 源码：

```java
/*
 * Copyright 2002-2010 the original author or authors.
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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * Extension to the standard {@link BeanFactoryPostProcessor} SPI, allowing for
 * the registration of further bean definitions <i>before</i> regular
 * BeanFactoryPostProcessor detection kicks in. In particular,
 * BeanDefinitionRegistryPostProcessor may register further bean definitions
 * which in turn define BeanFactoryPostProcessor instances.
 *
 * @author Juergen Hoeller
 * @since 3.0.1
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
 */
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {

	/**
	 * Modify the application context's internal bean definition registry after its
	 * standard initialization. All regular bean definitions will have been loaded,
	 * but no beans will have been instantiated yet. This allows for adding further
	 * bean definitions before the next post-processing phase kicks in.
	 * @param registry the bean definition registry used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException;

}

```



##### BeanFactoryPostProcessor.java 源码：

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

import org.springframework.beans.BeansException;

/**
 * Factory hook that allows for custom modification of an application context's
 * bean definitions, adapting the bean property values of the context's underlying
 * bean factory.
 *
 * <p>Useful for custom config files targeted at system administrators that
 * override bean properties configured in the application context. See
 * {@link PropertyResourceConfigurer} and its concrete implementations for
 * out-of-the-box solutions that address such configuration needs.
 *
 * <p>A {@code BeanFactoryPostProcessor} may interact with and modify bean
 * definitions, but never bean instances. Doing so may cause premature bean
 * instantiation, violating the container and causing unintended side-effects.
 * If bean instance interaction is required, consider implementing
 * {@link BeanPostProcessor} instead.
 *
 * <h3>Registration</h3>
 * <p>An {@code ApplicationContext} auto-detects {@code BeanFactoryPostProcessor}
 * beans in its bean definitions and applies them before any other beans get created.
 * A {@code BeanFactoryPostProcessor} may also be registered programmatically
 * with a {@code ConfigurableApplicationContext}.
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanFactoryPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanFactoryPostProcessor} beans that are registered programmatically
 * with a {@code ConfigurableApplicationContext} will be applied in the order of
 * registration; any ordering semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanFactoryPostProcessor} beans.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 06.07.2003
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}

```



可以简单看出 ConfigurationClassPostProcessor 可以说是一个 Bean 或者说是 Spring IoC 容器生命周期的回调。这里就是为了处理我们的一些生命周期逻辑，

但是实现是真的复杂。。。后面有机会深入说一下。



AutowiredAnnotationBeanPostProcessor 基本上就是一个生命周期的回调，或者说是后置处理。他处理了两个东西，一个是 @Autowired @Value

```java
/**
	 * Create a new {@code AutowiredAnnotationBeanPostProcessor} for Spring's
	 * standard {@link Autowired @Autowired} and {@link Value @Value} annotations.
	 * <p>Also supports JSR-330's {@link javax.inject.Inject @Inject} annotation,
	 * if available.
	 */
	@SuppressWarnings("unchecked")
	public AutowiredAnnotationBeanPostProcessor() {
		this.autowiredAnnotationTypes.add(Autowired.class);
		this.autowiredAnnotationTypes.add(Value.class);
		try {
			this.autowiredAnnotationTypes.add((Class<? extends Annotation>)
					ClassUtils.forName("javax.inject.Inject", AutowiredAnnotationBeanPostProcessor.class.getClassLoader()));
			logger.trace("JSR-330 'javax.inject.Inject' annotation found and supported for autowiring");
		}
		catch (ClassNotFoundException ex) {
			// JSR-330 API not available - simply skip.
		}
	}
```

意思就是注入的对象可能是一个 Spring Bean 也可能是一个可依赖的对象，再者就是外部化配置的属性。例如：@Value 就是一个外部化配置属性的一个注入。

当然 @Value 里面暗含了一些**属性转换**的能力。

根据 **AutowiredAnnotationPostProcessor** 的继承关系，我们可以看出他就是一个普通的 BeanPostProcessor ，继承了

**InstantiationAwareBeanPostProcessorAdaptor** 这个类，这是一个适配器，同时他又是一个 BeanPostProcessor 。



##### InstantiationAwareBeanPostProcessorAdaptor.java 源码：

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

package org.springframework.beans.factory.config;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.lang.Nullable;

/**
 * Adapter that implements all methods on {@link SmartInstantiationAwareBeanPostProcessor}
 * as no-ops, which will not change normal processing of each bean instantiated
 * by the container. Subclasses may override merely those methods that they are
 * actually interested in.
 *
 * <p>Note that this base class is only recommendable if you actually require
 * {@link InstantiationAwareBeanPostProcessor} functionality. If all you need
 * is plain {@link BeanPostProcessor} functionality, prefer a straight
 * implementation of that (simpler) interface.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 2.0
 */
public abstract class InstantiationAwareBeanPostProcessorAdapter implements SmartInstantiationAwareBeanPostProcessor {

	@Override
	@Nullable
	public Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	@Override
	@Nullable
	public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	@Nullable
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {

		return null;
	}

	@Deprecated
	@Override
	public PropertyValues postProcessPropertyValues(
			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

		return pvs;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}

```



可以看出 InstantiationPostBeanProcessorAdaptor 是一个 抽象类，里面实现了 SmartInstantiationAwareBeanPostProcessor 这个接口。



##### SmartInstantiationAwareBeanPostProcessor.java 源码：

```java
/*
 * Copyright 2002-2016 the original author or authors.
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

import java.lang.reflect.Constructor;

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * Extension of the {@link InstantiationAwareBeanPostProcessor} interface,
 * adding a callback for predicting the eventual type of a processed bean.
 *
 * <p><b>NOTE:</b> This interface is a special purpose interface, mainly for
 * internal use within the framework. In general, application-provided
 * post-processors should simply implement the plain {@link BeanPostProcessor}
 * interface or derive from the {@link InstantiationAwareBeanPostProcessorAdapter}
 * class. New methods might be added to this interface even in point releases.
 *
 * @author Juergen Hoeller
 * @since 2.0.3
 * @see InstantiationAwareBeanPostProcessorAdapter
 */
public interface SmartInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor {

	/**
	 * Predict the type of the bean to be eventually returned from this
	 * processor's {@link #postProcessBeforeInstantiation} callback.
	 * <p>The default implementation returns {@code null}.
	 * @param beanClass the raw class of the bean
	 * @param beanName the name of the bean
	 * @return the type of the bean, or {@code null} if not predictable
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	@Nullable
	default Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	/**
	 * Determine the candidate constructors to use for the given bean.
	 * <p>The default implementation returns {@code null}.
	 * @param beanClass the raw class of the bean (never {@code null})
	 * @param beanName the name of the bean
	 * @return the candidate constructors, or {@code null} if none specified
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	@Nullable
	default Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName)
			throws BeansException {

		return null;
	}

	/**
	 * Obtain a reference for early access to the specified bean,
	 * typically for the purpose of resolving a circular reference.
	 * <p>This callback gives post-processors a chance to expose a wrapper
	 * early - that is, before the target bean instance is fully initialized.
	 * The exposed object should be equivalent to the what
	 * {@link #postProcessBeforeInitialization} / {@link #postProcessAfterInitialization}
	 * would expose otherwise. Note that the object returned by this method will
	 * be used as bean reference unless the post-processor returns a different
	 * wrapper from said post-process callbacks. In other words: Those post-process
	 * callbacks may either eventually expose the same reference or alternatively
	 * return the raw bean instance from those subsequent callbacks (if the wrapper
	 * for the affected bean has been built for a call to this method already,
	 * it will be exposes as final bean reference by default).
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the raw bean instance
	 * @param beanName the name of the bean
	 * @return the object to expose as bean reference
	 * (typically with the passed-in bean instance as default)
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	default Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		return bean;
	}

}

```



SmartInstantiationAwreaBeanPostProcessor 接口又继承了 InstantiationAwreaBeanPostProcessor 这个接口



##### InstantiationAwraeBeanPostProcessor.java 源码：

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

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.lang.Nullable;

/**
 * Subinterface of {@link BeanPostProcessor} that adds a before-instantiation callback,
 * and a callback after instantiation but before explicit properties are set or
 * autowiring occurs.
 *
 * <p>Typically used to suppress default instantiation for specific target beans,
 * for example to create proxies with special TargetSources (pooling targets,
 * lazily initializing targets, etc), or to implement additional injection strategies
 * such as field injection.
 *
 * <p><b>NOTE:</b> This interface is a special purpose interface, mainly for
 * internal use within the framework. It is recommended to implement the plain
 * {@link BeanPostProcessor} interface as far as possible, or to derive from
 * {@link InstantiationAwareBeanPostProcessorAdapter} in order to be shielded
 * from extensions to this interface.
 *
 * @author Juergen Hoeller
 * @author Rod Johnson
 * @since 1.2
 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#setCustomTargetSourceCreators
 * @see org.springframework.aop.framework.autoproxy.target.LazyInitTargetSourceCreator
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

	/**
	 * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
	 * The returned bean object may be a proxy to use instead of the target bean,
	 * effectively suppressing default instantiation of the target bean.
	 * <p>If a non-null object is returned by this method, the bean creation process
	 * will be short-circuited. The only further processing applied is the
	 * {@link #postProcessAfterInitialization} callback from the configured
	 * {@link BeanPostProcessor BeanPostProcessors}.
	 * <p>This callback will be applied to bean definitions with their bean class,
	 * as well as to factory-method definitions in which case the returned bean type
	 * will be passed in here.
	 * <p>Post-processors may implement the extended
	 * {@link SmartInstantiationAwareBeanPostProcessor} interface in order
	 * to predict the type of the bean object that they are going to return here.
	 * <p>The default implementation returns {@code null}.
	 * @param beanClass the class of the bean to be instantiated
	 * @param beanName the name of the bean
	 * @return the bean object to expose instead of a default instance of the target bean,
	 * or {@code null} to proceed with default instantiation
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see #postProcessAfterInstantiation
	 * @see org.springframework.beans.factory.support.AbstractBeanDefinition#getBeanClass()
	 * @see org.springframework.beans.factory.support.AbstractBeanDefinition#getFactoryMethodName()
	 */
	@Nullable
	default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	/**
	 * Perform operations after the bean has been instantiated, via a constructor or factory method,
	 * but before Spring property population (from explicit properties or autowiring) occurs.
	 * <p>This is the ideal callback for performing custom field injection on the given bean
	 * instance, right before Spring's autowiring kicks in.
	 * <p>The default implementation returns {@code true}.
	 * @param bean the bean instance created, with properties not having been set yet
	 * @param beanName the name of the bean
	 * @return {@code true} if properties should be set on the bean; {@code false}
	 * if property population should be skipped. Normal implementations should return {@code true}.
	 * Returning {@code false} will also prevent any subsequent InstantiationAwareBeanPostProcessor
	 * instances being invoked on this bean instance.
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see #postProcessBeforeInstantiation
	 */
	default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		return true;
	}

	/**
	 * Post-process the given property values before the factory applies them
	 * to the given bean, without any need for property descriptors.
	 * <p>Implementations should return {@code null} (the default) if they provide a custom
	 * {@link #postProcessPropertyValues} implementation, and {@code pvs} otherwise.
	 * In a future version of this interface (with {@link #postProcessPropertyValues} removed),
	 * the default implementation will return the given {@code pvs} as-is directly.
	 * @param pvs the property values that the factory is about to apply (never {@code null})
	 * @param bean the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return the actual property values to apply to the given bean (can be the passed-in
	 * PropertyValues instance), or {@code null} which proceeds with the existing properties
	 * but specifically continues with a call to {@link #postProcessPropertyValues}
	 * (requiring initialized {@code PropertyDescriptor}s for the current bean class)
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @since 5.1
	 * @see #postProcessPropertyValues
	 */
	@Nullable
	default PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {

		return null;
	}

	/**
	 * Post-process the given property values before the factory applies them
	 * to the given bean. Allows for checking whether all dependencies have been
	 * satisfied, for example based on a "Required" annotation on bean property setters.
	 * <p>Also allows for replacing the property values to apply, typically through
	 * creating a new MutablePropertyValues instance based on the original PropertyValues,
	 * adding or removing specific values.
	 * <p>The default implementation returns the given {@code pvs} as-is.
	 * @param pvs the property values that the factory is about to apply (never {@code null})
	 * @param pds the relevant property descriptors for the target bean (with ignored
	 * dependency types - which the factory handles specifically - already filtered out)
	 * @param bean the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return the actual property values to apply to the given bean (can be the passed-in
	 * PropertyValues instance), or {@code null} to skip property population
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see #postProcessProperties
	 * @see org.springframework.beans.MutablePropertyValues
	 * @deprecated as of 5.1, in favor of {@link #postProcessProperties(PropertyValues, Object, String)}
	 */
	@Deprecated
	@Nullable
	default PropertyValues postProcessPropertyValues(
			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

		return pvs;
	}

}

```



InstantiationAwreaBeanPostProcessor 这个接口又继承了 BeanPostProcessor。



##### BeanPostProcssor.java 源码：

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

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * Factory hook that allows for custom modification of new bean instances &mdash;
 * for example, checking for marker interfaces or wrapping beans with proxies.
 *
 * <p>Typically, post-processors that populate beans via marker interfaces
 * or the like will implement {@link #postProcessBeforeInitialization},
 * while post-processors that wrap beans with proxies will normally
 * implement {@link #postProcessAfterInitialization}.
 *
 * <h3>Registration</h3>
 * <p>An {@code ApplicationContext} can autodetect {@code BeanPostProcessor} beans
 * in its bean definitions and apply those post-processors to any beans subsequently
 * created. A plain {@code BeanFactory} allows for programmatic registration of
 * post-processors, applying them to all beans created through the bean factory.
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanPostProcessor} beans that are registered programmatically with a
 * {@code BeanFactory} will be applied in the order of registration; any ordering
 * semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanPostProcessor} beans.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 10.10.2003
 * @see InstantiationAwareBeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see ConfigurableBeanFactory#addBeanPostProcessor
 * @see BeanFactoryPostProcessor
 */
public interface BeanPostProcessor {

	/**
	 * Apply this {@code BeanPostProcessor} to the given new bean instance <i>before</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 */
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * Apply this {@code BeanPostProcessor} to the given new bean instance <i>after</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>In case of a FactoryBean, this callback will be invoked for both the FactoryBean
	 * instance and the objects created by the FactoryBean (as of Spring 2.0). The
	 * post-processor can decide whether to apply to either the FactoryBean or created
	 * objects or both through corresponding {@code bean instanceof FactoryBean} checks.
	 * <p>This callback will also be invoked after a short-circuiting triggered by a
	 * {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation} method,
	 * in contrast to all other {@code BeanPostProcessor} callbacks.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 * @see org.springframework.beans.factory.FactoryBean
	 */
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}

```



因此我们可以得出结论：InstantiationAweraBeanPostProcessorAdaptor 他就是一个 BeanPostProcessor 的接口实现。

问：那么 InstantiationAwreaBeanPostProcessorAdaptor 为什么要用这个适配器的方式？

答：因为有些代码不需要进行完全的实现，按需实现就好了。。。





CommonAnnotationBeanPostProcessor 也是一个 Bean 的后置处理，CommonAnnotation 代表的是需要**有条件激活**的。为什么会有条件激活？后面再说。

CommonAnnotationBeanPostProcessor 存在的意义是为了 处理 JSR-250 这类的注解。我们之前用到过 @PostConstract、@Destory 这类的注解对初始化的

属性或者对象进行一系列初始化之前做什么事情，初始化之后做什么事情。



##### CommonAnnotationBeanPostProcessor 源码：

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
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceRef;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

/**
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} implementation
 * that supports common Java annotations out of the box, in particular the JSR-250
 * annotations in the {@code javax.annotation} package. These common Java
 * annotations are supported in many Java EE 5 technologies (e.g. JSF 1.2),
 * as well as in Java 6's JAX-WS.
 *
 * <p>This post-processor includes support for the {@link javax.annotation.PostConstruct}
 * and {@link javax.annotation.PreDestroy} annotations - as init annotation
 * and destroy annotation, respectively - through inheriting from
 * {@link InitDestroyAnnotationBeanPostProcessor} with pre-configured annotation types.
 *
 * <p>The central element is the {@link javax.annotation.Resource} annotation
 * for annotation-driven injection of named beans, by default from the containing
 * Spring BeanFactory, with only {@code mappedName} references resolved in JNDI.
 * The {@link #setAlwaysUseJndiLookup "alwaysUseJndiLookup" flag} enforces JNDI lookups
 * equivalent to standard Java EE 5 resource injection for {@code name} references
 * and default names as well. The target beans can be simple POJOs, with no special
 * requirements other than the type having to match.
 *
 * <p>The JAX-WS {@link javax.xml.ws.WebServiceRef} annotation is supported too,
 * analogous to {@link javax.annotation.Resource} but with the capability of creating
 * specific JAX-WS service endpoints. This may either point to an explicitly defined
 * resource by name or operate on a locally specified JAX-WS service class. Finally,
 * this post-processor also supports the EJB 3 {@link javax.ejb.EJB} annotation,
 * analogous to {@link javax.annotation.Resource} as well, with the capability to
 * specify both a local bean name and a global JNDI name for fallback retrieval.
 * The target beans can be plain POJOs as well as EJB 3 Session Beans in this case.
 *
 * <p>The common annotations supported by this post-processor are available in
 * Java 6 (JDK 1.6) as well as in Java EE 5/6 (which provides a standalone jar for
 * its common annotations as well, allowing for use in any Java 5 based application).
 *
 * <p>For default usage, resolving resource names as Spring bean names,
 * simply define the following in your application context:
 *
 * <pre class="code">
 * &lt;bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"/&gt;</pre>
 *
 * For direct JNDI access, resolving resource names as JNDI resource references
 * within the Java EE application's "java:comp/env/" namespace, use the following:
 *
 * <pre class="code">
 * &lt;bean class="org.springframework.context.annotation.CommonAnnotationBeanPostProcessor"&gt;
 *   &lt;property name="alwaysUseJndiLookup" value="true"/&gt;
 * &lt;/bean&gt;</pre>
 *
 * {@code mappedName} references will always be resolved in JNDI,
 * allowing for global JNDI names (including "java:" prefix) as well. The
 * "alwaysUseJndiLookup" flag just affects {@code name} references and
 * default names (inferred from the field name / property name).
 *
 * <p><b>NOTE:</b> A default CommonAnnotationBeanPostProcessor will be registered
 * by the "context:annotation-config" and "context:component-scan" XML tags.
 * Remove or turn off the default annotation configuration there if you intend
 * to specify a custom CommonAnnotationBeanPostProcessor bean definition!
 * <p><b>NOTE:</b> Annotation injection will be performed <i>before</i> XML injection; thus
 * the latter configuration will override the former for properties wired through
 * both approaches.
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see #setAlwaysUseJndiLookup
 * @see #setResourceFactory
 * @see org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 */
@SuppressWarnings("serial")
public class CommonAnnotationBeanPostProcessor extends InitDestroyAnnotationBeanPostProcessor
		implements InstantiationAwareBeanPostProcessor, BeanFactoryAware, Serializable {

	@Nullable
	private static Class<? extends Annotation> webServiceRefClass;

	@Nullable
	private static Class<? extends Annotation> ejbRefClass;

	private static Set<Class<? extends Annotation>> resourceAnnotationTypes = new LinkedHashSet<>(4);

	static {
		try {
			@SuppressWarnings("unchecked")
			Class<? extends Annotation> clazz = (Class<? extends Annotation>)
					ClassUtils.forName("javax.xml.ws.WebServiceRef", CommonAnnotationBeanPostProcessor.class.getClassLoader());
			webServiceRefClass = clazz;
		}
		catch (ClassNotFoundException ex) {
			webServiceRefClass = null;
		}

		try {
			@SuppressWarnings("unchecked")
			Class<? extends Annotation> clazz = (Class<? extends Annotation>)
					ClassUtils.forName("javax.ejb.EJB", CommonAnnotationBeanPostProcessor.class.getClassLoader());
			ejbRefClass = clazz;
		}
		catch (ClassNotFoundException ex) {
			ejbRefClass = null;
		}

		resourceAnnotationTypes.add(Resource.class);
		if (webServiceRefClass != null) {
			resourceAnnotationTypes.add(webServiceRefClass);
		}
		if (ejbRefClass != null) {
			resourceAnnotationTypes.add(ejbRefClass);
		}
	}


	private final Set<String> ignoredResourceTypes = new HashSet<>(1);

	private boolean fallbackToDefaultTypeMatch = true;

	private boolean alwaysUseJndiLookup = false;

	private transient BeanFactory jndiFactory = new SimpleJndiBeanFactory();

	@Nullable
	private transient BeanFactory resourceFactory;

	@Nullable
	private transient BeanFactory beanFactory;

	@Nullable
	private transient StringValueResolver embeddedValueResolver;

	private final transient Map<String, InjectionMetadata> injectionMetadataCache = new ConcurrentHashMap<>(256);


	/**
	 * Create a new CommonAnnotationBeanPostProcessor,
	 * with the init and destroy annotation types set to
	 * {@link javax.annotation.PostConstruct} and {@link javax.annotation.PreDestroy},
	 * respectively.
	 */
	public CommonAnnotationBeanPostProcessor() {
		setOrder(Ordered.LOWEST_PRECEDENCE - 3);
		setInitAnnotationType(PostConstruct.class);
		setDestroyAnnotationType(PreDestroy.class);
		ignoreResourceType("javax.xml.ws.WebServiceContext");
	}


	/**
	 * Ignore the given resource type when resolving {@code @Resource}
	 * annotations.
	 * <p>By default, the {@code javax.xml.ws.WebServiceContext} interface
	 * will be ignored, since it will be resolved by the JAX-WS runtime.
	 * @param resourceType the resource type to ignore
	 */
	public void ignoreResourceType(String resourceType) {
		Assert.notNull(resourceType, "Ignored resource type must not be null");
		this.ignoredResourceTypes.add(resourceType);
	}

	/**
	 * Set whether to allow a fallback to a type match if no explicit name has been
	 * specified. The default name (i.e. the field name or bean property name) will
	 * still be checked first; if a bean of that name exists, it will be taken.
	 * However, if no bean of that name exists, a by-type resolution of the
	 * dependency will be attempted if this flag is "true".
	 * <p>Default is "true". Switch this flag to "false" in order to enforce a
	 * by-name lookup in all cases, throwing an exception in case of no name match.
	 * @see org.springframework.beans.factory.config.AutowireCapableBeanFactory#resolveDependency
	 */
	public void setFallbackToDefaultTypeMatch(boolean fallbackToDefaultTypeMatch) {
		this.fallbackToDefaultTypeMatch = fallbackToDefaultTypeMatch;
	}

	/**
	 * Set whether to always use JNDI lookups equivalent to standard Java EE 5 resource
	 * injection, <b>even for {@code name} attributes and default names</b>.
	 * <p>Default is "false": Resource names are used for Spring bean lookups in the
	 * containing BeanFactory; only {@code mappedName} attributes point directly
	 * into JNDI. Switch this flag to "true" for enforcing Java EE style JNDI lookups
	 * in any case, even for {@code name} attributes and default names.
	 * @see #setJndiFactory
	 * @see #setResourceFactory
	 */
	public void setAlwaysUseJndiLookup(boolean alwaysUseJndiLookup) {
		this.alwaysUseJndiLookup = alwaysUseJndiLookup;
	}

	/**
	 * Specify the factory for objects to be injected into {@code @Resource} /
	 * {@code @WebServiceRef} / {@code @EJB} annotated fields and setter methods,
	 * <b>for {@code mappedName} attributes that point directly into JNDI</b>.
	 * This factory will also be used if "alwaysUseJndiLookup" is set to "true" in order
	 * to enforce JNDI lookups even for {@code name} attributes and default names.
	 * <p>The default is a {@link org.springframework.jndi.support.SimpleJndiBeanFactory}
	 * for JNDI lookup behavior equivalent to standard Java EE 5 resource injection.
	 * @see #setResourceFactory
	 * @see #setAlwaysUseJndiLookup
	 */
	public void setJndiFactory(BeanFactory jndiFactory) {
		Assert.notNull(jndiFactory, "BeanFactory must not be null");
		this.jndiFactory = jndiFactory;
	}

	/**
	 * Specify the factory for objects to be injected into {@code @Resource} /
	 * {@code @WebServiceRef} / {@code @EJB} annotated fields and setter methods,
	 * <b>for {@code name} attributes and default names</b>.
	 * <p>The default is the BeanFactory that this post-processor is defined in,
	 * if any, looking up resource names as Spring bean names. Specify the resource
	 * factory explicitly for programmatic usage of this post-processor.
	 * <p>Specifying Spring's {@link org.springframework.jndi.support.SimpleJndiBeanFactory}
	 * leads to JNDI lookup behavior equivalent to standard Java EE 5 resource injection,
	 * even for {@code name} attributes and default names. This is the same behavior
	 * that the "alwaysUseJndiLookup" flag enables.
	 * @see #setAlwaysUseJndiLookup
	 */
	public void setResourceFactory(BeanFactory resourceFactory) {
		Assert.notNull(resourceFactory, "BeanFactory must not be null");
		this.resourceFactory = resourceFactory;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		Assert.notNull(beanFactory, "BeanFactory must not be null");
		this.beanFactory = beanFactory;
		if (this.resourceFactory == null) {
			this.resourceFactory = beanFactory;
		}
		if (beanFactory instanceof ConfigurableBeanFactory) {
			this.embeddedValueResolver = new EmbeddedValueResolver((ConfigurableBeanFactory) beanFactory);
		}
	}


	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		super.postProcessMergedBeanDefinition(beanDefinition, beanType, beanName);
		InjectionMetadata metadata = findResourceMetadata(beanName, beanType, null);
		metadata.checkConfigMembers(beanDefinition);
	}

	@Override
	public void resetBeanDefinition(String beanName) {
		this.injectionMetadataCache.remove(beanName);
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) {
		return true;
	}

	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) {
		InjectionMetadata metadata = findResourceMetadata(beanName, bean.getClass(), pvs);
		try {
			metadata.inject(bean, beanName, pvs);
		}
		catch (Throwable ex) {
			throw new BeanCreationException(beanName, "Injection of resource dependencies failed", ex);
		}
		return pvs;
	}

	@Deprecated
	@Override
	public PropertyValues postProcessPropertyValues(
			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) {

		return postProcessProperties(pvs, bean, beanName);
	}


	private InjectionMetadata findResourceMetadata(String beanName, final Class<?> clazz, @Nullable PropertyValues pvs) {
		// Fall back to class name as cache key, for backwards compatibility with custom callers.
		String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
		// Quick check on the concurrent map first, with minimal locking.
		InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
		if (InjectionMetadata.needsRefresh(metadata, clazz)) {
			synchronized (this.injectionMetadataCache) {
				metadata = this.injectionMetadataCache.get(cacheKey);
				if (InjectionMetadata.needsRefresh(metadata, clazz)) {
					if (metadata != null) {
						metadata.clear(pvs);
					}
					metadata = buildResourceMetadata(clazz);
					this.injectionMetadataCache.put(cacheKey, metadata);
				}
			}
		}
		return metadata;
	}

	private InjectionMetadata buildResourceMetadata(final Class<?> clazz) {
		if (!AnnotationUtils.isCandidateClass(clazz, resourceAnnotationTypes)) {
			return InjectionMetadata.EMPTY;
		}

		List<InjectionMetadata.InjectedElement> elements = new ArrayList<>();
		Class<?> targetClass = clazz;

		do {
			final List<InjectionMetadata.InjectedElement> currElements = new ArrayList<>();

			ReflectionUtils.doWithLocalFields(targetClass, field -> {
				if (webServiceRefClass != null && field.isAnnotationPresent(webServiceRefClass)) {
					if (Modifier.isStatic(field.getModifiers())) {
						throw new IllegalStateException("@WebServiceRef annotation is not supported on static fields");
					}
					currElements.add(new WebServiceRefElement(field, field, null));
				}
				else if (ejbRefClass != null && field.isAnnotationPresent(ejbRefClass)) {
					if (Modifier.isStatic(field.getModifiers())) {
						throw new IllegalStateException("@EJB annotation is not supported on static fields");
					}
					currElements.add(new EjbRefElement(field, field, null));
				}
				else if (field.isAnnotationPresent(Resource.class)) {
					if (Modifier.isStatic(field.getModifiers())) {
						throw new IllegalStateException("@Resource annotation is not supported on static fields");
					}
					if (!this.ignoredResourceTypes.contains(field.getType().getName())) {
						currElements.add(new ResourceElement(field, field, null));
					}
				}
			});

			ReflectionUtils.doWithLocalMethods(targetClass, method -> {
				Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(method);
				if (!BridgeMethodResolver.isVisibilityBridgeMethodPair(method, bridgedMethod)) {
					return;
				}
				if (method.equals(ClassUtils.getMostSpecificMethod(method, clazz))) {
					if (webServiceRefClass != null && bridgedMethod.isAnnotationPresent(webServiceRefClass)) {
						if (Modifier.isStatic(method.getModifiers())) {
							throw new IllegalStateException("@WebServiceRef annotation is not supported on static methods");
						}
						if (method.getParameterCount() != 1) {
							throw new IllegalStateException("@WebServiceRef annotation requires a single-arg method: " + method);
						}
						PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, clazz);
						currElements.add(new WebServiceRefElement(method, bridgedMethod, pd));
					}
					else if (ejbRefClass != null && bridgedMethod.isAnnotationPresent(ejbRefClass)) {
						if (Modifier.isStatic(method.getModifiers())) {
							throw new IllegalStateException("@EJB annotation is not supported on static methods");
						}
						if (method.getParameterCount() != 1) {
							throw new IllegalStateException("@EJB annotation requires a single-arg method: " + method);
						}
						PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, clazz);
						currElements.add(new EjbRefElement(method, bridgedMethod, pd));
					}
					else if (bridgedMethod.isAnnotationPresent(Resource.class)) {
						if (Modifier.isStatic(method.getModifiers())) {
							throw new IllegalStateException("@Resource annotation is not supported on static methods");
						}
						Class<?>[] paramTypes = method.getParameterTypes();
						if (paramTypes.length != 1) {
							throw new IllegalStateException("@Resource annotation requires a single-arg method: " + method);
						}
						if (!this.ignoredResourceTypes.contains(paramTypes[0].getName())) {
							PropertyDescriptor pd = BeanUtils.findPropertyForMethod(bridgedMethod, clazz);
							currElements.add(new ResourceElement(method, bridgedMethod, pd));
						}
					}
				}
			});

			elements.addAll(0, currElements);
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);

		return InjectionMetadata.forElements(elements, clazz);
	}

	/**
	 * Obtain a lazily resolving resource proxy for the given name and type,
	 * delegating to {@link #getResource} on demand once a method call comes in.
	 * @param element the descriptor for the annotated field/method
	 * @param requestingBeanName the name of the requesting bean
	 * @return the resource object (never {@code null})
	 * @since 4.2
	 * @see #getResource
	 * @see Lazy
	 */
	protected Object buildLazyResourceProxy(final LookupElement element, final @Nullable String requestingBeanName) {
		TargetSource ts = new TargetSource() {
			@Override
			public Class<?> getTargetClass() {
				return element.lookupType;
			}
			@Override
			public boolean isStatic() {
				return false;
			}
			@Override
			public Object getTarget() {
				return getResource(element, requestingBeanName);
			}
			@Override
			public void releaseTarget(Object target) {
			}
		};
		ProxyFactory pf = new ProxyFactory();
		pf.setTargetSource(ts);
		if (element.lookupType.isInterface()) {
			pf.addInterface(element.lookupType);
		}
		ClassLoader classLoader = (this.beanFactory instanceof ConfigurableBeanFactory ?
				((ConfigurableBeanFactory) this.beanFactory).getBeanClassLoader() : null);
		return pf.getProxy(classLoader);
	}

	/**
	 * Obtain the resource object for the given name and type.
	 * @param element the descriptor for the annotated field/method
	 * @param requestingBeanName the name of the requesting bean
	 * @return the resource object (never {@code null})
	 * @throws NoSuchBeanDefinitionException if no corresponding target resource found
	 */
	protected Object getResource(LookupElement element, @Nullable String requestingBeanName)
			throws NoSuchBeanDefinitionException {

		if (StringUtils.hasLength(element.mappedName)) {
			return this.jndiFactory.getBean(element.mappedName, element.lookupType);
		}
		if (this.alwaysUseJndiLookup) {
			return this.jndiFactory.getBean(element.name, element.lookupType);
		}
		if (this.resourceFactory == null) {
			throw new NoSuchBeanDefinitionException(element.lookupType,
					"No resource factory configured - specify the 'resourceFactory' property");
		}
		return autowireResource(this.resourceFactory, element, requestingBeanName);
	}

	/**
	 * Obtain a resource object for the given name and type through autowiring
	 * based on the given factory.
	 * @param factory the factory to autowire against
	 * @param element the descriptor for the annotated field/method
	 * @param requestingBeanName the name of the requesting bean
	 * @return the resource object (never {@code null})
	 * @throws NoSuchBeanDefinitionException if no corresponding target resource found
	 */
	protected Object autowireResource(BeanFactory factory, LookupElement element, @Nullable String requestingBeanName)
			throws NoSuchBeanDefinitionException {

		Object resource;
		Set<String> autowiredBeanNames;
		String name = element.name;

		if (factory instanceof AutowireCapableBeanFactory) {
			AutowireCapableBeanFactory beanFactory = (AutowireCapableBeanFactory) factory;
			DependencyDescriptor descriptor = element.getDependencyDescriptor();
			if (this.fallbackToDefaultTypeMatch && element.isDefaultName && !factory.containsBean(name)) {
				autowiredBeanNames = new LinkedHashSet<>();
				resource = beanFactory.resolveDependency(descriptor, requestingBeanName, autowiredBeanNames, null);
				if (resource == null) {
					throw new NoSuchBeanDefinitionException(element.getLookupType(), "No resolvable resource object");
				}
			}
			else {
				resource = beanFactory.resolveBeanByName(name, descriptor);
				autowiredBeanNames = Collections.singleton(name);
			}
		}
		else {
			resource = factory.getBean(name, element.lookupType);
			autowiredBeanNames = Collections.singleton(name);
		}

		if (factory instanceof ConfigurableBeanFactory) {
			ConfigurableBeanFactory beanFactory = (ConfigurableBeanFactory) factory;
			for (String autowiredBeanName : autowiredBeanNames) {
				if (requestingBeanName != null && beanFactory.containsBean(autowiredBeanName)) {
					beanFactory.registerDependentBean(autowiredBeanName, requestingBeanName);
				}
			}
		}

		return resource;
	}


	/**
	 * Class representing generic injection information about an annotated field
	 * or setter method, supporting @Resource and related annotations.
	 */
	protected abstract static class LookupElement extends InjectionMetadata.InjectedElement {

		protected String name = "";

		protected boolean isDefaultName = false;

		protected Class<?> lookupType = Object.class;

		@Nullable
		protected String mappedName;

		public LookupElement(Member member, @Nullable PropertyDescriptor pd) {
			super(member, pd);
		}

		/**
		 * Return the resource name for the lookup.
		 */
		public final String getName() {
			return this.name;
		}

		/**
		 * Return the desired type for the lookup.
		 */
		public final Class<?> getLookupType() {
			return this.lookupType;
		}

		/**
		 * Build a DependencyDescriptor for the underlying field/method.
		 */
		public final DependencyDescriptor getDependencyDescriptor() {
			if (this.isField) {
				return new LookupDependencyDescriptor((Field) this.member, this.lookupType);
			}
			else {
				return new LookupDependencyDescriptor((Method) this.member, this.lookupType);
			}
		}
	}


	/**
	 * Class representing injection information about an annotated field
	 * or setter method, supporting the @Resource annotation.
	 */
	private class ResourceElement extends LookupElement {

		private final boolean lazyLookup;

		public ResourceElement(Member member, AnnotatedElement ae, @Nullable PropertyDescriptor pd) {
			super(member, pd);
			Resource resource = ae.getAnnotation(Resource.class);
			String resourceName = resource.name();
			Class<?> resourceType = resource.type();
			this.isDefaultName = !StringUtils.hasLength(resourceName);
			if (this.isDefaultName) {
				resourceName = this.member.getName();
				if (this.member instanceof Method && resourceName.startsWith("set") && resourceName.length() > 3) {
					resourceName = Introspector.decapitalize(resourceName.substring(3));
				}
			}
			else if (embeddedValueResolver != null) {
				resourceName = embeddedValueResolver.resolveStringValue(resourceName);
			}
			if (Object.class != resourceType) {
				checkResourceType(resourceType);
			}
			else {
				// No resource type specified... check field/method.
				resourceType = getResourceType();
			}
			this.name = (resourceName != null ? resourceName : "");
			this.lookupType = resourceType;
			String lookupValue = resource.lookup();
			this.mappedName = (StringUtils.hasLength(lookupValue) ? lookupValue : resource.mappedName());
			Lazy lazy = ae.getAnnotation(Lazy.class);
			this.lazyLookup = (lazy != null && lazy.value());
		}

		@Override
		protected Object getResourceToInject(Object target, @Nullable String requestingBeanName) {
			return (this.lazyLookup ? buildLazyResourceProxy(this, requestingBeanName) :
					getResource(this, requestingBeanName));
		}
	}


	/**
	 * Class representing injection information about an annotated field
	 * or setter method, supporting the @WebServiceRef annotation.
	 */
	private class WebServiceRefElement extends LookupElement {

		private final Class<?> elementType;

		private final String wsdlLocation;

		public WebServiceRefElement(Member member, AnnotatedElement ae, @Nullable PropertyDescriptor pd) {
			super(member, pd);
			WebServiceRef resource = ae.getAnnotation(WebServiceRef.class);
			String resourceName = resource.name();
			Class<?> resourceType = resource.type();
			this.isDefaultName = !StringUtils.hasLength(resourceName);
			if (this.isDefaultName) {
				resourceName = this.member.getName();
				if (this.member instanceof Method && resourceName.startsWith("set") && resourceName.length() > 3) {
					resourceName = Introspector.decapitalize(resourceName.substring(3));
				}
			}
			if (Object.class != resourceType) {
				checkResourceType(resourceType);
			}
			else {
				// No resource type specified... check field/method.
				resourceType = getResourceType();
			}
			this.name = resourceName;
			this.elementType = resourceType;
			if (Service.class.isAssignableFrom(resourceType)) {
				this.lookupType = resourceType;
			}
			else {
				this.lookupType = resource.value();
			}
			this.mappedName = resource.mappedName();
			this.wsdlLocation = resource.wsdlLocation();
		}

		@Override
		protected Object getResourceToInject(Object target, @Nullable String requestingBeanName) {
			Service service;
			try {
				service = (Service) getResource(this, requestingBeanName);
			}
			catch (NoSuchBeanDefinitionException notFound) {
				// Service to be created through generated class.
				if (Service.class == this.lookupType) {
					throw new IllegalStateException("No resource with name '" + this.name + "' found in context, " +
							"and no specific JAX-WS Service subclass specified. The typical solution is to either specify " +
							"a LocalJaxWsServiceFactoryBean with the given name or to specify the (generated) Service " +
							"subclass as @WebServiceRef(...) value.");
				}
				if (StringUtils.hasLength(this.wsdlLocation)) {
					try {
						Constructor<?> ctor = this.lookupType.getConstructor(URL.class, QName.class);
						WebServiceClient clientAnn = this.lookupType.getAnnotation(WebServiceClient.class);
						if (clientAnn == null) {
							throw new IllegalStateException("JAX-WS Service class [" + this.lookupType.getName() +
									"] does not carry a WebServiceClient annotation");
						}
						service = (Service) BeanUtils.instantiateClass(ctor,
								new URL(this.wsdlLocation), new QName(clientAnn.targetNamespace(), clientAnn.name()));
					}
					catch (NoSuchMethodException ex) {
						throw new IllegalStateException("JAX-WS Service class [" + this.lookupType.getName() +
								"] does not have a (URL, QName) constructor. Cannot apply specified WSDL location [" +
								this.wsdlLocation + "].");
					}
					catch (MalformedURLException ex) {
						throw new IllegalArgumentException(
								"Specified WSDL location [" + this.wsdlLocation + "] isn't a valid URL");
					}
				}
				else {
					service = (Service) BeanUtils.instantiateClass(this.lookupType);
				}
			}
			return service.getPort(this.elementType);
		}
	}


	/**
	 * Class representing injection information about an annotated field
	 * or setter method, supporting the @EJB annotation.
	 */
	private class EjbRefElement extends LookupElement {

		private final String beanName;

		public EjbRefElement(Member member, AnnotatedElement ae, @Nullable PropertyDescriptor pd) {
			super(member, pd);
			EJB resource = ae.getAnnotation(EJB.class);
			String resourceBeanName = resource.beanName();
			String resourceName = resource.name();
			this.isDefaultName = !StringUtils.hasLength(resourceName);
			if (this.isDefaultName) {
				resourceName = this.member.getName();
				if (this.member instanceof Method && resourceName.startsWith("set") && resourceName.length() > 3) {
					resourceName = Introspector.decapitalize(resourceName.substring(3));
				}
			}
			Class<?> resourceType = resource.beanInterface();
			if (Object.class != resourceType) {
				checkResourceType(resourceType);
			}
			else {
				// No resource type specified... check field/method.
				resourceType = getResourceType();
			}
			this.beanName = resourceBeanName;
			this.name = resourceName;
			this.lookupType = resourceType;
			this.mappedName = resource.mappedName();
		}

		@Override
		protected Object getResourceToInject(Object target, @Nullable String requestingBeanName) {
			if (StringUtils.hasLength(this.beanName)) {
				if (beanFactory != null && beanFactory.containsBean(this.beanName)) {
					// Local match found for explicitly specified local bean name.
					Object bean = beanFactory.getBean(this.beanName, this.lookupType);
					if (requestingBeanName != null && beanFactory instanceof ConfigurableBeanFactory) {
						((ConfigurableBeanFactory) beanFactory).registerDependentBean(this.beanName, requestingBeanName);
					}
					return bean;
				}
				else if (this.isDefaultName && !StringUtils.hasLength(this.mappedName)) {
					throw new NoSuchBeanDefinitionException(this.beanName,
							"Cannot resolve 'beanName' in local BeanFactory. Consider specifying a general 'name' value instead.");
				}
			}
			// JNDI name lookup - may still go to a local BeanFactory.
			return getResource(this, requestingBeanName);
		}
	}


	/**
	 * Extension of the DependencyDescriptor class,
	 * overriding the dependency type with the specified resource type.
	 */
	private static class LookupDependencyDescriptor extends DependencyDescriptor {

		private final Class<?> lookupType;

		public LookupDependencyDescriptor(Field field, Class<?> lookupType) {
			super(field, true);
			this.lookupType = lookupType;
		}

		public LookupDependencyDescriptor(Method method, Class<?> lookupType) {
			super(new MethodParameter(method, 0), true);
			this.lookupType = lookupType;
		}

		@Override
		public Class<?> getDependencyType() {
			return this.lookupType;
		}
	}

}

```



可以看出 CommonAnnotationBeanPostProcessor 这个东西还包括了一些 Java EE 的东西在里面，这里的 Common 主要是指通用或者是指 java 标准的一些

注解。包括了 javax.xml.ws.WebServiceRef ，这个作用是在本地引用一个 webService 的远程调用的一个引用。

以及 javax.ejb.EJB ，这个类是在 java 5.0 和 EJB 3.0 之后的一个产物。这个注解一样可以被 Spring 处理。

在源码最开始的静态初始化代码块中可以发现一个有意思的事情，他在初始化 javax.xml.ws.WebServiceRef 和 javax.ejb.EJB 的时候，用了 try-catch 的方式，

异常了就将属性设置为空，这是为什么呢？因为可能因为环境的不同，导致这些玩意会没有，所以这时候加个 try-catch 是为了看一下当前上下文里，当前的

classpath 里面是否存在这样一个注解类。





EventListenerMethodProcessor ，通过字面意思也能看出来 @EventListener 标注的处理器，因为在 Spring 4.2 之后提出了一个新的注解，@EventListener，

这个注解可以标注的方法上面，就不需要再去像过去一样实现 ApplicationListener 的规范。



通常 Spring 应用是需要通过我们的 ApplicationListener 来进行监听的，这个接口有什么缺陷或者什么限制吗？

他的限制主要是我们每一个 Event 的实现，例如：applicationContext.refresh()  应用上下文启动的时候，我每一个 Listener 只能对应一个 Event 实现。

这是由 ApplicationListener # onApplicationListener( E e) 这个方法签名所限制的。因为他只能处理一个相关的 事件。

于是在 Spring 4.2 引入了一个新的注解，叫做 @EventListener



##### ApplicationListener.java 源码：

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

package org.springframework.context;

import java.util.EventListener;

/**
 * Interface to be implemented by application event listeners.
 *
 * <p>Based on the standard {@code java.util.EventListener} interface
 * for the Observer design pattern.
 *
 * <p>As of Spring 3.0, an {@code ApplicationListener} can generically declare
 * the event type that it is interested in. When registered with a Spring
 * {@code ApplicationContext}, events will be filtered accordingly, with the
 * listener getting invoked for matching event objects only.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @param <E> the specific {@code ApplicationEvent} subclass to listen to
 * @see org.springframework.context.ApplicationEvent
 * @see org.springframework.context.event.ApplicationEventMulticaster
 * @see org.springframework.context.event.EventListener
 */
@FunctionalInterface
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

	/**
	 * Handle an application event.
	 * @param event the event to respond to
	 */
	void onApplicationEvent(E event);

}

```



##### EventListener.java 源码：

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

package org.springframework.context.event;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.ApplicationEvent;
import org.springframework.core.annotation.AliasFor;

/**
 * Annotation that marks a method as a listener for application events.
 *
 * <p>If an annotated method supports a single event type, the method may
 * declare a single parameter that reflects the event type to listen to.
 * If an annotated method supports multiple event types, this annotation
 * may refer to one or more supported event types using the {@code classes}
 * attribute. See the {@link #classes} javadoc for further details.
 *
 * <p>Events can be {@link ApplicationEvent} instances as well as arbitrary
 * objects.
 *
 * <p>Processing of {@code @EventListener} annotations is performed via
 * the internal {@link EventListenerMethodProcessor} bean which gets
 * registered automatically when using Java config or manually via the
 * {@code <context:annotation-config/>} or {@code <context:component-scan/>}
 * element when using XML config.
 *
 * <p>Annotated methods may have a non-{@code void} return type. When they
 * do, the result of the method invocation is sent as a new event. If the
 * return type is either an array or a collection, each element is sent
 * as a new individual event.
 *
 * <p>This annotation may be used as a <em>meta-annotation</em> to create custom
 * <em>composed annotations</em>.
 *
 * <h3>Exception Handling</h3>
 * <p>While it is possible for an event listener to declare that it
 * throws arbitrary exception types, any checked exceptions thrown
 * from an event listener will be wrapped in an
 * {@link java.lang.reflect.UndeclaredThrowableException UndeclaredThrowableException}
 * since the event publisher can only handle runtime exceptions.
 *
 * <h3>Asynchronous Listeners</h3>
 * <p>If you want a particular listener to process events asynchronously, you
 * can use Spring's {@link org.springframework.scheduling.annotation.Async @Async}
 * support, but be aware of the following limitations when using asynchronous events.
 *
 * <ul>
 * <li>If an asynchronous event listener throws an exception, it is not propagated
 * to the caller. See {@link org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler
 * AsyncUncaughtExceptionHandler} for more details.</li>
 * <li>Asynchronous event listener methods cannot publish a subsequent event by returning a
 * value. If you need to publish another event as the result of the processing, inject an
 * {@link org.springframework.context.ApplicationEventPublisher ApplicationEventPublisher}
 * to publish the event manually.</li>
 * </ul>
 *
 * <h3>Ordering Listeners</h3>
 * <p>It is also possible to define the order in which listeners for a
 * certain event are to be invoked. To do so, add Spring's common
 * {@link org.springframework.core.annotation.Order @Order} annotation
 * alongside this event listener annotation.
 *
 * @author Stephane Nicoll
 * @author Sam Brannen
 * @since 4.2
 * @see EventListenerMethodProcessor
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EventListener {

	/**
	 * Alias for {@link #classes}.
	 */
	@AliasFor("classes")
	Class<?>[] value() default {};

	/**
	 * The event classes that this listener handles.
	 * <p>If this attribute is specified with a single value, the
	 * annotated method may optionally accept a single parameter.
	 * However, if this attribute is specified with multiple values,
	 * the annotated method must <em>not</em> declare any parameters.
	 */
	@AliasFor("value")
	Class<?>[] classes() default {};

	/**
	 * Spring Expression Language (SpEL) expression used for making the event
	 * handling conditional.
	 * <p>The event will be handled if the expression evaluates to boolean
	 * {@code true} or one of the following strings: {@code "true"}, {@code "on"},
	 * {@code "yes"}, or {@code "1"}.
	 * <p>The default expression is {@code ""}, meaning the event is always handled.
	 * <p>The SpEL expression will be evaluated against a dedicated context that
	 * provides the following metadata:
	 * <ul>
	 * <li>{@code #root.event} or {@code event} for references to the
	 * {@link ApplicationEvent}</li>
	 * <li>{@code #root.args} or {@code args} for references to the method
	 * arguments array</li>
	 * <li>Method arguments can be accessed by index. For example, the first
	 * argument can be accessed via {@code #root.args[0]}, {@code args[0]},
	 * {@code #a0}, or {@code #p0}.</li>
	 * <li>Method arguments can be accessed by name (with a preceding hash tag)
	 * if parameter names are available in the compiled byte code.</li>
	 * </ul>
	 */
	String condition() default "";

}

```



这个注解可以用在方法上面，也可以用在注解上面。@EventListener 具有元注解的特性，后面细说。



注释 @see 的地方有一个引导，可以引导到 EventListenerMethodProcessor 里面去，实现了 BeanFactoryRegistryProcessor 说明可以去 Bean 容器的

生命周期。为什么要对 Bean 容器进行后置处理？因为 java 里面是静态语言，因此在静态语言里面标注一些注解的时候，我们反射可以调用到，在容器

Bean 生命周期处理的时候，可以得到一些东西。例如： BeanDefinition 里面有个方法叫做 getBeanClassName () 的方法，他标注了一个 @Nullable ，

类里面定义的方法是确定的，因此方法上面标注的注解也是确定的，所以说我们可以在 Bean 定义的时候得到这些信息。



### 注解驱动 Spring 应用上下文内建可查找依赖（补充）

| Bean 名称                                                    | Bean 实例                           | 使用场景                                               |
| ------------------------------------------------------------ | ----------------------------------- | ------------------------------------------------------ |
| org.springframework.context.event.internal.EventListenerFactory | DefaultEventListenerFactory 对象    | @EvenetListener 事件监听方法适配为 ApplicationListener |
| org.springframework.context.annotation.internal.PersistenceAnnotationProcessor | PersistenceAnnotationProcessor 对象 | （条件激活）处理 JPA 注解场景                          |
|                                                              |                                     |                                                        |



在 EventListenerMethodProcessor 里面有个 postProcessorBeanFactory 的方法：

```java
@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
		this.beanFactory = beanFactory;

        //这里可以实现多个 EvenetListerFactory 在里面进行处理。
		Map<String, EventListenerFactory> beans = beanFactory.getBeansOfType(EventListenerFactory.class, false, false);
		List<EventListenerFactory> factories = new ArrayList<>(beans.values());
		AnnotationAwareOrderComparator.sort(factories);
		this.eventListenerFactories = factories;
	}
```



##### EvenetListenerFactory.java 源码：

```java
/*
 * Copyright 2002-2015 the original author or authors.
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

package org.springframework.context.event;

import java.lang.reflect.Method;

import org.springframework.context.ApplicationListener;

/**
 * Strategy interface for creating {@link ApplicationListener} for methods
 * annotated with {@link EventListener}.
 *
 * @author Stephane Nicoll
 * @since 4.2
 */
public interface EventListenerFactory {

	/**
		判断方法是否支持 EventListener 的方式。
	 * Specify if this factory supports the specified {@link Method}.
	 * @param method an {@link EventListener} annotated method
	 * @return {@code true} if this factory supports the specified method
	 */
	boolean supportsMethod(Method method);

	/**
		我们前面标注 @EventListener ，后面都要用到 ApplicationListener ，这里就是做了个适配。把我们标注 @EventListener 的方法
		适配成我们想要的东西。
		这里需要三个东西 ，beanName beanType , 以及标注 @EventListener 的方法。
	 * Create an {@link ApplicationListener} for the specified method.
	 * @param beanName the name of the bean
	 * @param type the target type of the instance
	 * @param method the {@link EventListener} annotated method
	 * @return an application listener, suitable to invoke the specified method
	 */
	ApplicationListener<?> createApplicationListener(String beanName, Class<?> type, Method method);

}

```



具体的实现有两个：

​	DefaultEventListenerFactory.java

​	TransactionEventListenerFactory.java



##### DefaultEvenetListenerFactory.java 源码：

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

package org.springframework.context.event;

import java.lang.reflect.Method;

import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * Default {@link EventListenerFactory} implementation that supports the
 * regular {@link EventListener} annotation.
 *
 * <p>Used as "catch-all" implementation by default.
 *
 * @author Stephane Nicoll
 * @since 4.2
 */
public class DefaultEventListenerFactory implements EventListenerFactory, Ordered {

	private int order = LOWEST_PRECEDENCE;


	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return this.order;
	}


	@Override
	public boolean supportsMethod(Method method) {
        /*
        	默认都是 true
        	其实在筛选的时候，已经把没有标注 @EnevtListener 的方法筛选掉了。。。。
        */
		return true;
	}

	@Override
	public ApplicationListener<?> createApplicationListener(String beanName, Class<?> type, Method method) {
        /*
        	这里有个适配器，也就是说把传来的 beanName , beanType , 标注了 @EventListener 的方法换成了一个 ApplicationListener
        */
		return new ApplicationListenerMethodAdapter(beanName, type, method);
	}

}

```



ApplicationListenerMethodAdapter 实现了 GenericApplicationListener 继承了 ApplicationListener<ApplicationEvent>，这里先了解结构，后面在

Spring 事件的时候，具体讨论。



PersistentenceAnnotationBeanPostProcessor 这个对象是个条件激活的后置处理器，需要适配 JPA ，引入 spring-orm 的依赖。这个 Bean 名称，明显

不像我们之前的 AbstractApplicationContext 那些内建 Bean 名称一样好记。不知道这狗娃娃这么想写成这样的。。。其实也不用怕用的时候忘记了，有一个

类叫做 AnnotationConfigUtils ，



##### AnnotationConfigUtils.java 源码：

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

package org.springframework.context.annotation;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

/**
 * Utility class that allows for convenient registration of common
 * {@link org.springframework.beans.factory.config.BeanPostProcessor} and
 * {@link org.springframework.beans.factory.config.BeanFactoryPostProcessor}
 * definitions for annotation-based configuration. Also registers a common
 * {@link org.springframework.beans.factory.support.AutowireCandidateResolver}.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Chris Beams
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 2.5
 * @see ContextAnnotationAutowireCandidateResolver
 * @see ConfigurationClassPostProcessor
 * @see CommonAnnotationBeanPostProcessor
 * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
 * @see org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor
 */
public abstract class AnnotationConfigUtils {

	/**
	 * The bean name of the internally managed Configuration annotation processor.
	 */
	public static final String CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME =
			"org.springframework.context.annotation.internalConfigurationAnnotationProcessor";

	/**
	 * The bean name of the internally managed BeanNameGenerator for use when processing
	 * {@link Configuration} classes. Set by {@link AnnotationConfigApplicationContext}
	 * and {@code AnnotationConfigWebApplicationContext} during bootstrap in order to make
	 * any custom name generation strategy available to the underlying
	 * {@link ConfigurationClassPostProcessor}.
	 * @since 3.1.1
	 */
	public static final String CONFIGURATION_BEAN_NAME_GENERATOR =
			"org.springframework.context.annotation.internalConfigurationBeanNameGenerator";

	/**
	 * The bean name of the internally managed Autowired annotation processor.
	 */
	public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME =
			"org.springframework.context.annotation.internalAutowiredAnnotationProcessor";

	/**
	 * The bean name of the internally managed Required annotation processor.
	 * @deprecated as of 5.1, since no Required processor is registered by default anymore
	 */
	@Deprecated
	public static final String REQUIRED_ANNOTATION_PROCESSOR_BEAN_NAME =
			"org.springframework.context.annotation.internalRequiredAnnotationProcessor";

	/**
	 * The bean name of the internally managed JSR-250 annotation processor.
	 */
	public static final String COMMON_ANNOTATION_PROCESSOR_BEAN_NAME =
			"org.springframework.context.annotation.internalCommonAnnotationProcessor";

	/**
	 * The bean name of the internally managed JPA annotation processor.
	 */
	public static final String PERSISTENCE_ANNOTATION_PROCESSOR_BEAN_NAME =
			"org.springframework.context.annotation.internalPersistenceAnnotationProcessor";

	private static final String PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME =
			"org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor";

	/**
	 * The bean name of the internally managed @EventListener annotation processor.
	 */
	public static final String EVENT_LISTENER_PROCESSOR_BEAN_NAME =
			"org.springframework.context.event.internalEventListenerProcessor";

	/**
	 * The bean name of the internally managed EventListenerFactory.
	 */
	public static final String EVENT_LISTENER_FACTORY_BEAN_NAME =
			"org.springframework.context.event.internalEventListenerFactory";

	private static final boolean jsr250Present;

	private static final boolean jpaPresent;

	static {
		ClassLoader classLoader = AnnotationConfigUtils.class.getClassLoader();
		jsr250Present = ClassUtils.isPresent("javax.annotation.Resource", classLoader);
		jpaPresent = ClassUtils.isPresent("javax.persistence.EntityManagerFactory", classLoader) &&
				ClassUtils.isPresent(PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME, classLoader);
	}


	/**
	 * Register all relevant annotation post processors in the given registry.
	 * @param registry the registry to operate on
	 */
	public static void registerAnnotationConfigProcessors(BeanDefinitionRegistry registry) {
		registerAnnotationConfigProcessors(registry, null);
	}

	/**
	 * Register all relevant annotation post processors in the given registry.
	 * @param registry the registry to operate on
	 * @param source the configuration source element (already extracted)
	 * that this registration was triggered from. May be {@code null}.
	 * @return a Set of BeanDefinitionHolders, containing all bean definitions
	 * that have actually been registered by this call
	 */
	public static Set<BeanDefinitionHolder> registerAnnotationConfigProcessors(
			BeanDefinitionRegistry registry, @Nullable Object source) {

		DefaultListableBeanFactory beanFactory = unwrapDefaultListableBeanFactory(registry);
		if (beanFactory != null) {
			if (!(beanFactory.getDependencyComparator() instanceof AnnotationAwareOrderComparator)) {
				beanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
			}
			if (!(beanFactory.getAutowireCandidateResolver() instanceof ContextAnnotationAutowireCandidateResolver)) {
				beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
			}
		}

		Set<BeanDefinitionHolder> beanDefs = new LinkedHashSet<>(8);

		if (!registry.containsBeanDefinition(CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(ConfigurationClassPostProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		if (!registry.containsBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		// Check for JSR-250 support, and if present add the CommonAnnotationBeanPostProcessor.
		if (jsr250Present && !registry.containsBeanDefinition(COMMON_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(CommonAnnotationBeanPostProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, COMMON_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		// Check for JPA support, and if present add the PersistenceAnnotationBeanPostProcessor.
		if (jpaPresent && !registry.containsBeanDefinition(PERSISTENCE_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition();
			try {
				def.setBeanClass(ClassUtils.forName(PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME,
						AnnotationConfigUtils.class.getClassLoader()));
			}
			catch (ClassNotFoundException ex) {
				throw new IllegalStateException(
						"Cannot load optional framework class: " + PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME, ex);
			}
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, PERSISTENCE_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		if (!registry.containsBeanDefinition(EVENT_LISTENER_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(EventListenerMethodProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, EVENT_LISTENER_PROCESSOR_BEAN_NAME));
		}

		if (!registry.containsBeanDefinition(EVENT_LISTENER_FACTORY_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(DefaultEventListenerFactory.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, EVENT_LISTENER_FACTORY_BEAN_NAME));
		}

		return beanDefs;
	}

	private static BeanDefinitionHolder registerPostProcessor(
			BeanDefinitionRegistry registry, RootBeanDefinition definition, String beanName) {

		definition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
		registry.registerBeanDefinition(beanName, definition);
		return new BeanDefinitionHolder(definition, beanName);
	}

	@Nullable
	private static DefaultListableBeanFactory unwrapDefaultListableBeanFactory(BeanDefinitionRegistry registry) {
		if (registry instanceof DefaultListableBeanFactory) {
			return (DefaultListableBeanFactory) registry;
		}
		else if (registry instanceof GenericApplicationContext) {
			return ((GenericApplicationContext) registry).getDefaultListableBeanFactory();
		}
		else {
			return null;
		}
	}

	public static void processCommonDefinitionAnnotations(AnnotatedBeanDefinition abd) {
		processCommonDefinitionAnnotations(abd, abd.getMetadata());
	}

	static void processCommonDefinitionAnnotations(AnnotatedBeanDefinition abd, AnnotatedTypeMetadata metadata) {
		AnnotationAttributes lazy = attributesFor(metadata, Lazy.class);
		if (lazy != null) {
			abd.setLazyInit(lazy.getBoolean("value"));
		}
		else if (abd.getMetadata() != metadata) {
			lazy = attributesFor(abd.getMetadata(), Lazy.class);
			if (lazy != null) {
				abd.setLazyInit(lazy.getBoolean("value"));
			}
		}

		if (metadata.isAnnotated(Primary.class.getName())) {
			abd.setPrimary(true);
		}
		AnnotationAttributes dependsOn = attributesFor(metadata, DependsOn.class);
		if (dependsOn != null) {
			abd.setDependsOn(dependsOn.getStringArray("value"));
		}

		AnnotationAttributes role = attributesFor(metadata, Role.class);
		if (role != null) {
			abd.setRole(role.getNumber("value").intValue());
		}
		AnnotationAttributes description = attributesFor(metadata, Description.class);
		if (description != null) {
			abd.setDescription(description.getString("value"));
		}
	}

	static BeanDefinitionHolder applyScopedProxyMode(
			ScopeMetadata metadata, BeanDefinitionHolder definition, BeanDefinitionRegistry registry) {

		ScopedProxyMode scopedProxyMode = metadata.getScopedProxyMode();
		if (scopedProxyMode.equals(ScopedProxyMode.NO)) {
			return definition;
		}
		boolean proxyTargetClass = scopedProxyMode.equals(ScopedProxyMode.TARGET_CLASS);
		return ScopedProxyCreator.createScopedProxy(definition, registry, proxyTargetClass);
	}

	@Nullable
	static AnnotationAttributes attributesFor(AnnotatedTypeMetadata metadata, Class<?> annotationClass) {
		return attributesFor(metadata, annotationClass.getName());
	}

	@Nullable
	static AnnotationAttributes attributesFor(AnnotatedTypeMetadata metadata, String annotationClassName) {
		return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annotationClassName, false));
	}

	static Set<AnnotationAttributes> attributesForRepeatable(AnnotationMetadata metadata,
			Class<?> containerClass, Class<?> annotationClass) {

		return attributesForRepeatable(metadata, containerClass.getName(), annotationClass.getName());
	}

	@SuppressWarnings("unchecked")
	static Set<AnnotationAttributes> attributesForRepeatable(
			AnnotationMetadata metadata, String containerClassName, String annotationClassName) {

		Set<AnnotationAttributes> result = new LinkedHashSet<>();

		// Direct annotation present?
		addAttributesIfNotNull(result, metadata.getAnnotationAttributes(annotationClassName, false));

		// Container annotation present?
		Map<String, Object> container = metadata.getAnnotationAttributes(containerClassName, false);
		if (container != null && container.containsKey("value")) {
			for (Map<String, Object> containedAttributes : (Map<String, Object>[]) container.get("value")) {
				addAttributesIfNotNull(result, containedAttributes);
			}
		}

		// Return merged result
		return Collections.unmodifiableSet(result);
	}

	private static void addAttributesIfNotNull(
			Set<AnnotationAttributes> result, @Nullable Map<String, Object> attributes) {

		if (attributes != null) {
			result.add(AnnotationAttributes.fromMap(attributes));
		}
	}

}

```



可以看出一些 @EventListener 的处理 Bean 处理器常量化了，并且提供了 静态方法来获取注册的 Annotation 配置的处理器。



```java
/**
	 * Register all relevant annotation post processors in the given registry.
	 * @param registry the registry to operate on
	 * @param source the configuration source element (already extracted)
	 * that this registration was triggered from. May be {@code null}.
	 * @return a Set of BeanDefinitionHolders, containing all bean definitions
	 * that have actually been registered by this call
	 */
	public static Set<BeanDefinitionHolder> registerAnnotationConfigProcessors(
			BeanDefinitionRegistry registry, @Nullable Object source) {

		DefaultListableBeanFactory beanFactory = unwrapDefaultListableBeanFactory(registry);
		if (beanFactory != null) {
			if (!(beanFactory.getDependencyComparator() instanceof AnnotationAwareOrderComparator)) {
				beanFactory.setDependencyComparator(AnnotationAwareOrderComparator.INSTANCE);
			}
			if (!(beanFactory.getAutowireCandidateResolver() instanceof ContextAnnotationAutowireCandidateResolver)) {
				beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
			}
		}

		Set<BeanDefinitionHolder> beanDefs = new LinkedHashSet<>(8);

		if (!registry.containsBeanDefinition(CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(ConfigurationClassPostProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, CONFIGURATION_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		if (!registry.containsBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		// Check for JSR-250 support, and if present add the CommonAnnotationBeanPostProcessor.
		if (jsr250Present && !registry.containsBeanDefinition(COMMON_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(CommonAnnotationBeanPostProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, COMMON_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		// Check for JPA support, and if present add the PersistenceAnnotationBeanPostProcessor.
		if (jpaPresent && !registry.containsBeanDefinition(PERSISTENCE_ANNOTATION_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition();
			try {
				def.setBeanClass(ClassUtils.forName(PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME,
						AnnotationConfigUtils.class.getClassLoader()));
			}
			catch (ClassNotFoundException ex) {
				throw new IllegalStateException(
						"Cannot load optional framework class: " + PERSISTENCE_ANNOTATION_PROCESSOR_CLASS_NAME, ex);
			}
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, PERSISTENCE_ANNOTATION_PROCESSOR_BEAN_NAME));
		}

		if (!registry.containsBeanDefinition(EVENT_LISTENER_PROCESSOR_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(EventListenerMethodProcessor.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, EVENT_LISTENER_PROCESSOR_BEAN_NAME));
		}

		if (!registry.containsBeanDefinition(EVENT_LISTENER_FACTORY_BEAN_NAME)) {
			RootBeanDefinition def = new RootBeanDefinition(DefaultEventListenerFactory.class);
			def.setSource(source);
			beanDefs.add(registerPostProcessor(registry, def, EVENT_LISTENER_FACTORY_BEAN_NAME));
		}

		return beanDefs;
	}
```



这里其实就是发现我的 BeanDefinitionRegistry（Bean 定义注册中心）中有，通过名称找了处理器的注册之后，就不注册了。反之就是没有的话就会自己注册

一个。

在注册 PersistenceAnnotationProcessor 的时候为什么要判断 jsr250Present ？因为 Spring 3.0 适配的是 java 1.5 的版本，像 @Resource 是 java 1.6 才出来

的，所以说要适配 java 1.5 也要适配 java 1.6。





### 总结：

​	通过了解比如说通用型的 AbstractApplicationContext （通用抽象的应用上下文基类）以及 注解驱动里面的这些内置对象，可以大致知道我们在 Spring 场景

中，有一些内置的依赖是可以通过依赖查询的。不过 Spring 不只是包含这些内置的东西，包括在 AOP ，甚至在 Spring Boot 里面，他会有更多的内置对象。







## 8：依赖查找中经典异常：Bean 找不到？Bean 不是唯一的？Bean 创建失败？



### 依赖查找中的经典异常 - BeansException 子类型

在 Spring 设计里面有个技巧，他这里比较排斥 + 讨厌 别人把他的 异常给 try-catch 掉，这里都是用的 RuntimeException 的类型，因此不用在接口上声明，

必须用哪个接口，我们在调用的时候呢，也不需要到处去 try-catch ，这个和传统的 java EE 不太一样。传统的 java EE 一般都是一些 check 性的异常居多。

| 异常类型                        | 触发条件（举例）                                   | 场景举例                                                 |
| ------------------------------- | -------------------------------------------------- | -------------------------------------------------------- |
| NoSuchBeanDefinitionException   | 当 Bean 不存在于 IoC 容器中                        | BeanFactory # getBean()<br />ObjectFactory # getObject() |
| NoUniqueBeanDefinitionException | 类型依赖查找时，IoC 容器中存在<br />多个 Bean 实例 | BeanFactory # getBean( Class )                           |
| BeanInstantiationException      | 当 Bean 所对应的类型，非具体类时                   | BeanFactory # getBean()                                  |
| BeanCreationException           | 当 Bean 初始化过程中                               | Bean 初始化方法执行异常时                                |
| BeanDefinitionStoreException    | 当 BeanDefinition 配置元信息非法时                 | XML 资源配置无法打开时                                   |



#### 新增演示代码：

BeanCreationExceptionDemo.java

BeanInstantiationExceptionDemo.java

NoUniqueBeanDefinitionExceptionDemo.java



### 总结：

​	通过几个例子展示了 Spring 的 一些依赖查找的典型异常，其实这一部分还不只是依赖查找。实际上在整个 Spring 运行过程中，这些异常是经常遇到

。当前我刚上班的时候，天天抓住项目经理帮忙排错，我想大家看完之后应该不会和我一样犯错误了。







## 9：面试题



##### 1：沙雕面试题： ObjectFactory 和 BeanFactory 的区别？

问你这个问题的人估计真的是个沙雕。。。我遇到过。。。ObjectFactory 我们在工作中用的非常少，这是 Spring 早期的一个接口，用的 BeanFactory 相对比较多。这两者都具备依赖查找的能力，不过 ObjectFactory 不具备依赖查找的能力，他是根据泛型的约定，用 BeanFactory 依赖查找的能力来进行依赖查找的。BeanFactory 则是提供了单一类型的依赖查找、集合类型的依赖查找、层次性依赖查找等多种依赖查找的功能。

可以回顾一下之前的 ObjectFactoryCreatingFactoryBean... DependencyLookupDemo.java

ObjectFactoryCreatingFactoryBean 是通过 FactroyBean 来生成一个 ObjectFactory 的一个实现。可以看到里面有 getObjectType() 这么个方法。

```java
	@Override
	public Class<?> getObjectType() {
		return ObjectFactory.class;
	}

	@Override
	protected ObjectFactory<Object> createInstance() {
		BeanFactory beanFactory = getBeanFactory();
		Assert.state(beanFactory != null, "No BeanFactory available");
		Assert.state(this.targetBeanName != null, "No target bean name specified");
		return new TargetBeanObjectFactory(beanFactory, this.targetBeanName);
	}
```

当调用 ObjectFactoryCreatingFactoryBean # createInstance() 的时候，第一步就是获取 BeanFactory 并且作为一个 TargetBeanObjectFactory 来进行返回。

TargetBeanObjectFactory 里面提供了一个 getObject() 的方法，真正调用 getObject() 的时候，其实还是调用 BeanFactory 的 getBean(String beanName)

的方式来返回 Bean 对象。这里的 BeanFactory 的 getBean 不是安全的依赖查找，可能会出现 BeansException 。另外 ObjectFactory 是一个间接的引用其他

Bean 的方式来初始化 Bean，所以这里可以方便我们去做一些初始化的操作。



##### 2：996 面试题 BeanFactory.getBean 操作是否线程安全？

BeanFactory.getBean 操作是线程安全的，操作过程中有添加互斥锁。可以以 DefaultListableBeanFactory 里面就加了很多的 synchronized 关键字，另外由于

java 5 -> java 6 增加了偏向锁，他的初始化都是放在主线程里面完成的，不要把初始化操作交给子线程来操作。因为偏向锁可以提高一些性能上的优势，否则会

出现一些不必要的锁竞争，再严重点会出现死锁，搞得项目不稳定。





##### 3：劝退 面试题 Spring 的依赖查找和 Spring 的依赖注入在来源上有什么区别？

这个放在后面解答，因为我们还有去看 Spring IoC 依赖注入以及 Spring IoC 的依赖来源 的相关知识，淡定。。。。



### 总结：

​	通过前面几个小节的学习和探讨，我们基本上了解到 Spring IoC 依赖查找的相关特性，IoC 的依赖查找和依赖注入他是有区别的，通常 Spring 更愿意提到他自己更偏好的依赖注入。接下来我们将需要一大把的时间来研究 Spring IoC 依赖注入的一些细节，当然源码也是一大堆一大堆的，别害怕，找到自己的兴趣点，多给自己提点问题，咱们已经完成了一个 Spring 依赖查找的里程碑了~~~









# Spring IoC 依赖注入的模型和类型：Spring 提供了哪些依赖注入的模式和类型？



关于 Spring IoC 依赖注入的模型和类型，这里会分为 20 个小节进行讨论，这里是比较完善完整的。加油~



## Spring IoC 依赖注入

### 1、依赖注入的模式和类型

​	模式会分为手动模式和自动模式，类型好比说 setter 方法注入、构造器注入，以及其他方式注入。

### 2、自动绑定 （Autowiring）

​	1：为什么在 Spring 里面会引用到自动绑定？

​	2：自动绑定有哪些模式？官方提到的自动绑定有哪些不足？（其实这玩意官方提出来都后悔了。。。默认自动绑定是关闭的，因为场景非常有限）

​	3：一些注入的模式：setter 、constructor、filed、method、callback（interface） ... injection mode。

​	

### 3、自动绑定（Autowiring）模式

### 4、自动绑定（Autowiring）限制和不足

### 5、setter 方法依赖注入

### 6、构造器依赖注入

### 7、字段注入

### 8、方法注入

### 9、回调注入

### 10、依赖注入类型选择

### 11、基础类型注入

​	和传统的 Bean 注入不太一样，比如像 String 类型，或者是原生类型，并不像传统的注入类型

### 12、集合类型注入

​	对象中有一个集合，这个集合可以管理所有同类型的 Bean 注入，例如 User 对象，上下文里面有三个 User，怎么才能把这些 User 全部注入进来？

### 13、限定注入

​	对注入的对象进行一些限定，比如名称、如何分组（Spring cloud 场景 -> @LoadBalanced ==== @Qualifaier 这个注解不仅可以通过名称，还能通过类型来进行逻辑性的分离或者限定）

### 14、延迟依赖注入

​	延迟依赖查找和延迟依赖查询是有关联性的

### 15、依赖处理过程

​	依赖注入和依赖查找有什么区别？在依赖处理的过程中可以看出一些端倪。

### 16、@Autowired 注入原理

​	底层和 JSR-330 @inject 的原理一样

### 17、JSR-330 @inject 注入原理

​	底层和 @Autowired 的方式一样的。。这玩意是 Spring 的作者 Rod Johnson 来提出的规范，并推给了 Java 社区。在不影响自身的项目启动的同时，给开源社区做了一个很大的贡献。当然 Hibernate 提出了 Bean Validation 这个玩意，也是由开源社区引导的。

### 18、Java 通用注解注入原理

​	通用注解实现和 @Autowired 、JSR-330 的  @Inject 也类似。都是通过注解驱动的方式来注入我们的东西

### 19、自定义依赖注入注解

​	这部分在 Spring 官方是没有说明的。怎么去定义？简单？复杂？

​	简单的话可以依赖已有的实现进行操作，怎么依赖？

​	复杂的话就是自己造轮子，怎么造？Bean 的生命周期~~~比较烧脑。

### 20、面试题。。沙雕 -> 996 -> 劝退





## 1：依赖注入的模式和类型



### 	依赖注入的模式和类型

#### 	·	手动模式 - 配置或者编程的方式，提前安排注入规则（日常开发中用的比较多的），规则需要提前预知

​		·	XML 资源配置元信息

​		·	Java 注解配置元信息

​		·	API 配置元信息 - 这玩意不太会直接使用 。。。容器开发者 或者 Spring扩展机制，这玩意 666

​				XML 资源配置元信息、Java 注解配置元信息 依赖于 API 配置元信息~所以了解了 API 的底层实现，对于我们源码分析和理解有很大帮助

#### 	·	自动模式 - 实现方提供自动依赖、自动关联的方式，按照内建的注入规则。

​		·	Autowiring （自动绑定）- 这种模式官方后悔了。。不大推荐，默认都是 no（关闭）



## 	依赖注入的类型

#### 	

| 依赖注入类型 | 配置元数据举例                                   |
| ------------ | ------------------------------------------------ |
| Setter 方法  | <property name="user" ref="userBean" / >         |
| 构造方法     | <constructor-arg name="user" ref="userBean"  / > |
| 字段         | @Autowired User user                             |
| 方法         | @Autowired public void user(User user){...}      |
| 接口回调     | class MyBean implements BeanFactoryAware{ ... }  |

​	setter : identifier 鉴定服务 name or id or alias



​	构造器注入：构造器和 setter 注入在不同的一个时间段来进行注入



​	字段注入：是没法在 XML 文件里面配置的。setter 注入是需要一个 set() 方法，来绑定关联到一个数据上面来，所以 setter 是通过在 XML 里配置，拿到配置后通过反射的方法进行调用对应的 set() 方法来赋值，字段注入是直接把这个值注入到我的字段里面，官方不大愿意用户这么玩，但是晚了。。。因为这种方式非常危险，原因是：@Autowired 这种方式，那么就依赖于 Spring 的 API ，例如通用 API 就要应用于 @Resource 这种 API 的方式来进行操作，这种方式会有外部的依赖的方式，不是那么纯粹。



​	方法注入：@Autowired 只是一种方式，其实还有很多的方式。。例如：通过 @Bean 的方式也可以通过方法参数的方式进行参数传递，这种方式在维基百科中又称之为参数注入。说不出他是构造器方式还是 setter 方式，很笼统。



​	接口回调：这里主要是指 Spring 有一些内建接口，主要以 Aware 这种接口为主，例如：BeanFactoryAware 这种接口，会通过 API 的方式，显示的去传递一个BeanFactory 给当前的 Bean 来进行使用。这时候 Aware 接口会在方法或者是参数注入之前就进行回调，这种关系的注入前后依赖顺序，我们会在 Bean 生命周期里面详细讨论。



### 总结：

​	我们通过两个方面：依赖注入模式、依赖注入类型 分别讨论了各种依赖注入的不同点和相同点，下面我们会根据这些依赖注入的类型分别进行讨论。





## 2：自动绑定（Autowired）：为什么 Spring 会引入 Autowiring ?



### 自动绑定方式是为了解决什么问题？Spring 官方为什么要提出来？

其实有时代背景的。。

##### 官方解释：

#### 1.4.5. Autowiring Collaborators

The Spring container can autowire relationships between collaborating beans. You can let Spring resolve collaborators (other beans) automatically for your bean by inspecting the contents of the `ApplicationContext`. Autowiring has the following advantages:

- Autowiring can significantly reduce the need to specify properties or constructor arguments. (Other mechanisms such as a bean template [discussed elsewhere in this chapter](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-child-bean-definitions) are also valuable in this regard.)
- Autowiring can update a configuration as your objects evolve. For example, if you need to add a dependency to a class, that dependency can be satisfied automatically without you needing to modify the configuration. Thus autowiring can be especially useful during development, without negating the option of switching to explicit wiring when the code base becomes more stable.



<bean / >标签可以通过 autowire="byType" autowire="byName" 的方式去给 property 标签 赋值，byName 有一个不好的地方就是一旦字段发生了变化，Spring  就找不到关联的引用了，使代码变得不好维护了。所以说尽量的用 property 标签的 ref 显示的引用另外一个对象。

另外 Java 中有值传递和引用传递这么一说，所以当 ref 的对象变化的时候，引用的 对象也会发生变化



### 总结：

​	我们简单掌握了一下 Autowiring 的初衷，减少一些属性的配置，引用对象发生变化的时候会自己更新。但是这个初衷看起来不错，但是有问题的。。好比 byName 的方式，字段名称被改了。。。。





## 3：自动绑定（Autowired）模式：各种自动绑定的使用场景是什么 ?



### Autowiring Modes

| 模式        | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| no          | 默认值，未激活 autowiring，需要手动指定依赖注入对象          |
| byName      | 根据被注入属性的名称作为 Bean 名称进行依赖查找，并将对象设置到该属性。 |
| byType      | 根据被注入属性的类型作为 Bean 类型进行依赖查找，并将对象设置到该属性 |
| constructor | 特殊 byType 类型，用于构造器参数                             |

枚举参考 ： org.springframework.beans.factory.annotation.Autowire - Spring 2 出品 支持 Java 5。编译的 API 方面不要求 Java 5 但是在源码层面，会把语言级别提升上来。



no：Spring 不推荐使用这种自动绑定的方式来进行依赖注入，因为他太危险

byName：setter 方式进行注入，和 XML 方式非常贴切

byType：还是有点危险。。。假如注入的对象是一个单一的简单类型的对象的话还好，如果出现多个类型相同的 Bean 立马蛋疼，立竿见影。有两种解法，第一种是把不需要的 Bean 给干掉。。。但是你怎么知道哪个不需要呢。。。第二种方式把某个 Bean 设置成为 Primary 这种方式。。但是你怎么知。。。

constractor：特殊的 byType 方式。。



##### Autowire 源码：

```java
/*
 * Copyright 2002-2009 the original author or authors.
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

package org.springframework.beans.factory.annotation;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * Enumeration determining autowiring status: that is, whether a bean should
 * have its dependencies automatically injected by the Spring container using
 * setter injection. This is a core concept in Spring DI.
 *
 * <p>Available for use in annotation-based configurations, such as for the
 * AspectJ AnnotationBeanConfigurer aspect.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 2.0
 * @see org.springframework.beans.factory.annotation.Configurable
 * @see org.springframework.beans.factory.config.AutowireCapableBeanFactory
 */
public enum Autowire {

	/**
	 * Constant that indicates no autowiring at all.
	 */
	NO(AutowireCapableBeanFactory.AUTOWIRE_NO),

	/**
	 * Constant that indicates autowiring bean properties by name.
	 */
	BY_NAME(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME),

	/**
	 * Constant that indicates autowiring bean properties by type.
	 */
	BY_TYPE(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);


	private final int value;


	Autowire(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

	/**
	 * Return whether this represents an actual autowiring value.
	 * @return whether actual autowiring was specified
	 * (either BY_NAME or BY_TYPE)
	 */
	public boolean isAutowire() {
		return (this == BY_NAME || this == BY_TYPE);
	}

}

```



##### 为什么没看到构造器方式？

因为构造器是特殊的 byType。先分析下 **BY_TYPE(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);** 



##### AutowireCapableBeanFactory.java 源码：

```java
public interface AutowireCapableBeanFactory extends BeanFactory {

	/**
	 * Constant that indicates no externally defined autowiring. Note that
	 * BeanFactoryAware etc and annotation-driven injection will still be applied.
	 * @see #createBean
	 * @see #autowire
	 * @see #autowireBeanProperties
	 */
	int AUTOWIRE_NO = 0;

	/**
	 * Constant that indicates autowiring bean properties by name
	 * (applying to all bean property setters).
	 * @see #createBean
	 * @see #autowire
	 * @see #autowireBeanProperties
	 */
	int AUTOWIRE_BY_NAME = 1;

	/**
	 * Constant that indicates autowiring bean properties by type
	 * (applying to all bean property setters).
	 * @see #createBean
	 * @see #autowire
	 * @see #autowireBeanProperties
	 */
	int AUTOWIRE_BY_TYPE = 2;

	/**
	 * Constant that indicates autowiring the greediest constructor that
	 * can be satisfied (involves resolving the appropriate constructor).
	 * @see #createBean
	 * @see #autowire
	 */
	int AUTOWIRE_CONSTRUCTOR = 3;

	/**
		这个是一个历史遗留的东西，叫做自动探测，后面高版本就没了。。。
	 * Constant that indicates determining an appropriate autowire strategy
	 * through introspection of the bean class.
	 * @see #createBean
	 * @see #autowire
	 * @deprecated as of Spring 3.0: If you are using mixed autowiring strategies,
	 * prefer annotation-based autowiring for clearer demarcation of autowiring needs.
	 */
	@Deprecated
	int AUTOWIRE_AUTODETECT = 4;
```





### 总结：

​	我们通过列表以及相关 API 的查找，了解了 4 种 Autowiring 的模式的基本使用场景。由于版本问题，在 Spring 3.0 以后他不推荐使用 AUTODETECT 自动探测的方式来进行依赖注入了，我的《深入理解 Spring》 版本真的。。。低。





## 4：自动绑定（Autowired）的限制和不足：如何理解和挖掘官方文档中深层次的含义 ?

##### Limitations and Disadvantages of Autowiring

Autowiring works best when it is used consistently across a project. If autowiring is not used in general, it might be confusing to developers to use it to wire only one or two bean definitions.

Consider the limitations and disadvantages of autowiring:

- Explicit dependencies in `property` and `constructor-arg` settings always override autowiring. You cannot autowire simple properties such as primitives, `Strings`, and `Classes` (and arrays of such simple properties). This limitation is by-design.

  

  大意：显示的使用 property 和 constructor-arg 设置，会覆盖掉自动绑定，导致无法装配简单的属性。不能装配一些简单的 properties，例如：原生类型

  以及 String 类型、Class 类型。他是一个设计上的限制，因为 Autowiring 会自动绑定一些 Bean 或者是一些相关的引用对象，这时候我们的原生类型是无

  法声明成一个 Bean 的，所以无法执行自动的 Autowiring。但是我们可以通过 @Value 的方式进行注入，等于把文本类型转化成场景型 ，例如 long 类型

  

- Autowiring is less exact than explicit wiring. Although, as noted in the earlier table, Spring is careful to avoid guessing in case of ambiguity that might have unexpected results. The relationships between your Spring-managed objects are no longer documented explicitly.



​		大意：Autowiring 是一种精确的 比精确的绑定缺乏精确性，因为 Autowiring 是一种猜测性或者半猜测的东西，例如：byName 他会去上下文里面搜索，

​		看 名字 或者 字段 或者方法、参数 相同的这种方式，来进行自动的一个关联，会产生一个问题：Spring 非常关注精确性，如果出现了一些模糊性情况，

​		名称取得不对，恰好缺了一个关联性的名称，这个时候 Autowiring 就会出错。精确性无法保证，所以 Spring 非常在意精确性，尽可能的避免猜测这种

​		模糊的情况，这会导致一些不确定的结果。

​			User Bean ，字段里面恰好有一个 private User user;	实际上这里想取得一个 SuperUser 对象



- Wiring information may not be available to tools that may generate documentation from a Spring container.

  

  大意：根据工具方面来说明，自定绑定信息很难在一些工具类上进行呈现。比如生成文档。。。因为他不仅仅是头疼你的 Autowiring，更多的是他很难

  确定注入的 Bean 是否在上下文存在。无法确定所有 classpath 下的包都存在。（没有运行，没法提前预判）只能通过静态的方式来分析上下文里面是否

  有这些信息

  

- Multiple bean definitions within the container may match the type specified by the setter method or constructor argument to be autowired. For arrays, collections, or `Map` instances, this is not necessarily a problem. However, for dependencies that expect a single value, this ambiguity is not arbitrarily resolved. If no unique bean definition is available, an exception is thrown.

  

  大意：如果应用上下文里面存在多个 Bean 的定义，例如：User 包含 User 和 SupperUser 两个对象，这个时候用 Autowiring 自动绑定的方式，就不确定

  到底绑定哪个。。。到底是 byType 还是 byName ？byType 就会出错（你想要一个对象，但是出来了俩。。。）这时候就会产生一些歧义 - 

  NoUniqueBeanDefinitionException 



以上几点就是 Autowiring 的限制和不足。实际过程中，这玩意用的很少，所以给了这么几种选择。。。



In the latter scenario, you have several options:

- Abandon autowiring in favor of explicit wiring.

  大意：放弃或关闭这种自动绑定的方式。。。。

- Avoid autowiring for a bean definition by setting its `autowire-candidate` attributes to `false`, as described in the [next section](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-autowire-candidate).

  大意：把 autowire-candidate 这个属性设置成 false

- Designate a single bean definition as the primary candidate by setting the `primary` attribute of its `<bean/>` element to `true`.

  大意：把某一个 Bean 设置为 primary

- Implement the more fine-grained control available with annotation-based configuration, as described in [Annotation-based Container Configuration](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-annotation-config).

  大意：如果你想更细粒度化的控制，建议通过注解配置的方式来配置各种信息。



可以看出，Autowiring 这种自动绑定的方式，他是跟着我们的 XML 方式来走的。注解的方式通常会更好一点，因为注解的方式是通过精确的方式，例如：

@Autowired 或者 @Qualifier 这种注解，可以帮助我们精确的定位到底用哪个相关的 Bean 依赖。





### 总结：

​	通过文档分析，加上我们之前写过的 demo 代码，了解到 Spring 官方文档里面为什么说 Autowiring 自动绑定的这种方式有哪些限制和不足。

1：会被 property 或者 constructor-arg 覆盖掉

2：无法精确定位绑定

3：不容易生成文档

4：同类型下多个对象 -> NoUniqueBeanDefinitionException.



建议：

1：直接关闭或者不用

2：auto-candidate 属性设置为 false

3：同一类型下多个对象，把某一个对象设置为 Primary

4：用注解的方式进行配置







## 4：Setter 方法依赖注入：Setter 注入的原理是什么？



### Spring IoC 注入类型之 Setter 注入

#### 实现方法：

#### 	·	手动方式

#### 			·	XML 资源配置元信息

#### 			·	Java 注解配置元信息

#### 			·	API 配置元信息

#### 	·	自动方式 - 自动绑定（Autowiring）

#### 			·	byName

#### 			·	byType



##### 新增模块：

​	dependency-injection

第一步：

​	pom 引入 overview 模块

第二步：

​	包名： org.example.thinking.in.spring.denpendency.injection



首先根据 XML 的方式来进行 Setter 注入

##### 新增文件：

​	XmlDependencySetterInjectionDemo.java 	基于 XML 的方式演示 setter 注入

​	dependency-setter-injection.xml		手动绑定 xml 文件

​	UserHolder.java		User 对象持有类 

​	AnnotationDependencySetterInjectionDemo.java		基于注解的方式演示 setter 注入

​	ApiDependencySetterInjectionDemo.java		基于 API 的方式演示 Setter 注入

​	AutoWiringByNameDependencyInjectionDemo.java	基于 XML 演示 byName 自动绑定注入

​	autowiring-dependency-setter-injection.xml	自动绑定 xml 文件



##### XmlDependencySetterInjectionDemo.java

```java
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

```



##### dependency-setter-injection.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="classpath:/META-INF/dependency-lookup-context.xml" />

    <!-- 如何利用 setter 方式进行注入？？？ -->
    <bean class="org.example.thinking.in.spring.denpendency.injection.UserHolder">
<!--        <property name="user" ref="user" />-->
        <property name="user" ref="superUser" />
    </bean>

</beans>
```



##### UserHolder.java

```java
package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * User 持有类
 * */
public class UserHolder {

    private User user;

    public UserHolder() {

    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}

```



##### AnnotationDependencySetterInjectionDemo.java

```java
package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 基于 Java 注解的方式演示 Setter 注入
 * */
public class AnnotationDependencySetterInjectionDemo {

    public static void main(String[] args) {

        //创建 BeanDefinition 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //创建 XML 资源读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-setter-injection.xml";

        //读取 XML 资源文件并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //注册 AnnotationDependencySetterInjectionDemo 作为 Configuration Class
        applicationContext.register(AnnotationDependencySetterInjectionDemo.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

    /** 方法的依赖注入 */
    @Bean
    @Primary
    public UserHolder userHolder(User user){
        return new UserHolder(user);
    }

}

```



##### ApiDependencySetterInjectionDemo.java

```java
package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 API 配置元信息的方式 演示 Setter 注入
 * */
public class ApiDependencySetterInjectionDemo {

    public static void main(String[] args) {

        //创建 BeanDefinition 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 UserHolder 为 Configuration Class
//        applicationContext.register(UserHolder.class);

        //创建 UserHolderBeanDefinition
        BeanDefinition userHolderBeanDefinition = createUserHolderBeanDefinition();

        //注册 UserHolderBeanDefinition 到 容器中
        applicationContext.registerBeanDefinition("userHolder",userHolderBeanDefinition);

        //创建 XML Bean 定义读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //加载 XML 资源，并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);

        System.out.println(userHolder);


        //关闭 Spring 应用上下文
        applicationContext.close();



    }

    /** 创建 UserHolder BeanDefinition 方法 */
    public static BeanDefinition createUserHolderBeanDefinition(){

        //创建 UserHolder Bean 定义建造器
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(UserHolder.class);

        //为 UserHolder 中的 user 对象赋值，superUser 为 xml 加载到的对象
        beanDefinitionBuilder.addPropertyReference("user","superUser");

        //返回 Bean 定义
        return beanDefinitionBuilder.getBeanDefinition();

    }

}

```



##### AutoWiringByNameDependencyInjectionDemo.java

```java
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

```



##### autowiring-dependency-setter-injection.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <import resource="classpath:/META-INF/dependency-lookup-context.xml" />

    <!-- 如何利用 setter 方式进行注入？？？ -->
    <bean class="org.example.thinking.in.spring.denpendency.injection.UserHolder"
          autowire="byType">
<!--          autowire="byName">-->

<!--        <property name="user" ref="user" />-->
<!--        <property name="user" ref="superUser" />        替换成 autowiring 的方式  -->
    </bean>

</beans>
```



### 总结：

​	我们通过五个案例，分别演示了手动 Setter 注入 User 对象，自动绑定注入 User 对象。其中手动模式我们着重演示了 XML 、JAVA 注解、Spring API

的方式注入 User 对象到 UserHolder 中。另外通过 API 的方式可以理解到 Spring 底层设计的初衷，例如：addPropertyReference 的方式，引用到其他

的 Bean 来达到依赖注入的目的。





## 5：构造器依赖注入：官方为什么推荐构造器注入？



### 实现方式：

#### 	·	手动模式

#### 		·	XML 资源配置元信息

#### 		·	Java 注解配置元信息

#### 		·	API 配置元信息

#### 	·	自动模式

#### 		·	constructor - 一种特殊的 byType 的模式，仅限于 constructor 参数的依赖绑定





##### 新增文件：

​	dependency-constructor-injection.xml		构造器依赖注入 xml 配置 bean 文件

​	XmlDependencyConstructorInjectionDemo.java		基于 XML 构造器依赖注入演示

​	AnnotationDependencyConstructorInjectionDemo.java		基于注解 构造器依赖注入演示

​	ApiDependencyConstructorInjectionDemo.java		基于 API 构造器依赖注入演示

​	autowiring-dependency-constructor-injection.xml		自动绑定 XML 配置





#### 为什么官方推荐构造器注入？

​	因为 setter 方法的调用顺序是不确定的，但是构造器的调用顺序是确定的。换句话说就是 ，构造方法的注入方式其实可以根据构造方法的参数的顺序来决定。

例如：

```java
	public UserHolder(User user) {
        this.user = user;
    }
```

比如我的构造方法有多个，我可以第一个参数给 User ，第二个参数 Student，依次类推，形成顺序。



### 总结：

​	当我们有了 Setter 方法  API  依赖注入，再去学习 Constructor 依赖注入就会简单很多，无论是 XML 配置元信息、Java 注解配置元信息，甚至 API 的方式

配置元信息，都很容易理解。这些是理解 Spring IoC 底层注册生命周期的基本功。未来开发中间件的话，会大量的接触到 Spring API。。。学好了就能成为一个

彻彻底底 API 调用工程师。。。学不好也是一个 API 调用工程师。。。。





## 6：字段注入：为什么 Spring 官方没有列举这种注入方式？



字段注入是我们经常遇到的一种注入方式，比较特殊。在 Spring 官方是鼓励大家使用的构造器注入，不要用字段注入。。。但是我用的真的比较多。。。



### 实现方式

#### 	·	手动模式

#### 		·	Java 注解配置元信息

#### 			·	@Autowired

#### 			·	@Resource

#### 			·	@Inject（可选）这玩意需要 JSR-330 的 jar 包，我不演示了。。自己斟酌

​					JSR-330 一个新的 API，这个也是由 Spring 的作者 Rod Johnson 提出来的。现在在 Java EE 里面用途广泛，和 @Autowired、@Resource 类似，

​				都是注解的方式进行依赖注入。可以用在注解上面、方法上面、参数上面。

#### 	·	自动方式

# 		没用过，有用过的可以分享下。。。



##### 新增文件：

​	AnnotationDependencyFieldInjectionDemo.java		字段依赖注入演示



#### 问题：字段注入能不能给静态字段进行注入？

答：不能哇。。。@Autowired 会忽略掉 静态字段。。所以会获取到 null。

@Resource 也不行。。会直接告诉你不支持静态字段

Caused by: java.lang.IllegalStateException: @Resource annotation is not supported on static fields



后面会单独说 @Autowired 这种注解处理，提前预习下。。。**AutowiredAnnotationBeanPostProcessor**

这里面告诉了我们怎么排除掉一些静态字段和静态方法。





### 总结：

​	大致上讲解了 @Autowired 和 @Resource 的依赖注入方式，其中 @Autowired 会忽略掉静态字段和静态方法，@Resource 遇到静态字段会报错，

所以说我们的字段注入指的是实例字段（对象字段）的注入。接下来讨论下另外一些注解的方式。。。





## 7：方法注入：方法注入是 @Autowired 的专利吗？



@Autowired 注入会忽略掉 static 字段，字段注入实际上指的是实例字段（对象字段）的注入。

问题：@Autowired 是不是方法注入的唯一途径

答：不是。

## 方法注入实现方式：

### 	·	手动模式

#### 			·	Java 配置注解元信息

### 				·	@Autowired

### 				·	@Resource

### 				·	@Inject（可选）

### 				·	@Bean

​							帮助我们通过方法参数的方式，注入进来依赖。需要注入的参数，就会通过 类型的方式，从 Spring 上下文里面去查找，

​						查找结束之后，发现的参数会有一个特点，就是会优先查找 primary 的 Bean。



##### 新增文件：

​	AnnotationDependencyMethodInjectionDemo.java		方法依赖注入的演示



### 总结：

​	只需要调整一下字段注入时注解的位置，字段 -> 方法，就可以实现方法注入。方法注入的时候不关心方法的名字，不一定叫 setXXX

只要我的方法的参数里面有相关的类型，就可以进行依赖注入。





## 8：接口回调注入：接口回调注入的使用场景和限制是什么？



接口回调注入是一种比较特殊的注入方式：

## 接口回调注入

##### 	·	自动模式：

| 内建接口                | 说明                                             |
| ----------------------- | ------------------------------------------------ |
| BeanFactoryAware        | 获取 IoC 容器 - BeanFactory                      |
| ApplicationContextAware | 获取 Spring 应用上下文 - ApplicationContext 对象 |
| EnvironmentAware        | 获取 Envitonment 对象                            |
| ResourceLoaderAware     | 获取资源加载器 - ResourceLoader 对象             |
| BeanClassLoaderAware    | 获取加载当前 Bean Class 的 ClassLoader 对象      |
| BeanNameAware           | 获取当前 Bean 的名称                             |

可以看出回调接口是一个系列接口，叫做 XXXAware ，也就是 Aware 接口（意识接口）。可以成为 XX 的意识接口，这类接口一般都是 XXAware 的方式命名。

他是 Spring 3.1 提升出来的一个新接口，像 BeanFactoryAware 之前也存在，只是说从 3.1 搞了个 Aware 接口而已，称之为标记接口。

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

/**
 * A marker superinterface indicating that a bean is eligible to be notified by the
 * Spring container of a particular framework object through a callback-style method.
 * The actual method signature is determined by individual subinterfaces but should
 * typically consist of just one void-returning method that accepts a single argument.
 *
 * <p>Note that merely implementing {@link Aware} provides no default functionality.
 * Rather, processing must be done explicitly, for example in a
 * {@link org.springframework.beans.factory.config.BeanPostProcessor}.
 * Refer to {@link org.springframework.context.support.ApplicationContextAwareProcessor}
 * for an example of processing specific {@code *Aware} interface callbacks.
 *
 * @author Chris Beams
 * @author Juergen Hoeller
 * @since 3.1
 */
public interface Aware {

}

```

BeanFactoryAware：

​	我要注入的对象就是我们前面描述的东西，BeanFactoryAware 就是需要注入一个 BeanFactory，那么就获取当前 Bean 容器，

ApplicationContextAware：

​	获取当前应用上下文。

EnvironmentAware：

​	获取。。。

。。。。以此类推



##### 补充：自动模式

| 内建接口                       | 说明                                                  |
| ------------------------------ | ----------------------------------------------------- |
| MessageSourceAware             | 获取 MessageSource 对象，用于 Spring 国际化           |
| ApplicationEventPublisherAware | 获取 ApplicationEventPublisher 对象，用于 Spring 事件 |
| EmbeddedValueResolverAware     | 获取 StringValueResolver 对象，用于占位符处理         |

MessageSource：

​	用于国际化文案的接口，里面有 code 和文案，基于 ResourceBundle 来进行实现。后面会详细讨论 Spring 国际化

ApplicationEventPublisherAware：

​	获取 ApplicationEventPublisher ，发布事件

EmbeddedValueResolverAware：

​	这个获取的对象看上去是 EmbeddedValueResolver，最主要的还是为了获取他的规范 StringValueResolver 。用来处理占位符，例如：placeholder ${}





#### 基本上所有的 Spring Bean 都可以实现上面这些接口，但是 Aware 系列接口没办法进行扩展。他只是内嵌，因此这种回调方式可以认为是 Spring 内建的一种回调方式。也许他们以后会拉个分支，加上更多的 Aware 系列的实现。



之前说到的一些内建的可查询，不可查找的依赖，也是通过这种方式（注入的方式）来进行呈现的。例如：API 的方式去注册一些相应的单体对象，或者一些 Bean 的方式来操作。



### 总结：

​	任何一个类注册到注解驱动的 AnnotationConfigApplicationContext 里面，这个类就是一个 Spring 的 Bean，因此我们可以通过这个类的方式去实现各种 Aware 的接口。

​	这里注入的对象，和我们之前讨论的那些内建隐藏的依赖其实是同一个对象。只不过是实现的手段不一样罢了，后面会单独对这个论点进行激烈的讨论。（依赖注入和依赖查找的来源，和咱们说的这个 Aware 有啥联系？有啥区别？）





## 9：依赖注入类型选择：各种依赖注入有什么样的使用场景？



## 依赖注入类型选择

### 注入选型：

#### 	·	低依赖：构造器注入

​			构造方法参数过多，构造方法在构造的时候会异常的复杂。

#### 	·	多依赖：Setter 方法注入

​			尽管比构造方法执行时间晚，顺序性无法保证，但是可以有效的拆解参数多且复杂的情况。

​			假设依赖注入存在先后依赖的关系，这种方式绝对的蛋疼。

#### 	·	便利性：字段注入

​			我更喜欢这种气的 Rod Johnson 撞墙的方法。。。因为我想注入哪个字段对象，我就加个 @Autowired 就完事了。

​			但是要注意，这种方式在 Spring 、Spring boot 中慢慢的处于淘汰状态了，推荐使用构造器方式进行依赖注入

#### 	·	声明类：方法注入

​			通常不建议这么玩，最好把声明的方法用 @Bean 注解进行标识，提供构造参数，在 Bean 构造的情况下进行操作。

​			这其实就是个组合方式，用于方法参数外部的依赖注入，可以通过构造器或者 Setter 方法来进行二次手动 API 注入。



### 总结：

​	上头这些就是总结。。。具体情况具体分析，出问题了别说我说的就行。





## 9：基础类型注入：String 和 Java 原生类型也能注入 Bean 属性，他们算依赖注入吗？



这里将会了解到一些非常具体的注入：基础类型注入。并不完全涉及到一些 Bean 的注入，类似于类型转换这样的操作。



### ·	基础类型

### 	·	原生类型（Primitive）：boolean 、byte、char、short、int、float、long、double

### 	·	标量类型（Scalar）：Number、Character、Boolean、Enum、Locale、Charset、Currency、												Properties、UUID

### 	·	常规类型（General）：Object、String、TimeZone、Calendar、Optional 等

### 	·	Spring 类型：Resource、InputSource（SpringSource）、Formatter（Spring的） 等



##### 问题：这些类型怎么注入的呢？



找到：dependency-lookup-context.xml 这个文件。

User 里面定义的 id 和 name ，一个是 Long 类型， 一个是 String 类型。但是在 XML 文件里面定义的 id 是个字符类型，那么字符类型怎么转换成相应的 Long 类型呢？肯定是有一系列的规则转换。假如说我要转换成枚举类型怎么玩？



##### 新增文件：

​	City.java		枚举类

​	user-config.properties		用户属性类



把 City 作为属性添加到 User.java 中，随便找个对 XML 进行解析，并且对 User 进行依赖查找的示例代码运行下，就发现 city 我们在 xml 中给的是个字符类型，

输出的时候变成了一个枚举类型。思考这是为什么？枚举是 final static。



然后在 User.java 里面添加一个 Resource 对象作为属性，建立一个 user-config.properties 的文件，修改 XML 文件配置。



输出：

​	class path resource [META-INF/user-config.properties]

但是我们只是在 XML 文件中给了一个 字符串的路径。

```xml
	<bean id="user" class="org.example.thinking.in.spring.ioc.overview.dependency.domain.User">
        <property name="id" value="1" />
        <property name="name" value="xx" />
        <property name="city" value="BEIJING" />
        <property name="configFileLocation" value="classpath:/META-INF/user-config.properties" />
    </bean>
```

这里的 Resource 其实是 ClassPathResource ，只针对于 classpath 环境下面的一个操作。

```java
public String toString() {
	return this.getDescription();
}

public String getDescription() {
        StringBuilder builder = new StringBuilder("class path resource [");
        String pathToUse = this.path;
        if (this.clazz != null && !pathToUse.startsWith("/")) {
            builder.append(ClassUtils.classPackageAsResourcePath(this.clazz));
            builder.append('/');
        }

        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }

        builder.append(pathToUse);
        builder.append(']');
        return builder.toString();
    }
```

这些代码就是生成了 	class path resource [META-INF/user-config.properties] 的代码。。。





### 总结：

​	Spring 不仅仅支持一些简单的类型注入，还支持一些复杂类型的注入。例如：原始类型、标量类型、常规类型，Spring 的一些类型，不等同他一定是我们的一些 Bean 或者是一些外部对象。

​	基础类型的注入方式不同于我们传统的 Spring Bean 或者 BeanDefinition 依赖注入方式，大概就是输入一个字符串类型，他帮我们转换成一个相应类型的操作。细节部分我们会在 Spring 类型转换以及元信息配置那块细说里面流转的过程。当然 Spring 也提供了一些让我们扩展、自定义的空间。

​	下面讨论集合类型的注入。





## 10：集合类型注入：注入 Collection 和 Map 依赖类型的区别？还支持哪些集合类型？



前面我们大致可以知道 Spring 在基础类型注入的一些基本情况，接下来我们本着作死的心，来耍一把集合的依赖注入。

实际上就是对前面基础类型和原始的 Spring Bean 类型或者单体注入类型，来做一个集合转换。



### ·	集合类型

### 	·	数组类型（Array）：原生类型、标量类型、常规类型、Spring 类型

### 	·	集合类型（Collection）：

### 			·	Collection：List、Set（SortedSet、NavigableSet、EnumSet） -》 Java 1.3

### 			·	Map：Properties、Hashtable	

### 								-》 Java 1.0 最开始提供了 Directory 这种 k,v 类型 

### 								-》 Java 1.2 引入 Map 类型





##### 下面继续逮住我们的 User 弄。

User 增加 [] 的方式来定义一个属性 

```java
private City[] cityArray;
```



思考：什么是元素类型？

​	元素类型？当然就是元素所对应的类型了。如果说集合就是一个容器的话，那么容器里面都会有成员。比如 cityArray 里面的每一个成员就是一个 ElementType（元素类型）。

​	这玩意会涉及到后面一个元数据的编程。在 Java 里面会用 Class 这个类来标注。里面有个方法叫做 isArray，来告诉我们类型是否为数组（真狗）。如果是数组的话，会有一个 getComponentType() 的方法（我记得叫 getElementType的。。），这个东西的作用就是如果 isArray() 发现了是数组，调用这个方法就会返回这个数组里面所对应的东西。这东西比较特殊，因为只有数组有这个特性，然而 Java 的 Collection 是没有这个特性的。

​	为什么没有呢？因为 Java 集合里面他有一个泛型擦除这个特性的，底层存储的都是 Object 成员，所以他的 ComponentType 是不存在的。当然在 Java 集合（Collection）操作里面，我们后面会说一下关于泛型的处理。

```java
public interface Collection<E> extends Iterable<E> { ... }
```

就是这个 E ，怎么取出来。不过方法很狗，用的人不狗就行。在 Spring 泛型处理的时候会细说。



### 总结：

​	我们现在可以知道基础类型和集合类型就是一种组合和被组合的关系。 SpringFramework 框架里面能够帮我们便利的进行类型转换然后注入。

还有很多的注入。。。一个一个来。









## 11：限定注入：如何限定 Bean 名称注入？如何实现 Bean 逻辑分组注入？



### 限定注入：

### 		·	使用注解 @Qualifier 限定

### 				·	使用 Bean 名称限定

### 				·	通过分组限定

### 		·	基于注解 @Qualifier 扩展限定

### 				·	自定义注解 - 如 Spring Cloud 的 @LoadBalanced





##### 新增文件：

##### 	QualifierAnnotationDependencyInjectionDemo.java		演示注解限定注入

##### 	UserGroup.java		@Qualifier 的扩展



##### Qualifier 源码：

```java
/*
 * Copyright 2002-2011 the original author or authors.
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

package org.springframework.beans.factory.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation may be used on a field or parameter as a qualifier for
 * candidate beans when autowiring. It may also be used to annotate other
 * custom annotations that can then in turn be used as qualifiers.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @since 2.5
 * @see Autowired
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {

	String value() default "";

}

```

可以看出 @Qualifier 不仅可以用在字段上（@Autowired），还可以用在方法上（@Bean），另外还有方法参数上面、类型上面以及注解上面。



##### @Qualifier 扩展注解：

```java
package org.example.thinking.in.spring.denpendency.injection.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * 用户分组注解
 * {@link Qualifier @Qualifier}
 * */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}

```



##### @LoadBalanced 源码：

```java
/**
 //这里说了RestTemplate bean 被LoadBalancerClient配置
 * Annotation to mark a RestTemplate bean to be configured to use a LoadBalancerClient
 * @author Spencer Gibb
 */
@Target({ ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface LoadBalanced {
}

```

可以看出 @LoadBalanced 标注了 @Qualifier 注解，目的是什么呢？

​	在 Spring Cloud 里面，我们通常把 RestTemplate 这个 Bean 声明成一个 Spring 的 Bean，同时要标注一下 @LoadBalanced 注解，来进行分组。因此在 Spring 里面会有两种实现，一个是 RestTemplate 的两种 Bean，另外一种是非 @LoadBalanced 没有负载均衡能力的 Bean。由于 Spring Cloud 通过 @LoadBalance 注解来进行划分，如果标注了 @LoadBalanced 注解，那么他就会在 @Qualifier 的基础上做一些扩展。

​	我们的 @UserGroup 也是同样道理，可以选择范围，比如字段上标注、方法上标注、参数以及类型还有注解上都能标注。而 @LoadBalance 注解只能放在字段、参数、方法上面。换言之就是说 @LoadBalance 不能再去扩展。





### 总结：

​	我们了解到 @Qualifier 不仅可以通过名称的方式进行限定，而且他还可以实现逻辑或者物理上的一种分组。我们也看到了 Spring Cloud 的 @LoadBalance 注解，也是基于 @Qualifier 来进行扩展。代码的演示都在 github 上了，多看多思考。







## 12：延迟依赖注入：如何限制延迟依赖注入？与延迟依赖查找是类似的吗？



### 延迟依赖注入：



### 	·	使用 API ObjectFactory 延迟注入



#### 		·	单一类型



#### 		·	集合类型



### 	·	使用 API ObjectProvider 延迟注入（推荐）



#### 		·	单一类型



#### 		·	集合类型



为什么推荐使用 ObjectyProvider 呢？结合我们前面讨论的安全性理解一下。

ObjectProvider 无论是单一类型还是集合类型，包括 String 或者 Collection 方面的一个扩展，这个扩展可以帮我们做很多事情。

相反，ObjectFactory 只能通过 get 的方法查找单一类型或集合类型。ObjectFactory 只能二选一，没有 ObjectProvider 灵活。

二者都属于延迟注入的方法。例如：先注入一个 ObjectProvider 到我们的对象里面来，同样的方式再进行第二次操作，这种方式

就是我们非延迟 Bean，进行了延迟操作。



##### 新增代码：

##### 	LazyAnnotationDependencyInjectionDemo.java		展示延迟依赖注入



##### LazyAnnotationDependencyInjectionDemo 源码：

```java
package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 通过 {@link ObjectProvider} 的方式演示延迟依赖注入
 * */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 LazyAnnotationDependencyInjectionDemo 作为 Configuration Class
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 并且创建 BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //进行依赖查找
        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        System.out.println(demo.user);

        System.out.println(demo.userObjectProvider.getIfAvailable());

        //通过 ObjectProvider 输出 dependency-lookup-context.xml 里面的所有配置的 user 对象
        demo.userObjectProvider.forEach(System.err::println);

        //关闭 Spring 应用上下文
        applicationContext.close();


    }

}

```





### 总结：

​	我们通过延迟依赖注入顺便回顾了下之前的 ObjectFactory 和 ObjectProvider 的相关内容，也提了下依赖查找的安全内容。忘了的去翻一下 github。。。

我们这次的演示代码也能看出，ObjectProvider 的安全性也能运用到依赖注入中来。如果有看 Spring Boot 或者 Spring Cloud 源码的同学，可能会注意到，

构造技术中，大量用了 ObjectProvider 来进行注入一些非必要性的依赖，这种方式可以避免一些 NoSuchBeanDefinitionException 、

NoUniqueBeanDefinitionException 等 BeansException 相关的错误。







## 13：依赖处理过程：依赖处理时会发生什么？其中与依赖查找的差异在哪里？



这一节主要是来解析以来处理的过程。

### 基础知识：

### 	·	入口 - DefaultListableBeanFactory # resolveDependency

​				这个方法通常有两个重载方法，这两个方法就是我们的基本入口。

### 	·	依赖描述符 - DependencyScriptor

### 	·	自动绑定候选对象处理器 - AutowireCandidateResolver



上述三个部分是解决我们依赖和注入的基本入口。我们在使用 Spring 的时候，入口不需要到这里来，入口在更高层次有一个实现接口，

在 DefaultListableBeanFactory # resolveDependency 的上级，**AutowireCapableBeanFactory** 中：



##### AutowireCapableBeanFactory.java 源码部分：

```java
/**
	 * Resolve the specified dependency against the beans defined in this factory.
	 	
	 	descriptor 指的是一个描述符
	 	
	 * @param descriptor the descriptor for the dependency (field/method/constructor)
	 	
	 	requestingBeanName 就是我们当前要注入的 BeanName，例如：XX 字段被 Autowire 了，外围的 Bean 就是个 Bean 名称
	 	
	 * @param requestingBeanName the name of the bean which declares the given dependency
	 * @return the resolved object, or {@code null} if none found
	 * @throws NoSuchBeanDefinitionException if no matching bean was found
	 * @throws NoUniqueBeanDefinitionException if more than one matching bean was found
	 * @throws BeansException if dependency resolution failed for any other reason
	 * @since 2.5
	 * @see #resolveDependency(DependencyDescriptor, String, Set, TypeConverter)
	 */
	@Nullable
	Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName) throws BeansException;

	/**
	 * Resolve the specified dependency against the beans defined in this factory.
	 * @param descriptor the descriptor for the dependency (field/method/constructor)
	 * @param requestingBeanName the name of the bean which declares the given dependency
	 
	 	要是找多个的话，亦可以传多个 BeanName 过来。用 Set 是为了去重，也说明这里并不要求一个强制的顺序
	 
	 * @param autowiredBeanNames a Set that all names of autowired beans (used for
	 * resolving the given dependency) are supposed to be added to
	 
	 	typeConverter 这个是我们后面要说的 Spring 类型转换。这里简单理解他是一个类型转换器就行了。
	 
	 * @param typeConverter the TypeConverter to use for populating arrays and collections
	 * @return the resolved object, or {@code null} if none found
	 * @throws NoSuchBeanDefinitionException if no matching bean was found
	 * @throws NoUniqueBeanDefinitionException if more than one matching bean was found
	 * @throws BeansException if dependency resolution failed for any other reason
	 * @since 2.5
	 * @see DependencyDescriptor
	 */
	@Nullable
	Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException;
```



看完了 AutowireCapableBeanFactory 里面的接口方法定义，再回到 DefaultListableBeanFactory 的时候会发现根本不知道怎么下手看源码。。。

很简单，先从 DependencyDescriptor 这个描述符开始看，看看DependencyDeScriptor 到底描述了描述了什么东东。



##### DependencyDescriptor.java 源码：

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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Optional;

import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.ReflectJvmMapping;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.core.KotlinDetector;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.ResolvableType;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * Descriptor for a specific dependency that is about to be injected.
 * Wraps a constructor parameter, a method parameter or a field,
 * allowing unified access to their metadata.
 *
 * @author Juergen Hoeller
 * @since 2.5
 */
@SuppressWarnings("serial")
public class DependencyDescriptor extends InjectionPoint implements Serializable {

    //标识我们当前声明注入的描述符，换句话说就是被注入的容器。。。容器就是我们声明的类
	private final Class<?> declaringClass;

    //方法名称，这里的 @Nullable 是 Spring 5 提出的一个新的注解，作用是类型检查。大意就是允许 methodName 为空
    //因为注入的时候我们不一定是方法注入。。。
	@Nullable
	private String methodName;

    //参数类型
	@Nullable
	private Class<?>[] parameterTypes;

    //参数索引，从 0 开始计数，这里的作用就是为了看用哪个参数来操作的。。
	private int parameterIndex;

    //字段名称
	@Nullable
	private String fieldName;

    //是否必须，和 @Autowired 注解里面的 required 相对应。
	private final boolean required;

    //是不是饥饿。对应 @Lazy 注解 的 value 属性。如果为 true 那么就是不饥饿 false，默认false 是饥饿的 true
	private final boolean eager;

    //嵌入层次，这里主要是指 @Autowired 可能会放在嵌套类里面的
	private int nestingLevel = 1;

    //和 declaringClass 类型，被包含的类。在什么类里面来进行包含
	@Nullable
	private Class<?> containingClass;

    //关于 ResolveableType ，这个会在 Spring 泛型处理那块说
	@Nullable
	private transient volatile ResolvableType resolvableType;

    //类型的描述，和依赖的描述类似，主要是描述类型相关的内容
	@Nullable
	private transient volatile TypeDescriptor typeDescriptor;


	下面省略一万字。。。。

}

```



可以看出，无论你是方法注入、自动注入、构造器注入，methodName，parameterTypes、fieldName 这些属性都是可选的。但是也有一种情况是有 bug 的，

当三个参数都是空的时候。。。bug 来了 - 找不到注入的来源。



这个时候是不是还有点蒙。。debug 来看，但是要记住之前说的 入口在 DefaultListableBeanFactory # resolveDependency，其上层依赖源自于

AutowireCapableBeanFactory 的两个重载 resolveDependecy ，分别需要 DependencyDeScriptor、requestingBeanName、或者是多个去重不关心顺序的

requestingBeanName，还有一个TypeRecerver 类型转换器（Spring 泛型处理那块讲）。



这些记住了还不够，还要记住 DependencyDescriptor 里面有很多属性，以及省略了一万字的方法。属性大概有

declaringClass 被注入的容器

methodName 方法名称，可空

parameterTypes 参数类型，可空

parameterIndex 参数下索引

fieldName 字段名称

required 是否必须 和 @Autowired 的 required 有关

eager 是否饥饿，和 @Lazy 的 value 有关

nestingLevel 嵌套层次

containingClass 被包含的类

resolvableType	类型转换器（Spring 泛型说）

TypeDecriptor 类型描述符



##### 记不住上来翻一下。。。



顺便提一点，有没有发现 DependencyDescriptor 继承了一个叫做 **InjectionPoint** 的类？

这是在 Spring 4.3 的时候做了一下重构，加入了 Field 类，MethodParameter 类，以及 Annotation[] ，这样就可以更好的运用反射，更好的表达语义。



比如：

```java
	@Nullable
	public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
		return (this.field != null ? this.field.getAnnotation(annotationType) :
				obtainMethodParameter().getParameterAnnotation(annotationType));
	}
```

以 LazyAnnotationDependencyInjectionDemo.java 为例子， 他就是想说我们注入的 User 对象在 LazyAnnotationDependencyInjectionDemo 里面是什么类型

的。但是 Spring 4.3 之前，这些描述都是在 DependencyDescriptor 里面来进行描述的。



有了这些基础，大致上可以了解到，DefaultListableBeanFactory 的 resolveDependency() 就是一个实时注入，并且通过类型（User.class）查找的方式进行依赖

查找。



再次以 LazyAnnotationDependencyInjectionDemo.java 为例子。。。我们注入的 User 的字段名称为 user，这个东西也是有用到的。我会在代码演示中标识

一下。这样 User 就可以称为是 LazyAnnotationDependencyInjectionDemo 对象里面的一些元信息。同理，我们也可以认为 User 对象就是一个基本的 

DependencyDescriptor 。



DependencyDescriptor ->  实时注入 + User.class（用于类型查找） + user（字段名称）

注意 ：

​	实时注入 + User.class + user 不完全等于 DependencyDescriptor ！！！

​	DependencyDescriptor 蕴含 ( -> )  实时注入 + User.class + user。。。不能理解的话去参考 **离散数学**。。。



那么完整的 DependencyDescriptor 怎么表示？

DependencyDeScriptor = DependencyDescriptor 那一坨字段。。



其实上面说 DependencyDescriptor ->  实时注入 + User.class + user 也不是特别完善。应该改为



##### 	DependencyDescriptor -> （required = true）+ 实时注入 + User.class + （eager = true）+ user + 是否首要（Primary = true）



由于类型处理还没学。。。上面表述差一点问题也不大。（基本元信息）



##### 新增文件：

##### 	AnnotationDependencyInjectionResolutionDemo.java



##### AnnotationDependencyInjectionResolutionDemo.java 源码部分：

```java
package org.example.thinking.in.spring.denpendency.injection;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 依赖注入处理过程示例代码
 * */
public class AnnotationDependencyInjectionResolutionDemo {

    /*
        我们刚才也在 README.md 中说了，一个大概完整的 DependencyDescriptor 是蕴含了一下 6 个要素的
        （required = true） + 实时注入 + User.class（通过类型进行依赖查找） + （eager = true） + user（字段名称） + 是否首要（Primary = true）
    */
    @Autowired
    private User user;


    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 AnnotationDependencyInjectionResolutionDemo 作为 Configuration Class -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        //创建 XML 读取器
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String resourcePath = "classpath:/META-INF/dependency-lookup-context.xml";

        //读取 XML 并且创建 BeanDefinition 注册到 BeanDefinitionRegister 里面
        beanDefinitionReader.loadBeanDefinitions(resourcePath);

        //启动 Spring 容器上下文
        applicationContext.refresh();

        //通过依赖查找的方式，从 Spring 上下文中获取 this -> 依赖查找
        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        User user = demo.user;

        System.out.println(user);
        
        //关闭 Spring 容器上下文
        applicationContext.close();

    }

}

```





#### 下面开始 debug 看源码。

断点打在这里。。

![image-20200915233306488](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200915233306488.png)



这是运行效果。。记住了刚才 DependencyDescriptor 描述的小伙伴继续往下走。没记住的去对对字段。。。

![image-20200915233726923](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200915233726923.png)



##### DefaultListableBeanFactory # resolveDependency 源码部分：

```java
	@Override
	@Nullable
	public Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException {

		descriptor.initParameterNameDiscovery(getParameterNameDiscoverer());
        
        /**
        	这里有一个基本的特点，就是做了一个判断，判断 DependencyDescriptor 的 type 是不是 我们的 User
        	
        	1：这里有一个关于 Optional 的判断，就是说我们在注入 User 的时候可以注入为一个 Optional
        	2：另外一个就是看注入的是不是一个 ObjectFactory 或者 ObjectProvider，可以看 LazyAnnoyationDependencyInjectionDemo。
        	3：第三个是搞了一个 javaxInjectProviderClass 我们并没有这种 JSR330 的 BeanFactory。
        	4：else 才是我们能走到的地方。。
        */
        
		if (Optional.class == descriptor.getDependencyType()) {
			return createOptionalDependency(descriptor, requestingBeanName);
		}
		else if (ObjectFactory.class == descriptor.getDependencyType() ||
				ObjectProvider.class == descriptor.getDependencyType()) {
			return new DependencyObjectProvider(descriptor, requestingBeanName);
		}
		else if (javaxInjectProviderClass == descriptor.getDependencyType()) {
			return new Jsr330Factory().createDependencyProvider(descriptor, requestingBeanName);
		}
		else {
            /**
            	这里就涉及到了 AutowireCandidateResolver 。这个东西会返回一个 null。先不去管他
            */
			Object result = getAutowireCandidateResolver().getLazyResolutionProxyIfNecessary(
					descriptor, requestingBeanName);
			if (result == null) {
                
                /**
                	敲黑板。。。到重点了。
                	
                	F7 跟进去
                	
                	一大圈代码又回来了，这里拿到的是 谁，心里有 Mysql InnerDB 底层的数据结构了哈
                */
				result = doResolveDependency(descriptor, requestingBeanName, autowiredBeanNames, typeConverter);
			}
			return result;
		}
	}
```



##### DefaultListableBeanFactory # doResolveDependency 源码：

```java
	/**
		这个方法并不是重载的！！！是 DefaultListableBeanFactory 自己写了一个方法。。。
	*/
	@Nullable
	public Object doResolveDependency(DependencyDescriptor descriptor, @Nullable String beanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException {

        //处理嵌套多次注入的一个保护点。没有嵌套的话就返回一个 null
		InjectionPoint previousInjectionPoint = ConstructorResolver.setCurrentInjectionPoint(descriptor);
        
		try {
            //所谓的快捷方式
			Object shortcut = descriptor.resolveShortcut(this);
			if (shortcut != null) {
				return shortcut;
			}

            //这里获取到我们的 User.class
			Class<?> type = descriptor.getDependencyType();
            //这里也会返回空，下面这个 if 可以跳过，因为不符合 User 的预期。
			Object value = getAutowireCandidateResolver().getSuggestedValue(descriptor);
			if (value != null) {
				if (value instanceof String) {
					String strVal = resolveEmbeddedValue((String) value);
					BeanDefinition bd = (beanName != null && containsBean(beanName) ?
							getMergedBeanDefinition(beanName) : null);
					value = evaluateBeanDefinitionString(strVal, bd);
				}
				TypeConverter converter = (typeConverter != null ? typeConverter : getTypeConverter());
				try {
					return converter.convertIfNecessary(value, type, descriptor.getTypeDescriptor());
				}
				catch (UnsupportedOperationException ex) {
					// A custom TypeConverter which does not support TypeDescriptor resolution...
					return (descriptor.getField() != null ?
							converter.convertIfNecessary(value, type, descriptor.getField()) :
							converter.convertIfNecessary(value, type, descriptor.getMethodParameter()));
				}
			}

            //判断是不是多个 Bean -> 注入 User 的话一样返回空，不先关注
			Object multipleBeans = resolveMultipleBeans(descriptor, beanName, autowiredBeanNames, typeConverter);
			if (multipleBeans != null) {
				return multipleBeans;
			}

            /**
            	重点 ！！！
            	这里的 beanName 就是 DefaultListableBeanFactory # resolveDepdency 的 requesting。
            	哪个 Bean 被注入了就是哪个 Bean （annotationDependencyInjectionResolutionDemo）
            	
            	type ： class org.example.thinking.in.spring.ioc.overview.dependency.domain.User
            	
            	descriptor : field 'user'
            	
            	这里肯定会返回多个，因为 dependency-lookup-context.xml 里面有 User 和 SuperUser，被 XMLBeanDefinitionReader 
            	都搞到上下文里面去了，这个 doResolveDependency 又是包含在 DefaultListableBeanFactory 里面的，所以这里能找到当前
            	Spring 上下文中所管理的类。

				这里必须按个 F7 进去看看。
            	
            */
			Map<String, Object> matchingBeans = findAutowireCandidates(beanName, type, descriptor);
			if (matchingBeans.isEmpty()) {
				if (isRequired(descriptor)) {
					raiseNoMatchingBeanFound(type, descriptor.getResolvableType(), descriptor);
				}
				return null;
			}

			String autowiredBeanName;
			Object instanceCandidate;

            //在多个 User 里面找到 primary 
			if (matchingBeans.size() > 1) {
                /**
                	预判候选者（Candidate） 
                	
                	F7 跟进去
                */
				autowiredBeanName = determineAutowireCandidate(matchingBeans, descriptor);
				if (autowiredBeanName == null) {
					if (isRequired(descriptor) || !indicatesMultipleBeans(type)) {
						return descriptor.resolveNotUnique(descriptor.getResolvableType(), matchingBeans);
					}
					else {
						// In case of an optional Collection/Map, silently ignore a non-unique case:
						// possibly it was meant to be an empty collection of multiple regular beans
						// (before 4.3 in particular when we didn't even look for collection beans).
						return null;
					}
				}
                
                /**
                	在 findAutowireCandidate 的时候，返回了matchingBeans 对象是一个 LinkedHashMap
                	里面装了 user 和 superUser
                	
                	根据 datermineAutowireCandidate 返回的 beanName 作为找到 SuperUser.class
                */
				instanceCandidate = matchingBeans.get(autowiredBeanName);
			}
			else {
				// We have exactly one match.
				Map.Entry<String, Object> entry = matchingBeans.entrySet().iterator().next();
				autowiredBeanName = entry.getKey();
				instanceCandidate = entry.getValue();
			}

			if (autowiredBeanNames != null) {
                // SuperUser 的名称添加到 autowiredBeanNames 里面，参数传来的哪个 Set -> 第三个参数
				autowiredBeanNames.add(autowiredBeanName);
			}
			if (instanceCandidate instanceof Class) {
                //通过 beanName 进行依赖查找，并且改变引用指向
				instanceCandidate = descriptor.resolveCandidate(autowiredBeanName, type, this);
			}
            //赋值
			Object result = instanceCandidate;
			if (result instanceof NullBean) {
				if (isRequired(descriptor)) {
					raiseNoMatchingBeanFound(type, descriptor.getResolvableType(), descriptor);
				}
				result = null;
			}
			if (!ClassUtils.isAssignableValue(type, result)) {
				throw new BeanNotOfRequiredTypeException(autowiredBeanName, type, instanceCandidate.getClass());
			}
            //返回对象，回到 DefaultListableBeanFactory # resolveDependency()
			return result;
		}
		finally {
            //删除 ThreadLocal 中缓存的 当前注入的 User 对象
			ConstructorResolver.setCurrentInjectionPoint(previousInjectionPoint);
		}
	}
```



##### DefaultListableBeanFactory # findAutowireCandidates() 源码：

```java
/**
	 * Find bean instances that match the required type.
	 * Called during autowiring for the specified bean.
	 * @param beanName the name of the bean that is about to be wired
	 * @param requiredType the actual type of bean to look for
	 * (may be an array component type or collection element type)
	 * @param descriptor the descriptor of the dependency to resolve
	 * @return a Map of candidate names and candidate instances that match
	 * the required type (never {@code null})
	 * @throws BeansException in case of errors
	 * @see #autowireByType
	 * @see #autowireConstructor
	 */
	protected Map<String, Object> findAutowireCandidates(
			@Nullable String beanName, Class<?> requiredType, DependencyDescriptor descriptor) {

        /**
        	this（lbf） : 当前的 ListableBeanFactory（DefaultListableBeanFactory 是他的子类）
        	requiredType : 我们的 User。。
        	true : includeNonSingletons，去掉非单例的
        	descriptor.isEager() : allowEagerInit，是否懒加载（延迟加载）
        	
        	因为我们的 User 均来自 dependency-lookup-context.xml 里面，所以会有两个 name -> user、superUser
        	
        	数组里面的 beanName 和 xml 里面的顺序一致 ！
        	因为在 BeanFactory 里面注册是先来先注册。
        	
        */
		String[] candidateNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
				this, requiredType, true, descriptor.isEager());
        
		Map<String, Object> result = new LinkedHashMap<>(candidateNames.length);
        
        /**
        	resolvableDependencies 后面再说。
        	
        	这里面对应有四个接口：
        		ApplicationContext
        		ApplicationEventPublisher
        		BeanFactory
        		ResourceLoader
        */ 
		for (Map.Entry<Class<?>, Object> classObjectEntry : this.resolvableDependencies.entrySet()) {
			Class<?> autowiringType = classObjectEntry.getKey();
			if (autowiringType.isAssignableFrom(requiredType)) {
				Object autowiringValue = classObjectEntry.getValue();
				autowiringValue = AutowireUtils.resolveAutowiringValue(autowiringValue, requiredType);
				if (requiredType.isInstance(autowiringValue)) {
					result.put(ObjectUtils.identityToString(autowiringValue), autowiringValue);
					break;
				}
			}
		}
        
        //循环 beanName
		for (String candidate : candidateNames) {
            
            //是否自己的引用对象 && 是否 Autowired
			if (!isSelfReference(beanName, candidate) && isAutowireCandidate(candidate, descriptor)) {
                /**
                	这个地方加了两次。
                */
				addCandidateEntry(result, candidate, descriptor, requiredType);
			}
		}
        
        //判断是否多个 Bean
		if (result.isEmpty()) {
            
			boolean multiple = indicatesMultipleBeans(requiredType);
			// Consider fallback matches if the first pass failed to find anything...
			DependencyDescriptor fallbackDescriptor = descriptor.forFallbackMatch();
			for (String candidate : candidateNames) {
				if (!isSelfReference(beanName, candidate) && isAutowireCandidate(candidate, fallbackDescriptor) &&
						(!multiple || getAutowireCandidateResolver().hasQualifier(descriptor))) {
					addCandidateEntry(result, candidate, descriptor, requiredType);
				}
			}
			if (result.isEmpty() && !multiple) {
				// Consider self references as a final pass...
				// but in the case of a dependency collection, not the very same bean itself.
				for (String candidate : candidateNames) {
					if (isSelfReference(beanName, candidate) &&
							(!(descriptor instanceof MultiElementDescriptor) || !beanName.equals(candidate)) &&
							isAutowireCandidate(candidate, fallbackDescriptor)) {
						addCandidateEntry(result, candidate, descriptor, requiredType);
					}
				}
			}
		}
        
        //返回,继续回到 DefaultListableBeanFactory # doResolveDependency
		return result;
	}
```



##### DefaultListableBeanFactory # determineAutowireCandidate 源码：

```java
/**
	 * Determine the autowire candidate in the given set of beans.
	 * <p>Looks for {@code @Primary} and {@code @Priority} (in that order).
	 * @param candidates a Map of candidate names and candidate instances
	 * that match the required type, as returned by {@link #findAutowireCandidates}
	 * @param descriptor the target dependency to match against
	 * @return the name of the autowire candidate, or {@code null} if none found
	 */
	@Nullable
	protected String determineAutowireCandidate(Map<String, Object> candidates, DependencyDescriptor descriptor) {
		Class<?> requiredType = descriptor.getDependencyType();

        /**
        	在这里进行判断的 primary，走到这里会得到 SuperUser
        	
        	继续 F7
        */
		String primaryCandidate = determinePrimaryCandidate(candidates, requiredType);
		if (primaryCandidate != null) {
            
            /**
            	这里返回的只是 bean name
            	
            	他是在 BeanDefinition 里面去判断 哪个 Bean 是首要（Primary）的 Bean。
            	
            	继续回到，DefaultListableBeanFactory # doResolveDependency
            */
			return primaryCandidate;
		}
		String priorityCandidate = determineHighestPriorityCandidate(candidates, requiredType);
		if (priorityCandidate != null) {
			return priorityCandidate;
		}
		// Fallback
		for (Map.Entry<String, Object> entry : candidates.entrySet()) {
			String candidateName = entry.getKey();
			Object beanInstance = entry.getValue();
			if ((beanInstance != null && this.resolvableDependencies.containsValue(beanInstance)) ||
					matchesBeanName(candidateName, descriptor.getDependencyName())) {
				return candidateName;
			}
		}
		return null;
	}
```



##### DefaultListableBeanFactory # determinePrimaryCandidate() 源码：

```java
/**
	 * Determine the primary candidate in the given set of beans.
	 * @param candidates a Map of candidate names and candidate instances
	 * (or candidate classes if not created yet) that match the required type
	 * @param requiredType the target dependency type to match against
	 * @return the name of the primary candidate, or {@code null} if none found
	 * @see #isPrimary(String, Object)
	 */
	@Nullable
	protected String determinePrimaryCandidate(Map<String, Object> candidates, Class<?> requiredType) {
		String primaryBeanName = null;
		for (Map.Entry<String, Object> entry : candidates.entrySet()) {
			String candidateBeanName = entry.getKey();
			Object beanInstance = entry.getValue();
            
            //在这里进行判断的
			if (isPrimary(candidateBeanName, beanInstance)) {
				if (primaryBeanName != null) {
					boolean candidateLocal = containsBeanDefinition(candidateBeanName);
					boolean primaryLocal = containsBeanDefinition(primaryBeanName);
					if (candidateLocal && primaryLocal) {
						throw new NoUniqueBeanDefinitionException(requiredType, candidates.size(),
								"more than one 'primary' bean found among candidates: " + candidates.keySet());
					}
					else if (candidateLocal) {
						primaryBeanName = candidateBeanName;
					}
				}
				else {
					primaryBeanName = candidateBeanName;
				}
			}
		}
        //返回，DefaultListableBeanFactory # determineAutowireCandidate
		return primaryBeanName;
	}
```





##### DefaultListableBeanFactory # isPrimary() 源码：

```java
/**
	 * Return whether the bean definition for the given bean name has been
	 * marked as a primary bean.
	 * @param beanName the name of the bean
	 * @param beanInstance the corresponding bean instance (can be null)
	 * @return whether the given bean qualifies as primary
	 */
	protected boolean isPrimary(String beanName, Object beanInstance) {
		String transformedBeanName = transformedBeanName(beanName);
		if (containsBeanDefinition(transformedBeanName)) {
            
            /**
            	这个方法就在我们的 BeanDefinition 的元信息里面 -> isPrimary()
            	
            	默认情况下 BeanDefinition 的实现类 AbstractBeanDefinition 默认 false
            	private boolean primary = false;
            	
            	superUser 在 XMLBeanDefinitionReader # loadBeanDefinitions 的时候就已经把 primary 标签解析，
            	放入了 BeanDefinition 中了。所以 superUser 的 primary = true。
            */
            
			return getMergedLocalBeanDefinition(transformedBeanName).isPrimary();
		}
		BeanFactory parent = getParentBeanFactory();
        
        //返回，DefaultListableBeanFactory # determinePrimaryCandidate()
		return (parent instanceof DefaultListableBeanFactory &&
				((DefaultListableBeanFactory) parent).isPrimary(transformedBeanName, beanInstance));
	}
```



这一整个分析就运用到了最开始说的三个点：

##### DefaultListableBeanFactory # resolveDependency		入口

##### DependencyDescriptor		依赖描述符

​	包含了注入的元信息。

##### AutowireCandidateResolver		自动绑定候选处理器



看完这点还不能超神。。。后面还要深入理解和研究。





#### 集合类的 @Autowired 注入，Spring 做了哪些处理？

继续回到 **AnnotationDependencyInjectResolutionDemo.java** 



```java
// key 就是 beanName，value 就是返回的 User 对象 -> 集合类型的依赖注入
@Autowired
private Map<String,User> users;
```



DefaultListableBeanFactory # resolveDependency 的第一个断点不变。直接到 doResolveDependency() 方法，去看重点。

一直到 

```java
InjectionPoint previousInjectionPoint = ConstructorResolver.setCurrentInjectionPoint(descriptor);
```

shortcut（快捷方式） 还是 null -> 不看

```java
Object shortcut = descriptor.resolveShortcut(this);
```



BeanFactory的自动装配候选解析器 也是空 -> 不看

```java
Object value = getAutowireCandidateResolver().getSuggestedValue(descriptor);
```



咱们注入的 集合 User 是 Map<String,User> 这里也是空，假如是 List 或者数组这里就有了。 pass

```java
Object multipleBeans = resolveMultipleBeans(descriptor, beanName, autowiredBeanNames, typeConverter);
```



寻找自动注入的候选者还是两个 -> User 、SuperUser

```java
Map<String, Object> matchingBeans = findAutowireCandidates(beanName, type, descriptor);
```



在给定的bean组中确定自动装配候选。返回的还是 SuperUser。

```java
autowiredBeanName = determineAutowireCandidate(matchingBeans, descriptor);
```



还是获取的 SuperUser

```java
instanceCandidate = matchingBeans.get(autowiredBeanName);
```



依然添加 SuperUser 的 beanName -> superUser

```java
if (autowiredBeanNames != null) {
	autowiredBeanNames.add(autowiredBeanName);
}
```



创建出 SuperUser

```java
if (instanceCandidate instanceof Class) {
	instanceCandidate = descriptor.resolveCandidate(autowiredBeanName, type, this);
}
```



返回 SuperUser 对象和之前一样。这里当做回顾一下之前看的那一遍单一类型自动依赖注入。。哈哈



##### 第二次进来就是集合类型自动注入了~

字段的名称变化了~

![image-20200916010044304](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200916010044304.png)



字段的类型也变了~

![image-20200916010226170](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200916010226170.png)



这一次就会走到 **resolveMultipleBeans()** 方法里面来了



##### DefaultListableBeanFactory # resolveMultipleBeans 源码 ：

```java
@Nullable
	private Object resolveMultipleBeans(DependencyDescriptor descriptor, @Nullable String beanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) {

        //从上面那个图可以看出，这里是个 Map.class
		final Class<?> type = descriptor.getDependencyType();

        //判断给的是不是一个 java8 的 stream 类型
		if (descriptor instanceof StreamDependencyDescriptor) {
			Map<String, Object> matchingBeans = findAutowireCandidates(beanName, type, descriptor);
			if (autowiredBeanNames != null) {
				autowiredBeanNames.addAll(matchingBeans.keySet());
			}
			Stream<Object> stream = matchingBeans.keySet().stream()
					.map(name -> descriptor.resolveCandidate(name, type, this))
					.filter(bean -> !(bean instanceof NullBean));
			if (((StreamDependencyDescriptor) descriptor).isOrdered()) {
				stream = stream.sorted(adaptOrderComparator(matchingBeans));
			}
			return stream;
		}
        //是否为数组
		else if (type.isArray()) {
			Class<?> componentType = type.getComponentType();
			ResolvableType resolvableType = descriptor.getResolvableType();
			Class<?> resolvedArrayType = resolvableType.resolve(type);
			if (resolvedArrayType != type) {
				componentType = resolvableType.getComponentType().resolve();
			}
			if (componentType == null) {
				return null;
			}
			Map<String, Object> matchingBeans = findAutowireCandidates(beanName, componentType,
					new MultiElementDescriptor(descriptor));
			if (matchingBeans.isEmpty()) {
				return null;
			}
			if (autowiredBeanNames != null) {
				autowiredBeanNames.addAll(matchingBeans.keySet());
			}
			TypeConverter converter = (typeConverter != null ? typeConverter : getTypeConverter());
			Object result = converter.convertIfNecessary(matchingBeans.values(), resolvedArrayType);
			if (result instanceof Object[]) {
				Comparator<Object> comparator = adaptDependencyComparator(matchingBeans);
				if (comparator != null) {
					Arrays.sort((Object[]) result, comparator);
				}
			}
			return result;
		}
        //是否是单值类集合
		else if (Collection.class.isAssignableFrom(type) && type.isInterface()) {
			Class<?> elementType = descriptor.getResolvableType().asCollection().resolveGeneric();
			if (elementType == null) {
				return null;
			}
			Map<String, Object> matchingBeans = findAutowireCandidates(beanName, elementType,
					new MultiElementDescriptor(descriptor));
			if (matchingBeans.isEmpty()) {
				return null;
			}
			if (autowiredBeanNames != null) {
				autowiredBeanNames.addAll(matchingBeans.keySet());
			}
			TypeConverter converter = (typeConverter != null ? typeConverter : getTypeConverter());
			Object result = converter.convertIfNecessary(matchingBeans.values(), type);
            //是否为 List
			if (result instanceof List) {
				Comparator<Object> comparator = adaptDependencyComparator(matchingBeans);
				if (comparator != null) {
					((List<?>) result).sort(comparator);
				}
			}
			return result;
		}
        //是否为 Map 键值对集合
		else if (Map.class == type) {
            /**
            	我们注入的字段是 AnnotationDependencyInjectionResolutionDemo 里面的 users
            	所以在 DependencyDescriptor 里面就有一个对应的关系放在 Field 里面。
            	我们可以看一下泛型相关的东西，后面迟早要看，先看一眼

				((Field)descript.getAnnotatedElement()).getGenericInfo()
				
				可以看到 genericType 是一个 Map<String,User>
				
				这样一个 API 就简化了 java 8 的操作
            	
            */
			ResolvableType mapType = descriptor.getResolvableType().asMap();
            //获取 key 的 Class
			Class<?> keyType = mapType.resolveGeneric(0);
			if (String.class != keyType) {
				return null;
			}
            //获取 value 的 Class，暴露集合对象的元素信息，可以作为 Bean 的 type（User）
			Class<?> valueType = mapType.resolveGeneric(1);
			if (valueType == null) {
				return null;
			}
            
            //由于 value 会暴露集合对象的元素信息，这样 findAutowireCandidates 可以通过类型查找，得到两个 User
			Map<String, Object> matchingBeans = findAutowireCandidates(beanName, valueType,
					new MultiElementDescriptor(descriptor));
			if (matchingBeans.isEmpty()) {
				return null;
			}
			if (autowiredBeanNames != null) {
				autowiredBeanNames.addAll(matchingBeans.keySet());
			}
            //返回结果
			return matchingBeans;
		}
		else {
			return null;
		}
	}
```





### 其实还没完。。。还一个 Optional 的例子。

```java
//这个是有填充的 肯定是选 primary，因此这里返回的 User 是 SuperUser
@Autowired
private Optional<User> userOptional;
```



这个时候 **DefaultListableBeanFactory # resolveDependency()** 中的 **Optional.class** 判断的地方就会触发：

```java
@Override
	@Nullable
	public Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException {

		descriptor.initParameterNameDiscovery(getParameterNameDiscoverer());
        
        /**
        	这个地方~~~~
        	不先管 DependencyDescriptor 里面的 类型
        	
        	((Field)descriptor.getAnnotatedElement()).getType().getGenericInfo()

			...
			factory = CoreReflectionFactory 这里面 decl 的 name 就是 java.util.Optional
			tree = ClassTypeSignature
        */
		if (Optional.class == descriptor.getDependencyType()) {
			return createOptionalDependency(descriptor, requestingBeanName);
		}
		else if (ObjectFactory.class == descriptor.getDependencyType() ||
				ObjectProvider.class == descriptor.getDependencyType()) {
			return new DependencyObjectProvider(descriptor, requestingBeanName);
		}
		else if (javaxInjectProviderClass == descriptor.getDependencyType()) {
			return new Jsr330Factory().createDependencyProvider(descriptor, requestingBeanName);
		}
		else {
			Object result = getAutowireCandidateResolver().getLazyResolutionProxyIfNecessary(
					descriptor, requestingBeanName);
			if (result == null) {
				result = doResolveDependency(descriptor, requestingBeanName, autowiredBeanNames, typeConverter);
			}
			return result;
		}
	}
```



@Lazy 的处理方式

```java
@Autowired
@Lazy
private User lazyUser;
```



![image-20200916014023658](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200916014023658.png)



这里会返回一个 CGLIB 增强的对象，为什么呢？

因为这个时候并没有执行任何相关的依赖查找工作，而是返回一个代理对象，当方法去执行的时候，再进行依赖查找，在依赖查找的时候执行 resolve，

因此 DefaultListableBeanFactory # doResolveDependency 米有进。



### 总结：

​		我们今天讨论了 单一注入、延迟注入、集合注入 的方式在 Spring IoC 依赖处理过程中，主要是通过 DefaultListableBeanFactory # resolveDependency 作

为入口，同时可以处理 Optional 对象封装、类型封装、ObjectFactory  + ObjectProvider，以及 JSR-330 （Rod Johnson 提出的关于 Java EE 里面的依赖注入方案 

-> @Inject）。除此之外就是去处理 Spring 的默认操作，Spring 的默认依赖处理主要是在 DefaultListableBeanFactory # resolveDependency 里面来做的。

只不过依赖处理是依赖注入的一个环节，在注入的过程中，把对象的依赖进行解析，然后插入（反射）的方式来调入。



下一章继续讨论 @Autowired 注解处理依赖注入的一个基本过程。





## 14：@Autowired 注入：@Autowired 注入的规则和原理有哪些？



### @Autowired 注入过程



### 	·	元信息解析

​			在前面自动依赖注入处理过程中我们看到有一个类叫做 DependencyDescriptor 的类（依赖描述符），这里面记录了我要注入的对象是哪个字段，例如字段名称、字段相关的元信息（ Java 反射中的 Field ，4.3 引入的 InjectionPoint ）。这部分就会把相关的元信息全部加载进来，帮助我们去分析字段类型，方法名称等等。

### 	·	依赖查找

​			这里可以认为是依赖处理。

### 	·	依赖注入（字段、方法）



我们可以看出基本的一个操作思路，首先解析，其次依赖查找，最后注入到字段或者方法。

解析就是指：谁要进行注入；

查找就是指：查找出依赖的根源；

注入到字段或方法是指：注入的一个对象



##### 再来到我们的 DefaultListableBeanFactory # resolveDependency 方法：

```java
	@Override
	@Nullable
	public Object resolveDependency(DependencyDescriptor descriptor, @Nullable String requestingBeanName,
			@Nullable Set<String> autowiredBeanNames, @Nullable TypeConverter typeConverter) throws BeansException {

		descriptor.initParameterNameDiscovery(getParameterNameDiscoverer());
		if (Optional.class == descriptor.getDependencyType()) {
			return createOptionalDependency(descriptor, requestingBeanName);
		}
		else if (ObjectFactory.class == descriptor.getDependencyType() ||
				ObjectProvider.class == descriptor.getDependencyType()) {
			return new DependencyObjectProvider(descriptor, requestingBeanName);
		}
		else if (javaxInjectProviderClass == descriptor.getDependencyType()) {
			return new Jsr330Factory().createDependencyProvider(descriptor, requestingBeanName);
		}
		else {
			Object result = getAutowireCandidateResolver().getLazyResolutionProxyIfNecessary(
					descriptor, requestingBeanName);
			if (result == null) {
				result = doResolveDependency(descriptor, requestingBeanName, autowiredBeanNames, typeConverter);
			}
			return result;
		}
	}
```



在这里打断点， IDEA 会有一个调用链的堆栈，

![image-20200916223751672](C:\Users\WTY\AppData\Roaming\Typora\typora-user-images\image-20200916223751672.png)



这里有一个类是 **AutowiredAnnotationBeanPostProcessor**，这里面有一个嵌套类叫做 **AutowiredFieldElement**（自动绑定的元素，是个内置类） 点一下进去。



##### AutowiredAnnotationBeanPostProcessor 源码：

```java
		/**
			bean : 我们的 AnnotationDependencyInjectionDemo
			beanName : annotationDependencyInjectionDemo 通过 BeanNameGeneric 首字母大写。
		*/
		@Override
		protected void inject(Object bean, @Nullable String beanName, @Nullable PropertyValues pvs) throws Throwable {
			
            /**
            	这里的 Field 并不是所有的 Field ，只有标注了 @Autowired 注解的字段
            	可以猜测到，前面的操作肯定做了一些筛选，来进行查找查找的。
            	可以猜到我们的 AnnotationDependencyInjectionDemo 会有四个字段，因为我们标注了四个 @Autowired 字段
            	这里会跑 4 遍，虽然我们的 4 个字段是各种不同的包装，但是那些只是说依赖注入处理过程的不同，和这里的依赖注入没关系。
            	依赖注入这里只关心我能找到我相关的被注入的对象，并且能够反射赋值就行了
            	
            	元信息解析，主要是关于字段的操作
            */
            Field field = (Field) this.member;
			Object value;
			if (this.cached) {
				value = resolvedCachedArgument(beanName, this.cachedFieldValue);
			}
			else {
                //从这里创建的 DependencyDescriptor 对象，发现可以把字段丢进去，也可以把是否必须丢进去
				DependencyDescriptor desc = new DependencyDescriptor(field, this.required);
				desc.setContainingClass(bean.getClass());
				Set<String> autowiredBeanNames = new LinkedHashSet<>(1);
				Assert.state(beanFactory != null, "No BeanFactory available");
				TypeConverter typeConverter = beanFactory.getTypeConverter();
				try {
                    //1：这里属于依赖处理的过程，这个 value 会在后面以反射的方式赋值
                    //这里主要是找到我们要依赖注入的的对象
					value = beanFactory.resolveDependency(desc, beanName, autowiredBeanNames, typeConverter);
				}
				catch (BeansException ex) {
					throw new UnsatisfiedDependencyException(null, beanName, new InjectionPoint(field), ex);
				}
				synchronized (this) {
					if (!this.cached) {
						if (value != null || this.required) {
							this.cachedFieldValue = desc;
							registerDependentBeans(beanName, autowiredBeanNames);
							if (autowiredBeanNames.size() == 1) {
								String autowiredBeanName = autowiredBeanNames.iterator().next();
								if (beanFactory.containsBean(autowiredBeanName) &&
										beanFactory.isTypeMatch(autowiredBeanName, field.getType())) {
									this.cachedFieldValue = new ShortcutDependencyDescriptor(
											desc, autowiredBeanName, field.getType());
								}
							}
						}
						else {
							this.cachedFieldValue = null;
						}
						this.cached = true;
					}
				}
			}
			if (value != null) {
                //反射的方式赋值
                //找到依赖注入相应的字段，进行赋值
				ReflectionUtils.makeAccessible(field);
				field.set(bean, value);
			}
		}
	}
```



同样道理，根据 IDEA 给的调用链路继续往上找 **InjectionMetadata # inject() **

```java
public void inject(Object target, @Nullable String beanName, @Nullable PropertyValues pvs) throws Throwable {
		Collection<InjectedElement> checkedElements = this.checkedElements;
		Collection<InjectedElement> elementsToIterate =
				(checkedElements != null ? checkedElements : this.injectedElements);
		if (!elementsToIterate.isEmpty()) {
			for (InjectedElement element : elementsToIterate) {
				if (logger.isTraceEnabled()) {
					logger.trace("Processing injected element of bean '" + beanName + "': " + element);
				}
				element.inject(target, beanName, pvs);
			}
		}
	}
```



**AutowiredAnnotationBeanPostProcessor # postProcessProperties()** 

这里涉及了一些 Spring Bean 生命周期的东西，通过这个 postProcessProperties() 找他的父类，

##### -> InstantiationAwareBeanPostProcessorAdaptor

##### 	->InstantiationAwareBeanPostProcessor （这是个接口）发现这个方法是 Spring 5.1 的时候新加的，老版本的方法在下面，标注了不推荐使用

字面意思来看 InstantiationAwareBeanPostProcessor 的意思是 **具有初始化意识的 BeanPostProcessor **，继承了 **BeanPostProcessor**



##### BeanPostProcessor 源码：对 Bean 初始化之前，之后，对自定义的 Bean 做一些初始化的操作

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

import org.springframework.beans.BeansException;
import org.springframework.lang.Nullable;

/**
 * Factory hook that allows for custom modification of new bean instances &mdash;
 * for example, checking for marker interfaces or wrapping beans with proxies.
 *
 * <p>Typically, post-processors that populate beans via marker interfaces
 * or the like will implement {@link #postProcessBeforeInitialization},
 * while post-processors that wrap beans with proxies will normally
 * implement {@link #postProcessAfterInitialization}.
 *
 * <h3>Registration</h3>
 * <p>An {@code ApplicationContext} can autodetect {@code BeanPostProcessor} beans
 * in its bean definitions and apply those post-processors to any beans subsequently
 * created. A plain {@code BeanFactory} allows for programmatic registration of
 * post-processors, applying them to all beans created through the bean factory.
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanPostProcessor} beans that are registered programmatically with a
 * {@code BeanFactory} will be applied in the order of registration; any ordering
 * semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanPostProcessor} beans.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 10.10.2003
 * @see InstantiationAwareBeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see ConfigurableBeanFactory#addBeanPostProcessor
 * @see BeanFactoryPostProcessor
 */
public interface BeanPostProcessor {

	/**
	 * Apply this {@code BeanPostProcessor} to the given new bean instance <i>before</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 */
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	/**
	 * Apply this {@code BeanPostProcessor} to the given new bean instance <i>after</i> any bean
	 * initialization callbacks (like InitializingBean's {@code afterPropertiesSet}
	 * or a custom init-method). The bean will already be populated with property values.
	 * The returned bean instance may be a wrapper around the original.
	 * <p>In case of a FactoryBean, this callback will be invoked for both the FactoryBean
	 * instance and the objects created by the FactoryBean (as of Spring 2.0). The
	 * post-processor can decide whether to apply to either the FactoryBean or created
	 * objects or both through corresponding {@code bean instanceof FactoryBean} checks.
	 * <p>This callback will also be invoked after a short-circuiting triggered by a
	 * {@link InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation} method,
	 * in contrast to all other {@code BeanPostProcessor} callbacks.
	 * <p>The default implementation returns the given {@code bean} as-is.
	 * @param bean the new bean instance
	 * @param beanName the name of the bean
	 * @return the bean instance to use, either the original or a wrapped one;
	 * if {@code null}, no subsequent BeanPostProcessors will be invoked
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet
	 * @see org.springframework.beans.factory.FactoryBean
	 */
	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}

```



##### InstantiationAwareBeanPostProcessor 源码：

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

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.lang.Nullable;

/**
 * Subinterface of {@link BeanPostProcessor} that adds a before-instantiation callback,
 * and a callback after instantiation but before explicit properties are set or
 * autowiring occurs.
 *
 * <p>Typically used to suppress default instantiation for specific target beans,
 * for example to create proxies with special TargetSources (pooling targets,
 * lazily initializing targets, etc), or to implement additional injection strategies
 * such as field injection.
 *
 * <p><b>NOTE:</b> This interface is a special purpose interface, mainly for
 * internal use within the framework. It is recommended to implement the plain
 * {@link BeanPostProcessor} interface as far as possible, or to derive from
 * {@link InstantiationAwareBeanPostProcessorAdapter} in order to be shielded
 * from extensions to this interface.
 *
 * @author Juergen Hoeller
 * @author Rod Johnson
 * @since 1.2
 * @see org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator#setCustomTargetSourceCreators
 * @see org.springframework.aop.framework.autoproxy.target.LazyInitTargetSourceCreator
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

	/**
	 * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>.
	 * The returned bean object may be a proxy to use instead of the target bean,
	 * effectively suppressing default instantiation of the target bean.
	 * <p>If a non-null object is returned by this method, the bean creation process
	 * will be short-circuited. The only further processing applied is the
	 * {@link #postProcessAfterInitialization} callback from the configured
	 * {@link BeanPostProcessor BeanPostProcessors}.
	 * <p>This callback will be applied to bean definitions with their bean class,
	 * as well as to factory-method definitions in which case the returned bean type
	 * will be passed in here.
	 * <p>Post-processors may implement the extended
	 * {@link SmartInstantiationAwareBeanPostProcessor} interface in order
	 * to predict the type of the bean object that they are going to return here.
	 * <p>The default implementation returns {@code null}.
	 * @param beanClass the class of the bean to be instantiated
	 * @param beanName the name of the bean
	 * @return the bean object to expose instead of a default instance of the target bean,
	 * or {@code null} to proceed with default instantiation
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see #postProcessAfterInstantiation
	 * @see org.springframework.beans.factory.support.AbstractBeanDefinition#getBeanClass()
	 * @see org.springframework.beans.factory.support.AbstractBeanDefinition#getFactoryMethodName()
	 */
	@Nullable
	default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	/**
	 * Perform operations after the bean has been instantiated, via a constructor or factory method,
	 * but before Spring property population (from explicit properties or autowiring) occurs.
	 * <p>This is the ideal callback for performing custom field injection on the given bean
	 * instance, right before Spring's autowiring kicks in.
	 * <p>The default implementation returns {@code true}.
	 * @param bean the bean instance created, with properties not having been set yet
	 * @param beanName the name of the bean
	 * @return {@code true} if properties should be set on the bean; {@code false}
	 * if property population should be skipped. Normal implementations should return {@code true}.
	 * Returning {@code false} will also prevent any subsequent InstantiationAwareBeanPostProcessor
	 * instances being invoked on this bean instance.
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see #postProcessBeforeInstantiation
	 */
	default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		return true;
	}

	/**
	
		它对应这我们的 AbstractBeanDefinition 的 setPropertiesValues()
		

		public void setPropertyValues(MutablePropertyValues propertyValues) {
			this.propertyValues = propertyValues;
		}
		
		MutablePropertyValues：使我们的属性和 value 对应起来
		
		简单说，找到我们的 dependency-lookup-context.xml，里面配置很多 bean 标签，
		bean 标签里面包含了很多属性 property 标签
		
		这些东西就是我们看到的 setPropertyValues() 所需要的的东西
		
		可以去 setPropertyValues() 里面打个断点，第一个 User 对象有七个属性，所以就会有对应的七个内容
		这七个内容的类型（PropertyValue 的 value 属性）都是 TypedStringValue ，也就是 String 类型
		
		综上实验可以得出结论，setPropertyValue 就是把我们的 XML 解析成 Spring 对象的一个过程 
		
		假如说在 User 对象的 setId 方法打个断点，Spring 就会通过反射的类型 把 String 的 value 值，映射
		成为一个 Long 类型的 value 值，并且赋值给 User 对象。
		
		这里涉及了两步操作：
			1：读取 XML 配置，把 XML 内容变成配置项；
			2：配置项进行类型转换，赋值
	
	 * Post-process the given property values before the factory applies them
	 * to the given bean, without any need for property descriptors.
	 * <p>Implementations should return {@code null} (the default) if they provide a custom
	 * {@link #postProcessPropertyValues} implementation, and {@code pvs} otherwise.
	 * In a future version of this interface (with {@link #postProcessPropertyValues} removed),
	 * the default implementation will return the given {@code pvs} as-is directly.
	 * @param pvs the property values that the factory is about to apply (never {@code null})
	 * @param bean the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return the actual property values to apply to the given bean (can be the passed-in
	 * PropertyValues instance), or {@code null} which proceeds with the existing properties
	 * but specifically continues with a call to {@link #postProcessPropertyValues}
	 * (requiring initialized {@code PropertyDescriptor}s for the current bean class)
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @since 5.1
	 * @see #postProcessPropertyValues
	 */
	@Nullable
	default PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
			throws BeansException {

		return null;
	}

	/**
	 * Post-process the given property values before the factory applies them
	 * to the given bean. Allows for checking whether all dependencies have been
	 * satisfied, for example based on a "Required" annotation on bean property setters.
	 * <p>Also allows for replacing the property values to apply, typically through
	 * creating a new MutablePropertyValues instance based on the original PropertyValues,
	 * adding or removing specific values.
	 * <p>The default implementation returns the given {@code pvs} as-is.
	 * @param pvs the property values that the factory is about to apply (never {@code null})
	 * @param pds the relevant property descriptors for the target bean (with ignored
	 * dependency types - which the factory handles specifically - already filtered out)
	 * @param bean the bean instance created, but whose properties have not yet been set
	 * @param beanName the name of the bean
	 * @return the actual property values to apply to the given bean (can be the passed-in
	 * PropertyValues instance), or {@code null} to skip property population
	 * @throws org.springframework.beans.BeansException in case of errors
	 * @see #postProcessProperties
	 * @see org.springframework.beans.MutablePropertyValues
	 * @deprecated as of 5.1, in favor of {@link #postProcessProperties(PropertyValues, Object, String)}
	 */
	@Deprecated
	@Nullable
	default PropertyValues postProcessPropertyValues(
			PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {

		return pvs;
	}

}

```



在这个方法里面也打个断点，看一下到底 是属性被应用之前操作，还是被应用之后操作，谁先谁后？？？

##### 答案是 postProcessProperties 会在 set 赋值之前执行。最后才反射赋值。。。

当我们去映射的时候，那么 postProcessProperties 方法，会在 set() 执行之前，来提前执行。

这有什么好处？？

这就可以提前帮我们在没赋值之前，我就把元信息进行注入。



**AutowiredAnnotationBeanPostProcessor # postProcessProperties()**

```java
	@Override
	public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) 
        
        /**
        	这个方法里面有很多的 Cache，想一下在 Cache 构建好之前又是什么过程呢？
        	这就涉及到了另外一个周期的过程，这个周期比较复杂。
        	
        	可以去看 AutowiredAnnotationBeanPostProcesor 里面有另外一个 Process 的扩展 - MergedBeanDefinitionPostProcessor
        	
        	MergedBeanDefinitionPostProcessor 是干什么用的？
        	
        	回到我们的 dependency-lookup-context.xml 里面：
        	
        		可以看到我们的 user 配置是一个普通类（根类），因为他是没有标记 parent（继承） 的。
        		但是 superUser 配置是一个有父类的（User 是他的父类）。
        		可以得出 superUser 是 user，但是 user 不是 superUser
        		预期要达到的效果是，创建 superUser 的时候，user 的属性要一起跟过去，创建 user 的时候 superUser 的属性不用跟过去。
        		这就是刚才在 AbstractBeanDefinition # setPropertyValues 打断点的时候，superUser 比 user 多一个字段的原因。
        	
        */
		InjectionMetadata metadata = findAutowiringMetadata(beanName, bean.getClass(), pvs);
		try {
			metadata.inject(bean, beanName, pvs);
		}
		catch (BeanCreationException ex) {
			throw ex;
		}
		catch (Throwable ex) {
			throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
		}
		return pvs;
	}
```



##### MergedAnnotationBeanPostProcessor 源码：

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

package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Post-processor callback interface for <i>merged</i> bean definitions at runtime.
 * {@link BeanPostProcessor} implementations may implement this sub-interface in order
 * to post-process the merged bean definition (a processed copy of the original bean
 * definition) that the Spring {@code BeanFactory} uses to create a bean instance.
 *
 * <p>The {@link #postProcessMergedBeanDefinition} method may for example introspect
 * the bean definition in order to prepare some cached metadata before post-processing
 * actual instances of a bean. It is also allowed to modify the bean definition but
 * <i>only</i> for definition properties which are actually intended for concurrent
 * modification. Essentially, this only applies to operations defined on the
 * {@link RootBeanDefinition} itself but not to the properties of its base classes.
 *
 * @author Juergen Hoeller
 * @since 2.5
 * @see org.springframework.beans.factory.config.ConfigurableBeanFactory#getMergedBeanDefinition
 */
public interface MergedBeanDefinitionPostProcessor extends BeanPostProcessor {

	/**
		这里是说 当我们的 Bean 被 Merge 完了之后，或产生以回调，可以看 AutowiredAnnotationBeanPostProcess 的实现
	 * Post-process the given merged bean definition for the specified bean.
	 * @param beanDefinition the merged bean definition for the bean
	 * @param beanType the actual type of the managed bean instance
	 * @param beanName the name of the bean
	 * @see AbstractAutowireCapableBeanFactory#applyMergedBeanDefinitionPostProcessors
	 */
	void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName);

	/**
	 * A notification that the bean definition for the specified name has been reset,
	 * and that this post-processor should clear any metadata for the affected bean.
	 * <p>The default implementation is empty.
	 * @param beanName the name of the bean
	 * @since 5.1
	 * @see DefaultListableBeanFactory#resetBeanDefinition
	 */
	default void resetBeanDefinition(String beanName) {
	}

}

```



##### AutowiredAnnotationBeanPostProcessor # postProcessMergeBeanDefinition 源码：

```java
	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
        /**
        	这里会产生一次查找，只是为了检查配置
        	其实就是去缓存里面去查找，那么什么时候放进缓存的呢？或者缓存没有怎么办？
        */
		InjectionMetadata metadata = findAutowiringMetadata(beanName, beanType, null);
		metadata.checkConfigMembers(beanDefinition);
	}
```



##### AutowiredAnnotationBeanPostProcessor # findAutowiringMetadata() 源码：

```java
/**
	其实就是去缓存里面去查找，那么什么时候放进缓存的呢？或者缓存没有怎么办？
*/
private InjectionMetadata findAutowiringMetadata(String beanName, Class<?> clazz, @Nullable PropertyValues pvs) {
		// Fall back to class name as cache key, for backwards compatibility with custom callers.
		String cacheKey = (StringUtils.hasLength(beanName) ? beanName : clazz.getName());
		// Quick check on the concurrent map first, with minimal locking.
		InjectionMetadata metadata = this.injectionMetadataCache.get(cacheKey);
		if (InjectionMetadata.needsRefresh(metadata, clazz)) {
			synchronized (this.injectionMetadataCache) {
				metadata = this.injectionMetadataCache.get(cacheKey);
				if (InjectionMetadata.needsRefresh(metadata, clazz)) {
					if (metadata != null) {
						metadata.clear(pvs);
					}
                    //缓存里面没有的话就重新来进行构建
					metadata = buildAutowiringMetadata(clazz);
					this.injectionMetadataCache.put(cacheKey, metadata);
				}
			}
		}
		return metadata;
	}
```



问题：MergedBeanDefinitionPostProcessor 的 postProcessorMergedBeanDefinition 先执行还是 AutowiredAnnotationBeanPostProcessor 的 postProcessProperties() 在前？







## 15：JSR-330 @Inject 注入：@Inject 和 @Autowired 的注入原理有怎么样的联系？



#### @Inject 注入过程：

​	如果 JSR-330 存在于 class path 中，复用 AutowiredAnnotationBeanPostProcessor 实现





## 16：Java 通用注解注入原理：Spring 是如何实现 @Resource 和 @EJB 等注解注入的？



java 1.6 以后引入的 API。



##### CommonAnnotationBeanPostProcessor - 内建的依赖可查找的 Bean

##### 	注入注解：

##### 		javax.xml.ws.WebServiceRef

##### 		javax.ejb.EJB

##### 		javax.annoyation.Resource - jdk 1.6 引入



##### 生命周期注解：

##### 		javax.annotation.PostConstruct

##### 		javax.annotation.PreDestroy





##### 问题：AutowiredAnnotationBeanPostProcessor 和 CommonAnnotationBeanPostProcessor 有什么区别？

AutowiredAnnotationBeanPostProcessor 是处理 @Autowired、@Value、@Inject 这种方式的一个依赖注入，CommonAnnotationBeanPostProcessor 主要是处理一个通用型的注解，包括：@WebServiceRef、@EJB、@Resource、@PostConstruct、@PreDestroy





## 17：自定义依赖注入注解：如何最简化实现自定义依赖注入注解？



##### 基于 AutowiredAnnotationBeanPostProcessor 实现



##### 自定义实现：

​	生命周期处理：

​		InstantiationAwareBeanPostProcessor

​		MegerdBeanDefinitionPostProcessor

​	元数据：

​		InjectedElement

​		InjectionMetadata



### 总结：

​	通过扩展 AutowiredAnnotationBeanPostProcessor 的方式来自定义注解，例如 @MyAutowired，还一种方式就是类似于 @Inject 的方式，用 @Bean 在配置类里面新增一个 AutowiredAnnotationBeanPostProcessor 的配置类，标记为 static 可以提早触发 Bean 的注册，这样做可以让上下文中存在两个 AutowiredAnnotationBeanPostProcessor ，便于兼容新老两种注解处理，与此同时 static 生命的配置类也会提早的进行初始化。