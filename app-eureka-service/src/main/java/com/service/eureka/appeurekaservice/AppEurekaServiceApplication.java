package com.service.eureka.appeurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppEurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppEurekaServiceApplication.class, args);
	}

}
