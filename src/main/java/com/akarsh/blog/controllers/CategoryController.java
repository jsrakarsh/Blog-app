 package com.akarsh.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akarsh.blog.payloads.CategoryDto;
import com.akarsh.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//CREATE
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
	    CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
	    return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
	}

	//UPDATE
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
	    CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, catId);
	    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	//DELETE
	@DeleteMapping("/{catId}")
	public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Integer catId) {
	    this.categoryService.deleteCategory(catId);
	    return new ResponseEntity<>(Map.of("message", "Category deleted successfully"), HttpStatus.OK);
	}

	//GET
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
	    CategoryDto categoryDto = this.categoryService.getCategory(catId);
	    return new ResponseEntity<>(categoryDto, HttpStatus.OK);
	}

	//GET ALL
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories() {
	    List<CategoryDto> categories = this.categoryService.getCategories();
	    return ResponseEntity.ok(categories);
	}
  


}
