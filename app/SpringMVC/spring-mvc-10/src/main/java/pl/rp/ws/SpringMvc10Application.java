package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc10Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc10Application main(" + args + ")");
		SpringApplication.run(SpringMvc10Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc10Application main(" + args + ")");
	}
}
