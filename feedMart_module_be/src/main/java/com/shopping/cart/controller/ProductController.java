package com.shopping.cart.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.entity.Product;
import com.shopping.cart.service.ProductService;


@RestController
@CrossOrigin()
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
        return ResponseEntity.ok(productService.searchProducts(query));
    }

    @PostMapping("/display")
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    
    @PostMapping("/displayAll")
    public List<Product> createProduct(@RequestBody List<Product> products){
        return productService.createAllProduct(products);
    }
   
    @GetMapping("/getProducts")
    public List<Product> getProducts()
    { 
    	return productService.getAllProducts();
    }
}