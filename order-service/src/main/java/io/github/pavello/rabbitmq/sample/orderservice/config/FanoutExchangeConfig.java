package io.github.pavello.rabbitmq.sample.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("fanout-exchange")
public class FanoutExchangeConfig {

	public static final String ORDERS_FANOUT_QUEUE_1_NAME = "fanout-orders-queue-1";
	public static final String ORDERS_FANOUT_QUEUE_2_NAME = "fanout-orders-queue-2";

	public static final String FANOUT_ORDERS_EXCHANGE = "fanout-orders-exchange";

	@Bean
	public Queue firstFanoutQueue() {
		return new Queue(ORDERS_FANOUT_QUEUE_1_NAME, false);
	}

	@Bean
	public Queue secondFanoutQueue() {
		return new Queue(ORDERS_FANOUT_QUEUE_2_NAME, false);
	}

	@Bean
	FanoutExchange exchange() {
		return new FanoutExchange(FANOUT_ORDERS_EXCHANGE);
	}

	@Bean
	Binding firstOrdersBinding(Queue firstFanoutQueue, FanoutExchange exchange) {
		return BindingBuilder.bind(firstFanoutQueue).to(exchange);
	}

	@Bean
	Binding secondOrdersBinding(Queue secondFanoutQueue, FanoutExchange exchange) {
		return BindingBuilder.bind(secondFanoutQueue).to(exchange);
	}

	@Bean
	ApplicationRunner runner(ConnectionFactory cf) {
		return args -> cf.createConnection().close();
	}
}
