package csk.dubbo.provider.xml;

import csk.dubbo.protocol.IService;

public class ServiceImpl implements IService {
    public int add(int x, int y) {
        return x + y;
    }
}
