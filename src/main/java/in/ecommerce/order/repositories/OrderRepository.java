package in.ecommerce.order.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Order;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {
}
