package com.sura.constructores.constructor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ConstructorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConstructorApplication.class, args);
	}

}
