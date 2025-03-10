package com.akarsh.blog.services;

import java.util.List;  // ✅ Correct Import

import com.akarsh.blog.payloads.CategoryDto;

public interface CategoryService {
	
	// create
	CategoryDto createCategory (CategoryDto categoryDto);
	
	// update
	CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId) ;
	
	// delete
	public void deleteCategory (Integer categoryId);
	
	// get
	CategoryDto getCategory (Integer categoryId);
	
	// get All
	List<CategoryDto> getCategories();
	
	// Created interface just for the purpose of LOOSE COUPLING so that whenever needed implementation can be changed accordingly

}


