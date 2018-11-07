package csk.spring.Process;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Tran annotation = method.getAnnotation(Tran.class);
        if (annotation!=null){
            System.out.println("注解内容="+annotation.value());
            System.out.println("开启事务 " + method.getName());
        }
        Object invokeResult = methodProxy.invokeSuper(o, objects);
        if (annotation!=null){
            System.out.println("提交或回滚事务 " + method.getName());
        }
        return invokeResult;
    }
}
