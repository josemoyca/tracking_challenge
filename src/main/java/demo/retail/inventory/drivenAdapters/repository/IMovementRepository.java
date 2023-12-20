package demo.retail.inventory.drivenAdapters.repository;

import demo.retail.inventory.models.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovementRepository extends ReactiveMongoRepository<Movement, String> {
}
