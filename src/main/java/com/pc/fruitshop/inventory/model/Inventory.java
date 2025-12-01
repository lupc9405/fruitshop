package com.pc.fruitshop.inventory.model;

import java.time.LocalDateTime;

import com.pc.fruitshop.product.model.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

	// 庫存紀錄編號
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	// 一個商品 對 一筆庫存
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    private Product product;

    // 庫存數量
    @Column(nullable = false)
    private Integer stock;

    // 更新時間
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    
    // ===== 建構子
	public Inventory() {
		
	}

	
	// ===== getter/setter
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	    
}
