package csk.spring.Process.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AutoRegistry implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {
    private Logger LOGGER = LoggerFactory.getLogger(AutoRegistry.class);
    private ApplicationContext applicationContext;
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        LOGGER.info("postProcessBeanDefinitionRegistry() beanDefinitionName=====>"+registry.getBeanDefinitionNames().toString());
        // 需要被代理的接口
//        BeanDefinition
        AnnotationScanner annotationScanner = new AnnotationScanner(registry);
        annotationScanner.setResourceLoader(applicationContext);
        // "com.pepsi.annotationproxy.service"是我 接口所在的包
        annotationScanner.scan("csk.spring.Process.proxy.autoLoad");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        LOGGER.info("postProcessBeanFactory() beanDefinition的个数=====>"+beanFactory.getBeanDefinitionCount());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
