package com.pc.fruitshop.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderPageController {

	// 單價
	private static final int APPLE_PRICE = 10;
	private static final int BANANA_PRICE = 12;
	private static final int WATERMELON_PRICE = 20;

	// shop入口
	@GetMapping("/shop")
	public String showShopPage() {
		return "shop"; // 對應 templates/shop.html
	}

	// 下單頁面
	@GetMapping("/order")
	public String showOrderForm() {
		return "order-form"; // 對應 templates/order-form.html
	}

	// 計算金額
	@PostMapping("/order/submit")
	public String submitOrder(@RequestParam("customerName") String customerName,
			@RequestParam(name = "appleQty", required = false) Integer appleQty,
			@RequestParam(name = "bananaQty", required = false) Integer bananaQty,
			@RequestParam(name = "watermelonQty", required = false) Integer watermelonQty, Model model) {

		// 數量處理
		appleQty = normalizeQuantity(appleQty);
		bananaQty = normalizeQuantity(bananaQty);
		watermelonQty = normalizeQuantity(watermelonQty);

		// 各項小計
		int appleTotal = appleQty * APPLE_PRICE;
		int bananaTotal = bananaQty * BANANA_PRICE;
		int watermelonTotal = watermelonQty * WATERMELON_PRICE;

		// 總金額
		int totalAmount = appleTotal + bananaTotal + watermelonTotal;

		// 傳入畫面顯示用
		model.addAttribute("customerName", customerName);
		model.addAttribute("appleQty", appleQty);
		model.addAttribute("bananaQty", bananaQty);
		model.addAttribute("watermelonQty", watermelonQty);
		model.addAttribute("totalAmount", totalAmount);

		return "order-result"; // 對應 templates/order-result.html
	}

	// 數量 0~10
	private Integer normalizeQuantity(Integer qty) {
		if (qty == null) {
			return 0;
		}
		if (qty < 0) {
			return 0;
		}
		if (qty > 10) {
			return 10;
		}
		return qty;
	}
}
