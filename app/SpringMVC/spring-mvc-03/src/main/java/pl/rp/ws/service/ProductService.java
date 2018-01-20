package pl.rp.ws.service;

import java.util.List;
import java.util.Map;

import pl.rp.ws.model.Product;

public interface ProductService {

	void updateAllStock();
	
	List<Product> getAllProducts();
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	Product getProductById(String productID);
}
