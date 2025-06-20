package com.society;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SocietyManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocietyManagementApplication.class, args);
	}

}
