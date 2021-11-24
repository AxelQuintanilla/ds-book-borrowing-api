package com.telus.ds;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories ("domain.repository-package")
@ComponentScan(basePackages = {"com.telus.ds.service", "com.telus.ds.entity", "com.telus.ds.controller", "com.telus.ds.entity.dto", "com.telus.ds.repository"})
@OpenAPIDefinition(info = @Info(title = "Track API", version = "1.0", description = "Track API"))
public class TrackApplication {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(TrackApplication.class, args);
	}
	
}
