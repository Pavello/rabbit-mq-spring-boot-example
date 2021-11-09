package io.github.pavello.rabbitmq.sample.orderservice.application;

import org.springframework.stereotype.Service;

import io.github.pavello.rabbitmq.sample.orderservice.application.dto.OrderDto;
import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEventPublisher;
import io.github.pavello.rabbitmq.sample.orderservice.domain.Order;
import io.github.pavello.rabbitmq.sample.orderservice.domain.OrderDomainRepository;
import io.github.pavello.rabbitmq.sample.orderservice.domain.OrderFacade;
import io.github.pavello.rabbitmq.sample.orderservice.domain.command.CreateOrderCommand;
import io.github.pavello.rabbitmq.sample.orderservice.domain.vo.OrderEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase implements OrderFacade {

	private final OrderDomainRepository orderJpaRepository;
	private final DomainEventPublisher publisher;

	@Override
	public OrderDto createOrder(final CreateOrderCommand createOrderCommand) {

		Order order = orderJpaRepository.save(Order.from(createOrderCommand));
		publisher.publish(new OrderEvent(
				order.getId(),
				OrderEvent.State.CREATED,
				new OrderEvent.Data(
						order.getDescription(),
						order.getName(),
						order.getAmount()
				)
			)
		);

		return new OrderDto();
	}
}
