package com.marondal.marondalgram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarondalgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarondalgramApplication.class, args);
	}

}
