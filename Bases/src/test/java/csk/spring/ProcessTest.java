package csk.spring;

import csk.spring.Process.CglibTest;
import csk.spring.Process.Config;
import csk.spring.Process.IProxyTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProcessTest {
    @Test
    public void ts(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        IProxyTest bean = context.getBean(IProxyTest.class);
        System.out.println("Proxy代理测试");
        bean.say("Proxy小红");
        bean.say2("Proxy小红");
        System.out.println("Cglib代理测试");
        CglibTest bean1 = context.getBean(CglibTest.class);
        bean1.say("Cglib小红");
        bean1.say2("Cglib小红");
    }
}
