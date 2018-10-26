package com.eim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//public class SellApplication  extends SpringBootServletInitializer {
public class SellApplication  {
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(SellApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(SellApplication.class, args);
	}
}
