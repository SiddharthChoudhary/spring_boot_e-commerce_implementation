package in.ecommerce.order.Services;

import java.util.List;

import in.ecommerce.order.entitymodels.Order;
import in.ecommerce.order.responsemodels.OrdersResponceModel;

public interface OrderService {

    public Order createOrder(Order order);

    public Order getOrder(String id);
    
    public List<Order> getAllOrders();

    public void cancelOrder(String id);

    public OrdersResponceModel createOrders(List<Order> orders);

    public OrdersResponceModel updateOrders(List<Order> orders);

}
