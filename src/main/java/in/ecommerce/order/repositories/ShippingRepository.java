package in.ecommerce.order.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Shipping;
import in.ecommerce.order.util.ShippingMethode;

@Repository
public interface ShippingRepository extends PagingAndSortingRepository<Shipping, String> {

    public Optional<Shipping> findByShippingAddressline1AndShippingAddressline2AndShippingCityAndShippingStateAndShippingZipAndShippingMethod(
            String sAdress1, String sAdress2, String sCity, String sState, String szip, ShippingMethode sMethod);
}
