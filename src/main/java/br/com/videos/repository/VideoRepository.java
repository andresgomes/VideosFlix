package br.com.videos.repository;

import br.com.videos.modelo.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video, Long>{
	
	List<Video> findByTitle(String tituloVideo);

	List<Video> findByListCategoryId(Long categoryId);

}
