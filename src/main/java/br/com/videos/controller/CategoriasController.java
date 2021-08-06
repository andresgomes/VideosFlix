package br.com.videos.controller;


import br.com.videos.dto.CategoryDto;
import br.com.videos.modelo.Category;
import br.com.videos.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
