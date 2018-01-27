package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc06Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc06Application main(" + args + ")");
		SpringApplication.run(SpringMvc06Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc06Application main(" + args + ")");
	}
}
