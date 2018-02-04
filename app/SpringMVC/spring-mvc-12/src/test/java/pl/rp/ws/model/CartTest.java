package pl.rp.ws.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.rp.ws.model.CartItem;

public class CartTest {

	private CartItem cartItem;
    private Cart cartCart;

    @Before
    public void setup() {
    	cartCart = new Cart("111");
    }

    @Test
    public void cart_grand_total_price_should_be_equal_to_summary_price_of_cart_items1() {
        //Arrange
    	cartItem = new CartItem("1");
    	
        Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
        cartItem.setProduct(iphone);
        
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem);
        cartCart.setCartItems(cartItems);

        //Act
        BigDecimal grandTotalPrice = cartCart.getGrandTotal();
        //System.out.println(grandTotalPrice);

        //Assert
        Assert.assertEquals(iphone.getUnitPrice(), grandTotalPrice);
    }
    
    @Test
    public void cart_grand_total_price_should_be_equal_to_summary_price_of_cart_items2() {
        //Arrange
    	List<CartItem> cartItems = new ArrayList<CartItem>();
    	Product iphone = new Product("P1234","iPhone 5s", new BigDecimal(500));
        Product inspiron = new Product("P1235","Dell Inspiron", new BigDecimal(700));
        Product nexus = new Product("P1236","Nexus 7", new BigDecimal(300));
        
        CartItem cartItem;
        
        cartItem = new CartItem("1");
        cartItem.setProduct(iphone);
        cartItems.add(cartItem);
        
        cartItem = new CartItem("2");
        cartItem.setProduct(inspiron);
        cartItems.add(cartItem);
        
        cartItem = new CartItem("3");
        cartItem.setProduct(nexus);
        cartItems.add(cartItem);

        cartCart.setCartItems(cartItems);
        
        //for(CartItem element : cartItems) System.out.println(element.getProduct().getUnitPrice());

        //Act
        BigDecimal grandTotalPrice = cartCart.getGrandTotal();
        
        //System.out.println(grandTotalPrice);

        //Assert
        Assert.assertEquals(iphone.getUnitPrice().add(inspiron.getUnitPrice()).add(nexus.getUnitPrice()), grandTotalPrice);
    }
}
