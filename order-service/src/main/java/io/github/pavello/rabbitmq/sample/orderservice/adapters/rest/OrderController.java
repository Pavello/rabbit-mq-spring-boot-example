package io.github.pavello.rabbitmq.sample.orderservice.adapters.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.pavello.rabbitmq.sample.orderservice.application.CreateOrderUseCase;
import io.github.pavello.rabbitmq.sample.orderservice.application.dto.OrderDto;
import io.github.pavello.rabbitmq.sample.orderservice.domain.command.CreateOrderCommand;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class OrderController {

	private final CreateOrderUseCase createOrderUseCase;

	@PostMapping("/orders/default")
	ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
		return ResponseEntity.ok(createOrderUseCase.createOrder(createOrderCommand));
	}
}
