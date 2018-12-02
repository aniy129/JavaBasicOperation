package csk.spring.autoRegisterProxyForInterface;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassRegistryBeanScannerConfig {

    @Bean(name = "mapperManagerFactoryBean")
    public MapperManagerFactoryBean configMapperManagerFactoryBean() {
        MapperManagerFactoryBean mapperManagerFactoryBean = new MapperManagerFactoryBean();
        return mapperManagerFactoryBean;
    }

    @Bean
    public DefaultClassRegistryBeanFactory configDefaultClassRegistryBeanFactory() {
        DefaultClassRegistryBeanFactory defaultClassRegistryBeanFactory =
                new DefaultClassRegistryBeanFactory();
        defaultClassRegistryBeanFactory.setScanPackage("csk.spring.autoRegisterProxyForInterface.bean");
        defaultClassRegistryBeanFactory.setMapperManagerFactoryBean("mapperManagerFactoryBean");
        return defaultClassRegistryBeanFactory;
    }
}
