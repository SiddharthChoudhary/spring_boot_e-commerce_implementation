package in.ecommerce.order.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Customer;

@Repository
public interface CostomerRepository extends PagingAndSortingRepository<Customer, String> {

}
