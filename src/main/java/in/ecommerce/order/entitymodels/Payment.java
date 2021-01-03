package in.ecommerce.order.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Payment extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column
    private String paymentDate;

    @Column
    private Integer paymentConfirmationNumber;

    @Column
    private Double total;

    @ManyToOne
    @JsonIgnore
    private Order order;

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getPaymentConfirmationNumber() {
        return paymentConfirmationNumber;
    }

    public void setPaymentConfirmationNumber(Integer paymentConfirmationNumber) {
        this.paymentConfirmationNumber = paymentConfirmationNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
