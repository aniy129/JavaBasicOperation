package csk.spring.Process.proxy;

import csk.spring.Process.proxy.proxyTran;

public interface IProxyTest {
    @proxyTran("各种日志事务哈")
    String say(String name);

    String say2(String name);
}
