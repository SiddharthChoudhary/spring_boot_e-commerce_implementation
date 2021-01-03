package in.ecommerce.order.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import in.ecommerce.order.util.ShippingMethode;

@Entity
public class Shipping extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column
    private String shippingAddressline1;

    @Column
    private String shippingAddressline2;

    @Column
    private String shippingCity;

    @Column
    private String shippingState;

    @Column
    private String shippingZip;

    @Column
    @Enumerated(EnumType.STRING)
    private ShippingMethode shippingMethod;

    public String getShippingAddressline1() {
        return shippingAddressline1;
    }

    public void setShippingAddressline1(String shippingAddressline1) {
        this.shippingAddressline1 = shippingAddressline1;
    }

    public String getShippingAddressline2() {
        return shippingAddressline2;
    }

    public void setShippingAddressline2(String shippingAddressline2) {
        this.shippingAddressline2 = shippingAddressline2;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingState() {
        return shippingState;
    }

    public void setShippingState(String shippingState) {
        this.shippingState = shippingState;
    }

    public String getShippingZip() {
        return shippingZip;
    }

    public void setShippingZip(String shippingZip) {
        this.shippingZip = shippingZip;
    }

    public ShippingMethode getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethode shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

}
