package com.SunCycle.SunCycle;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SunCycleApplication {

	public static void main(String[] args) {
//		System.out.println("Starting SunCycle");
//		SpringApplication.run(SunCycleApplication.class, args);
		new SpringApplicationBuilder()
				.profiles("dev") // and so does this
				.sources(SunCycleApplication.class)
				.run(args);
	}

}
