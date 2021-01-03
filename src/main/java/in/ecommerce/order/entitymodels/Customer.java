package in.ecommerce.order.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Customer extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
