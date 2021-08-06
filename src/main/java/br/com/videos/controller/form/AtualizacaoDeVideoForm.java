package br.com.videos.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.videos.modelo.Video;
import br.com.videos.repository.VideoRepository;

public class AtualizacaoDeVideoForm {

	@NotNull @NotEmpty @Length(min = 4, max = 30)
	private String title;
	
	@NotNull @NotEmpty
	private String description;
	
	@NotNull @NotEmpty
	private String url;
	
	
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Video update(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.getById(id);
		video.setTitle(this.title);
		video.setDescription(this.description);
		
		return video;
	}
}
