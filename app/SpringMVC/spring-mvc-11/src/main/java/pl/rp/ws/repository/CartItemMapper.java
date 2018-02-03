package pl.rp.ws.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.CartItem;
import pl.rp.ws.service.ProductService;

public class CartItemMapper implements RowMapper<CartItem> {
   private ProductService productService;
   
   public CartItemMapper(ProductService productService) {
      this.productService = productService;
   }

   @Override
   public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "CartItemMapper mapRow(" + rs + ", " + rowNum + ")");
	   CartItem cartItem = new CartItem(rs.getString("ID"));
	   cartItem.setProduct(productService.getProductById(rs.getString("PRODUCT_ID")));
	   cartItem.setQuantity(rs.getInt("QUANTITY"));
	   TestController.traceCounter(TestController.InsertType.OUT, "CartItemMapper mapRow(" + rs + ", " + rowNum + ")");
	   
	   return cartItem;
   }
}
