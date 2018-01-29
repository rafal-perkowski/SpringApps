package pl.rp.ws;

import java.util.ArrayList;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Product;

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
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig addResourceHandlers(" + registry + ")");
    	registry.addResourceHandler("/img/**")
              .addResourceLocations("/resources/images/");
    	TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig addResourceHandlers(" + registry + ")");
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig multipartResolver()");
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        resolver.setMaxUploadSize(10240000);
        TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig multipartResolver()");
        
        return resolver;
    }
    
    @Bean
    public MappingJackson2JsonView jsonView() {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig jsonView()");
    	MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
    	jsonView.setPrettyPrint(true);
    	TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig jsonView()");
    	
    	return jsonView; 
    }
    
    @Bean
    public MarshallingView xmlView() {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig xmlView()");
    	Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    	marshaller.setClassesToBeBound(Product.class);
    	MarshallingView xmlView = new MarshallingView(marshaller);
    	TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig xmlView()");
    	
    	return xmlView;
    }
    
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
    	
    	TestController.traceCounter(TestController.InsertType.IN, "WebApplicationContextConfig contentNegotiatingViewResolver(" + manager + ")");
    	ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    	resolver.setContentNegotiationManager(manager);
    	ArrayList<View> views = new ArrayList<>();
    	views.add(jsonView());
    	views.add(xmlView());
    	resolver.setDefaultViews(views);
    	TestController.traceCounter(TestController.InsertType.OUT, "WebApplicationContextConfig contentNegotiatingViewResolver(" + manager + ")");
    	
    	return resolver;
    }
    
}

