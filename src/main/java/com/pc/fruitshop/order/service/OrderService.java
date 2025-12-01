package com.pc.fruitshop.order.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

	// 單價
	private static final int APPLE_PRICE = 10;
	private static final int BANANA_PRICE = 12;
	private static final int WATERMELON_PRICE = 20;

	// 計算總金額
	public int calculateTotal(int appleQty, int bananaQty, int watermelonQty) {
		return (appleQty * APPLE_PRICE) 
				+ (bananaQty * BANANA_PRICE) 
				+ (watermelonQty * WATERMELON_PRICE);
	}
	
	// 數量 0~10
	public int normalizeQty(Integer qty) {
        if (qty == null || qty < 0) return 0;
        if (qty > 10) return 10;
        return qty;
    }

}
