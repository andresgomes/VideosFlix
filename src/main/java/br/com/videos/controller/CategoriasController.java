package br.com.videos.controller;


import br.com.videos.dto.CategoryDto;
import br.com.videos.modelo.Category;
import br.com.videos.repository.CategoriaRepository;
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
    private CategoriaRepository categoriaRepository;

    //listar todas as categorias
    @GetMapping
    public List<CategoryDto> read(){
        List<Category> categories = categoriaRepository.findAll();
        return CategoryDto.converter(categories);
    }

    //Busca categoria pelo id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> search(@PathVariable Long id){
        Optional<Category> optional = categoriaRepository.findById(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(new CategoryDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public  ResponseEntity<Category> insert (@RequestBody @Valid Category category){

        return ResponseEntity.ok(categoriaRepository.save(category));

    }

}
