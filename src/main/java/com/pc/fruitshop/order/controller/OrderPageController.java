package com.pc.fruitshop.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderPageController {

    @GetMapping("/order-result")
    public String orderResultPage() {
        return "order-result"; // 對應 templates/order-result.html
    }
}

