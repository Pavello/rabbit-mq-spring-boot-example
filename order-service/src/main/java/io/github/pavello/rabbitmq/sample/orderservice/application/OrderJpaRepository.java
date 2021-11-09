package io.github.pavello.rabbitmq.sample.orderservice.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pavello.rabbitmq.sample.orderservice.domain.Order;
import io.github.pavello.rabbitmq.sample.orderservice.domain.OrderDomainRepository;

@Repository
interface OrderJpaRepository extends OrderDomainRepository, JpaRepository<Order, Integer> {
}
