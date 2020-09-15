package com.cabletvbackend;

import com.cabletvbackend.repository.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.GenericWebApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.cabletvbackend.*")
public class CableTvProjectBackEndApplication{

	@Autowired
	GenericWebApplicationContext context;
	public static void main(String[] args) {
		SpringApplication.run(CableTvProjectBackEndApplication.class, args);
	}


//	@Override
//	public void run(String... args) throws Exception {
//		context.registerBean(UserDetailService.class);
//	}
}
