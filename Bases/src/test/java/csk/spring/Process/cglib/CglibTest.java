package csk.spring.Process.cglib;

public class CglibTest {
    @CglibTran("Proxy代理测试")
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
