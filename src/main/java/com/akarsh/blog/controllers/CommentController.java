package com.akarsh.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akarsh.blog.payloads.CommentDto;
import com.akarsh.blog.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	
	@Autowired
	private CommentService commentService ;
	
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, 
	                                                @PathVariable Integer postId) {
	   
	    CommentDto createComment = this.commentService.createComment(comment, postId);
	    
	   
	    return new ResponseEntity<>(createComment, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId) {
	 
	    this.commentService.deleteComment(commentId);

	    // Return success message
	    return new ResponseEntity<>("Comment deleted successfully!", HttpStatus.OK);
	}

	//UPDATE COMMENT
	
	@PutMapping("/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, 
	                                                @PathVariable Integer commentId) {
	    
	    CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
	    
	    return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	}


	
}
