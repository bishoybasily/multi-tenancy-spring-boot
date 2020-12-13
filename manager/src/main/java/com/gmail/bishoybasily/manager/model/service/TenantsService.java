package com.gmail.bishoybasily.manager.model.service;

import com.github.bishoybasily.springframework.commons.amqp.AmqpPublisher;
import com.github.bishoybasily.springframework.commons.core.data.params.Params;
import com.github.bishoybasily.springframework.commons.jpa.service.JpaService;
import com.gmail.bishoybasily.manager.model.repository.TenantsRepository;
import com.gmail.bishoybasily.manager.model.entity.Tenant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author bishoybasily
 * @since 2020-12-12
 */
@RequiredArgsConstructor
@Service
public class TenantsService implements JpaService<Tenant, String, Params>, AmqpPublisher {

    @Getter
    private final TenantsRepository jpaRepository;
    @Getter
    private final RabbitTemplate rabbitTemplate;

    public Mono<Tenant> add(Tenant tenant) {
        return Mono.fromCallable(() -> tenant)
                .flatMap(this::persist)
                .flatMap(it -> publish("_tenants", String.format("%s.%s.%s", "tenants", it.getId(), "create"), it));
    }

}
