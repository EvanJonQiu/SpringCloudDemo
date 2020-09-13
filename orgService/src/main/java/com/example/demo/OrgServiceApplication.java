package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

import com.example.demo.utils.UserContextFilter;

@SpringBootApplication
@EnableBinding(Source.class)
public class OrgServiceApplication {
	
//	@Bean
//	public Filter userContextFilter() {
//		UserContextFilter userContextFilter = new UserContextFilter();
//		return userContextFilter;
//	}

	public static void main(String[] args) {
		SpringApplication.run(OrgServiceApplication.class, args);
	}

}
