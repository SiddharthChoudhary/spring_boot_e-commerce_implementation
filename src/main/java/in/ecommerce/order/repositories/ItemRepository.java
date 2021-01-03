package in.ecommerce.order.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.ecommerce.order.entitymodels.Item;

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, String> {

}
