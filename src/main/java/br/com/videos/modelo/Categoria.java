package br.com.videos.modelo;

import javax.persistence.*;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Enumerated
	private CategoriaCores cor;

	public CategoriaCores getCor() {
		return cor;
	}

	public void setCor(CategoriaCores cor) {
		this.cor = cor;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	
	
	

}
