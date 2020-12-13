package com.gmail.bishoybasily.application;

import com.gmail.bishoybasily.application.config.TenantBasedDataSource;
import com.gmail.bishoybasily.application.model.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.gmail.bishoybasily.application.model.repository.UsersRepository;

/**
 * @author bishoybasily
 * @since 2020-12-13
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(UsersRepository repository) {

        HandlerFilterFunction<ServerResponse, ServerResponse> tenantIdFilter = (serverRequest, handlerFunction) -> {
            TenantBasedDataSource.getDataSource(serverRequest.pathVariable("tenantId"));
            return handlerFunction.handle(serverRequest);
        };

        RouterFunction<ServerResponse> all = RouterFunctions.route(
                RequestPredicates.GET("/api/{tenantId}/users"),
                req -> ServerResponse.ok().body(Flux.fromIterable(repository.findAll()), User.class)
        ).filter(tenantIdFilter);

        RouterFunction<ServerResponse> add = RouterFunctions.route(
                RequestPredicates.POST("/api/{tenantId}/users"),
                req -> ServerResponse.ok().body(req.bodyToMono(User.class).map(repository::save), User.class)
        ).filter(tenantIdFilter);

        RouterFunction<ServerResponse> one = RouterFunctions.route(
                RequestPredicates.GET("/api/{tenantId}/users/{id}"),
                req -> ServerResponse.ok().body(Mono.fromCallable(() -> repository.getOne(req.pathVariable("id"))), User.class)
        ).filter(tenantIdFilter);

        return all.and(one).and(add);
    }

}
