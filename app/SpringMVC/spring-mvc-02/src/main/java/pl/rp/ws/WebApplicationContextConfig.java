package pl.rp.ws;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import pl.rp.ws.controller.TestController;

@Configuration
@EnableWebMvc
@ComponentScan("pl.rp.ws")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
	
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
		TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig configureDefaultServletHandling(" + configurer + ")");
        configurer.enable();
        TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig configureDefaultServletHandling(" + configurer + ")");
    }

}

