package com.akarsh.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akarsh.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
