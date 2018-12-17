package csk.spring.proxyForInterface;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Arrays;
import java.util.Objects;

/**
 * 自动将接口注册为bean，基于动态代理创建bean的实例
 */
public class AutoRegistryInterfaceToBeanFactory implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(EnableProxyForInterface.class.getName()));
        String[] values = Objects.requireNonNull(annotationAttributes).getStringArray("value");
        System.out.println(Arrays.toString(values));
        InterfacePackageScan defaultClassPathScanner = new InterfacePackageScan(beanDefinitionRegistry);
        defaultClassPathScanner.registerFilters();
        defaultClassPathScanner.doScan(values);
    }

}
