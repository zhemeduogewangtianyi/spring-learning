package org.example.thinking.in.spring.ioc.java.beans;

import java.beans.*;

/**
 * jdk 提供的依赖查找
 * @author WTY
 * @date 2020/8/16 8:49
 **/
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class,Object.class);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for(PropertyDescriptor propertyDescriptor : propertyDescriptors){
            System.out.println(propertyDescriptor);

            Class<?> propertyType = propertyDescriptor.getPropertyType();

            String name = propertyDescriptor.getName();

            //为 age 字段/属性增加 PropertyEditor
            if("age".equals(name)){
                //Integer.valueOf()
                System.err.println(name);

                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
//                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(new StringToIntegerPropertyEditor());
//                String asText = propertyEditor.getAsText();
//                System.out.println("asText : " + asText);
                System.out.println();
            }
        }

    }

}
