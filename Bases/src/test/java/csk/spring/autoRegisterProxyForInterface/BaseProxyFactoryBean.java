package csk.spring.autoRegisterProxyForInterface;

import csk.spring.autoRegisterProxyForInterface.bean.BaseMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.lang.reflect.Proxy;

public class BaseProxyFactoryBean<T> implements
        FactoryBean<T>,
        InitializingBean,
        ApplicationListener<ApplicationEvent>,
        ApplicationContextAware {
    /**
     * 要注入的接口类定义
     */
    private Class<T> mapperInterface;

    /**
     * Spring上下文
     */
    private ApplicationContext applicationContext;

    //也因该走工厂方法注入得来

    private BaseMapper mapperManagerFactoryBean;

    public BaseProxyFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() {
        //采用动态代理生成接口实现类，核心实现
        return (T) Proxy.newProxyInstance(
                applicationContext.getClassLoader(),
                new Class[]{mapperInterface},
                new MapperJavaProxy(mapperManagerFactoryBean, mapperInterface));
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 判断属性的注入是否正确-如mapperInterface判空
        if (null == mapperInterface)
            throw new IllegalArgumentException("Mapper Interface Can't Be Null!!");
    }

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //TODO 可依据事件进行扩展
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }


    public void setMapperManagerFactoryBean(BaseMapper mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }
}
