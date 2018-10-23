package csk.spring.Beans.annotation;

import org.springframework.stereotype.Component;

@Component
public class Log {
    public Log(){
        System.out.println("csk.spring.Beans.csk.dubbo.provider.annotation.Log 初始化完成！！！");
    }
    public void writeLog(String log){
        System.out.println(log);
    }
}
