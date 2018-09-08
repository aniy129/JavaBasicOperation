package csk.spring.Beans.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("service")
public class BusinessService implements IBusinessService {

    @Value("我的注解服务")
    private String serviceName;
    @Autowired
    private Log log;

    @Override
    public String getServiceName() {
        return this.serviceName;
    }

    @AspectAnnotation(method = "执行测试")
    @Override
    public void invoke() {
        System.out.println("执行注解服务中的方法");
        log.writeLog("执行完成，注解日志已记录");
    }

    @AspectAnnotation(method = "执行测试")
    @Override
    public void throwException() throws Exception {
        throw new Exception("申明中出现异常");
    }

    @AspectAnnotation(method = "执行测试")
    @Override
    public String putStringsTogether(String first, String second) {
        return first + second;
    }
}
