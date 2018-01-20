package pl.rp.ws.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Product;
import pl.rp.ws.repository.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository{
  
   @Autowired
   private NamedParameterJdbcTemplate jdbcTemplate;

   @Override
   public List<Product> getAllProducts() {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryProductRepository getAllProducts()");
	   Map<String, Object> params = new HashMap<String, Object>();
	   List<Product> result = jdbcTemplate.query("SELECT * FROM products", params, new ProductMapper());
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryProductRepository getAllProducts()");
	   return result;
   }

   private static final class ProductMapper implements RowMapper<Product> {
      public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
    	  
    	  TestController.traceCounter(TestController.InsertType.IN, "ProductMapper mapRow()");
    	  Product product = new Product();
    	  product.setProductId(rs.getString("ID"));
    	  product.setName(rs.getString("NAME"));
    	  product.setDescription(rs.getString("DESCRIPTION"));
    	  product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
    	  product.setManufacturer(rs.getString("MANUFACTURER"));
    	  product.setCategory(rs.getString("CATEGORY"));
    	  product.setCondition(rs.getString("CONDITION"));
    	  product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
    	  product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
    	  product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
    	  TestController.traceCounter(TestController.InsertType.OUT, "ProductMapper mapRow()");
    	  return product;
      }
   }
   
   @Override
   public void updateStock(String productId, long noOfUnits) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryProductRepository updateStock(" + productId + ", " + noOfUnits + ")");
	   String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id"; 
	   Map<String, Object> params = new HashMap<>();
	   params.put("unitsInStock", noOfUnits); 
	   params.put("id", productId);
	   jdbcTemplate.update(SQL, params);
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryProductRepository updateStock(" + productId + ", " + noOfUnits + ")");
   }
   
   @Override
   public List<Product> getProductsByCategory(String category) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryProductRepository getProductsByCategory(" + category + ")");
	   String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY = :category";
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("category", category);
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryProductRepository getProductsByCategory(" + category + ")");
	   return jdbcTemplate.query(SQL, params, new ProductMapper());
   }
   
   @Override
   public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryProductRepository getProductsByFilter(" + filterParams + ")");
	   String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY IN ( :categories ) AND MANUFACTURER IN ( :brands)";
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryProductRepository getProductsByFilter(" + filterParams + ")");
	   return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
   }
   
   @Override
   public Product getProductById(String productID) {
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryProductRepository getProductById(" + productID + ")");
	   String SQL = "SELECT * FROM PRODUCTS WHERE ID = :id";
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("id", productID);
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryProductRepository getProductById(" + productID + ")");
	   return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
   }

}
