package com.akarsh.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElements;
	
	private int totalPages;
	
	private boolean lastPage;
	
	// PURPOSE OF THIS CLASS IS TO SHOW DETAILS OF PAGINATION i.e HOW MANY PAGES ? , HOW MANY ELEMENTS ? etc.

}
