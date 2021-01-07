package in.ecommerce.order.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
    public List<Order> findAll();
}
