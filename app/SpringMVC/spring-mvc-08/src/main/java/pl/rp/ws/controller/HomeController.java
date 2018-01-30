package pl.rp.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {
  
	@RequestMapping
	public String welcome(Model model, RedirectAttributes redirectAttributes) {
		
		TestController.traceCounter(TestController.InsertType.IN, "HomeController welcome(" + model + ", " + redirectAttributes +")");
		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");
		redirectAttributes.addFlashAttribute("greeting", "Welcome to Web Store!");
		redirectAttributes.addFlashAttribute("tagline", "The one and only amazing web store");
		TestController.traceCounter(TestController.InsertType.OUT, "HomeController welcome(" + model + ", " + redirectAttributes + ")");
		
		return "redirect:/welcome/greeting";
	}
	
	@RequestMapping("/welcome/greeting")
	public String greeting() {
		
		TestController.traceCounter(TestController.InsertType.INOUT, "HomeController greeting()");
		
		return "index";
	}
}
