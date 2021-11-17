package io.github.pavello.rabbitmq.sample.orderservice.adapters.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEvent;
import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.github.pavello.rabbitmq.sample.orderservice.config.FanoutExchangeConfig.FANOUT_ORDERS_EXCHANGE;

@Component
@RequiredArgsConstructor
@Profile("fanout-exchange")
@Slf4j
class RabbitMessageFanoutPublisher implements DomainEventPublisher {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void publish(final DomainEvent event) {
		rabbitTemplate.convertAndSend(FANOUT_ORDERS_EXCHANGE,"", event);
		log.info("Message sent to queue : " + event);
	}
}
