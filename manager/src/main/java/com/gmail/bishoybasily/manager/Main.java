package com.gmail.bishoybasily.manager;

import com.github.bishoybasily.springframework.commons.core.data.params.Params;
import com.gmail.bishoybasily.manager.model.entity.Tenant;
import com.gmail.bishoybasily.manager.model.repository.TenantsRepository;
import com.gmail.bishoybasily.manager.model.service.TenantsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author bishoybasily
 * @since 2020-12-13
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> composedRoutes(TenantsService service) {

        RouterFunction<ServerResponse> all = RouterFunctions.route(
                RequestPredicates.GET("/api/tenants"),
                req -> ServerResponse.ok().body(service.all(new Params()), Tenant.class)
        );

        RouterFunction<ServerResponse> add = RouterFunctions.route(
                RequestPredicates.POST("/api/tenants"),
                req -> ServerResponse.ok().body(req.bodyToMono(Tenant.class).flatMap(service::add), Tenant.class)
        );

        RouterFunction<ServerResponse> one = RouterFunctions.route(
                RequestPredicates.GET("/api/tenants/{id}"),
                req -> ServerResponse.ok().body(service.one(req.pathVariable("id")), Tenant.class)
        );

        return all.and(one).and(add);
    }

}
