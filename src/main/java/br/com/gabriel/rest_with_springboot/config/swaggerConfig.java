package br.com.gabriel.rest_with_springboot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class swaggerConfig {

    @Bean
    public OpenAPI customSwagger(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Restuful")
                        .version("v1")
                        .description("")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0")));
    }
}
