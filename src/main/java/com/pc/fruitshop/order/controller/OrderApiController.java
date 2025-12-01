package com.pc.fruitshop.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pc.fruitshop.order.dto.OrderRequest;
import com.pc.fruitshop.order.dto.OrderResponse;
import com.pc.fruitshop.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    private final OrderService orderService;

    // 建構子注入
    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest request) {

        OrderResponse response = orderService.processOrder(request);
        return ResponseEntity.ok(response);
    }

}
