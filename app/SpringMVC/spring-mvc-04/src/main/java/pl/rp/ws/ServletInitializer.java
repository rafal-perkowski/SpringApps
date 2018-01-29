package pl.rp.ws;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import pl.rp.ws.controller.TestController;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ServletInitializer configure(" + application + ")");
		setRegisterErrorPageFilter(false);
		TestController.traceCounter(TestController.InsertType.OUT, "ServletInitializer configure(" + application + ")");
		
		return application.sources(SpringMvc04Application.class);
	}

}
