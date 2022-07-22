package com.eliasnepo.motosport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MotosportApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotosportApplication.class, args);
	}

}
