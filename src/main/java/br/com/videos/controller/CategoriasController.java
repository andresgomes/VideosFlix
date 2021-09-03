package br.com.videos.controller;


import br.com.videos.controller.dto.CategoryDto;
import br.com.videos.controller.dto.VideoDto;
import br.com.videos.modelo.Category;
import br.com.videos.modelo.Video;
import br.com.videos.repository.CategoryRepository;
import br.com.videos.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VideoRepository videoRepository;

    //listar todas as categorias
    @GetMapping
    public List<CategoryDto> read(){
        List<Category> categories = categoryRepository.findAll();
        return CategoryDto.converter(categories);
    }

    //Busca categoria pelo id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> search(@PathVariable Long id){
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(new CategoryDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    //Busca videos pela categoria
    @GetMapping("/{id}/videos")
    public ResponseEntity<Page<VideoDto>> searchVideosByCategory(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            Pageable pageable = PageRequest.of(1,5);
            Page<Video> videos = videoRepository.findByListCategoryId(id,pageable);

            return ResponseEntity.ok(VideoDto.converterPageble(videos));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public  ResponseEntity<Category> insert (@RequestBody @Valid Category category){

        return ResponseEntity.ok(categoryRepository.save(category));

    }

    //apagar categoria
    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity delete (@PathVariable Long id){
        Optional<Category> optional = categoryRepository.findById(id);
        if (optional.isPresent()){
            categoryRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
