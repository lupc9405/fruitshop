package com.pc.fruitshop.order.dto;

public class OrderItemResponse {

	private Long productId;
	private String productName;
	private int qty;
	private int price;
	private int amount;
	private String unitName;

	public OrderItemResponse(Long productId, String productName, int qty, int price, String unitName) {
		this.productId = productId;
		this.productName = productName;
		this.qty = qty;
		this.price = price;
		this.unitName = unitName;
		this.amount = qty * price;
	}

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public int getQty() {
		return qty;
	}

	public int getPrice() {
		return price;
	}

	public int getAmount() {
		return amount;
	}

	public String getUnitName() {
		return unitName;
	}
}
