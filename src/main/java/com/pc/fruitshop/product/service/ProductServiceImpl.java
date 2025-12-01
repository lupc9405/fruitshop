package com.pc.fruitshop.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pc.fruitshop.product.model.Product;
import com.pc.fruitshop.product.repository.ProductRepository;

/*
 * [ ProductServiceImpl ]
 * 負責實作 ProductService 的商業邏輯
 * 透過 ProductRepository 與資料庫進行互動
 * 
 * */

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	// 建構子注入 productRepository
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// 回傳全部商品列表
	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	// 依名稱回傳對應商品
	@Override
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}
}
