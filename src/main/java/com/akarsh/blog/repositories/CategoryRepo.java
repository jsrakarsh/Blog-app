package com.akarsh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akarsh.blog.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
 