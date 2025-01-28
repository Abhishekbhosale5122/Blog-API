package com.Blog.repositers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.enetites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
