package csk.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class XmlAop {

    //在方法执行之前运行通知。
    public void before() {
        System.out.println("aop: 方法执行前");
    }

    //在方法执行后运行通知，无论其结果如何。
    public void after() {
        System.out.println("aop 方法执行后");
    }

    //只有方法成功完成后才能在方法执行后运行通知。
    public void afterReturningAdvice(Object retVal) {
        System.out.println("aop 方法成功执行:" + (retVal==null ? "无参数" : retVal.toString()));
    }

    //只有在方法通过抛出异常而退出方法执行之后才能运行通知。
    public void afterThrowingAdvice(Exception ex) {
        System.out.println("aop 方法执行发送异常 " + ex.toString());
    }

    //在调用通知方法之前和之后运行通知。
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        return AopAroundHand.aroundAdvice(proceedingJoinPoint);
    }
}
