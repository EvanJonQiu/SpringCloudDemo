package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.example.demo.utils.UserContextFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableResourceServer
public class OrganizationServiceApplication {

//	@Bean
//	public Filter userContextFilter() {
//		UserContextFilter userContextFilter = new UserContextFilter();
//		return userContextFilter;
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(OrganizationServiceApplication.class, args);
	}

}
