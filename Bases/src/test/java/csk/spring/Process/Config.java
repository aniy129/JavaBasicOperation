package csk.spring.Process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@TranEnable
public class Config {
    @Bean
    public Test test() {
        return new Test();
    }
}
