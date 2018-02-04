package pl.rp.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rp.ws.repository.ProductRepository;
import pl.rp.ws.service.ProductService;

//import java.util.List;
//import pl.rp.ws.model.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/products")
	public String list(Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController list(" + model+ ")");
//		List<Product> resultList = productRepository.getAllProducts();
//		model.addAttribute("products", resultList);
		model.addAttribute("products", productRepository.getAllProducts());	
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController list(" + model+ ")");
	   
	   return "products";
	}
	
	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController updateStock(" + model + ")");
		productService.updateAllStock();
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController updateStock(" + model + ")");
	   
	   return "redirect:/products";
	}

}
