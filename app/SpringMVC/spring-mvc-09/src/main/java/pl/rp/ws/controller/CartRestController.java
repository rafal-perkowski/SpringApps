package pl.rp.ws.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.rp.ws.model.Cart;
import pl.rp.ws.dto.CartDto;
import pl.rp.ws.service.CartService;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {

   @Autowired
   private CartService cartService;
   
   @RequestMapping(method = RequestMethod.POST)
   @ResponseStatus(value = HttpStatus.CREATED)
   public void create(@RequestBody CartDto cartDto) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartRestController create(" + cartDto + ")");
	   cartService.create(cartDto);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartRestController create(" + cartDto + ")");
   }

   @RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
   public Cart read(@PathVariable(value = "cartId") String cartId) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "CartRestController read(" + cartId + ")");
	   
	   return cartService.read(cartId);
   }

   @RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
   @ResponseStatus(value = HttpStatus.OK)
   public void update(@PathVariable(value = "cartId") String cartId, @RequestBody CartDto cartDto) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartRestController update(" + cartId + ", " + cartDto + ")");
	   cartDto.setId(cartId);
	   cartService.update(cartId, cartDto);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartRestController update(" + cartId + ", " + cartDto + ")");
   }

   @RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
   @ResponseStatus(value = HttpStatus.OK)
   public void delete(@PathVariable(value = "cartId") String cartId) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartRestController delete(" + cartId + ")");
	   cartService.delete(cartId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartRestController delete(" + cartId + ")");
   }
      
   @RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
   @ResponseStatus(value = HttpStatus.OK)
   public void addItem(@PathVariable String productId, HttpSession session) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartRestController update(" + productId + ", " + session + ")");
	   cartService.addItem(session.getId(),productId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartRestController update(" + productId + ", " + session + ")");
   }
   
   @RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
   @ResponseStatus(value = HttpStatus.OK)
   public void removeItem(@PathVariable String productId, HttpSession session) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartRestController removeItem(" + productId + ", " + session + ")");
	   cartService.removeItem(session.getId(),productId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartRestController removeItem(" + productId + ", " + session + ")");
   }
}
