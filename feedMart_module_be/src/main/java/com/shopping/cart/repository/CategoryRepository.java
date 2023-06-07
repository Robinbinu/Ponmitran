package com.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.cart.entity.Category;



public interface CategoryRepository extends JpaRepository <Category,Long>
{
	
}
