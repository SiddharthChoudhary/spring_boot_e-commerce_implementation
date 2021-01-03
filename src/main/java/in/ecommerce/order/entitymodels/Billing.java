package in.ecommerce.order.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Billing extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String billingAddressline1;

    @Column
    private String billingAddressline2;

    @Column
    private String billingCity;

    @Column
    private String billingState;

    @Column
    private String billingZip;

    public String getBillingAddressline1() {
        return billingAddressline1;
    }

    public void setBillingAddressline1(String billingAddressline1) {
        this.billingAddressline1 = billingAddressline1;
    }

    public String getBillingAddressline2() {
        return billingAddressline2;
    }

    public void setBillingAddressline2(String billingAddressline2) {
        this.billingAddressline2 = billingAddressline2;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingState() {
        return billingState;
    }

    public void setBillingState(String billingState) {
        this.billingState = billingState;
    }

    public String getBillingZip() {
        return billingZip;
    }

    public void setBillingZip(String billingZip) {
        this.billingZip = billingZip;
    }

}
