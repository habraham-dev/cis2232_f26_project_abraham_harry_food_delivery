package info.hccis.squash.repositories;

import info.hccis.squash.jpa.entity.FoodDeliveryOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for food delivery orders. Spring Data JPA automatically provides
 * the basic database methods (save, findAll, findById, deleteById, count) so
 * we do not have to write any SQL for the CRUD functionality.
 *
 * @author Harry Abraham
 * @since 20261123
 * Issue#6
 */
@Repository
public interface FoodDeliveryOrderRepository extends CrudRepository<FoodDeliveryOrder, Integer> {

}