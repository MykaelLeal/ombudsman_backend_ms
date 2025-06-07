package com.ms.reclamacao_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@FeignClient
public class ReclamacaoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReclamacaoServiceApplication.class, args);
	}

}
