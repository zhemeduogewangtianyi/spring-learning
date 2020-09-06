package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

public class BeanCreationExceptionDemo {

    public static void main(String[] args) {

        //创建 Spring Ioc 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(BeanCreationExceptionDemo.class,POJO.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        String bean = applicationContext.getBean(String.class);
        System.out.println(bean);

        //关闭 Spring 应用上下文
        applicationContext.close();

    }

    /** 到他了就会抛出 BeanCreationException  */
    static class POJO implements InitializingBean{

        //这里再次体验下优先级。。。。

        @PostConstruct
        public void init() throws Throwable{
            throw new Throwable("init() : For purposes !");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() : For purposes !");
        }
    }

    @Bean
    public String bean1(){
        return "bean1";
    }
}