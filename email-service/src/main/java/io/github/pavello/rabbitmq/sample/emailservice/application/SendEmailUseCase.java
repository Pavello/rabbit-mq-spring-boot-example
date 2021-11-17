package io.github.pavello.rabbitmq.sample.emailservice.application;

import org.springframework.stereotype.Service;

import io.github.pavello.rabbitmq.sample.emailservice.domain.EmailSender;
import io.github.pavello.rabbitmq.sample.emailservice.domain.event.OrderEvent;
import io.github.pavello.rabbitmq.sample.emailservice.domain.vo.EmailAddress;
import io.github.pavello.rabbitmq.sample.emailservice.domain.vo.EmailMessage;
import io.github.pavello.rabbitmq.sample.emailservice.domain.vo.EmailMessageBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailUseCase implements EmailSender {

	private static final String FAKE_EMAIL_ADDRESS = "fake@gmail.com";

	public void prepareAndSend(OrderEvent orderEvent) {
		EmailMessage emailMessage = EmailMessage.of(EmailAddress.of(FAKE_EMAIL_ADDRESS), EmailMessageBody.of(orderEvent.toString()));
		sendEmail(emailMessage);
	}

	@Override
	public void sendEmail(final EmailMessage message) {
		log.info("Email message sent: " + message);
	}
}
