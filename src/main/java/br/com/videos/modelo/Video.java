package br.com.videos.modelo;

import java.util.List;

import javax.persistence.*;

@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String description;
	private String url;
	@ManyToMany
	private List<Category> listCategory;
	
	public Video() {
	}


	public Video(String title, String description, String url, List<Category> listCategory) {
		this.title = title;
		this.description = description;
		this.url = url;
		this.listCategory = listCategory;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public List<Category> getListCategory() {
		return listCategory;
	}


	public void setListCategory(List<Category> listaCategory) {
		this.listCategory = listaCategory;
	}


	
	
	
	

}
