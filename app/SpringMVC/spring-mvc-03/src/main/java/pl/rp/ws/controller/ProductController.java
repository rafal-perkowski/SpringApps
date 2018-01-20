package pl.rp.ws.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rp.ws.service.ProductService;

@Controller
@RequestMapping("/market")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String list(Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController list(" + model +")");
		model.addAttribute("products", productService.getAllProducts());
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController list(" + model +")");
	   
	   return "products";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController updateStock(" + model +")");
		productService.updateAllStock();
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController updateStock(" + model +")");
	   
	   return "redirect:/market/products";
	}
	
	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController getProductsByCategory(" + model + ", " + productCategory +")");
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController getProductsByCategory(" + model + ", " + productCategory +")");
	   
	   return "products";
	}
	
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(@MatrixVariable(pathVar="params") Map<String, List<String>> filterParams, Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController getProductsByFilter(" + filterParams + ", " + model + ")");
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController getProductsByFilter(" + filterParams + ", " + model + ")");
	   
	   return "products";
	}

	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController getProductById(" + productId + ", " + model + ")");
		model.addAttribute("product", productService.getProductById(productId));
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController getProductById(" + productId + ", " + model + ")");
		
		return "product";
	}
}
