package csk.spring.Process;

public class Test {
    @Tran("各种日志事务哈")
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
