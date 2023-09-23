package com.github.lucspb.pdv;

import com.github.lucspb.pdv.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PdvApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdvApplication.class, args);

		User user1 = new User();
	}

}
