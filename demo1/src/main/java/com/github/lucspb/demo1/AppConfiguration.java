package com.github.lucspb.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

@Configuration
public class AppConfiguration {
    public String getMessage(){
        return "Texto configuração";
    }

    @Bean
    public void init(){
        System.out.println("bean inicializado");
    }
}
