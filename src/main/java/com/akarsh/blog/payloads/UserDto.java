package com.akarsh.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int id;
	
	@NotBlank
	@Size(min = 4, message = "Username must be min of 4 characters")
	private String name; 
	
	@Email(message = "Email address is invalid")
	private String email; 
	
	@NotBlank
	@Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10 chars !!")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{3,10}$",
	message = "Password must contain at least one uppercase letter, one number, and one special character")
	private String password; 
	
	@NotBlank
	private String about;

	
}
