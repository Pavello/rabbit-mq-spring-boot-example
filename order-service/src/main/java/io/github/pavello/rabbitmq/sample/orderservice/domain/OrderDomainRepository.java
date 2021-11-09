package io.github.pavello.rabbitmq.sample.orderservice.domain;

import java.util.Optional;

import io.github.pavello.rabbitmq.sample.orderservice.domain.command.CreateOrderCommand;

public interface OrderDomainRepository {

	Order save(Order order);

	Optional<Order> getById(int id);
}
