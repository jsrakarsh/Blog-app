package com.akarsh.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akarsh.blog.entities.Comment;
import com.akarsh.blog.entities.Post;
import com.akarsh.blog.entities.User;
import com.akarsh.blog.exceptions.ResourceNotFoundException;
import com.akarsh.blog.payloads.CommentDto;
import com.akarsh.blog.repositories.CommentRepo;
import com.akarsh.blog.repositories.PostRepo;
import com.akarsh.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	    
	    // Fetch the post or throw an exception if not found
	    Post post = this.postRepo.findById(postId)
	            .orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));

	    // Map DTO to Entity
	    Comment comment = this.modelMapper.map(commentDto, Comment.class);

	    // Set the post reference
	    comment.setPost(post);

	    // Save the comment
	    Comment savedComment = this.commentRepo.save(comment);

	    // Convert entity back to DTO and return
	    return this.modelMapper.map(savedComment, CommentDto.class);
	}

	
	// NW
	
	@Override
	public void deleteComment(Integer commentId) {

	    Comment comment = this.commentRepo.findById(commentId)
	            .orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

	    // Get associated post
	    Post post = comment.getPost();

	    // Remove the comment from the post's comment list
	    post.getComments().remove(comment);

	    // Save the post first to update its comments list
	    this.postRepo.save(post);

	    // Delete the comment from the database
	    this.commentRepo.deleteById(commentId);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
	    
	    // Fetch the comment or throw an exception if not found
	    Comment comment = this.commentRepo.findById(commentId)
	            .orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));

	    // Update the comment content
	    comment.setContent(commentDto.getContent());

	    // Save the updated comment
	    Comment updatedComment = this.commentRepo.save(comment);

	    // Convert entity back to DTO and return
	    return this.modelMapper.map(updatedComment, CommentDto.class);
	}



}
