package pl.rp.ws;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.UrlPathHelper;

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

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig configurePathMatch(" + configurer + ")");
    	UrlPathHelper urlPathHelper = new UrlPathHelper();
    	urlPathHelper.setRemoveSemicolonContent(false);
    	configurer.setUrlPathHelper(urlPathHelper);
    	TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig configurePathMatch(" + configurer + ")");
    }
    
    @Bean
    public MessageSource messageSource() {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig messageSource()");
    	ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
    	resource.setBasename("messages");
    	TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig messageSource()");
    	
    	return resource;    
    }
    
}

