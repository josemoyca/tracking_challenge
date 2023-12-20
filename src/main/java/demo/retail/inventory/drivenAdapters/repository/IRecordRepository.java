package demo.retail.inventory.drivenAdapters.repository;

import demo.retail.inventory.models.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IRecordRepository extends ReactiveMongoRepository<Record, String> {
    Flux<Record> findAllBy(Pageable pageable);

    @Query("{'eventType':'ERROR'}")
    Flux<Record> findAllErrors();
}
