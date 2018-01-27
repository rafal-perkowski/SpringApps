package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc02Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc02Application main(" + args + ")");
		
		SpringApplication.run(SpringMvc02Application.class, args);
		
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc02Application main(" + args + ")");
		
	}
}
