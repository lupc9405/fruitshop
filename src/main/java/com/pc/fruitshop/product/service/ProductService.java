package com.pc.fruitshop.product.service;

import java.util.List;

import com.pc.fruitshop.product.model.Product;

/*
 * [ ProductService ]
 * 負責描述 商品的商業邏輯規格
 * Controller 透過此介面取得商品資料
 * 
 * */

public interface ProductService {

	// 取得資料庫中所有商品
	List<Product> findAll();

	// 依名稱查詢單一商品
	Product findByName(String name);

}
