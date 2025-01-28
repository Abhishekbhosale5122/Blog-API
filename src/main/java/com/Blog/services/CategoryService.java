package com.Blog.services;

import java.util.List;

import com.Blog.payload.CategoryDto;

public interface CategoryService {

	// Create Catgegory
	CategoryDto creatCategory(CategoryDto categoryDto);

	// updating Category

	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
	
	//delete category 
	
	void deleteCategory(int categoryId);
	
	// getAll Category 
	
	List<CategoryDto> getAllCategory();
	
	//getCategoryById
	
	CategoryDto getCategoryById(int categoryId);

}
