package com.cabletvbackend.CableTVProjectBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cabletvbackend")
public class CableTvProjectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CableTvProjectBackEndApplication.class, args);
	}

}
