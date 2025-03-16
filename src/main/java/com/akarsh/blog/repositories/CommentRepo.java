package com.akarsh.blog.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.akarsh.blog.entities.Comment;


public interface CommentRepo extends JpaRepository<Comment,Integer> {
	
	

}
