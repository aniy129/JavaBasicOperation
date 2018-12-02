package csk.spring.Process.proxy;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

public class AnnotationScanner extends ClassPathBeanDefinitionScanner {

    public AnnotationScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void resetFilters(boolean useDefaultFilters) {
        this.addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitions) {
            GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();
            //BeanFactory.getBean的方法跟进去后有一个判断是不是FactroyBean类型的。如果是从FactroyBean.getObejct获取
            //RefrenceAnnotationFactoryBean 实现了FactoryBean
            definition.setBeanClass(RefrenceAnnotationFactoryBean.class);
            this.getRegistry().registerBeanDefinition(holder.getBeanName(), definition);
        }
        return beanDefinitions;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
}
