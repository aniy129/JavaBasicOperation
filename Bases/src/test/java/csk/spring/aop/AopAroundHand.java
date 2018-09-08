package csk.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class AopAroundHand {
    public static Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append("aop 环绕通知 方法名称：");
        sb.append(proceedingJoinPoint.getSignature().getName());
        Object[] args = proceedingJoinPoint.getArgs();
        if (args.length > 0) {
            sb.append("参数: ");
            for (int i = 0; i < args.length; i++) {
                sb.append("arg " + (i + 1) + ": " + args[i] + " ");
            }
        }
        Object result = proceedingJoinPoint.proceed(args);
        sb.append("结果 " + result);
        System.out.println(sb.toString());
        return result;
    }
}
