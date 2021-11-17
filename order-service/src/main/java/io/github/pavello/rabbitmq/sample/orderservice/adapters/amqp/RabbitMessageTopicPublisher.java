package io.github.pavello.rabbitmq.sample.orderservice.adapters.amqp;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEvent;
import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.github.pavello.rabbitmq.sample.orderservice.config.TopicExchangeConfig.TOPIC_ORDERS_EXCHANGE;

@Component
@RequiredArgsConstructor
@Profile("topic-exchange")
@Slf4j
class RabbitMessageTopicPublisher implements DomainEventPublisher {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void publish(final DomainEvent event) {
		rabbitTemplate.convertAndSend(TOPIC_ORDERS_EXCHANGE,"second.message.send", event);
		log.info("Message sent to queue : " + event);
	}
}
