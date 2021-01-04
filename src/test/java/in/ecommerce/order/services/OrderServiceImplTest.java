package in.ecommerce.order.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.ecommerce.order.ServicesImpl.OrdersServiceImpl;
import in.ecommerce.order.entitymodels.Billing;
import in.ecommerce.order.entitymodels.Customer;
import in.ecommerce.order.entitymodels.Item;
import in.ecommerce.order.entitymodels.Order;
import in.ecommerce.order.entitymodels.Payment;
import in.ecommerce.order.entitymodels.Shipping;
import in.ecommerce.order.repositories.BillingRepository;
import in.ecommerce.order.repositories.CustomerRepository;
import in.ecommerce.order.repositories.ItemRepository;
import in.ecommerce.order.repositories.OrderRepository;
import in.ecommerce.order.repositories.PaymentRepository;
import in.ecommerce.order.repositories.ShippingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BillingRepository billingRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private ShippingRepository shippingRepository;

    @Mock
    private CustomerRepository CustomerRepository;

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private OrdersServiceImpl orderService;

    private Customer customer;
    private Item item;
    private List<Item> items;
    private Item item2;
    private Billing billing;
    private Shipping shipping;
    private List<Payment> payments;
    private List<Payment> payments2;

    private Order order;
    private Payment payment;
    private Payment payment2;
    private Order order2payment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        customer = new Customer();
        customer.setName("nani");

        items = new ArrayList<>();
        item = new Item();
        item.setName("product1");
        item2 = new Item();
        item2.setName("product2");
        items.add(item);
        items.add(item2);

        billing = new Billing();

        billing.setBillingAddressline1("street 1");
        billing.setBillingAddressline2("street 2");
        billing.setBillingCity("NW");
        billing.setBillingState("florida");
        billing.setBillingZip("120000");

        shipping = new Shipping();

        shipping.setShippingAddressline1("street 1");
        shipping.setShippingAddressline2("street 2");
        shipping.setShippingCity("NW");
        shipping.setShippingState("florida");
        shipping.setShippingZip("120000");

        payments = new ArrayList<>();
        payments2 = new ArrayList<>();

        payment = new Payment();

        payment.setPaymentConfirmationNumber(12345);
        payment.setPaymentDate("12/12/2000");
        payments.add(payment);
        payments2.add(payment);
        payment2 = new Payment();

        payment2.setPaymentConfirmationNumber(12345);
        payment2.setPaymentDate("12/12/2000");
        payments2.add(payment2);

        order = new Order();

        order.setBilling(billing);
        order.setShipping(shipping);
        order.setItems(items);
        order.setPayment(payments);
        order.setCustomer(customer);
        order.setShippingCharges(12.0);
        order.setTax(2.00);
        order.setTotal(24.00);
        order.setSubtotal(10.00);
        order2payment = new Order();
        BeanUtils.copyProperties(order, order2payment);

        order2payment.setPayment(payments2);
    }

    @Test
    public void createOrder() {

        when(shippingRepository.save(shipping)).thenReturn(shipping);
        when(billingRepository.save(billing)).thenReturn(billing);
        when(paymentRepository.save(payment)).thenReturn(payment);
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        when(itemRepository.findById(item2.getId())).thenReturn(Optional.of(item2));
        when(CustomerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Order orderRes = orderService.createOrder(order);

        assertTrue(orderRes.getTotal() == order.getTotal());

        assertTrue(orderRes.getId().equals(order.getId()));
        assertTrue(billing.getBillingAddressline1().equals(orderRes.getBilling().getBillingAddressline1()));
        assertTrue(billing.getBillingAddressline2().equals(orderRes.getBilling().getBillingAddressline2()));
        assertTrue(billing.getBillingCity().equals(orderRes.getBilling().getBillingCity()));
        assertTrue(billing.getBillingState().equals(orderRes.getBilling().getBillingState()));
        assertTrue(billing.getBillingZip().equals(orderRes.getBilling().getBillingZip()));

        assertTrue(shipping.getShippingAddressline1().equals(orderRes.getShipping().getShippingAddressline1()));
        assertTrue(shipping.getShippingAddressline1().equals(orderRes.getShipping().getShippingAddressline1()));
        assertTrue(shipping.getShippingCity().equals(orderRes.getShipping().getShippingCity()));
        assertTrue(shipping.getShippingState().equals(orderRes.getShipping().getShippingState()));
        assertTrue(shipping.getShippingZip().equals(orderRes.getShipping().getShippingZip()));

        assertTrue(customer.getName().equals(orderRes.getCustomer().getName()));

        assertTrue(orderRes.getItems().get(0).getName().equals(items.get(0).getName()));
        assertTrue(orderRes.getItems().get(0).getQuantity() == items.get(0).getQuantity());

        assertTrue(orderRes.getItems().get(1).getName().equals(items.get(1).getName()));
        assertTrue(orderRes.getItems().get(1).getQuantity() == items.get(1).getQuantity());

        assertTrue(orderRes.getTotal() == order.getTotal());
        assertTrue(orderRes.getSubtotal() == order.getSubtotal());
        assertTrue(orderRes.getShippingCharges() == order.getShippingCharges());

        assertTrue(orderRes.getPayment().size() == 1);

    }

    @Test
    public void createOrderTwoCardsPayment() {

        when(shippingRepository.save(shipping)).thenReturn(shipping);
        when(billingRepository.save(billing)).thenReturn(billing);
        when(paymentRepository.save(payment)).thenReturn(payment);
        when(paymentRepository.save(payment2)).thenReturn(payment2);
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));
        when(itemRepository.findById(item2.getId())).thenReturn(Optional.of(item2));
        when(CustomerRepository.findById(customer.getId())).thenReturn(Optional.of(customer));

        Order orderRes = orderService.createOrder(order2payment);

        assertTrue(customer.getName().equals(orderRes.getCustomer().getName()));

        assertTrue(orderRes.getItems().get(0).getName().equals(items.get(0).getName()));
        assertTrue(orderRes.getItems().get(0).getQuantity() == items.get(0).getQuantity());

        assertTrue(orderRes.getItems().get(1).getName().equals(items.get(1).getName()));
        assertTrue(orderRes.getItems().get(1).getQuantity() == items.get(1).getQuantity());

        assertTrue(orderRes.getTotal() == order2payment.getTotal());
        assertTrue(orderRes.getSubtotal() == order2payment.getSubtotal());
        assertTrue(orderRes.getShippingCharges() == order2payment.getShippingCharges());

        assertTrue(orderRes.getPayment().size() == 2);
        assertTrue(orderRes.getTotal() == orderRes.getPayment().get(1).getTotal()
                + orderRes.getPayment().get(0).getTotal());

    }

    @Test
    public void getOrder() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        Order orderRes = orderService.getOrder(order.getId());
        assertTrue(orderRes.getId().equals(order.getId()));

    }

    @Test
    public void getOrderWithWrongId() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.ofNullable(order));

        try {
            orderService.getOrder(order.getId());

        } catch (RuntimeException e) {
            assertTrue(e.getMessage().equals("order not found"));

        }

    }
}
