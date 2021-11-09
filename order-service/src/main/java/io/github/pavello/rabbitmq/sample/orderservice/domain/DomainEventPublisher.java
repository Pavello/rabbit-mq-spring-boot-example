package io.github.pavello.rabbitmq.sample.orderservice.domain;

public interface DomainEventPublisher {
	void publish(DomainEvent event);
}
