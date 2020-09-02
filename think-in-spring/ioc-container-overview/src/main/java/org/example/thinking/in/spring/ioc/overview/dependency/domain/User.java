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
        user.setName("小毛驴");
        System.err.println("静态方法");
        return user;
    }

}
