package br.com.videos.modelo;

import br.com.videos.dto.CategoriaDto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String descricao;
	private String url;
	
	@OneToMany
	private List<Categoria> listaCategoria;
	
	public Video() {
	}


	public Video(String titulo, String descricao, String url, List<Categoria> listaCategoria) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.url = url;
		this.listaCategoria = listaCategoria;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}


	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}


	
	
	
	

}
