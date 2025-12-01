package com.pc.fruitshop.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pc.fruitshop.order.dto.OrderResult;
import com.pc.fruitshop.order.service.OrderService;

@Controller
public class OrderPageController {
	
	private final OrderService orderService;

	// 建構子注入
    public OrderPageController(OrderService orderService) {
        this.orderService = orderService;
    }
	
	// 下單頁面
	@GetMapping("/order")
	public String showOrderForm() {
		return "order-form"; // 對應 templates/order-form.html
	}

	// 提交訂單: 數量正規化+金額計算 由 Service 處理
	@PostMapping("/order/submit")
	public String submitOrder(
			@RequestParam String customerName,
            @RequestParam Integer appleQty,
            @RequestParam Integer bananaQty,
            @RequestParam Integer watermelonQty,
            Model model) {

		// 數量正規化(null、負數、超過10, 由 Service 處理)
		appleQty = orderService.normalizeQty(appleQty);
		bananaQty = orderService.normalizeQty(bananaQty);
		watermelonQty = orderService.normalizeQty(watermelonQty);

		// 訂單處理 (庫存檢查、扣庫存、計算金額)
		 OrderResult result = orderService.processOrder(
	                customerName, appleQty, bananaQty, watermelonQty);

		// 回傳前端渲染
		 model.addAttribute("result", result);

		return "order-result"; // 對應 templates/order-result.html
	}

}
