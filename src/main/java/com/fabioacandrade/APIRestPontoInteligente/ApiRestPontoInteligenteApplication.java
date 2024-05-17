package com.fabioacandrade.APIRestPontoInteligente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@SpringBootApplication //Retirar ao chegar na aula 24
@EnableCaching
public class ApiRestPontoInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestPontoInteligenteApplication.class, args);
	}

	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable()
				.authorizeRequests().antMatchers("/").permitAll();
	}

}
