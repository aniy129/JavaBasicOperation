package csk.spring.customization;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;

public class ProxyMethodManagementConfiguration  extends  AbstractMethodManagementConfiguration{

//    @Bean
//    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
//    public MethodInterceptor transactionInterceptor() {
//        MethodInterceptor interceptor = new MethodInterceptor();
//        interceptor.setTransactionAttributeSource(transactionAttributeSource());
//        if (this.txManager != null) {
//            interceptor.setTransactionManager(this.txManager);
//        }
//        return interceptor;
//    }

}
