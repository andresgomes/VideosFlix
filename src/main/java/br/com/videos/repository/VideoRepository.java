package br.com.videos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.videos.modelo.Video;


public interface VideoRepository extends JpaRepository<Video, Long>{
	
	List<Video> findByTitulo(String tituloVideo);

}
