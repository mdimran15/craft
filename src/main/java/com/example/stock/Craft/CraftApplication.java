package com.example.stock.Craft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@SpringBootApplication
public class CraftApplication {

	public static void main(String[] args) {
		SpringApplication.run(CraftApplication.class, args);
	}

}

