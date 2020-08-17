package org.example.thinking.in.spring.bean.definition;

import org.example.thinking.in.spring.ioc.overview.dependency.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link BeanDefinition} 构建 demo
 *
 * @author WTY
 * @date 2020/8/17 22:42
 **/
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        /*
            1：通过 BeanDefinitionBuilder 构建

                genericBeanDefinition() 构建一个普通的 BeanDefinition
                rootBeanDefinition()    构建一个 root BeanDefinition
        */

        //构建 BeanDefinition
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);

        //为 User 类添加属性，类似于 xml 中 <bean> 下的 <property> 标签
        beanDefinitionBuilder.addPropertyValue("id", 1L);
        beanDefinitionBuilder.addPropertyValue("name", "BTree");

        //可以链式调用
//        beanDefinitionBuilder
//                .addPropertyValue("id", 1L)
//                .addPropertyValue("name", "BTree");

        //获取 BeanDefinition，这里返回的是一个抽象类 - AbstractBeanDefinition，它实现了 BeanDefinition，才使得我们可以像 List list = new ArrayList<>() 一样去使用。
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        //此时的 BeanDefinition 并非最终状态，是可以自定义修改的
        


        /*
            2：通过 AbstractBeanDefinition 来构建 BeanDefinition
                注意：AbstractBeanDefinition 的派生类也可以去构建 BeanDefinition
                补充：
                    早期 BeanDefinition 是不能进行 setter 操作的，后面版本加上的。
                    5.1之前的版本都是通过 AbstractBeanDefinition 这个类来进行操作的。

        */

        //构建 AbstractBeanDefinition 的其中一个派生类 GenericBeanDefinition
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();

        //为 GenericBeanDefinition 设置 Bean 对象类型
        genericBeanDefinition.setBeanClass(User.class);

        //批量添加属性的暂存区
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue("id",1L);
        mutablePropertyValues.addPropertyValue("name","aaa");

        //也可以链式调用
//        mutablePropertyValues.add("id",1L).add("name","aaa");


        //为 GenericBeanDefinition 批量添加属性
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);



    }

}
