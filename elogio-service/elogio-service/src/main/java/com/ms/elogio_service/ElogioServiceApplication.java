package com.ms.elogio_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class ElogioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElogioServiceApplication.class, args);
	}

}
