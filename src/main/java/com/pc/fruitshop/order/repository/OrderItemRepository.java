package com.pc.fruitshop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pc.fruitshop.order.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
