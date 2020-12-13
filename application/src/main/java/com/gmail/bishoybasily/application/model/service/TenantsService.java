package com.gmail.bishoybasily.application.model.service;

import com.gmail.bishoybasily.application.model.entity.Tenant;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author bishoybasily
 * @since 2020-12-13
 */
@Service
public class TenantsService {

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(
                                    value = "application-tenants-create",
                                    autoDelete = "false",
                                    durable = "true",
                                    exclusive = "false"
                            ),
                            exchange = @Exchange(
                                    value = "_tenants",
                                    type = "topic"
                            ),
                            key = "tenants.*.create"
                    )
            }
    )
    public void createEvent(Tenant tenant) {
        tenant.provision();
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(
                                    value = "application-tenants-update",
                                    autoDelete = "false",
                                    durable = "true",
                                    exclusive = "false"
                            ),
                            exchange = @Exchange(
                                    value = "_tenants",
                                    type = "topic"
                            ),
                            key = "tenants.*.update"
                    )
            }
    )
    public void updateEvent(Tenant tenant) {
    }

    @RabbitListener(
            bindings = {
                    @QueueBinding(
                            value = @Queue(
                                    value = "application-tenants-delete",
                                    autoDelete = "false",
                                    durable = "true",
                                    exclusive = "false"
                            ),
                            exchange = @Exchange(
                                    value = "_tenants",
                                    type = "topic"
                            ),
                            key = "tenants.*.delete"
                    )
            }
    )
    public void deleteEvent(Tenant tenant) {
    }

}
