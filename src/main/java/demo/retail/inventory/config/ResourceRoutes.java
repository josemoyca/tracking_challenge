package demo.retail.inventory.config;

import demo.retail.inventory.handlers.usecase.GetAllMovementsByInventoryUseCase;
import demo.retail.inventory.handlers.usecase.GetAllMovementsByTypeUseCase;
import demo.retail.inventory.handlers.usecase.GetPageableRecordsUseCase;
import demo.retail.inventory.handlers.usecase.GetRecordsUByErrorsUseCase;
import demo.retail.inventory.models.Movement;
import demo.retail.inventory.models.Record;
import demo.retail.inventory.models.RecordTypes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ResourceRoutes {
    public static final int PAGE_SIZE = 100;

    @Bean
    public RouterFunction<ServerResponse> getAllRecordsRouter(GetPageableRecordsUseCase getPageableRecordsUseCase) {
        return route(
                GET("/api/v1/records"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getPageableRecordsUseCase.apply(
                                PageRequest.of(
                                        request.queryParam("page").isEmpty() ? 0 : Integer.parseInt(request.queryParam("page").get()), PAGE_SIZE)
                        ), Record.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getAllRecordsByError(GetRecordsUByErrorsUseCase getRecordsUByErrorsUseCase) {
        return route(
                GET("/api/v1/records/error"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getRecordsUByErrorsUseCase.get(), Record.class)));
    }

    @Bean
    public RouterFunction<ServerResponse> getMovementsByInventoryId(GetAllMovementsByInventoryUseCase getAllMovementsByInventoryUseCase) {
        return route(
                GET("/api/v1/movements/{inventoryId}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(getAllMovementsByInventoryUseCase.apply(request.pathVariable("inventoryId")), Movement.class)
        );
    }
    @Bean
    public RouterFunction<ServerResponse> getMovementsByRetailSale(GetAllMovementsByTypeUseCase getAllMovementsByTypeUseCase) {
        return route(
                GET("/api/v1/movements/retail"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(getAllMovementsByTypeUseCase.apply(RecordTypes.RETAIL_SALE.toString()), Movement.class)
        );
    }
    @Bean
    public RouterFunction<ServerResponse> getMovementsByWholeSale(GetAllMovementsByTypeUseCase getAllMovementsByTypeUseCase) {
        return route(
                GET("/api/v1/movements/wholesale"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(getAllMovementsByTypeUseCase.apply(RecordTypes.WHOLESALE.toString()), Movement.class)
        );
    }
}
