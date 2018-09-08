package csk.spring.Beans.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Log implements InitializingBean, DisposableBean {
    public void writeLog(String log) {
        System.out.println(log);
    }

    @Override
    public void destroy() {
        System.out.println(" csk.spring.Beans.bean.Log已销毁");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(" csk.spring.Beans.bean.Log已初始化");
    }
}
