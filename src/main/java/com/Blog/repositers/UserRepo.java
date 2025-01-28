package com.Blog.repositers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.enetites.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);

}
