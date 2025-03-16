package com.akarsh.blog.services;

import com.akarsh.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId);

	void deleteComment (Integer commentId); 
	
	CommentDto updateComment(CommentDto commentDto, Integer commentId);

}
