package csk.spring.Beans.xml;

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
        return this.serviceName;
    }

    @Override
    public void invoke() {
        System.out.println("执行xml服务中的方法");
        log.writeLog("执行完成，xml日志已记录");
    }
    public void throwException() throws Exception {
        throw new Exception("发生错误");
    }

    @Override
    public String putStringsTogether(String first, String second) {
        return first + second;
    }
}
