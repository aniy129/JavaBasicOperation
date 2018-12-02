package csk.spring.Process.proxy;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.ParameterizedType;

public class RefrenceAnnotationFactoryBean<T> implements FactoryBean<T> {
    @Override
    public T getObject() {
        return (T) InterfaceProxy.newInstance(getObjectType());
    }
    @Override
    public Class<?> getObjectType() {
       return  (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
