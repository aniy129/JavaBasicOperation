package csk.spring.proxyForInterface;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 所有注入的接口中方法调用时会在这里拦截，
 * //TODO 后续业务逻辑在这里处理……
 */
public class ProxyHandler implements InvocationHandler {
    Log logger = LogFactory.getLog(getClass());

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        logger.info(String.format("正在调用:类名-> %s 方法名称 -> %s 参数 -> %s",
               method.getDeclaringClass().getName(), method.getName(), Arrays.toString(args)));
        return "代理成功！";
    }
}
