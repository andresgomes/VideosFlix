package br.com.videos.controller.form;

import br.com.videos.modelo.Category;
import br.com.videos.modelo.Video;
import br.com.videos.repository.VideoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class VideoForm {
	
	@NotNull @NotEmpty @Length(min = 4, max = 30)
	private String title;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String url;

	@NotNull @NotEmpty
	private List<Category> listCategory;
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public List<Category> getListCategory() {
		return listCategory;
	}

	public Video converter(VideoRepository videoRepository) {
		return new Video(title, description, url, listCategory);
	}



}
