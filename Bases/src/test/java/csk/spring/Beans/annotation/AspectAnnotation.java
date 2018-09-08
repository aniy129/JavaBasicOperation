package csk.spring.Beans.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//放置在接口上无效
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectAnnotation {
    String method() default "我的切面";
}
