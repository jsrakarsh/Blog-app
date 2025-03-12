package com.akarsh.blog.controllers;

import lombok.Value;

import org.springframework.http.MediaType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.akarsh.blog.config.AppConstants;
import com.akarsh.blog.entities.Post;
import com.akarsh.blog.payloads.PostDto;
import com.akarsh.blog.payloads.PostResponse;
import com.akarsh.blog.services.FileService;
import com.akarsh.blog.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;


@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Component
	public class YourService {
	    
	    @org.springframework.beans.factory.annotation.Value("${project.image}")
	    private String path;
	}
	
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
	        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
	        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
	        @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
	        @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {

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
	
	// Upload Image on a Post using its PostId
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
	        @RequestParam("image") MultipartFile image,
	        @PathVariable Integer postId) throws IOException {

	    // Hardcoded path inside your project directory
	    String path = "/Users/Singh/Downloads/BLOG-Backend/blog-app-apis/images"; 

	    // Ensure the directory exists before uploading
	    File directory = new File(path);
	    if (!directory.exists()) {
	        directory.mkdirs(); // Create if it doesn't exist
	    }

	    // Fetch the post by ID
	    PostDto postDto = this.postService.getPostById(postId);

	    // Upload the image and get the file name
	    String fileName = this.fileService.uploadImage(path, image);

	    // Set the uploaded image name in Post DTO
	    postDto.setImageName(fileName);

	    // Update the post with the new image
	    PostDto updatedPost = this.postService.updatePost(postDto, postId);

	    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}


	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
	    // Define the folder where images are stored
	    String path = "/Users/Singh/Downloads/BLOG-Backend/blog-app-apis/images";

	    // Get the image as an InputStream
	    InputStream resource = this.fileService.getResource(path, imageName);

	    // Set content type for response
	    response.setContentType(MediaType.IMAGE_JPEG_VALUE);

	    // Write the image to the response output stream
	    StreamUtils.copy(resource, response.getOutputStream());
	}






}
