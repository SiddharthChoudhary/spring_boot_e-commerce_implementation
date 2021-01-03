package in.ecommerce.order.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ecommerce.order.Services.OrderService;
import in.ecommerce.order.entitymodels.Order;
import in.ecommerce.order.responsemodels.OrdersResponceModel;

@RestController
@RequestMapping("api/batch")
public class BatchOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("orders")
    public OrdersResponceModel createOrder(@RequestBody List<Order> ordersList) {

        return this.orderService.createOrders(ordersList);
    }

    @PutMapping("orders")
    public OrdersResponceModel updateOrder(@RequestBody List<Order> ordersList) {

        return this.orderService.updateOrders(ordersList);
    }
}
