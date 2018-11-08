package csk.spring.Process.proxy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(CustomerProxyProcessor.class)
public @interface ProxyTranEnable {

}
