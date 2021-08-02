package br.com.videos.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.videos.modelo.Categoria;
import br.com.videos.modelo.Video;

public class VideoDto {
	
	private String titulo;
	private String descricao;
	private String url;
	private List<Categoria> listaCategoria;
	
	
	
	public VideoDto(Video video) {
		this.titulo = video.getTitulo();
		this.descricao = video.getDescricao();
		this.url = video.getUrl();
		this.listaCategoria = video.getListaCategoria();
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public String getTitulo() {
		return titulo;
	}



	public String getDescricao() {
		return descricao;
	}



	public String getUrl() {
		return url;
	}



	public static List<VideoDto> converter(List<Video> videos) {
		return videos.stream().map(VideoDto::new).collect(Collectors.toList());
	}
	

	
	

}
