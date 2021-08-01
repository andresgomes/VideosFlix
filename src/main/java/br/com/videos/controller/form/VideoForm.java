package br.com.videos.controller.form;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.videos.modelo.Categoria;
import org.hibernate.validator.constraints.Length;

import br.com.videos.modelo.Video;
import br.com.videos.repository.VideoRepository;

import java.util.List;

public class VideoForm {
	
	@NotNull @NotEmpty @Length(min = 4, max = 30)
	private String titulo;
	
	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull @NotEmpty
	private String url;

	@NotNull @NotEmpty
	@OneToMany
	private List<Categoria> listaCategoria;
	
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Video converter(VideoRepository videoRepository) {
		return new Video(titulo, descricao, url, listaCategoria);
	}



}
