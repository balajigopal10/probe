package com.sea.probe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sea.probe")
public class ProbeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProbeApplication.class, args);
	}

}
