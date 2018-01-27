package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc05Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc05Application main(" + args + ")");
		SpringApplication.run(SpringMvc05Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc05Application main(" + args + ")");
	}
}
