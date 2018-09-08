package csk.spring.Beans.xml;

public class Log {
    /**
     * @Author: Aniy on 2018/9/8 12:25
     * @methodParameters: [log]
     * @methodReturnType: void
     * @Description:
     */
    public void writeLog(String log) {
        System.out.println(log);
    }

    public static void initialize() {
        System.out.println("csk.spring.Beans.xml 初始化完成");
    }

    public static void destroy() {
        System.out.println("csk.spring.Beans.xml 销毁完成");
    }
}
