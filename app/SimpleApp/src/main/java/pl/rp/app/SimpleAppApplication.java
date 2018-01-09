package pl.rp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.rp.app.controller.TestController;

@EnableJpaRepositories("pl.rp.app") 
@EntityScan("pl.rp.app.model")
@SpringBootApplication
public class SimpleAppApplication {

	public static void main(String[] args) {
		
		TestController.traceCounter(1, "SimpleAppApplication main(" + args + ")");
		
		SpringApplication.run(SimpleAppApplication.class, args);
		
		TestController.traceCounter(2, "SimpleAppApplication main(" + args + ")");
	}
}
