package io.github.pavello.rabbitmq.sample.orderservice.domain;

import io.github.pavello.rabbitmq.sample.orderservice.application.dto.OrderDto;
import io.github.pavello.rabbitmq.sample.orderservice.domain.command.CreateOrderCommand;

public interface OrderFacade {

	OrderDto createOrder(CreateOrderCommand createOrderCommand);
}
