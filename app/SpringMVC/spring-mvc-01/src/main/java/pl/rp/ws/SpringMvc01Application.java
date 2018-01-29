package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc01Application {
	
	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc01Application main(" + args + ")");
		SpringApplication.run(SpringMvc01Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc01Application main(" + args + ")");
	}
}
