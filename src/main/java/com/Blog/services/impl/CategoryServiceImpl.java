package com.Blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.enetites.Category;
import com.Blog.exceptions.ResourceNotFoundException;
import com.Blog.payload.CategoryDto;
import com.Blog.repositers.CategoryRepo;
import com.Blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto creatCategory(CategoryDto categoryDto) {

		Category category = this.modelMapper.map(categoryDto, Category.class);

		Category addedCat = this.categoryRepo.save(category);

		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		category.setTitle(categoryDto.getTitle());

		category.setDescription(categoryDto.getDescription());

		Category UpdateCat = this.categoryRepo.save(category);

		return this.modelMapper.map(UpdateCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {

		Category deleteCategory = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		this.categoryRepo.delete(deleteCategory);

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCat = this.categoryRepo.findAll();

		List<CategoryDto> categoryDtos = allCat.stream()
				.map((catDtos) -> this.modelMapper.map(catDtos, CategoryDto.class)).collect(Collectors.toList());
		return categoryDtos;
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		return this.modelMapper.map(cat, CategoryDto.class);
	}

}
