package org.example.thinking.in.spring.bean.definition;

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
