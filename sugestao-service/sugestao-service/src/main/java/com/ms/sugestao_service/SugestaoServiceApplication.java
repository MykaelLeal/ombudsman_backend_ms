package com.ms.sugestao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class SugestaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SugestaoServiceApplication.class, args);
	}

}
