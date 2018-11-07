package csk.spring;

import csk.spring.Process.Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProcessTest {
    @Test
    public void ts(){
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        csk.spring.Process.Test bean = context.getBean(csk.spring.Process.Test.class);
        bean.say("小红");
        bean.say2("小红");
    }
}
