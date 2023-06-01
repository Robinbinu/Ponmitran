package com.shopping.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.entity.Category;
import com.shopping.cart.service.CategoryService;


@CrossOrigin
@RequestMapping("/api/v1/category")
@RestController
public class CategoryController 
{
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/addCategory")
	public Category postCategory(@RequestBody Category category)
	{
		return categoryService.saveCategory(category);
	}
	
	@PostMapping("/addAllCategory")
	public List<Category> postCategory(@RequestBody List<Category> categorys)
	{
		return categoryService.saveAllCategory(categorys);
	}
	
	@GetMapping("/getCategory")
	public List<Category> getCategory()
	{
		return categoryService.getAllCategory();
	}

}
