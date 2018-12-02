package csk.proxy;

import csk.proxy.cglib.GeneralClass;
import csk.proxy.cglib.Interceptor;
import csk.proxy.jdk.IProxyInterface;
import csk.proxy.jdk.MyProxyHandler;
import csk.proxy.jdk.ProxyImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

public class ProxyTest {

    @Test
    public void cglibTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(GeneralClass.class);
        enhancer.setCallback(new Interceptor());
        GeneralClass generalClassInstance = (GeneralClass) enhancer.create();
        generalClassInstance.Say("yes");
        generalClassInstance.Say("cliche");
    }

    @Test
    public void jdkTest(){
        IProxyInterface instance =
                (IProxyInterface) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class<?>[]{IProxyInterface.class},
                new MyProxyHandler(new ProxyImpl()));
        instance.Say("yes");
        instance.Say("jdk");

    }
}
