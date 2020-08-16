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