package com.Blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.payload.JwtAuthRequest;
import com.Blog.payload.JwtAuthResponse;
import com.Blog.payload.UserDto;
import com.Blog.security.JwtTokenHelper;
import com.Blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager; 
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		this.authenticate(request.getUserName(),request.getPassword());
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String userName, String password) throws Exception {
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userName, password);
		
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			
		} catch (BadCredentialsException e) {
			
			System.out.println("Invalide Details");
			
			throw new Exception("Invalide User or Password");
		}
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto>registerUser(@RequestBody UserDto userDto){
		
		UserDto registerUser = this.userService.registerUser(userDto);
		
		return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
	}

}
