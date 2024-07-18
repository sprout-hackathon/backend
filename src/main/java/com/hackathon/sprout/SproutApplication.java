package com.hackathon.sprout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SproutApplication {

	public static void main(String[] args) {
		SpringApplication.run(SproutApplication.class, args);
	}

}
