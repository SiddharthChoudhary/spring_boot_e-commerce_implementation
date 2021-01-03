package in.ecommerce.order.entitymodels;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import in.ecommerce.order.util.OrderStatus;

@Entity
@Table(name = "e_order")
@SQLDelete(sql = "UPDATE e_order SET status = 'CANCELED' WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "status <> 'CANCELED'")
public class Order extends AbstractBaseEntity {

    /**
    *
    */
    private static final long serialVersionUID = 4120031373413154865L;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.ACTIVE;

    @ManyToOne
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private List<Item> items;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Payment> payment;

    @ManyToOne
    private Billing billing;

    @ManyToOne
    private Shipping shipping;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double tax;

    @Column(nullable = false)
    private Double shippingCharges;

    @Column(nullable = false)
    private Double total;

    @PreRemove
    public void cancelOrder() {
        this.status = OrderStatus.CANCELED;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(Double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
