package com.pc.fruitshop.product.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
	
	// 商品編號
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 商品名稱
    @Column(nullable = false, length = 50)
    private String name;

	// 單價
    @Column(nullable = false)
    private Integer price;
    
    // 單位
    @Column(name = "unit_name", nullable = false, length = 20)
	private String unitName;
	
    // 建立時間
    @Column(name = "created_at")
	private LocalDateTime createdAt;
	
    // 更新時間
    @Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	
	// ===== 建構子
	public Product() {
		
	}
	
	
	// ===== getter/setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
