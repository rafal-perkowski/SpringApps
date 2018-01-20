package pl.rp.ws.repository;

import java.util.List;
import java.util.Map;

import pl.rp.ws.model.Product;

public interface ProductRepository {

	void updateStock(String productId, long noOfUnits);
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	Product getProductById(String productID);
}
