package com.pc.fruitshop.inventory.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pc.fruitshop.inventory.model.Inventory;
import com.pc.fruitshop.inventory.repository.InventoryRepository;
import com.pc.fruitshop.product.model.Product;

/*
 * [ InventoryServiceImpl ]
 * 負責實作 InventoryService 的商業邏輯
 * 透過 InventoryRepository 與資料庫互動
 * 
 * */

@Service
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	// 建構子注入 InventoryRepository
	public InventoryServiceImpl(InventoryRepository inventoryRepository) {
		this.inventoryRepository = inventoryRepository;
	}

	// 查詢庫存
	@Override
	public Inventory findByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId);
    }

	// 檢查庫存
	@Override
	public boolean hasEnoughStock(Long productId, int requiredQty) {
        Inventory inventory = findByProductId(productId);
        if (inventory == null) {
            return false;
        }
        return inventory.getStock() >= requiredQty;
    }

	// 扣減庫存
	@Transactional
	@Override
	public void decreaseStock(Long productId, int qty) {
        Inventory inventory = findByProductId(productId);
        if (inventory == null) {
			throw new IllegalStateException("找不到該商品的庫存資料");
		}
		if (inventory.getStock() < qty) {
			throw new IllegalStateException("庫存不足，無法扣減");
		}

		// 扣庫存
		inventory.setStock(inventory.getStock() - qty);
		inventory.setUpdatedAt(LocalDateTime.now());
		inventoryRepository.save(inventory);
	}

}
