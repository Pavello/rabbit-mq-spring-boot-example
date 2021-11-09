package io.github.pavello.rabbitmq.sample.orderservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default-exchange")
public class DefaultExchangeConfig {

	private static final String ORDERS_QUEUE_NAME = "orders-queue";

	@Bean
	public Queue ordersQueue() {
		return new Queue(ORDERS_QUEUE_NAME, false);
	}

	@Bean
	ApplicationRunner runner(ConnectionFactory cf) {
		return args -> cf.createConnection().close();
	}
}
