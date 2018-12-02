package csk.spring.autoRegisterProxyForInterface;

import csk.spring.autoRegisterProxyForInterface.bean.BaseMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperJavaProxy implements InvocationHandler {

    private BaseMapper baseMapper;

    private Class<?> interfaceClass;

    public MapperJavaProxy(BaseMapper baseMapper, Class<?> interfaceClass) {
        this.baseMapper = baseMapper;
        this.interfaceClass = interfaceClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("mapperInterface is not interface.");
        }
        return  "代理成功！";
    }
}
