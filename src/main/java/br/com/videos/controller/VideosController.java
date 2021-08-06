package br.com.videos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.videos.controller.form.AtualizacaoDeVideoForm;
import br.com.videos.controller.form.VideoForm;
import br.com.videos.dto.VideoDto;
import br.com.videos.modelo.Video;
import br.com.videos.repository.VideoRepository;

@RestController
@RequestMapping("/videos")
public class VideosController {
	
	@Autowired
	private VideoRepository videoRepository;
	
	//Listar todos os videos ou 1 pelo nome
	@GetMapping
	public List<VideoDto> read (String tituloVideo){
		if(tituloVideo == null) {
			List<Video> videos = videoRepository.findAll();
			return VideoDto.converter(videos);
		}
		
		List<Video> videos = videoRepository.findByTitle(tituloVideo);
		return VideoDto.converter(videos);
	}
	
	//Buscar video pelo id
	@GetMapping("/{id}")
	public ResponseEntity<VideoDto> buscar (@PathVariable Long id){
		Optional<Video> optional = videoRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new VideoDto(optional.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//Cadastrar um novo video
	@PostMapping
	@Transactional
	public ResponseEntity<VideoDto> insert(@RequestBody @Valid VideoForm videoForm, UriComponentsBuilder uriBuilder) {
		Video video = videoForm.converter(videoRepository);
		videoRepository.save(video);

		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
		return ResponseEntity.created(uri).body(new VideoDto(video));

	}
	
	//Atualizar video existe
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<VideoDto> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoDeVideoForm videoForm) {
		Optional<Video> optional = videoRepository.findById(id);
		if (optional.isPresent()) {
			Video video = videoForm.update(id, videoRepository);
			return ResponseEntity.ok(new VideoDto(video));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	//Apagar video
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Video> optional = videoRepository.findById(id);
		if(optional.isPresent()) {
			videoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

}
