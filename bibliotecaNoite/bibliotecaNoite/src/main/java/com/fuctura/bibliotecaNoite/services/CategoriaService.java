package com.fuctura.bibliotecaNoite.services;

import com.fuctura.bibliotecaNoite.dtos.CategoriaDto;
import com.fuctura.bibliotecaNoite.exceptions.DataIntegrityViolationException;
import com.fuctura.bibliotecaNoite.exceptions.IllegalArgumentException;
import com.fuctura.bibliotecaNoite.exceptions.ObjectNotFoundException;
import com.fuctura.bibliotecaNoite.models.Categoria;
import com.fuctura.bibliotecaNoite.repositories.CategoriaRopository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRopository categoriaRopository;

    @Autowired
    private ModelMapper modelMapper;


    public Categoria findById(Long id) {
        Optional<Categoria> cat = categoriaRopository.findById(id);
        //return cat.orElseThrow(() -> new ObjectNotFoundException("Categoria não encontrada!"));
        if(cat.isPresent()) {
            return cat.get();
        }
        throw new ObjectNotFoundException("Categoria não encontrada!");
    }

    public List<Categoria> findAll() {
        return categoriaRopository.findAll();
    }

    public Categoria save(CategoriaDto categoriaDto) {
        findByNome(categoriaDto);
        categoriaDto.setId(null);
        return categoriaRopository.save(modelMapper.map(categoriaDto, Categoria.class));
    }

    public Categoria upDate(CategoriaDto categoriaDto) {
        findById(categoriaDto.getId());
        findByNome(categoriaDto);
        return categoriaRopository.save(modelMapper.map(categoriaDto, Categoria.class));
    }

    public void delete(Long id) {
        findById(id);
        Optional<Categoria> cat = categoriaRopository.findById(id);
        if(!cat.get().getLivros().isEmpty()) {
            throw new DataIntegrityViolationException("Categoria possui livros, não pode ser deletada!");
        }
        categoriaRopository.deleteById(id);
    }

    private void findByNome(CategoriaDto categoriaDto) {
        Optional<Categoria> cat = categoriaRopository.findByNome(categoriaDto.getNome());
        if (cat.isPresent() && cat.get().getNome().equals(categoriaDto.getNome())){
            throw new IllegalArgumentException("Já existe uma categoria com este nome: " + categoriaDto.getNome());
        }
    }

    public void buscarPorNome(String nome) {
        Optional<Categoria> cat = categoriaRopository.findByNomeContainingIgnoreCase(nome);
        if (cat.isEmpty()) {
            throw new ObjectNotFoundException("Categoria não encontrada!");
        }
    }
}

