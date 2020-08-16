package org.example.thinking.in.spring.ioc.java.beans;

import java.beans.PropertyEditorSupport;

public class StringToIntegerPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        Integer value = Integer.valueOf(text);
        super.setValue(value);
    }

}
