package br.com.videos.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.videos.modelo.Video;
import br.com.videos.repository.VideoRepository;

public class AtualizacaoDeVideoForm {

	@NotNull @NotEmpty @Length(min = 4, max = 30)
	private String titulo;
	
	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull @NotEmpty
	private String url;
	
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Video atualizar(Long id, VideoRepository videoRepository) {
		Video video = videoRepository.getById(id);
		video.setTitulo(this.titulo);
		video.setDescricao(this.descricao);
		
		return video;
	}
}
