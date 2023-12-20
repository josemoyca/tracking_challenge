package demo.retail.inventory.handlers.usecase;


import demo.retail.inventory.drivenAdapters.repository.IRecordRepository;
import demo.retail.inventory.models.Record;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetPageableRecordsUseCase implements Function<Pageable, Flux<Record>> {
    private IRecordRepository repository;

    public GetPageableRecordsUseCase(IRecordRepository repository) {
        this.repository = repository;
    }

    public Flux<Record> apply(Pageable pageable) {
        return repository.findAllBy(pageable);
    }
}
