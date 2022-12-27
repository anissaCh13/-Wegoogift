package com.example.wegoogift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WegoogiftApplication {

	public static void main(String[] args) {
		SpringApplication.run(WegoogiftApplication.class, args);
	}

}
