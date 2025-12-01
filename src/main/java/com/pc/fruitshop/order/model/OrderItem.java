package com.pc.fruitshop.order.model;

import com.pc.fruitshop.product.model.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

	// 明細編號
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 所屬訂單 (多筆訂單明細 對 一筆訂單)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord orderRecord;

    // 購買的產品 (多筆訂單明細 對 某一個產品)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 數量
    @Column(nullable = false)
    private Integer quantity;

    
    // ===== 建構子
    public OrderItem() {
    	
    }


    // ===== getter/setter
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public OrderRecord getOrderRecord() {
		return orderRecord;
	}


	public void setOrderRecord(OrderRecord orderRecord) {
		this.orderRecord = orderRecord;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
}
