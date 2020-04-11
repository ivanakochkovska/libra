package com.example.libra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan("com.example.libra.Model")
@ComponentScan
public class LibraApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(LibraApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {    registry.addViewController("/").setViewName("home");  }

	}

