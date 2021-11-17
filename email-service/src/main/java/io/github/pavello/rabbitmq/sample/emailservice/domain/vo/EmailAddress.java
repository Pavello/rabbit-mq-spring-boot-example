package io.github.pavello.rabbitmq.sample.emailservice.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor(staticName = "of")
@ToString
public class EmailAddress {
	private final String address;
}
