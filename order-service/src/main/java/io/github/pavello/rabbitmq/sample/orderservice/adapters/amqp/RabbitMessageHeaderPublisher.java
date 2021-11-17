package io.github.pavello.rabbitmq.sample.orderservice.adapters.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEvent;
import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static io.github.pavello.rabbitmq.sample.orderservice.config.HeaderExchangeConfig.HEADER_ORDERS_EXCHANGE;

@Component
@RequiredArgsConstructor
@Profile("header-exchange")
@Slf4j
class RabbitMessageHeaderPublisher implements DomainEventPublisher {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void publish(final DomainEvent event) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("header", "first");
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(event, messageProperties);

		rabbitTemplate.send(HEADER_ORDERS_EXCHANGE,"", message);
		log.info("Message sent to queue : " + event);
	}
}
