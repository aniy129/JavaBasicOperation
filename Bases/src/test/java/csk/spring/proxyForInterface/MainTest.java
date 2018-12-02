package csk.spring.proxyForInterface;


import csk.spring.proxyForInterface.beans.IAdd;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainTest {
    /**
     * 给指定的包下的接口自动注入动态代理bean的测试
     */
    @Test
    public void test(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfig.class);
        IAdd add = context.getBean(IAdd.class);
        String s = add.add("yes", "no");
        System.out.println(s);
    }
}
