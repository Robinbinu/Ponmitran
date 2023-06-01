package com.shopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shopping.cart.entity.Product;



public interface ProductRepository extends JpaRepository<Product, Long>{

	 @Query("SELECT p FROM Product p WHERE " +
	            "p.category LIKE CONCAT('%',:query, '%')" +
	            "Or p.product_name LIKE CONCAT('%', :query, '%')")
	    List<Product> searchProducts(String query);

	 @Query("SELECT p FROM Product p WHERE " +
	            "p.category LIKE CONCAT('%',:query, '%')" +
	            "Or p.product_name LIKE CONCAT('%', :query, '%')")
	List<Product> searchProductsSQL(String query);
}
