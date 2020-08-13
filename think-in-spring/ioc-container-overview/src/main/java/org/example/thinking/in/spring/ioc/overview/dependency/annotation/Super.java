package org.example.thinking.in.spring.ioc.overview.dependency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 超级
 * @author WTY
 * @date 2020/8/14 0:16
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Super {
}
