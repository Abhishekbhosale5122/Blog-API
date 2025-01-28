package com.Blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min = 3,message = "Title should be minimum 3 character ")
	private String title;
	@NotBlank
	@Size(min = 10 ,message = "Minimum 10 character")
	private String description;
}
