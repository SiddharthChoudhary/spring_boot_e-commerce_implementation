package in.ecommerce.order.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ecommerce.order.Services.OrderService;
import in.ecommerce.order.entitymodels.Billing;
import in.ecommerce.order.entitymodels.Customer;
import in.ecommerce.order.entitymodels.Item;
import in.ecommerce.order.entitymodels.Order;
import in.ecommerce.order.entitymodels.Payment;
import in.ecommerce.order.entitymodels.Shipping;
import in.ecommerce.order.repositories.BillingRepository;
import in.ecommerce.order.repositories.CostomerRepository;
import in.ecommerce.order.repositories.ItemRepository;
import in.ecommerce.order.repositories.OrderRepository;
import in.ecommerce.order.repositories.PaymentRepository;
import in.ecommerce.order.repositories.ShippingRepository;
import in.ecommerce.order.responsemodels.OrdersResponceModel;
import in.ecommerce.order.util.OrderStatus;

@Service
@Transactional
public class OrdersServiceImpl implements OrderService {

    Logger logger = LoggerFactory.logger(OrdersServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShippingRepository shippingRepository;

    @Autowired
    private CostomerRepository costomerRepository;

    @Override
    public Order createOrder(Order order) {

        logger.info("creating order ...");

        Order orderRes = new Order();
        orderRes.setStatus(OrderStatus.ACTIVE);

        BeanUtils.copyProperties(order, orderRes);

        // check reauired data to create an order
        if (order.getBilling() != null && order.getCustomer() != null && order.getItems() != null
                && order.getPayment() != null && order.getShipping() != null) {

            // create order billing
            Billing billing = new Billing();

            logger.info("creating order billing");
            BeanUtils.copyProperties(order.getBilling(), billing);
            this.billingRepository.save(billing);
            orderRes.setBilling(billing);

            // we need to check if customer is already in our db
            Customer customer = order.getCustomer();
            Optional<Customer> OptCustomer = this.costomerRepository.findById(customer.getId());

            if (OptCustomer.isPresent()) {
                customer = new Customer();
                BeanUtils.copyProperties(order.getCustomer(), customer);
                orderRes.setCustomer(customer);
            } else {
                logger.error("customer does not exist");
                throw new RuntimeException("customer does not exist");
            }

            // we need to check if the payment is by one card or two
            logger.info("paying with " + order.getPayment().size() + " cards");

            List<Payment> payments = new ArrayList<Payment>();

            for (Payment payment : order.getPayment()) {
                Payment payment2 = new Payment();
                BeanUtils.copyProperties(payment, payment2);
                payment2.setTotal(order.getTotal() / order.getPayment().size());
                payments.add(payment2);
                this.paymentRepository.save(payment2);
            }
            orderRes.setPayment(payments);

            // creating order shipping
            Shipping shipping = new Shipping();

            logger.info("creating order shipping");
            BeanUtils.copyProperties(order.getShipping(), shipping);
            this.shippingRepository.save(shipping);
            orderRes.setShipping(shipping);

            List<Item> items = new ArrayList<Item>();

            for (Item i : order.getItems()) {
                if (this.itemRepository.findById(i.getId()).isPresent()) {
                    items.add(i);
                } else {
                    logger.error("missing informations");
                    throw new RuntimeException("one or more items are not identify");
                }
            }
            orderRes.setItems(items);

            this.orderRepository.save(orderRes);

        } else {
            logger.error("missing informations");
            throw new RuntimeException("please make sure you submit all required informations");
        }

        return orderRes;
    }

    @Override
    public Order getOrder(String id) {

        Optional<Order> order = this.orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            logger.error("order not found");
            throw new RuntimeException("order not found");
        }

    }

    @Override
    public void cancelOrder(String id) {

        this.orderRepository.deleteById(id);
    }

    @Override
    public OrdersResponceModel createOrders(List<Order> orders) {

        OrdersResponceModel ordersResponceModel = new OrdersResponceModel();

        List<Billing> billings = new ArrayList<>();
        List<Shipping> shippings = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();

        for (Order order : orders) {

            shippings.add(order.getShipping());
            billings.add(order.getBilling());
            payments.addAll(order.getPayment());

        }
        try {

            this.billingRepository.saveAll(billings);
            this.shippingRepository.saveAll(shippings);
            this.paymentRepository.saveAll(payments);
            this.orderRepository.saveAll(orders);
            ordersResponceModel.setStatus("created");
        } catch (Exception e) {
            logger.error(e.getMessage());
            ordersResponceModel.setStatus("batch faild, data incorrect");

        }

        return ordersResponceModel;
    }

    @Override
    public OrdersResponceModel updateOrders(List<Order> orders) {

        OrdersResponceModel ordersResponceModel = new OrdersResponceModel();
        List<Billing> billings = new ArrayList<>();
        List<Shipping> shippings = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();

        for (Order order : orders) {

            shippings.add(order.getShipping());
            billings.add(order.getBilling());
            payments.addAll(order.getPayment());

        }
        try {
            this.billingRepository.saveAll(billings);
            this.shippingRepository.saveAll(shippings);
            this.paymentRepository.saveAll(payments);
            this.orderRepository.saveAll(orders);
            ordersResponceModel.setStatus("updated");
        } catch (Exception e) {
            logger.error(e.getMessage());
            ordersResponceModel.setStatus("batch faild, data incorrect");

        }

        return ordersResponceModel;
    }

}
