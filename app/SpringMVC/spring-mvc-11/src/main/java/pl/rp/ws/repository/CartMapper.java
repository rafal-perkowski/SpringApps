package pl.rp.ws.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Cart;
import pl.rp.ws.model.CartItem;
import pl.rp.ws.service.ProductService;

public class CartMapper implements RowMapper<Cart> {
   private CartItemMapper cartItemMapper;
   private NamedParameterJdbcTemplate jdbcTempleate;
   
   public CartMapper(NamedParameterJdbcTemplate jdbcTempleate, ProductService productService) {
      this.jdbcTempleate = jdbcTempleate;
      cartItemMapper = new CartItemMapper(productService);
   }

   public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartMapper mapRow(" + rs + ", " + rowNum + ")");
	   
	   String id = rs.getString("ID");
	   Cart cart = new Cart(id);
	   
	   String SQL = String.format("SELECT * FROM CART_ITEM WHERE CART_ID = '%s'", id);
	   List<CartItem> cartItems = jdbcTempleate.query(SQL, cartItemMapper);
	   cart.setCartItems(cartItems);
	   
	   TestController.traceCounter(TestController.InsertType.OUT, "CartMapper mapRow(" + rs + ", " + rowNum + ")");
	   
	   return cart;
   }
}
