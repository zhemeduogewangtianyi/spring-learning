# 									Spring学习

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

##### dependency-lookup-context.xml 

##### User.java

##### DependencyLookupDemo.java		---  启动入口

##### SuperUser.java 	---  注解获取Bean

##### Super.java			---	自定义注解