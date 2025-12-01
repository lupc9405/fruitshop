package com.pc.fruitshop.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductPageController {

	// 產品列表
	@GetMapping("/products")
	public String showProductsPage() {
		return "products"; // 對應 templates/products.html
	}
}
