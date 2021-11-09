package io.github.pavello.rabbitmq.sample.orderservice.adapters.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEvent;
import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Profile("default-exchange")
@Slf4j
class RabbitMessagePublisher implements DomainEventPublisher {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void publish(final DomainEvent event) {
		rabbitTemplate.convertAndSend(event);
		log.info("Message sent to queue : " + event);
	}
}
