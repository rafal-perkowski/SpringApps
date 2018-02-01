package pl.rp.ws.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rp.ws.model.Product;
import pl.rp.ws.controller.TestController;
import pl.rp.ws.exception.ProductNotFoundException;
import pl.rp.ws.service.ProductService;

public class ProductIdValidator implements ConstraintValidator<ProductId, String>{
   
   @Autowired
   private ProductService productService;

   public void initialize(ProductId constraintAnnotation) {
	   //  intentionally left blank; this is the place to initialize the constraint annotation for any sensible default values.
	   TestController.traceCounter(TestController.InsertType.INOUT, "ProductIdValidator initialize(" + constraintAnnotation + ")");
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "ProductIdValidator isValid()");
	   Product product;
	   try {
		   product = productService.getProductById(value);
	   } catch (ProductNotFoundException e) {
		   return true;
	   }
      
	   if(product!= null) {
		   return false;
	   }
	   TestController.traceCounter(TestController.InsertType.OUT, "ProductIdValidator isValid()");
      
	   return true;
   }   
}
