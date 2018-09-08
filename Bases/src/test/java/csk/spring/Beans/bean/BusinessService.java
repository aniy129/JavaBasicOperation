package csk.spring.Beans.bean;

public class BusinessService implements IBusinessService {

    private String serviceName;
    private Log log;

    public void setLog(Log log) {
        this.log = log;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public String getServiceName() {
        String x=this.serviceName;
        return x;
    }

    @Override
    public void invoke() {
        System.out.println("执行java服务中的方法");
        log.writeLog("执行完成，java日志已记录");
    }

    @Override
    public void throwException() throws Exception {
        throw new Exception("java aop 异常");
    }

    @Override
    public String putStringsTogether(String first, String second) {
        return first + second;
    }
}
