package csk.spring.Process.proxy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({CustomerProxyProcessor.class,AutoRegistry.class})
public @interface ProxyTranEnable {

}
