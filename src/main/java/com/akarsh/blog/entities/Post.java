package com.akarsh.blog.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Posts")
public class Post {
	
	@Id
	@GeneratedValue (strategy = GenerationType. IDENTITY)
	private Integer postId;
	
	@Column(nullable = false , length = 100)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn (name = "category_id")
	private Category category;
	
	@ManyToOne
	private User user;

}
