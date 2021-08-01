package br.com.videos.dto;

import br.com.videos.modelo.Categoria;
import br.com.videos.modelo.CategoriaCores;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaDto {

    private String titulo;
    private Enum<CategoriaCores> cor;

    public CategoriaDto(Categoria categoria) {
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public static List<CategoriaDto> converter(List<Categoria> categorias) {
        return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
    }
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Enum<CategoriaCores> getCor() {
        return cor;
    }

    public void setCor(Enum<CategoriaCores> cor) {
        this.cor = cor;
    }
}
