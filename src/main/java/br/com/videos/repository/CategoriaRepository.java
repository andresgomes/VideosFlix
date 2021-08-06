package br.com.videos.repository;

import br.com.videos.modelo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Category, Long> {

}
