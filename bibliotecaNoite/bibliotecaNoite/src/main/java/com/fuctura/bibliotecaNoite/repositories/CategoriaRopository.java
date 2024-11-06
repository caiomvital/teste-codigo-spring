package com.fuctura.bibliotecaNoite.repositories;

import com.fuctura.bibliotecaNoite.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRopository extends JpaRepository<Categoria, Long> {


    Optional<Categoria> findByNome(String nome);

    Optional<Categoria> findByNomeContainingIgnoreCase(String nome);

}
