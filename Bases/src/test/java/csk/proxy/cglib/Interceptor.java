package csk.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Interceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before " + method.getName());
        Object invokeResult = methodProxy.invokeSuper(o, objects);
        System.out.println("after " + method.getName());
        return invokeResult;
    }
}
