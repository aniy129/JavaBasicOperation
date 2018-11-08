package csk.spring.Process.cglib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor implements MethodInterceptor {
    Logger logger = LoggerFactory.getLogger(CglibMethodInterceptor.class);

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        CglibTran annotation = method.getAnnotation(CglibTran.class);
        if (annotation != null) {
            logger.info("注解内容=" + annotation.value());
            logger.info("开启事务 " + method.getName());
        }
        Object invokeResult = methodProxy.invokeSuper(o, objects);
        if (annotation != null) {
            logger.info("提交或回滚事务 " + method.getName());
        }
        return invokeResult;
    }
}
