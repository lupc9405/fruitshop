package com.pc.fruitshop.product.service;

import java.util.List;

import com.pc.fruitshop.product.model.Product;

/*
 * [ ProductService ]
 * 負責描述 商品的商業邏輯規格
 * 
 * */

public interface ProductService {

	// 取得資料庫中所有商品
	List<Product> findAll();

	// 依名稱查詢單一商品
	Product findByName(String name);
	
	// 依商品編號查詢商品
	Product findById(Long id);

}
