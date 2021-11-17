package io.github.pavello.rabbitmq.sample.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("header-exchange")
public class HeaderExchangeConfig {

	public static final String ORDERS_HEADER_QUEUE_1_NAME = "header-orders-queue-1";
	public static final String ORDERS_HEADER_QUEUE_2_NAME = "header-orders-queue-2";

	public static final String HEADER_ORDERS_EXCHANGE = "header-orders-exchange";

	@Bean
	public Queue firstHeaderQueue() {
		return new Queue(ORDERS_HEADER_QUEUE_1_NAME, false);
	}

	@Bean
	public Queue secondHeaderQueue() {
		return new Queue(ORDERS_HEADER_QUEUE_2_NAME, false);
	}

	@Bean
	public Queue allQueueHeader() {
		return new Queue("All_topic_queue", false);
	}

	@Bean
	HeadersExchange exchange() {
		return new HeadersExchange(HEADER_ORDERS_EXCHANGE);
	}

	@Bean
	Binding firstOrdersBinding(Queue firstHeaderQueue, HeadersExchange exchange) {
		return BindingBuilder.bind(firstHeaderQueue).to(exchange).where("header").matches("first");
	}

	@Bean
	Binding secondOrdersBinding(Queue secondHeaderQueue, HeadersExchange exchange) {
		return BindingBuilder.bind(secondHeaderQueue).to(exchange).where("header").matches("second");
	}

	@Bean
	Binding allQueueBinding(Queue allQueueHeader, HeadersExchange exchange) {
		return BindingBuilder.bind(allQueueHeader).to(exchange).where("header").matches("all");
	}

	@Bean
	ApplicationRunner runner(ConnectionFactory cf) {
		return args -> cf.createConnection().close();
	}
}
