package com.biruntha.security.basicauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.biruntha.security.basicauth.repository"})
public class SpringbootBookApiBasicauthMongodbApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBookApiBasicauthMongodbApplication.class, args);
	}
}
