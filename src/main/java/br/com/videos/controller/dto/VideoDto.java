package br.com.videos.controller.dto;

import br.com.videos.modelo.Category;
import br.com.videos.modelo.Video;
import org.springframework.data.domain.Page;

import java.util.List;

public class VideoDto {

	private Long id;
	private String title;
	private String description;
	private String url;
	private List<Category> listCategory;
	
	
	
	public VideoDto(Video video) {
		this.id = video.getId();
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.url = video.getUrl();
		this.listCategory = video.getListCategory();
	}



	public List<Category> getListCategoria() {
		return listCategory;
	}

	public void setListCategory(List<Category> listCategory) {
		this.listCategory = listCategory;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public Long getId() {
		return id;
	}

	public static Page<VideoDto> converter(Page<Video> videos) {
		return videos.map(VideoDto::new);
	}

	public static Page<VideoDto> converterPageble(Page<Video> videos) {
		return videos.map(VideoDto::new);
	}

	
	

}
