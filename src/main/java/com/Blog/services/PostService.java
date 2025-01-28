package com.Blog.services;

import java.util.List;


import com.Blog.payload.PostDto;
import com.Blog.payload.PostResponse;

public interface PostService {

	// create

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	// update
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete

	void deletePost(Integer postId);

	// Get

	PostDto getPostById(Integer postId);

	// getAll

	PostResponse getAllPost(Integer PageNumber, Integer pageSize, String sortBy,String sortDic);

	// get all post by user

	List<PostDto> getAllPostByUser(Integer userId);

	// get all post by category

	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	List<PostDto> searchPost(String keyword);
}
