package csk.spring.Beans.bean;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;

@Configuration
@ComponentScan(
        basePackages = {"csk.spring.aop"}
)
//@ImportResource("classpath:javaAop.csk.dubbo.provider.xml")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BeansConfiguration {
    @Bean()
    @Scope("prototype")//作用域 prototype singleton
    public Log log() {
        return new Log();
    }

    @Bean("service")
    public BusinessService businessService() {
        BusinessService service = new BusinessService();
        service.setServiceName("java配置服务");
        service.setLog(log());
        return service;
    }

    @Bean
    @Order(1)
    public InitBean initBean() {
        return new InitBean();
    }

}
