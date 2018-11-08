package csk.spring.Process;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomerCglibProcessor.class)
public @interface CglibTranEnable {

}
