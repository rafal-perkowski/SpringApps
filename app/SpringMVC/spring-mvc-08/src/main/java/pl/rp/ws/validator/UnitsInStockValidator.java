package pl.rp.ws.validator;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Product;

@Component
public class UnitsInStockValidator implements Validator{

   public boolean supports(Class<?> clazz) {
	   
	   TestController.traceCounter(TestController.InsertType.INOUT, "UnitsInStockValidator supports(" + clazz + ")");
	   
	   return Product.class.isAssignableFrom(clazz);  
   }

   public void validate(Object target, Errors errors) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "UnitsInStockValidator validate(" + target + ", " + errors + ")");
	   Product product = (Product) target;
	   
	   if(product.getUnitPrice()!= null && new BigDecimal(1000).compareTo(product.getUnitPrice())<=0 && product.getUnitsInStock()>99) {
		   errors.rejectValue("unitsInStock", "pl.rp.ws.validator.UnitsInStockValidator.message");
	   }
	   TestController.traceCounter(TestController.InsertType.OUT, "UnitsInStockValidator validate(" + target + ", " + errors + ")");
   }

}
