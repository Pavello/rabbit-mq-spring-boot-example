package io.github.pavello.rabbitmq.sample.emailservice.adapters.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.pavello.rabbitmq.sample.emailservice.application.SendEmailUseCase;
import io.github.pavello.rabbitmq.sample.emailservice.domain.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQConsumer {

	private final SendEmailUseCase sendEmailUseCase;
	private final ObjectMapper objectMapper;

	@RabbitListener(queues = "${application.rabbitmq.queue}")
	public void onMessage(OrderEvent event) {
		if (true) { throw new RuntimeException();}
		log.info("Consuming Message - " + event);
		sendEmailUseCase.prepareAndSend(event);
	}

}