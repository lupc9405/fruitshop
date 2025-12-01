package com.pc.fruitshop.inventory.service;

import com.pc.fruitshop.inventory.model.Inventory;

/*
 * [ InventoryService ]
 * 負責描述 與庫存相關的商業邏輯規格
 * 
 * */

public interface InventoryService {

	// 查詢庫存
	Inventory findByProductId(Long productId);

	// 該商品是否有足夠庫存
	boolean hasEnoughStock(Long productId, int requiredQty);

	// 扣減庫存, 不足則拋例外
	void decreaseStock(Long productId, int qty);

}
