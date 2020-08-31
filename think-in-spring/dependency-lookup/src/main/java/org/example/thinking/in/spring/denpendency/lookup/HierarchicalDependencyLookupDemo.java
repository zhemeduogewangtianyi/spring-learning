package org.example.thinking.in.spring.denpendency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找示例
 * */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {

        //创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //将当前类作为配置类（Configuration Class）注册到 BeanFactory 中
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        /*
            获取 HierarchicalBeanFactory -> 根据层次性要求，我们可以获取到 ConfigurableBeanFactory
            又因为 ConfigurableListableBeanFactory 又是 ConfigurableBeanFactory 的一个子类
            那么 HierarchicalBeanFactory 就是一个 根接口（root）
            所以我们只需要的到子类 ConfigurableListableBeanFactory 就可以了
         */
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();


        //层次性会有多个，这里我们看一下他的 父 BeanFactory 有多少个 - 这里明显是 null ，因为没有设置。
        System.out.println("当前 BeanFactory 的 parent BeanFactory ： " + beanFactory.getParentBeanFactory());

        //设置 Parent BeanFactory , 根据 ConfigurableListableBeanFactory 的父类 ConfigurableBeanFactory 的 setParentBeanFactory(BeanFactory parentBeanFactory)来设置。
        //这里有个 bug 。。。 加入我设置自己为自己的父类，就会 stackOverflowError
//        beanFactory.setParentBeanFactory(beanFactory);

        //这里用 spring-container-overview 里面的 BeanFactoryAsIocContainerDemo 里面的代码，加载到另外一个 BeanFactory .
        HierarchicalBeanFactory parentBeanFactory = createParentBeanFactory();

        beanFactory.setParentBeanFactory(parentBeanFactory);

        //这时候的 beanFactory 已经是一个有双亲的实现了。
        System.out.println(beanFactory.getParentBeanFactory());

        //展示 LocalBean
        displayContainsLocalBean(beanFactory,"user");
        displayContainsLocalBean(beanFactory,"superUser");
        displayContainsLocalBean(beanFactory,"objectFactoryBean");

        displayContainsLocalBean(parentBeanFactory,"user");
        displayContainsLocalBean(parentBeanFactory,"superUser");
        displayContainsLocalBean(parentBeanFactory,"objectFactoryBean");
        displayContainsLocalBean(parentBeanFactory,"objectFactoryBean1");

        //自己实现的双亲
        displayLocalBean(beanFactory,"user");
        displayLocalBean(parentBeanFactory,"user");

        //启动 Spring IoC 容器上下文
        applicationContext.refresh();

        //关闭 Spring IoC 容器上下文
        applicationContext.close();

    }

    /**
     * 自己实现双亲委派查找 bean - 发现没有 bean 主动向上去找。
     * */
    public static void displayLocalBean(HierarchicalBeanFactory hierarchicalBeanFactory,String beanName){

        System.err.printf("当前 BeanFactory [%s] 是否包含 ： bean [name ： %s]  %s\n", hierarchicalBeanFactory , beanName ,
                containsBean(hierarchicalBeanFactory,beanName));

    }

    private static boolean containsBean(HierarchicalBeanFactory hierarchicalBeanFactory,String beanName){
        BeanFactory parentBeanFactory = hierarchicalBeanFactory.getParentBeanFactory();
        if(parentBeanFactory instanceof HierarchicalBeanFactory){
            //递归父类是否包含 bean
            return containsBean(HierarchicalBeanFactory.class.cast(parentBeanFactory),beanName);

        }
        //判断当前 BeanFactory 是否包含 bean
        return hierarchicalBeanFactory.containsLocalBean(beanName);
    }

    /**
     * 展示 LocalBean
     * */
    private static void displayContainsLocalBean(HierarchicalBeanFactory hierarchicalBeanFactory, String beanName){
        System.out.printf("当前 BeanFactory [%s] 是否包含 ：local bean [name ： %s]  %s\n", hierarchicalBeanFactory , beanName ,
                hierarchicalBeanFactory.containsLocalBean(beanName));

    }

    /**
     * 创建父类 BeanFactory
     * */
    private static HierarchicalBeanFactory createParentBeanFactory() {

        // 创建 BeanFactory 容器
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //创建 XML Bean 定义读取器
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        String localPath = "classpath:/META-INF/dependency-lookup-context.xml";

        int i = reader.loadBeanDefinitions(localPath);

        System.out.println(i);

        return defaultListableBeanFactory;


    }

}
