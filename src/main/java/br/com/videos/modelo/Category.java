package br.com.videos.modelo;

import javax.persistence.*;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Enumerated
	private CategoryColors color;


	public CategoryColors getColor() {
		return color;
	}

	public void setColor(CategoryColors color) {
		this.color = color;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	

}
