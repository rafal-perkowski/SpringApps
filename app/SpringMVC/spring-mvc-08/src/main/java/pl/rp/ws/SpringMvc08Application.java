package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc08Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc08Application main(" + args + ")");
		SpringApplication.run(SpringMvc08Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc08Application main(" + args + ")");
	}
}
