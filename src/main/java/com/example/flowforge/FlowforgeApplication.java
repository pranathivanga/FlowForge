package com.example.flowforge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FlowforgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowforgeApplication.class, args);
	}

}
