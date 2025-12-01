package com.pc.fruitshop.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pc.fruitshop.product.model.Product;
import com.pc.fruitshop.product.service.ProductService;

/*
 * [ ProductPageController ]
 * - 負責處理商品相關頁面顯示 
 * - 導向頁面、帶入商品 
 * - 不處理 CRUD、訂單提交
 * 
 * */

@Controller
public class ProductPageController {

	private final ProductService productService;

	// 建構子注入 ProductService
	public ProductPageController(ProductService productService) {
		this.productService = productService;
	}

	/*
	 * /shop 
	 * - 透過 ProductService 從資料庫取得全部商品 
	 * - 將商品列表加入 model, 回傳 shop.html
	 * 
	 * */
	@GetMapping("/shop")
	public String showShopPage(Model model) {
		// 取得所有商品資料
		List<Product> products = productService.findAll();

		// 加入 model 供前端使用
		model.addAttribute("products", products);

		// 回傳templates/shop.html
		return "shop"; // 對應 templates/shop.html
	}
}
