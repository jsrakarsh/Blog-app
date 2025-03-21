package com.akarsh.blog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akarsh.blog.payloads.UserDto;
import com.akarsh.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser (userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
		
	}
	
	//PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uid)
	{
		UserDto updatedUser = this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	//DELETE -delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer uid)
	{
		this.userService.deleteUser(uid);
		
		return new ResponseEntity(Map.of("message"
				, "User Deleted Successfully"), HttpStatus.OK);
	}
	
	//GET - fetch user
	
	//fetch all
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers () {
	return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId) {
	return ResponseEntity.ok (this.userService.getUserById(userId));
	}
	
	
}
