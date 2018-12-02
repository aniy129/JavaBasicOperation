package csk.spring.autoRegisterProxyForInterface;

import csk.spring.autoRegisterProxyForInterface.bean.UserMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ClassRegistryBeanScannerConfig.class);
        UserMapper userMapper = context.getBean(UserMapper.class);
        String rs = userMapper.remove("MyTest");
        System.out.println(rs);
        context.stop();
    }
}
