package io.github.pavello.rabbitmq.sample.orderservice.domain.command;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {

	private String name;
	private String description;
	private BigDecimal amount;
}
