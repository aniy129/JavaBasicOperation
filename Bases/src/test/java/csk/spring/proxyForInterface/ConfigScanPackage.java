package csk.spring.proxyForInterface;

public class ConfigScanPackage {
    /**
     * 需要将指定的接口自动注册为bean的包名称处理类
     */
    private String[] packages;

    public String[] getPackages() {
        return packages;
    }

    public void setPackages(String[] packages) {
        this.packages = packages;
    }

}
