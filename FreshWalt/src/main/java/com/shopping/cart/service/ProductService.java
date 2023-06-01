package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.entity.Product;



public interface ProductService {
	
	List<Product> searchProducts(String query);
    Product createProduct(Product product);
	List<Product> createAllProduct(List<Product> products);
	List<Product> getAllProducts();
	Product getProductById(Long productId);
	Product findById(Long productId);
    

	

}
