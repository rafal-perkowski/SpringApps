package pl.rp.ws.service;

import java.util.List;

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
}
