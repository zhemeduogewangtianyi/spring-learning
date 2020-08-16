package org.example.thinking.in.spring.ioc.java.beans;

/**
 * 人的描述
 * */
public class Person {

    //String to String
    String name; //property

    //String to Integer
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
