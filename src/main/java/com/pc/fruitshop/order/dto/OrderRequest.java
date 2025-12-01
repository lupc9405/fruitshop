package com.pc.fruitshop.order.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class OrderRequest {

	@NotBlank(message = "客戶名稱不可空白")
	private String customerName;

	@NotEmpty(message = "訂單至少需要一個商品")
	private List<@Valid OrderItemRequest> items;

	// getter / setter
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderItemRequest> getItems() {
		return items;
	}

	public void setItems(List<OrderItemRequest> items) {
		this.items = items;
	}
}
