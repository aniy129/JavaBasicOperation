package csk.spring.Process.proxy;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface proxyTran {
    String value() default "";
}
