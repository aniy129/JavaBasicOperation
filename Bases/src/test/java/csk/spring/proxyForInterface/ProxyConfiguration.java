package csk.spring.proxyForInterface;

import org.springframework.context.annotation.Bean;

/**
 * 动态代理接口的配置
 */
public class ProxyConfiguration {

    @Bean
    public ConfigScanPackage configScanPackage(){
        ConfigScanPackage configScanPackage = new ConfigScanPackage();
        configScanPackage.setPackages(new String[]{"csk.spring.proxyForInterface.beans"});
        return configScanPackage;
    }

    @Bean
    public AutoRegistryInterfaceToBeanFactory configDefaultClassRegistryBeanFactory() {
        AutoRegistryInterfaceToBeanFactory defaultClassRegistryBeanFactory =
                new AutoRegistryInterfaceToBeanFactory();
        defaultClassRegistryBeanFactory.setConfigScanPackage(configScanPackage());
        return defaultClassRegistryBeanFactory;
    }

}
