package csk.spring.Process.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    private Object bean;
    public  ProxyInvocationHandler(Object object){
        this.bean=object;
    }
    Logger logger = LoggerFactory.getLogger(ProxyInvocationHandler.class);
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        proxyTran annotation = method.getAnnotation(proxyTran.class);
        if (annotation != null) {
            logger.info("注解内容=" + annotation.value());
            logger.info("开启事务：");
        }
        Object rs = method.invoke(bean, args);
        if (annotation != null) {
            logger.info("提交或回滚事务：");
        }
        return rs;
    }
}
