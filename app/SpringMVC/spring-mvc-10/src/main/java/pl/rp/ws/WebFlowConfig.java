package pl.rp.ws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
//import org.thymeleaf.spring4.SpringTemplateEngine;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import pl.rp.ws.controller.TestController;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {

   @Bean
   public FlowDefinitionRegistry flowRegistry() {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "WebFlowConfig flowRegistry()");
	   
       return getFlowDefinitionRegistryBuilder()
               .setBasePath("/WEB-INF/flows")
               .addFlowLocationPattern("/**/*-flow.xml")
               .build();
       
//       return getFlowDefinitionRegistryBuilder()
//    		   .setBasePath("classpath*:/templates/flows")
//               .addFlowLocationPattern("/**/*-flow.xml")
//               .build();
   
   }
   
//   @Bean public ViewResolver viewResolver() {
//	   
//	   TestController.traceCounter(TestController.InsertType.IN, "WebFlowConfig viewResolver()");
//	   ClassLoaderTemplateResolver templateResolver = new
//	   ClassLoaderTemplateResolver();
//	   templateResolver.setTemplateMode("XHTML");
//	   templateResolver.setPrefix("/");
//	   templateResolver.setSuffix(".html");
//	   SpringTemplateEngine engine = new SpringTemplateEngine();
//	   engine.setTemplateResolver(templateResolver);
//	   ThymeleafViewResolver thymeleafviewResolver = new
//	   ThymeleafViewResolver();
//	   thymeleafviewResolver.setTemplateEngine(engine);
//	   TestController.traceCounter(TestController.InsertType.OUT, "WebFlowConfig viewResolver()");
//	   
//	   return thymeleafviewResolver;
//   }

   @Bean
   public FlowExecutor flowExecutor() {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "WebFlowConfig flowExecutor()");
	   
       return getFlowExecutorBuilder(flowRegistry()).build();
   }

   @Bean
   public FlowHandlerMapping flowHandlerMapping() {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "WebFlowConfig flowHandlerMapping()");
       FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
       handlerMapping.setOrder(-1);
       handlerMapping.setFlowRegistry(flowRegistry());
       TestController.traceCounter(TestController.InsertType.OUT, "WebFlowConfig flowHandlerMapping()");
       
       return handlerMapping;
   }

   @Bean
   public FlowHandlerAdapter flowHandlerAdapter() {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "WebFlowConfig flowHandlerAdapter()");
       FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
       handlerAdapter.setFlowExecutor(flowExecutor());
       handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
       TestController.traceCounter(TestController.InsertType.OUT, "WebFlowConfig flowHandlerAdapter()");
       
       return handlerAdapter;
   }
   
}
