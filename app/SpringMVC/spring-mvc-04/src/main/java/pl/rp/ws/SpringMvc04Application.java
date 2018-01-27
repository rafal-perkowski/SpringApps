package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc04Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc04Application main(" + args + ")");
		SpringApplication.run(SpringMvc04Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc04Application main(" + args + ")");
	}
}
