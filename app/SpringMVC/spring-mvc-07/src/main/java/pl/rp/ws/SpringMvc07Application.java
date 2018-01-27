package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc07Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc07Application main(" + args + ")");
		SpringApplication.run(SpringMvc07Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc07Application main(" + args + ")");
	}
}
