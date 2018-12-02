package csk.spring.autoRegisterProxyForInterface;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class TestDefaultClassRegistryBeanFactory extends DefaultClassRegistryBeanFactory {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        beanDefinitionRegistry.registerBeanDefinition("test", new GenericBeanDefinition() {
        });
    }
}
