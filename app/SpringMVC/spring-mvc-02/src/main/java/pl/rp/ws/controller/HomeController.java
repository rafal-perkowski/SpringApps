package pl.rp.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

  @RequestMapping("/")
  public String welcome(Model model) {
	  
	TestController.traceCounter(TestController.InsertType.IN, "HomeController welcome()");
	  
    model.addAttribute("greeting", "Welcome to Web Store!");
    model.addAttribute("tagline", "The one and only amazing web store");

    TestController.traceCounter(TestController.InsertType.OUT, "HomeController welcome()");
    
    return "index";
  }
}
