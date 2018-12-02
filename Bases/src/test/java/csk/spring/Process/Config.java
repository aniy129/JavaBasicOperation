package csk.spring.Process;

import csk.spring.Process.cglib.CglibTest;
import csk.spring.Process.cglib.CglibTranEnable;
import csk.spring.Process.proxy.ProxyTranEnable;
import csk.spring.Process.proxy.ProxyTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ProxyTranEnable
@CglibTranEnable
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
//@ComponentScan(basePackages = {"csk.spring.Process.proxy.autoLoad"})
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
