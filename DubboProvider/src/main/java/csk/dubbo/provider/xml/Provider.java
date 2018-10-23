package csk.dubbo.provider.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("META-INF\\spring\\provider.xml");
        applicationContext.start();
        System.out.println("csk.dubbo.provider is ready!");
        System.in.read();
    }
}
