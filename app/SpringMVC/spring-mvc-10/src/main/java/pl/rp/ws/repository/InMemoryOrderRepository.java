package pl.rp.ws.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Address;
import pl.rp.ws.model.Customer;
import pl.rp.ws.model.Order;
import pl.rp.ws.model.ShippingDetail;
import pl.rp.ws.repository.OrderRepository;
import pl.rp.ws.service.CartService;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

   @Autowired
   private NamedParameterJdbcTemplate jdbcTempleate;
   
   @Autowired
   private CartService CartService;

   @Override
   public long saveOrder(Order order) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryOrderRepository saveOrder(" + order + ")");
       Long customerId = saveCustomer(order.getCustomer());
       Long shippingDetailId = saveShippingDetail(order.getShippingDetail());
       
       order.getCustomer().setCustomerId(customerId);
       order.getShippingDetail().setId(shippingDetailId);
       
       long createdOrderId = createOrder(order);
       CartService.clearCart(order.getCart().getId());
       TestController.traceCounter(TestController.InsertType.OUT, "InMemoryOrderRepository saveOrder(" + order + ")");
       
       return createdOrderId;
   }
   
   private long saveShippingDetail(ShippingDetail shippingDetail) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryOrderRepository saveShippingDetail(" + shippingDetail + ")");
	   long addressId = saveAddress(shippingDetail.getShippingAddress());
	   
	   String SQL = "INSERT INTO SHIPPING_DETAIL(NAME,SHIPPING_DATE,SHIPPING_ADDRESS_ID) "
            + "VALUES (:name, :shippingDate, :addressId)";
	   
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("name", shippingDetail.getName());
	   params.put("shippingDate", shippingDetail.getShippingDate());
	   params.put("addressId", addressId);
	   
	   SqlParameterSource paramSource = new MapSqlParameterSource(params);
	   
	   KeyHolder keyHolder = new GeneratedKeyHolder();
	   jdbcTempleate.update(SQL, paramSource,keyHolder, new String[]{"ID"});
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryOrderRepository saveShippingDetail(" + shippingDetail + ")");
	   
	   return keyHolder.getKey().longValue();
   }

   private long saveCustomer(Customer customer) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryOrderRepository saveCustomer(" + customer + ")");
	   long addressId = saveAddress(customer.getBillingAddress());
	   
	   String SQL = "INSERT INTO CUSTOMER(NAME,PHONE_NUMBER,BILLING_ADDRESS_ID) "
            + "VALUES (:name, :phoneNumber, :addressId)";
	   
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("name", customer.getName());
	   params.put("phoneNumber", customer.getPhoneNumber());
	   params.put("addressId", addressId);
	   
	   SqlParameterSource paramSource = new MapSqlParameterSource(params);
	   
	   KeyHolder keyHolder = new GeneratedKeyHolder();
	   jdbcTempleate.update(SQL, paramSource,keyHolder, new String[]{"ID"});
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryOrderRepository saveCustomer(" + customer + ")");
	   
	   return keyHolder.getKey().longValue();
   }

   private long saveAddress(Address address) {
	   
	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryOrderRepository saveAddress(" + address + ")");
	   String SQL = "INSERT INTO ADDRESS(DOOR_NO,STREET_NAME,AREA_NAME,STATE,COUNTRY,ZIP) "
            + "VALUES (:doorNo, :streetName, :areaName, :state, :country, :zip)";
	   
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("doorNo", address.getDoorNo());
	   params.put("streetName", address.getStreetName());
	   params.put("areaName", address.getAreaName());
	   params.put("state", address.getState());
	   params.put("country", address.getCountry());
	   params.put("zip", address.getZipCode());
	   
	   SqlParameterSource paramSource = new MapSqlParameterSource(params);
	   
	   KeyHolder keyHolder = new GeneratedKeyHolder();
	   jdbcTempleate.update(SQL, paramSource,keyHolder, new String[]{"ID"});
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryOrderRepository saveAddress(" + address + ")");
	   
	   return keyHolder.getKey().longValue();
   }

   private long createOrder(Order order) {

	   TestController.traceCounter(TestController.InsertType.IN, "InMemoryOrderRepository createOrder(" + order + ")");
	   String SQL = "INSERT INTO ORDERS(CART_ID,CUSTOMER_ID,SHIPPING_DETAIL_ID) "
            + "VALUES (:cartId, :customerId, :shippingDetailId)";
	   
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("id", order.getOrderId());
	   params.put("cartId", order.getCart().getId());
	   params.put("customerId", order.getCustomer().getCustomerId());
	   params.put("shippingDetailId", order.getShippingDetail().getId());
	   
	   SqlParameterSource paramSource = new MapSqlParameterSource(params);
	   
	   KeyHolder keyHolder = new GeneratedKeyHolder();
	   jdbcTempleate.update(SQL, paramSource,keyHolder, new String[]{"ID"});
	   TestController.traceCounter(TestController.InsertType.OUT, "InMemoryOrderRepository createOrder(" + order + ")");
	   
	   return keyHolder.getKey().longValue();
   }
}
