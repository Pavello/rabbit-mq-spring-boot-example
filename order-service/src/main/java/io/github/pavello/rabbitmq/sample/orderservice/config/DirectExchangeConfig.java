package io.github.pavello.rabbitmq.sample.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("direct-exchange")
public class DirectExchangeConfig {

	public static final String ORDERS_QUEUE_1_NAME = "direct-orders-queue-1";
	public static final String ORDERS_QUEUE_2_NAME = "direct-orders-queue-2";

	public static final String ORDERS_DIRECT_EXCHANGE = "direct-orders-exchange";

	public static final String ORDERS_DIRECT_ROUTING_KEY_1 = "direct-orders-routing-key-1";
	public static final String ORDERS_DIRECT_ROUTING_KEY_2 = "direct-orders-routing-key-2";

	@Bean
	public Queue firstOrdersQueue() {
		return new Queue(ORDERS_QUEUE_1_NAME, false);
	}

	@Bean
	public Queue secondOrdersQueue() {
		return new Queue(ORDERS_QUEUE_2_NAME, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(ORDERS_DIRECT_EXCHANGE);
	}

	@Bean
	Binding firstOrdersBinding(Queue firstOrdersQueue, DirectExchange exchange) {
		return BindingBuilder.bind(firstOrdersQueue).to(exchange).with(ORDERS_DIRECT_ROUTING_KEY_1);
	}

	@Bean
	Binding secondOrdersBinding(Queue secondOrdersQueue, DirectExchange exchange) {
		return BindingBuilder.bind(secondOrdersQueue).to(exchange).with(ORDERS_DIRECT_ROUTING_KEY_2);
	}

	@Bean
	ApplicationRunner runner(ConnectionFactory cf) {
		return args -> cf.createConnection().close();
	}
}
