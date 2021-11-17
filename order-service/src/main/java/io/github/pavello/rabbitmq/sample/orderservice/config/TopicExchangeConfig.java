package io.github.pavello.rabbitmq.sample.orderservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("topic-exchange")
public class TopicExchangeConfig {

	public static final String ORDERS_TOPIC_QUEUE_1_NAME = "topic-orders-queue-1";
	public static final String ORDERS_TOPIC_QUEUE_2_NAME = "topic-orders-queue-2";

	public static final String TOPIC_ORDERS_EXCHANGE = "topic-orders-exchange";

	@Bean
	public Queue firstTopicQueue() {
		return new Queue(ORDERS_TOPIC_QUEUE_1_NAME, false);
	}

	@Bean
	public Queue secondTopicQueue() {
		return new Queue(ORDERS_TOPIC_QUEUE_2_NAME, false);
	}

	@Bean
	public Queue allQueueTopic() {
		return new Queue("All_topic_queue", false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(TOPIC_ORDERS_EXCHANGE);
	}

	@Bean
	Binding firstOrdersBinding(Queue firstTopicQueue, TopicExchange exchange) {
		return BindingBuilder.bind(firstTopicQueue).to(exchange).with("first.*");
	}

	@Bean
	Binding secondOrdersBinding(Queue secondTopicQueue, TopicExchange exchange) {
		return BindingBuilder.bind(secondTopicQueue).to(exchange).with("second.#");
	}

	@Bean
	Binding allQueueBinding(Queue allQueueTopic, TopicExchange exchange) {
		return BindingBuilder.bind(allQueueTopic).to(exchange).with("*.all.#");
	}

	@Bean
	ApplicationRunner runner(ConnectionFactory cf) {
		return args -> cf.createConnection().close();
	}
}
