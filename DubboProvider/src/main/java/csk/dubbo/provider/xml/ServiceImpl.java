package csk.dubbo.provider.xml;

import csk.dubbo.protocol.IService;

public class ServiceImpl implements IService {
    public int add(int x, int y) {
        return x + y;
    }

    public String getStr(String string) {
        String property = System.getProperty("dubbo.protocol.port");
        System.out.println(property);
        return string + " 端口号:" + property;
    }
}
