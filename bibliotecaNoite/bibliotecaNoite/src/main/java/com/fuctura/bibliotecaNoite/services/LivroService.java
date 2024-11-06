package com.fuctura.bibliotecaNoite.services;

import com.fuctura.bibliotecaNoite.dtos.LivroDto;
import com.fuctura.bibliotecaNoite.exceptions.ObjectNotFoundException;
import com.fuctura.bibliotecaNoite.models.Categoria;
import com.fuctura.bibliotecaNoite.models.Livro;
import com.fuctura.bibliotecaNoite.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if(livro.isPresent()) {
            return livro.get();
        }
        throw new ObjectNotFoundException("Livro não encontrado!");
    }

    public List<Livro> findAll(Long id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllLivroByCategoria(id_cat);
    }

    public List<Livro> findAllLivroByCategoriaTitulo(String nome) {
        categoriaService.buscarPorNome(nome);
        return livroRepository.findByCategoriaNomeContainingIgnoreCase(nome);
    }

    public Livro save(Long id_cat, LivroDto livroDto) {
        livroDto.setId(null);
        Categoria cat = categoriaService.findById(id_cat);
        livroDto.setCategoria(cat);
        return livroRepository.save(new Livro(livroDto));
    }

    public Livro upDate(Long id, LivroDto livroDto) {
        Livro livro = findById(id);
        livroDto.setId(id);
        upDateDados(livro, livroDto);
        return livroRepository.save(livro);
    }

    public void delete(Long id) {
        findById(id);
        livroRepository.deleteById(id);
    }

    private void upDateDados(Livro livro, LivroDto livroDto) {
        livro.setId(livroDto.getId());
        livro.setTitulo(livroDto.getTitulo());
        livro.setAutor(livroDto.getAutor());
        livro.setTexto(livroDto.getTexto());
        livro.setTamanho(livroDto.getTamanho());
        livro.setCategoria(livroDto.getCategoria());
    }
}
















