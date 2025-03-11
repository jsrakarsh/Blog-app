package com.akarsh.blog.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.akarsh.blog.entities.Category;
import com.akarsh.blog.entities.Post;
import com.akarsh.blog.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	
	//CUSTOM METHODS
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	 @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	 List<Post> searchByTitleOrContent(@Param("keyword") String keyword);
	
}
 