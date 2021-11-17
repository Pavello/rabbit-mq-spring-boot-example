package io.github.pavello.rabbitmq.sample.emailservice.domain.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent {
	private int sourceId;
	private Instant occurredOn;
	private String state;
	private Data data;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	public static class Data {
		private String description;
		private String name;
		private BigDecimal amount;
	}
}
