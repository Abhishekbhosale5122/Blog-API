package com.Blog.services;

import java.util.List;

import com.Blog.payload.UserDto;

public interface UserService {
	
	UserDto registerUser(UserDto userDto);

	public UserDto createUser(UserDto userDto);
	
	public List<UserDto> getAllUser();
	
	public UserDto getUserById(Integer id);
	
	public UserDto updateUser(UserDto userDto ,Integer id);
	
	public void deleteUser(Integer id);
	
}
