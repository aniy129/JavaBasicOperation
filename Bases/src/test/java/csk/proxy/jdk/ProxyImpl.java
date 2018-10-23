package csk.proxy.jdk;

public class ProxyImpl implements IProxyInterface {
    @Override
    public String Say(String string) {
        System.out.println("jdk hello "+ string);
        return "hello "+ string;
    }
}
