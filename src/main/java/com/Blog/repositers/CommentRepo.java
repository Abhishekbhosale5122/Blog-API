package com.Blog.repositers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.enetites.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	

}
