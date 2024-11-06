package com.fuctura.bibliotecaNoite.controllers;

import com.fuctura.bibliotecaNoite.dtos.CategoriaDto;
import com.fuctura.bibliotecaNoite.models.Categoria;
import com.fuctura.bibliotecaNoite.services.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    //@GetMapping("/{id}") = buscar por id;
    //@GetMapping = buscar todos;
    //@PostMapping = salvar um objeto;
    //@PutMapping("/{id}") = atualizar um objeto;
    //@DeleteMapping("/{id}") = deletar um objeto;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Long id) {
        Categoria cat = categoriaService.findById(id);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list.stream().map(itemList ->
                modelMapper.map(itemList, CategoriaDto.class)).collect(Collectors.toList()));

    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria cat = categoriaService.save(categoriaDto);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> upDate(@PathVariable Long id,@Valid @RequestBody CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Categoria cat = categoriaService.upDate(categoriaDto);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

