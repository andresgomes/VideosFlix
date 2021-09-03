package br.com.videos.repository;

import br.com.videos.modelo.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VideoRepository extends JpaRepository<Video, Long>{
	
	Page<Video> findByTitle(String tituloVideo, Pageable pageable);

	Page<Video> findByListCategoryId(Long categoryId, Pageable pageable);

}
