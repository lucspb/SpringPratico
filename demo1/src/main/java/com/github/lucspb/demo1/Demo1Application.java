package com.github.lucspb.demo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Demo1Application {

	@Autowired
	private AppConfiguration appConfig;

	@Value("${app.message}")
	private String message;


	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

	@GetMapping("/home")
	public String inicio(){
		return message;
	}

	@GetMapping("/configuracao")
	public String config(){
		return this.appConfig.getMessage();
	}



}
