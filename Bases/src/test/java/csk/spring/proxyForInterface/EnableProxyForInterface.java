package csk.spring.proxyForInterface;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({ProxyConfiguration.class})
public @interface EnableProxyForInterface {

}
