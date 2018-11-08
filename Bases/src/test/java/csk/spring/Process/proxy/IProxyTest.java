package csk.spring.Process.proxy;

public interface IProxyTest {
    @ProxTran("各种日志事务哈")
    String say(String name);

    String say2(String name);
}
