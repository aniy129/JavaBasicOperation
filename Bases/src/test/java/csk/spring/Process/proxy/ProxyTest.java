package csk.spring.Process.proxy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class ProxyTest implements IProxyTest {
    @proxyTran("Proxy代理测试")
    public String say(String name) {
        String str = "hello " + name;
        System.out.println(str);
        return str;
    }

    public String say2(String name) {
        String str = "hello2 " + name;
        System.out.println(str);
        return str;
    }
}
