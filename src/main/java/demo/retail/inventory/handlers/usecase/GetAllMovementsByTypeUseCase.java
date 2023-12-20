package demo.retail.inventory.handlers.usecase;


import demo.retail.inventory.drivenAdapters.repository.IMovementRepository;
import demo.retail.inventory.models.Movement;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class GetAllMovementsByTypeUseCase implements Function<String, Flux<Movement>> {
    private IMovementRepository repository;

    public GetAllMovementsByTypeUseCase(IMovementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<Movement> apply(String type) {
        return repository.findByType(type);
    }
}
