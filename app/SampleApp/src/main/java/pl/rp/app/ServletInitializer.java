package pl.rp.app;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import pl.rp.app.controller.TestController;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "SpringApplicationBuilder configure(" + application + ")");
		
		return application.sources(SampleAppApplication.class);
	}

}
