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

    // 建構子注入, productRepository 注入
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 取得所有商品資料
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
