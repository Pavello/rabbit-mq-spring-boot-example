package io.github.pavello.rabbitmq.sample.orderservice.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import io.github.pavello.rabbitmq.sample.orderservice.domain.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class OrderEvent implements DomainEvent, Serializable {

	public enum State {
		CREATED, UPDATED, DELETED, PAID
	}

	private final int sourceId;
	private final Instant occurredOn;
	private final State state;
	private final Data data;

	public OrderEvent(final int sourceId, final State state, final Data data) {
		this.sourceId = sourceId;
		this.occurredOn = Instant.now();
		this.state = state;
		this.data = data;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	@ToString
	public static class Data implements Serializable {
		private final String description;
		private final String name;
		private final BigDecimal amount;
	}
}
