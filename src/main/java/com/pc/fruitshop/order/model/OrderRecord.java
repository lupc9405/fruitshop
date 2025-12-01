package com.pc.fruitshop.order.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "order_records")
public class OrderRecord {

	// 訂單編號
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 客戶名稱
    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;

    // 總金額
    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    // 下單時間
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    
    // ===== 建構子
    public OrderRecord() {
    	
    }

    
    // ===== getter/setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
}
