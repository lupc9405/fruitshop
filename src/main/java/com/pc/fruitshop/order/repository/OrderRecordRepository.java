package com.pc.fruitshop.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pc.fruitshop.order.model.OrderRecord;

public interface OrderRecordRepository extends JpaRepository<OrderRecord, Long> {}
