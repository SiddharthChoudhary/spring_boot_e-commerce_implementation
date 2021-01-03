package in.ecommerce.order.entitymodels;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column
    private String name;

    @Column
    private Integer quantity;

    @ManyToMany(mappedBy = "items")
    @JsonIgnore
    private List<Order> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
