package com.pc.fruitshop.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    // 金額是否正確
    @Test
    void testCalculateTotal() {
        int total = orderService.calculateTotal(3, 2, 1);
        assertEquals(74, total);
        // 3 * 10 + 2 * 12 + 1 * 20 = 30 + 24 + 20 = 74
    }

    // null -> 0
    @Test
    void testNormalizeQty_Null() {
        int qty = orderService.normalizeQty(null);
        assertEquals(0, qty);
    }

    // 負數 -> 0
    @Test
    void testNormalizeQty_Negative() {
        int qty = orderService.normalizeQty(-5);
        assertEquals(0, qty);
    }

    // 大於 10 -> 10
    @Test
    void testNormalizeQty_TooLarge() {
        int qty = orderService.normalizeQty(99);
        assertEquals(10, qty);
    }

    // 正常數字 -> 原數字
    @Test
    void testNormalizeQty_Normal() {
        int qty = orderService.normalizeQty(4);
        assertEquals(4, qty);
    }
}
