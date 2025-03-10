package com.akarsh.blog.services.impl;

import java.util.stream.Collectors;
import java.util.List;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akarsh.blog.entities.Category;
import com.akarsh.blog.exceptions.ResourceNotFoundException;
import com.akarsh.blog.payloads.CategoryDto;
import com.akarsh.blog.repositories.CategoryRepo;
import com.akarsh.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCat = this. categoryRepo.save(category) ;
		return this.modelMapper.map (addedCat, CategoryDto.class) ;
		
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId)
			    .orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));

		
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updatedcat = this.categoryRepo.save(cat);
		
		return this.modelMapper.map (updatedcat, CategoryDto.class) ;
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				 .orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
	
		Category cat=this.categoryRepo.findById(categoryId)
				 .orElseThrow(() -> new ResourceNotFoundException("Category ", "Category Id", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<Category> categories = this.categoryRepo.findAll();

		List<CategoryDto> categoryDtos = categories.stream()
		    .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
		    .collect(Collectors.toList());

		return categoryDtos;
		
	}

}
