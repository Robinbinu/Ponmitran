package com.shopping.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.cart.entity.Category;
import com.shopping.cart.repository.CategoryRepository;



@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Category saveCategory(Category category)
	{
		return categoryRepo.save(category);
	}
	public List<Category> saveAllCategory(List<Category> categorys)
	{
		return categoryRepo.saveAll(categorys);
	}
	
	public List<Category> getAllCategory()
	{
		return categoryRepo.findAll();
	}

}
