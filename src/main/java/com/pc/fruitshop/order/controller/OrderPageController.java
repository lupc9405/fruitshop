package com.pc.fruitshop.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pc.fruitshop.order.service.OrderService;

@Controller
public class OrderPageController {
	
	@Autowired
	private OrderService orderService;

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

	// 提交訂單 (包含計算金額): 數量+金額計算 由Service 處理
	@PostMapping("/order/submit")
	public String submitOrder(@RequestParam("customerName") String customerName,
			@RequestParam(name = "appleQty", required = false) Integer appleQty,
			@RequestParam(name = "bananaQty", required = false) Integer bananaQty,
			@RequestParam(name = "watermelonQty", required = false) Integer watermelonQty, Model model) {

		// 數量
		appleQty = orderService.normalizeQty(appleQty);
		bananaQty = orderService.normalizeQty(bananaQty);
		watermelonQty = orderService.normalizeQty(watermelonQty);

		// 總金額
		int totalAmount = orderService.calculateTotal(appleQty, bananaQty, watermelonQty);

		// 回傳前端
		model.addAttribute("customerName", customerName);
		model.addAttribute("appleQty", appleQty);
		model.addAttribute("bananaQty", bananaQty);
		model.addAttribute("watermelonQty", watermelonQty);
		model.addAttribute("totalAmount", totalAmount);

		return "order-result"; // 對應 templates/order-result.html
	}

	// 數量處理由OrderService管理

}
