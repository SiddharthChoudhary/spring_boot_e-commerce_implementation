package in.ecommerce.order.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecommerce.order.Services.OrderService;
import in.ecommerce.order.entitymodels.Order;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("orders")
    public Order createOrder(@RequestBody Order orderModel) {
        return this.orderService.createOrder(orderModel);
    }

    @GetMapping(path = "orders/{id}")
    public Order getOrder(@PathVariable String id) {
        return orderService.getOrder(id);
    }

    @GetMapping(path="orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @DeleteMapping("orders/{id}")
    public void cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id);
    }

}
