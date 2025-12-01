package com.pc.fruitshop.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.fruitshop.inventory.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	// 查詢庫存（依商品編號）
    Inventory findByProductId(Long productId);

}
