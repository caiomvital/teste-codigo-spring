package com.fuctura.bibliotecaNoite.services;

import com.fuctura.bibliotecaNoite.enuns.Tamanho;
import com.fuctura.bibliotecaNoite.models.Categoria;
import com.fuctura.bibliotecaNoite.models.Livro;
import com.fuctura.bibliotecaNoite.repositories.CategoriaRopository;
import com.fuctura.bibliotecaNoite.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRopository categoriaRopository;

    public void instanciaDB() {

        Categoria cat1 = new Categoria(null, "Informática", "Livro de TI");
        Categoria cat2 = new Categoria(null, "Ficção Científica", "Ficção Científica");
        Categoria cat3 = new Categoria(null, "Biografias", "Livros de Biografias");

        Livro l1 = new Livro(null, "Clean code", "Robertin Martin", "Lorem ipsum", Tamanho.MEDIO, cat1);
        Livro l2 = new Livro(null, "Engenharia de Software", "Louis V. Gerstner", "Lorem ipsum", Tamanho.GRANDE, cat1);
        Livro l3 = new Livro(null, "The time machine", "H. G. Wells", "Lorem ipsum", Tamanho.MEDIO, cat2);
        Livro l4 = new Livro(null, "The war of the worlds", "H. G. Wells", "Lorem ipsum", Tamanho.PEQUENO, cat2);
        Livro l5 = new Livro(null, "I, robot", "Isaac Asimov", "Lorem ipsum", Tamanho.GRANDE, cat2);

        cat1.getLivros().addAll(Arrays.asList(l1, l2));
        cat2.getLivros().addAll(Arrays.asList(l3, l4, l5));

        categoriaRopository.saveAll(Arrays.asList(cat1, cat2, cat3));
        livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));

    }
}
