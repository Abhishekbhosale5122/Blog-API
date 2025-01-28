package com.Blog.repositers;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.enetites.Category;
import com.Blog.enetites.Post;
import com.Blog.enetites.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

}
