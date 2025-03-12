package com.akarsh.blog.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.akarsh.blog.entities.Category;
import com.akarsh.blog.entities.Post;
import com.akarsh.blog.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;


public interface PostRepo extends JpaRepository<Post,Integer> {
	
	
	//CUSTOM METHODS
	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	
	
	 /**
     * This method searches for posts where the keyword is present in either the title or the content.
     * 
     * How it works:
     * - LOWER(p.title) → Converts the title to lowercase so the search is not case-sensitive.
     * - LOWER(p.content) → Converts the content to lowercase for the same reason.
     * - CONCAT('%', :keyword, '%') → Helps find the keyword anywhere in the title or content.
     *   - '%' before and after means the keyword can be in the middle of a sentence too.
     *   - Example: Searching for "tech" will match:
     *     - "Technology is evolving"
     *     - "Latest tech news"
     *     - "High-tech gadgets"
     */
	
	 @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	 List<Post> searchByTitleOrContent(@Param("keyword") String keyword);
	
}
 