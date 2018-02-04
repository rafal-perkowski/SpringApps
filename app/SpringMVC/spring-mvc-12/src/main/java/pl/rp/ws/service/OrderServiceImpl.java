package pl.rp.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.rp.ws.controller.TestController;
import pl.rp.ws.model.Order;
import pl.rp.ws.repository.OrderRepository;
import pl.rp.ws.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
   
   @Autowired
   private OrderRepository orderRepository;
   
   @Override
   public Long saveOrder(Order order) {
	   TestController.traceCounter(TestController.InsertType.INOUT, "OrderServiceImpl saveOrder(" + order + ")");
	   return orderRepository.saveOrder(order);
   }
}
