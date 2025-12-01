package com.pc.fruitshop.product.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pc.fruitshop.product.model.Product;
import com.pc.fruitshop.product.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductApiController {

	private final ProductService productService;

	public ProductApiController(ProductService productService) {
		this.productService = productService;
	}

	// 4.1 產品列表 – GET /api/products
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.findAll();
	}

	// 4.2 單一產品資訊 – GET /api/products/{id}
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.findById(id);
		if (product == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(product);
	}

	// 4.3 建立產品 – POST /api/product
	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {

		// 自動寫入
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());

		Product saved = productService.save(product);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(saved);
	}

	// 4.4 修改產品資訊 – PUT /api/product/{id}
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product input) {

		Product existing = productService.findById(id);

		if (existing == null) {
			return ResponseEntity.notFound().build();
		}

		// 更新允許變更的欄位
		existing.setName(input.getName());
		existing.setPrice(input.getPrice());
		existing.setUnitName(input.getUnitName());
		existing.setUpdatedAt(LocalDateTime.now());

		Product updated = productService.save(existing);

		return ResponseEntity.ok(updated);
	}

	// 4.5 刪除產品資訊 – DELETE /api/product/{id}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		Product existing = productService.findById(id);

		if (existing == null) {
			return ResponseEntity.notFound().build();
		}

		productService.deleteById(id);

		return ResponseEntity.noContent().build();
	}

}
