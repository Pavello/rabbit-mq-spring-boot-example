package io.github.pavello.rabbitmq.sample.emailservice.domain;

import io.github.pavello.rabbitmq.sample.emailservice.domain.vo.EmailMessage;

public interface EmailSender {

	void sendEmail(EmailMessage message);
}
