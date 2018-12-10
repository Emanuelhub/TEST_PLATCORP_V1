package br.com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestPlatcorpV1Application {

	public static void main(String[] args) {
		SpringApplication.run(TestPlatcorpV1Application.class, args);
	}
}
