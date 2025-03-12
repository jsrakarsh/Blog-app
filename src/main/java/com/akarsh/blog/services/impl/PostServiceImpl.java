package com.akarsh.blog.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.akarsh.blog.entities.Category;
import com.akarsh.blog.entities.Post;
import com.akarsh.blog.entities.User;
import com.akarsh.blog.exceptions.ResourceNotFoundException;
import com.akarsh.blog.payloads.PostDto;
import com.akarsh.blog.payloads.PostResponse;
import com.akarsh.blog.repositories.CategoryRepo;
import com.akarsh.blog.repositories.PostRepo;
import com.akarsh.blog.repositories.UserRepo;
import com.akarsh.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//TO GET USER DETAIL FOR CREATE POST() METHOD
	@Autowired
	private UserRepo userRepo;
	
	//TO GET CATEGORY DETAIL FOR CREATE POST() METHOD
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
	    
	    User user = this.userRepo.findById(userId)
	            .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
	    
	    Category category = this.categoryRepo.findById(categoryId)
	            .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
	    
	    Post post = this.modelMapper.map(postDto, Post.class);
	    
	   
	    post.setImageName("default.png"); 
	    post.setAddedDate(new Date(Calendar.getInstance().getTime().getTime()));

	    post.setUser(user);
	    post.setCategory(category);

	    Post newPost =  this.postRepo.save(post);  

	    
	    return this.modelMapper.map(newPost, PostDto.class);
	    
	}


	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
	    // Fetch the post or throw an exception if not found
	    Post post = this.postRepo.findById(postId)
	            .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

	    // Update post fields
	    post.setTitle(postDto.getTitle());
	    post.setContent(postDto.getContent());
	    post.setImageName(postDto.getImageName());

	    // Save updated post
	    Post updatedPost = this.postRepo.save(post);

	    // Convert to DTO and return
	    return this.modelMapper.map(updatedPost, PostDto.class);
	}


	@Override
	public void deletePost(Integer postId) {
	    Post post = this.postRepo.findById(postId)
	            .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

	    this.postRepo.delete(post);
	}


	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		
		
		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc")) {
		    sort = Sort.by(sortBy).ascending();
		} else {
		    sort = Sort.by(sortBy).descending();
		}

		// Create a pageable request with page number, page size, and sorting criteria
		Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
		
	    // ALTERNATE WAY FOR DESCENDING -> Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).descending());
	    
	    // Fetch paginated and sorted posts from the database
	    Page<Post> pagePost = postRepo.findAll(pageable);
	    
	    // Convert retrieved posts to PostDto
	    List<PostDto> postDtos = pagePost.getContent().stream()
	            .map(post -> modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    // Prepare response
	    PostResponse postResponse = new PostResponse();
	    
	    postResponse.setContent(postDtos);
	    
	    postResponse.setPageNumber(pagePost.getNumber());
	    
	    postResponse.setPageSize(pagePost.getSize());
	    
	    postResponse.setTotalElements(pagePost.getTotalElements());
	    
	    postResponse.setTotalPages(pagePost.getTotalPages());
	    
	    postResponse.setLastPage(pagePost.isLast());

	    return postResponse;
	}




	@Override
	public PostDto getPostById(Integer postId) {
	    Post post = this.postRepo.findById(postId)
	            .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

	    return this.modelMapper.map(post, PostDto.class);
	}


	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	    // Fetch category or throw exception if not found
	    Category category = this.categoryRepo.findById(categoryId)
	        .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

	    // Fetch posts related to the category
	    List<Post> posts = this.postRepo.findByCategory(category);

	    // Convert Post entities to PostDto using modelMapper
	    List<PostDto> postDtos = posts.stream()
	            .map(post -> this.modelMapper.map(post, PostDto.class))
	            .collect(Collectors.toList());

	    return postDtos;
	}


	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
	    // Fetch user or throw exception if not found
	    User user = this.userRepo.findById(userId)
	        .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));

	    // Retrieve posts associated with the user
	    List<Post> posts = this.postRepo.findByUser(user);

	    // Convert List<Post> to List<PostDto> using ModelMapper
	    List<PostDto> postDtos = posts.stream()
	        .map(post -> this.modelMapper.map(post, PostDto.class))
	        .collect(Collectors.toList());

	    return postDtos;
	}


	@Override
	public List<PostDto> searchPosts(String keyword) {
	    List<Post> posts = this.postRepo.searchByTitleOrContent(keyword);
	    return posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}



	

}
