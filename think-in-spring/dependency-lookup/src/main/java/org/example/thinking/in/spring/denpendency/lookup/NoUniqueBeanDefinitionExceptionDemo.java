package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@link NoUniqueBeanDefinitionException}
 * */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {

        // 创建 Spring IoC 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //注册 Configuration Class 配置类
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        //启动 Spring 应用上下文
        applicationContext.refresh();

        //BeanDefinitionBuilder 的方式创建 BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(String.class);
        beanDefinitionBuilder.addConstructorArgValue("bean4");
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        applicationContext.registerBean("bean4",String.class,beanDefinition);

        //AbstractBeanDefinition 子类创建 BeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(String.class);
        genericBeanDefinition.setBeanClassName("bean5");

        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addGenericArgumentValue("bean5");
        genericBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);

        applicationContext.registerBean("bean5",String.class,genericBeanDefinition);



        try{
            //由于 Spring IoC 容器中拥有两个 String 类型的 Bean，因此 NoUniqueBeanDefinitionException 会抛出异常
            String bean = applicationContext.getBean(String.class);
            System.out.println(bean);

        }catch(NoUniqueBeanDefinitionException e){
            System.err.println(String.format("Spring 当前应用上下文存在 %d 个 %s 类型的 Bean , 具体原因 %s",e.getNumberOfBeansFound(),e.getBeanType().getName(),
                    e.getMessage()));
        }

        // 关闭 Spring 应用上下文
        applicationContext.close();

    }

    @Bean
    public String bean1(){
        return "bean1";
    }

    @Bean
    public String bean2(){
        return "bean2";
    }

    @Bean
    public String bean3(){
        return "bean3";
    }

}
