package com.pc.fruitshop.order.dto;

import jakarta.validation.constraints.*;

public class OrderItemRequest {

    @NotNull(message = "商品 ID 不可為空")
    private Long productId;

    @NotNull(message = "商品數量不可為空")
    @Min(value = 0, message = "商品數量不可小於 0")
    @Max(value = 10, message = "商品數量不可大於 10")
    private Integer qty;

	// getter / setter
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
