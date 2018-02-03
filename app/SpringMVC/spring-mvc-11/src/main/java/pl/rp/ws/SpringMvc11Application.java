package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc11Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc11Application main(" + args + ")");
		SpringApplication.run(SpringMvc11Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc11Application main(" + args + ")");
	}
}
