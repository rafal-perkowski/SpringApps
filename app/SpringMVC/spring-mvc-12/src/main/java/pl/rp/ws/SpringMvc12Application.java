package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc12Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc12Application main(" + args + ")");
		SpringApplication.run(SpringMvc12Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc12Application main(" + args + ")");
	}
}
