package csk.spring.Process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ProxyTranEnable
@CglibTranEnable
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class Config {
    @Bean
    public ProxyTest proxyTest() {
        return new ProxyTest();
    }

    @Bean
    public CglibTest cglibTest() {
        return new CglibTest();
    }
}
