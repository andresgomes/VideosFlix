package br.com.videos.controller;


import br.com.videos.dto.CategoriaDto;
import br.com.videos.modelo.Categoria;
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
    public List<CategoriaDto> listar (){
        List<Categoria> categorias = categoriaRepository.findAll();
        return CategoriaDto.converter(categorias);
    }

    //Busca categoria pelo id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> buscar (@PathVariable Long id){
        Optional<Categoria> optional = categoriaRepository.findById(id);
        if (optional.isPresent()){
            return ResponseEntity.ok(new CategoriaDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public  ResponseEntity<Categoria> insert (@RequestBody @Valid Categoria categoria){

        return ResponseEntity.ok(categoriaRepository.save(categoria));

    }

}
