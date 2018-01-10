package pl.rp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.rp.app.controller.TestController;

@SpringBootApplication
public class SampleAppApplication {

	public static void main(String[] args) {
		
		TestController.traceCounter(TestController.InsertType.IN, "SampleAppApplication main(" + args + ")");
		
		SpringApplication.run(SampleAppApplication.class, args);
		
		TestController.traceCounter(TestController.InsertType.OUT, "SampleAppApplication main(" + args + ")");
	}
}
