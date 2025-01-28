package com.Blog.payload;

import java.util.HashSet;
import java.util.Set;

import com.Blog.enetites.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private int id;
	@NotEmpty(message = "Name should not empty")
	private String name;
	@Email(message = "Invaild Email")
	@NotEmpty
	private String email;
	@NotEmpty(message = "Password Should not Empty")
	@Size(min = 3,max = 8, message = "Invaild password")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();
	
	
}
