package demo.retail.inventory.handlers.usecase;


import demo.retail.inventory.drivenAdapters.repository.IRecordRepository;
import demo.retail.inventory.models.Record;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class GetRecordsUByErrorsUseCase implements Supplier<Flux<Record>> {
    private IRecordRepository repository;

    public GetRecordsUByErrorsUseCase(IRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Record> get() {
        return repository.findAllErrors();
    }
}
