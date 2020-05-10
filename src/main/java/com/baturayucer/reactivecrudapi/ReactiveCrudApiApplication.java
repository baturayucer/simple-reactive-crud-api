package com.baturayucer.reactivecrudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import javax.validation.constraints.Null;
import java.util.function.Consumer;

@SpringBootApplication
public class ReactiveCrudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveCrudApiApplication.class, args);
	}

}
