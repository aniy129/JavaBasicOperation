package csk.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxyHandler implements InvocationHandler {
    IProxyInterface instance;
    public MyProxyHandler(IProxyInterface instance){
        this.instance=instance;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before "+method.getName());
        Object rs = method.invoke(instance, args);
        System.out.println("after "+method.getName());
        return rs;
    }
}
