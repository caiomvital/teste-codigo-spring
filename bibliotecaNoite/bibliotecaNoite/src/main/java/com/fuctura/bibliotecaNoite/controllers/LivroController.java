package com.fuctura.bibliotecaNoite.controllers;

import com.fuctura.bibliotecaNoite.dtos.LivroDto;
import com.fuctura.bibliotecaNoite.models.Livro;
import com.fuctura.bibliotecaNoite.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Long id) {
        Livro livro = livroService.findById(id);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> findAllLivrosByCategoria(@RequestParam(value = "categoria", defaultValue = "0") Long id){
        List<Livro> list = livroService.findAll(id);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList()));
        //localhost:8080/livro?catagoria=1
    }

    @GetMapping("/categoria/{nome}")
    public ResponseEntity<List<LivroDto>> findAllLivrosByCategoriaNome(@PathVariable String nome) {
        List<Livro> list = livroService.findAllLivroByCategoriaTitulo(nome);
        return ResponseEntity.ok().body(list.stream().map(obj -> new LivroDto(obj)).collect(Collectors.toList()));

    }

    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestParam(value = "categoria", defaultValue = "0") Long id_cat,
                                         @RequestBody LivroDto livroDto) {
        Livro livro = livroService.save(id_cat, livroDto);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> upDate(@PathVariable Long id, @RequestBody LivroDto livroDto) {
        Livro livro = livroService.upDate(id, livroDto);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
