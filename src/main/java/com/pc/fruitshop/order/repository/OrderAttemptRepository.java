package com.pc.fruitshop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pc.fruitshop.order.model.OrderAttempt;

public interface OrderAttemptRepository extends JpaRepository<OrderAttempt, Long> {}
