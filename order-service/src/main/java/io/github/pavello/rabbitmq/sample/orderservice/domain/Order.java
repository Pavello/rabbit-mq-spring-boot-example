package io.github.pavello.rabbitmq.sample.orderservice.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.github.pavello.rabbitmq.sample.orderservice.domain.command.CreateOrderCommand;
import lombok.Getter;

@Getter
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private BigDecimal amount;

	private Order() {
	}

	public static Order from(CreateOrderCommand createOrderCommand) {
		Order order = new Order();
		order.name = createOrderCommand.getName();
		order.description = createOrderCommand.getDescription();
		order.amount = createOrderCommand.getAmount();

		return order;
	}
}
