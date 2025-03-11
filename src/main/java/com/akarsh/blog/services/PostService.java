package com.akarsh.blog.services;

import java.util.List;

import com.akarsh.blog.entities.Post;
import com.akarsh.blog.payloads.PostDto;

public interface PostService {
	
	
	//CREATE
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//UPDATE
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//DELETE
	void deletePost(Integer postId);
	
	//RETRIEVE ALL POSTS
	List<PostDto> getAllPost();
	
	// RETRIEVE SINGLE POST
	PostDto getPostById(Integer postId);
	
	//Retrieve all posts by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//Retrieve all posts by User
	List<PostDto> getPostsByUser(Integer userId);
	
	//SEARCH POST BY A SPECIFIC KEYWORD
	List<PostDto> searchPosts(String keyword);
	
	
}
