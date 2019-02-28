package csk.spring.multi;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MultiInterface {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        //获取所有接口的实现
        Map<String, ISay> list = context.getBeansOfType(ISay.class);
        System.out.println(list.size());
        list.forEach((k,v)-> System.out.println(k+" "+ v.say("小明")));
    }
}
