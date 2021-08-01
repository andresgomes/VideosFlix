package br.com.videos.repository;

import br.com.videos.modelo.Categoria;
import br.com.videos.modelo.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
