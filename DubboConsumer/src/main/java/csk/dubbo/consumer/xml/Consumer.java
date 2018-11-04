package csk.dubbo.consumer.xml;

import csk.dubbo.protocol.IService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("consumer.xml");
        applicationContext.start();

        for (int i = 0; i < 10000; i++) {
            IService service = (IService) applicationContext.getBean("service");
//        int rs = service.add(100, 200);
//        System.out.println(rs);
            System.out.println("[ERROR] "+i + service.getStr(" hello "));
        }
    }
}
