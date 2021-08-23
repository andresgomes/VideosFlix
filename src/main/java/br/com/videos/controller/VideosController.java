package br.com.videos.controller;

import br.com.videos.controller.form.AtualizacaoDeVideoForm;
import br.com.videos.controller.form.VideoForm;
import br.com.videos.controller.dto.VideoDto;
import br.com.videos.modelo.Video;
import br.com.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

	//Listar 5 videos sem estar "logago"
	@GetMapping("/free")
	public Page<VideoDto> readFree (@PageableDefault(sort = "id", direction = Direction.ASC, size = 5) Pageable pageable){
		Page<Video> videos = videoRepository.findAll(pageable);

		return VideoDto.converterPageble(videos);
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
