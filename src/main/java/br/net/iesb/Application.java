package br.net.iesb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application extends SpringApplication {
		
	public static void main (String... args) {
		SpringApplication.run(Application.class,args);
	}

}
