package csk.spring.Process.proxy;

import csk.spring.Process.cglib.CustomerCglibProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/**
 * 自定义bean后置处理器通过Jdk Proxy实现动态代理功能，所代理的bean必须实现相应的接口，否则报错
 * 仅当【接口】上标注了ProxyTran注解的时候才对该对象实现动态代理
 * 执行代理对象时仅对标注了ProxyTran注解的方法执行前后开启拦截功能
 */
public class CustomerProxyProcessor implements BeanPostProcessor {
    Logger logger = LoggerFactory.getLogger(CustomerCglibProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("CustomerProxyProcessor处理器初始化前 bean" + bean + " beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("CustomerProxyProcessor处理器初始化后 bean" + bean + " beanName=" + beanName);
        Method[] methods = bean.getClass().getMethods();
        boolean flag = false;
        for (Method method : methods) {
            proxyTran annotation = method.getAnnotation(proxyTran.class);
            if (annotation != null) {
                flag = true;
                break;
            }
        }
        if (flag) {
            Object instance = Proxy.newProxyInstance
                    (
                            bean.getClass().getClassLoader(),
                            bean.getClass().getInterfaces(),
                            new ProxyInvocationHandler(bean)
                    );
            return instance;
        } else {
            return bean;
        }
    }

}
