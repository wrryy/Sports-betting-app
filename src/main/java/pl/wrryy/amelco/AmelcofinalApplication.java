package pl.wrryy.amelco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

@SpringBootApplication
@EnableAsync
public class AmelcofinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmelcofinalApplication.class, args);
	}

	@Bean
    public AuthSuccessHandler authSuccessHandler(){
	    return new AuthSuccessHandler();
    }

//    @Bean
//    public SpringDataDialect springDataDialect() {
//        return new SpringDataDialect();
//    }
}
