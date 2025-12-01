package com.pc.fruitshop.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.fruitshop.inventory.service.InventoryService;
import com.pc.fruitshop.order.dto.OrderResult;
import com.pc.fruitshop.product.model.Product;
import com.pc.fruitshop.product.service.ProductService;

/*
 * [ OrderServiceImpl ]
 * 負責實作 OrderService 的商業邏輯
 * 數量正規化、庫存檢查、扣庫存、金額計算
 * 
 * */

@Service
public class OrderServiceImpl implements OrderService {

	private final ProductService productService;
	private final InventoryService inventoryService;

	// 建構子注入 ProductService、InventoryService
	public OrderServiceImpl(ProductService productService, InventoryService inventoryService) {
		this.productService = productService;
		this.inventoryService = inventoryService;
	}

	// 數量正規化: null 或負數 → 0；大於 10 → 10
	@Override
	public int normalizeQty(Integer qty) {
		if (qty == null || qty < 0)
			return 0;
		if (qty > 10)
			return 10;
		return qty;
	}

	// 處理整筆訂單
	@Override
	@Transactional
	public OrderResult processOrder(String customerName, int appleQty, int bananaQty, int watermelonQty) {

		// 查詢商品資料
		Product apple = productService.findByName("蘋果");
		Product banana = productService.findByName("香蕉");
		Product watermelon = productService.findByName("西瓜");

		// 多筆錯誤累積
        StringBuilder errorMessage = new StringBuilder();
        boolean hasError = false;

        // 蘋果庫存檢查
        if (appleQty > 0 && !inventoryService.hasEnoughStock(apple.getId(), appleQty)) {
            hasError = true;
            errorMessage.append("蘋果庫存不足<br>");
        }

        // 香蕉庫存檢查
        if (bananaQty > 0 && !inventoryService.hasEnoughStock(banana.getId(), bananaQty)) {
            hasError = true;
            errorMessage.append("香蕉庫存不足<br>");
        }

        // 西瓜庫存檢查
        if (watermelonQty > 0 && !inventoryService.hasEnoughStock(watermelon.getId(), watermelonQty)) {
            hasError = true;
            errorMessage.append("西瓜庫存不足<br>");
        }

        // 若有任何品項不足: 不扣庫存直接回傳
        if (hasError) {
            return new OrderResult(
                    false,
                    errorMessage.toString(),
                    0,
                    appleQty,
                    bananaQty,
                    watermelonQty
            );
        }

		// 庫存充足: 扣減庫存
        if (appleQty > 0) {
            inventoryService.decreaseStock(apple.getId(), appleQty);
        }
        if (bananaQty > 0) {
            inventoryService.decreaseStock(banana.getId(), bananaQty);
        }
        if (watermelonQty > 0) {
            inventoryService.decreaseStock(watermelon.getId(), watermelonQty);
        }

		// 計算總金額
        int totalAmount =
                appleQty * apple.getPrice() +
                bananaQty * banana.getPrice() +
                watermelonQty * watermelon.getPrice();

        // 組成回應訊息（給前端顯示）
        String message =
                customerName + " 你好，" +
                "您總共購買蘋果 " + appleQty + " 顆" +
                "、香蕉 " + bananaQty + " 條" +
                "、西瓜 " + watermelonQty + " 片" +
                "，總計 " + totalAmount + " 元";
        
        return new OrderResult(
                true,
                message,
                totalAmount,
                appleQty,
                bananaQty,
                watermelonQty
        );

	}
}
