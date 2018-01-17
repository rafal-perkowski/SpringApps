package pl.rp.ws.repository;

import java.util.List;

import pl.rp.ws.model.Product;

public interface ProductRepository {

	List <Product> getAllProducts();
	
	void updateStock(String productId, long noOfUnits);
}
