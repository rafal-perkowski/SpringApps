package pl.rp.ws.service;

import java.util.List;
import java.util.Map;
//import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Product;
import pl.rp.ws.repository.ProductRepository;
import pl.rp.ws.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

   @Autowired
   private ProductRepository productRepository;
  
   @Override
   public void updateAllStock() {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "ProductServiceImpl updateAllStock()");
	   List<Product> allProducts = productRepository.getAllProducts();
	   for(Product product : allProducts) {
		   if(product.getUnitsInStock()<500)
			   productRepository.updateStock(product.getProductId(), product.getUnitsInStock()+1000);
		   }
      TestController.traceCounter(TestController.InsertType.OUT, "ProductServiceImpl updateAllStock()");
   }
   
   @Override
   public List<Product> getAllProducts() {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "ProductServiceImpl getAllProducts()");
	   return productRepository.getAllProducts();
   }
   
   public List<Product> getProductsByCategory(String category) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "ProductServiceImpl getProductsByCategory(" + category + ")");
	   return productRepository.getProductsByCategory(category);
	}   
   
   public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "ProductServiceImpl getProductsByFilter(" + filterParams + ")");
	   return productRepository.getProductsByFilter(filterParams);
	}
   
   @Override
   public Product getProductById(String productID) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "ProductServiceImpl getProductById(" + productID + ")");
	   return productRepository.getProductById(productID);
   }
   
   @Override
   public void addProduct(Product product) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "ProductServiceImpl addProduct(" + product + ")");
	   productRepository.addProduct(product);
   }
}
