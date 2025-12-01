package com.pc.fruitshop.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.pc.fruitshop.product.model.Product;
import com.pc.fruitshop.product.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	private final ProductService productService;

    public ProductApiController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(){
        return productService.findAll();
    }
}
