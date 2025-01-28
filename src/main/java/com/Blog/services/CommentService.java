package com.Blog.services;

import java.util.Set;

import com.Blog.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer postId);

	// CommentDto updateComment(CommentDto comment, Integer commentId);

	void deleteComment(Integer commentId);

	//Set<CommentDto> getCommentByPost(Integer postId);

}
