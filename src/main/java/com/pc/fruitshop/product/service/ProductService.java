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
    public List<Product> findAll();
    
}
