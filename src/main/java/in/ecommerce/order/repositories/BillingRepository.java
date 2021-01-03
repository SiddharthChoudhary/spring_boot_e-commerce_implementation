package in.ecommerce.order.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Billing;

@Repository
public interface BillingRepository extends PagingAndSortingRepository<Billing, String> {

    public Optional<Billing> findByBillingAddressline1AndBillingAddressline2AndBillingCityAndBillingStateAndBillingZip(
            String bAdress1, String bAdress2, String bCity, String bState, String bzip);
}
