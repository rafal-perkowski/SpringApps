package pl.rp.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	private static final boolean langPLBoolean = true;

  @RequestMapping("/")
  public String welcome(Model model) {
	  
	  TestController.traceCounter(TestController.InsertType.IN, "HomeController welcome(" + model + ")");
	 
	if(langPLBoolean) {
	    model.addAttribute("greeting", "Witaj w Sklepie Internetowym!");
	    model.addAttribute("tagline", "Testowa strona sklepu internetowego");
	}
	else {
	    model.addAttribute("greeting", "Welcome to Web Store!");
	    model.addAttribute("tagline", "The one and only amazing web store");
    }

	TestController.traceCounter(TestController.InsertType.OUT, "HomeController welcome(" + model + ")");
	
    return "index";
  }
}
