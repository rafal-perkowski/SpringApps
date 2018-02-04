package pl.rp.ws.controller;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pl.rp.ws.exception.NoProductsFoundUnderCategoryException;
import pl.rp.ws.exception.ProductNotFoundException;
import pl.rp.ws.model.Product;
import pl.rp.ws.service.ProductService;

@Controller
@RequestMapping("market")
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
		List<Product> products = productService.getProductsByCategory(productCategory);
		if (products == null || products.isEmpty()) {
			TestController.traceCounter(TestController.InsertType.OUT, "ProductController getProductsByCategory(" + model + ", " + productCategory +")");
			TestController.traceCounter(TestController.InsertType.DEBUG, "ProductController getProductsByCategory(" + model + ", " + productCategory +")");
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
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
	
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController getAddNewProductForm(" + model + ")");
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController getAddNewProductForm(" + model + ")");
		
		return "addProduct";
	}
	   
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result, HttpServletRequest request) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController processAddNewProductForm(" + newProduct + ", " + result + "," + request + ")");
		String[] suppressedFields = result.getSuppressedFields();
		
		if (suppressedFields.length > 0) {
			TestController.traceCounter(TestController.InsertType.OUT, "ProductController processAddNewProductForm(" + newProduct + ", " + result + "," + request + ")");
			TestController.traceCounter(TestController.InsertType.DEBUG, "ProductController processAddNewProductForm(" + newProduct + ", " + result + "," + request + ")");
			throw new RuntimeException("Attempting to bind disallowed fields: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		MultipartFile productImage = newProduct.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		if (productImage!=null && !productImage.isEmpty()) {
			try {
				productImage.transferTo(new File(rootDirectory+"resources\\images\\"+ newProduct.getProductId() + ".png"));
			} catch (Exception e) {
				TestController.traceCounter(TestController.InsertType.OUT, "ProductController processAddNewProductForm(" + newProduct + ", " + result + "," + request + ")");
				TestController.traceCounter(TestController.InsertType.DEBUG, "ProductController processAddNewProductForm(" + newProduct + ", " + result + "," + request + ")");
				throw new RuntimeException("Product Image saving failed", e);
			}
		}
		productService.addProduct(newProduct);
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController processAddNewProductForm(" + newProduct + ", " + result + "," + request + ")");

		return "redirect:/market/products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController initialiseBinder(" + binder + ")");
		binder.setAllowedFields(
				"productId",
	            "name",
	            "unitPrice",
	            "description",
	            "manufacturer",
	            "category",
	            "unitsInStock",
	            "condition",
	            "unitsInOrder",
	            "discontinued",
	            "productImage",
	            "language");
		TestController.traceCounter(TestController.InsertType.OUT, "ProductController initialiseBinder(" + binder + ")");
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		
		TestController.traceCounter(TestController.InsertType.IN, "ProductController handleError(" + req + ", " + exception + ")");
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("invalidProductId", exception.getProductId());
	    mav.addObject("exception", exception);
	    mav.addObject("url", req.getRequestURL()+"?"+req.getQueryString());
	    mav.setViewName("productNotFound");
	    TestController.traceCounter(TestController.InsertType.OUT, "ProductController handleError(" + req + ", " + exception + ")");
	    return mav;
	}
	
	@RequestMapping("/products/invalidPromoCode")
	public String invalidPromoCode() {
	      return "invalidPromoCode";
	}
	
}
