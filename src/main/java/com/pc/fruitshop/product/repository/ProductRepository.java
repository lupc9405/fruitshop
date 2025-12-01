package com.pc.fruitshop.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pc.fruitshop.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	// 依據商品名稱查詢
	Product findByName(String name);

}
