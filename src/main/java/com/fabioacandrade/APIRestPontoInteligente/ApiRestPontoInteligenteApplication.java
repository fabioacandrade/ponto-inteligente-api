package com.fabioacandrade.APIRestPontoInteligente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApiRestPontoInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestPontoInteligenteApplication.class, args);
	}

}
