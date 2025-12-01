package com.pc.fruitshop.order.dto;

public class OrderResult {

	private boolean success; 	// 是否成功
	private String message; 	// 回應訊息
	private int totalAmount; 	// 成功時的總金額
	private int appleQty; 		// 蘋果訂購數量
	private int bananaQty; 		// 香蕉訂購數量
	private int watermelonQty; 	// 西瓜訂購數量

	public OrderResult(boolean success, String message, int totalAmount, int appleQty, int bananaQty,
			int watermelonQty) {
		this.success = success;
		this.message = message;
		this.totalAmount = totalAmount;
		this.appleQty = appleQty;
		this.bananaQty = bananaQty;
		this.watermelonQty = watermelonQty;
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

	public int getAppleQty() {
		return appleQty;
	}

	public int getBananaQty() {
		return bananaQty;
	}

	public int getWatermelonQty() {
		return watermelonQty;
	}
}
