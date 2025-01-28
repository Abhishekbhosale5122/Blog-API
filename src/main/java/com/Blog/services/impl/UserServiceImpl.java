package com.Blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Blog.exceptions.ResourceNotFoundException;
import com.Blog.payload.UserDto;
import com.Blog.config.AppConstant;
import com.Blog.enetites.Role;
import com.Blog.enetites.User;
import com.Blog.repositers.RoleRepo;
import com.Blog.repositers.UserRepo;
import com.Blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		return this.userToUserDto(savedUser);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());

		return userDtos;
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

		return this.userToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {

		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updatedUser = this.userRepo.save(user);

		UserDto userDto1 = this.userToUserDto(updatedUser);

		return userDto1;
	}

	@Override
	public void deleteUser(Integer id) {

		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

		userRepo.delete(user);

	}

	private User dtoToUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());

		return user;
	}

	private UserDto userToUserDto(User user) {

		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

	@Override
	public UserDto registerUser(UserDto userDto) {

		User user = this.modelMapper.map(userDto, User.class);

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRoles().add(role);
		User user2 = this.userRepo.save(user);

		return this.modelMapper.map(user2, UserDto.class);
	}

}
