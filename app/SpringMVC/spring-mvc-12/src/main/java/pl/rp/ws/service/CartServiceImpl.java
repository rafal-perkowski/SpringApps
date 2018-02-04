package pl.rp.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.rp.ws.exception.InvalidCartException;
import pl.rp.ws.model.Cart;
import pl.rp.ws.repository.CartRepository;
import pl.rp.ws.controller.TestController;
import pl.rp.ws.dto.CartDto;
import pl.rp.ws.service.CartService;

@Service
public class CartServiceImpl implements CartService{
   
   @Autowired
   private CartRepository cartRepository;

   public void create(CartDto cartDto) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl create(" + cartDto + ")");
	   cartRepository.create(cartDto);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl create(" + cartDto + ")");
   }

   @Override
   public Cart read(String id) {
	   TestController.traceCounter(TestController.InsertType.INOUT, "CartServiceImpl read(" + id + ")");
	   return cartRepository.read(id);
   }

   @Override
   public void update(String id, CartDto cartDto) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl update(" + id + ", " + cartDto + ")");
	   cartRepository.update(id, cartDto);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl update(" + id + ", " + cartDto + ")");
   }

   @Override
   public void delete(String id) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl delete(" + id + ")");
	   cartRepository.delete(id);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl delete(" + id + ")");
   }

   @Override
   public void addItem(String cartId, String productId) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl addItem(" + cartId + ", " + productId + ")");
	   cartRepository.addItem(cartId, productId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl addItem(" + cartId + ", " + productId + ")");
   }

   @Override
   public void removeItem(String cartId, String productId) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl removeItem(" + cartId + ", " + productId + ")");
	   cartRepository.removeItem(cartId, productId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl removeItem(" + cartId + ", " + productId + ")");
   }
   
   @Override
   public Cart validate(String cartId) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl validate(" + cartId + ")");
	   Cart cart = cartRepository.read(cartId);
	   if(cart==null || cart.getCartItems().size()==0) {
		   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl validate(" + cartId + ")");
		   TestController.traceCounter(TestController.InsertType.DEBUG, "CartServiceImpl validate(" + cartId + ")");
		   throw new InvalidCartException(cartId);
	   }
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl validate(" + cartId + ")");
      
      return cart;
   }

   @Override
   public void clearCart(String cartId) {
	   TestController.traceCounter(TestController.InsertType.IN, "CartServiceImpl clearCart(" + cartId + ")");
	   cartRepository.clearCart(cartId);
	   TestController.traceCounter(TestController.InsertType.OUT, "CartServiceImpl clearCart(" + cartId + ")");
   }
}
