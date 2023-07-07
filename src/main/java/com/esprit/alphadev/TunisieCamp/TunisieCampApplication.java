package com.esprit.alphadev.TunisieCamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class TunisieCampApplication {

	public static void main(String[] args) {
		SpringApplication.run(TunisieCampApplication.class, args);
	}

}
