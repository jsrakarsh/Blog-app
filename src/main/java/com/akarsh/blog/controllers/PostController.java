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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akarsh.blog.entities.Post;
import com.akarsh.blog.payloads.PostDto;
import com.akarsh.blog.payloads.PostResponse;
import com.akarsh.blog.services.PostService;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	// CREATE a new post
	@PostMapping("/user/{userId}/category/{categoryId}/posts") 
	public ResponseEntity<PostDto> createPost(
	        @RequestBody PostDto postDto, 
	        @PathVariable Integer userId, 
	        @PathVariable Integer categoryId) {
	    
	    PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
	    return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}
	
	// Retrieve all posts by a user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
	    List<PostDto> posts = this.postService.getPostsByUser(userId);
	    return ResponseEntity.ok(posts);
	}

	// Retrieve all posts of a category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
	    List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
	    return ResponseEntity.ok(posts);
	}
	 
	// Get all posts
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
	        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
	        @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
	        @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {

	    PostResponse postResponse = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
	    return ResponseEntity.ok(postResponse);
	}




	
	// Get post detail using post Id
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		
	    PostDto postDto = this.postService.getPostById(postId);
	    
	    return new ResponseEntity<>(postDto, HttpStatus.OK);
	}
	
	// Delete Post
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<Map<String, String>> deletePost(@PathVariable Integer postId) {
		
	    this.postService.deletePost(postId);
	    
	    return new ResponseEntity<>(Map.of("message", "Post Deleted Successfully"), HttpStatus.OK);
	}

	// Update Post
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(
	        @RequestBody PostDto postDto, 
	        @PathVariable Integer postId) 
	{
	    
	    PostDto updatedPost = this.postService.updatePost(postDto, postId);
	    return ResponseEntity.ok(updatedPost);
	}
	
	// Search Posts by keyword
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable String keyword) {
	    List<PostDto> results = this.postService.searchPosts(keyword);
	    return ResponseEntity.ok(results);
	}





}
