package com.pc.fruitshop.order.dto;

import java.util.List;

public class OrderResponse {

    private boolean success;
    private String message;
    private int totalAmount;
    private List<OrderItemResponse> items;

    public OrderResponse(boolean success, String message, int totalAmount, List<OrderItemResponse> items) {
        this.success = success;
        this.message = message;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public List<OrderItemResponse> getItems() {
        return items;
    }
}
