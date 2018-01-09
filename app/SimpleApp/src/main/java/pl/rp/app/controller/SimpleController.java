package pl.rp.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.rp.app.controller.TestController;

@Controller
public class SimpleController {
    @Value("${spring.application.name}")
    String appName;
 
    @GetMapping("/")
    public String homePage(Model model) {
    	
    	TestController.traceCounter(1, "SimpleController homePage(" + model + ")");
    	
        model.addAttribute("appName", appName);
        
        TestController.traceCounter(2, "SimpleController homePage(" + model + ")");
        
        return "home";
    }
}