package csk.spring.Process.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        System.out.println("ObjectProxy execute:" + method.getName());
        String methodName = method.getName();
        String param = Arrays.toString(args);
        return String.format("methodName=%s,para=%s",methodName,param);
    }
    public static <T> T newInstance(Class<T> innerInterface) {
        ClassLoader classLoader = innerInterface.getClassLoader();
        Class[] interfaces = new Class[]{innerInterface};
        InterfaceProxy proxy = new InterfaceProxy();
        return (T) Proxy.newProxyInstance(classLoader, interfaces, proxy);
    }
}
