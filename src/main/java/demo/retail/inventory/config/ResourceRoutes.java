package demo.retail.inventory.config;

import demo.retail.inventory.handlers.usecase.GetPageableRecordsUseCase;
import demo.retail.inventory.models.Record;
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


}
