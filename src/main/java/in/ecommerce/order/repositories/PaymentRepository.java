package in.ecommerce.order.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Payment;

@Repository
public interface PaymentRepository extends PagingAndSortingRepository<Payment, String> {

}
