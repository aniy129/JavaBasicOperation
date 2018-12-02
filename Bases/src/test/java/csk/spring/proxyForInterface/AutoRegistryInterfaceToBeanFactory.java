package csk.spring.proxyForInterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

/**
 * 自动将接口注册为bean，基于动态代理创建bean的实例
 */
public class AutoRegistryInterfaceToBeanFactory implements
        ApplicationContextAware,
        BeanDefinitionRegistryPostProcessor {

    public void setConfigScanPackage(ConfigScanPackage configScanPackage) {
        this.configScanPackage = configScanPackage;
    }

    private ConfigScanPackage configScanPackage;

    private ApplicationContext applicationContext;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        if (configScanPackage == null) {
            throw new RuntimeException("can't find bean of ConfigScanPackage!");
        }
        if (configScanPackage.getPackages() == null || StringUtils.isEmpty(configScanPackage.getPackages())) {
            throw new RuntimeException("please set value of packages of configScanPackage");
        }
        for (String packageStr : configScanPackage.getPackages()) {
            String basePackage2 = this.applicationContext.getEnvironment().resolvePlaceholders(packageStr);
            String[] packages = StringUtils.tokenizeToStringArray(basePackage2, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
            InterfacePackageScan defaultClassPathScanner = new InterfacePackageScan(beanDefinitionRegistry);
            defaultClassPathScanner.registerFilters();
            defaultClassPathScanner.doScan(packages);
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
