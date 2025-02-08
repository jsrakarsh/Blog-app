package com.akarsh.blog.services;

import com.akarsh.blog.payloads.UserDto;

import java.util.List;

import com.akarsh.blog.entities.User;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId) ;
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
