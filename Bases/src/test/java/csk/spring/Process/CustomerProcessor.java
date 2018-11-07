package csk.spring.Process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

/**
 * 自定义bean后置处理器通过cglib实现动态代理功能
 * 仅当方法上标注了Tran注解的时候才对该对象实现动态代理
 * 执行代理对象时仅对标注了Tran注解的方法执行前后开启拦截功能
 */
public class CustomerProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("处理器初始化前 bean" + bean + " beanName=" + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("处理器初始化后 bean" + bean + " beanName=" + beanName);
        Method[] methods = bean.getClass().getMethods();
        boolean flag = false;
        for (Method method : methods) {
            Tran annotation = method.getAnnotation(Tran.class);
            if (annotation != null) {
                flag = true;
                break;
            }
        }
        if (flag) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(bean.getClass());
            enhancer.setCallback(new CglibMethodInterceptor());
            return enhancer.create();
        } else {
            return bean;
        }
    }
}
