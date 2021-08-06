package br.com.videos.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.videos.modelo.Category;
import br.com.videos.modelo.Video;

public class VideoDto {
	
	private String title;
	private String description;
	private String url;
	private List<Category> listCategory;
	
	
	
	public VideoDto(Video video) {
		this.title = video.getTitle();
		this.description = video.getDescription();
		this.url = video.getUrl();
		this.listCategory = video.getListCategory();
	}

	public List<Category> getListaCategoria() {
		return listCategory;
	}

	public void setListaCategoria(List<Category> listaCategory) {
		this.listCategory = listaCategory;
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



	public static List<VideoDto> converter(List<Video> videos) {
		return videos.stream().map(VideoDto::new).collect(Collectors.toList());
	}
	

	
	

}
