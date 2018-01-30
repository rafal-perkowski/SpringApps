package pl.rp.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pl.rp.ws.controller.TestController;

@SpringBootApplication
public class SpringMvc09Application {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SpringMvc09Application main(" + args + ")");
		SpringApplication.run(SpringMvc09Application.class, args);
		TestController.traceCounter(TestController.InsertType.OUT, "SpringMvc09Application main(" + args + ")");
	}
}
