package pl.rp.ws.repository;

import pl.rp.ws.model.Cart;
import pl.rp.ws.dto.CartDto;

public interface CartRepository {

   void create(CartDto cartDto);
   
   Cart read(String id);
   
   void update(String id, CartDto cartDto);
   
   void delete(String id);

   void addItem(String cartId, String productId);

   void removeItem(String cartId, String productId);
}
