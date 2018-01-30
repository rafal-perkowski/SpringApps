package pl.rp.ws.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

   @RequestMapping
   public String get(HttpServletRequest request) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "CartController get(" + request + ")");
	   
	   return "redirect:/cart/"+request.getSession(true).getId();
   }
   
   @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
   public String getCart(@PathVariable(value = "cartId") String cartId, Model model) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartController getCart(" + cartId + ", " + model + ")");
	   model.addAttribute("cartId",cartId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartController getCart(" + cartId + ", " + model + ")");
	   
	   return "cart";
   }
}
