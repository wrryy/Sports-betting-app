package pl.wrryy.amelco.system;

import org.springframework.context.annotation.Bean;

public class SpringDataDialect {
    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }
}
