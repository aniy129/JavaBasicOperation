package csk.dubbo.consumer.xml;

import csk.dubbo.protocol.IService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        applicationContext.start();
        IService service = (IService)applicationContext.getBean("service");
        int rs = service.add(100, 200);
        System.out.println(rs);
    }
}
