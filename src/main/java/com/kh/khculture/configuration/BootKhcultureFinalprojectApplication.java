package com.kh.khculture.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.kh.khculture")
public class BootKhcultureFinalprojectApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BootKhcultureFinalprojectApplication.class, args);
	}
}
