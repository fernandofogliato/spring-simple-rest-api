package com.fernandofogliato.demoacmeapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableOpenApi
public class DemoAcmeAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoAcmeAppApplication.class, args);
    }

    @Bean
    public springfox.documentation.spring.web.plugins.Docket docket() {
        Docket docket = new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2);
        return docket.apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Acme Producer REST API")
                .description("API Documentation")
                .version("1.0")
                .contact(new Contact("Fernando Fogliato", "", "fernandofogliato@gmail.com"))
                .license("API License")
                .build();
    }

}