package demo.retail.inventory.drivenAdapters.repository;

import demo.retail.inventory.models.Record;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecordRepository extends ReactiveMongoRepository<Record, String> {
}
