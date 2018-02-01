package pl.rp.ws.repository;

import pl.rp.ws.model.Order;

public interface OrderRepository {
   long saveOrder(Order order);
}
