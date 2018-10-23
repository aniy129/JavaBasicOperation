package csk.spring;

import csk.spring.Beans.bean.BeansConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

//三种方式的依赖   注入及aop测试
public class DIAndAopTest {

    /*
     * AbstractApplicationContext 抽象上下文提供销毁方法
     *
     * ClassPathXmlApplicationContext 从classpath加载配置
     * FileSystemXmlApplicationContext 从文件系统加载配置
     * AnnotationConfigApplicationContext 从注解加载配置
     * */


    /*
     * xml配置获取ApplicationContext(AbstractApplicationContext)
     * 初始化和销毁需要p配置 init-method="initialize" destroy-method="destroy">
     * */
    @Test
    public void xmlContextTest() {
        AbstractApplicationContext context =
                new FileSystemXmlApplicationContext("classpath:xmlContext.csk.dubbo.provider.xml");
        csk.spring.Beans.xml.BusinessService service =
                (csk.spring.Beans.xml.BusinessService) context.getBean("businessService");
        System.out.println("服务名称：" + service.getServiceName());
        service.invoke();
        service.putStringsTogether("你好", "北京");
//        service.throwException();
        context.registerShutdownHook();
    }

    /*
     * 扫描指定包下注解获取ApplicationContext
     * */
    @Test
    public void annotationContextTest() {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("annotationContext.xml");
        csk.spring.Beans.annotation.IBusinessService service =
                (csk.spring.Beans.annotation.IBusinessService) context.getBean("service");
        System.out.println("服务名称：" + service.getServiceName());
        service.invoke();
        service.putStringsTogether("hello ", "world");
//        service.throwException();
    }

    /*
     * 使用java bean配置的方式获取注解
     * 初始化和销毁需要实现 InitializingBean,DisposableBean 接口
     * */
    @Test
    public void javaContextTest() {
        AbstractApplicationContext context =
                new AnnotationConfigApplicationContext(BeansConfiguration.class);
        csk.spring.Beans.bean.IBusinessService service =
                (csk.spring.Beans.bean.IBusinessService) context.getBean("service");
        System.out.println("服务名称：" + service.getServiceName());
        service.invoke();
        System.out.println("返回值" + service.putStringsTogether("hello ", "world"));
//        service.throwException();
        context.registerShutdownHook();
    }

    /*
     * 使用BeanFactory获取bean
     * */
    @Test
    public void beanFactoryTest() {
        BeanFactory beanFactory = new DefaultListableBeanFactory();
        Resource resource = new ClassPathResource("xmlContext.xml");
        BeanDefinitionReader bdr = new XmlBeanDefinitionReader((BeanDefinitionRegistry) beanFactory);
        bdr.loadBeanDefinitions(resource);
        csk.spring.Beans.xml.IBusinessService service =
                (csk.spring.Beans.xml.IBusinessService) beanFactory.getBean("businessService");
        System.out.println("服务名称：" + service.getServiceName());
        service.invoke();
    }
}
