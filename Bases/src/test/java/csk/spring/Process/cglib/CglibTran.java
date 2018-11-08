package csk.spring.Process.cglib;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CglibTran {
    String value() default "";
}
